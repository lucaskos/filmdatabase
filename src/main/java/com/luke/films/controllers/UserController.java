package com.luke.films.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.luke.films.common.ControllerConstants;
import com.luke.films.model.user.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.luke.films.model.user.User;
import com.luke.films.model.user.role.RoleDao;
import com.luke.films.service.UserService;

import java.util.List;

@Controller
public class UserController {

	@Autowired
	private UserService usersService;

	@Autowired
	private RoleDao roleDao;

	/**
	 * Spring security see this:
	 * 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject(ControllerConstants.ERROR, "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	public ModelAndView adminPage() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "This is Admin page!");
		model.addObject(ControllerConstants.MESSAGE, "This is protected page! For Users with Admin privilege only.");
		model.setViewName("admin");
		List<UserBean> usersData = usersService.getAllUsers();

		if(!usersData.isEmpty())
			model.addObject("users", usersData);

		model.addObject(ControllerConstants.ROLES, roleDao.getAllRoles());
		return model;

	}

	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			auth.setAuthenticated(false);
			SecurityContextHolder.getContextHolderStrategy().clearContext();
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/newaccount", method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "newaccount";
	}

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String registerUser(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {

			return "newaccount";
		}

		if (usersService.checkUsername(user) == true) {
			result.rejectValue("username", "DuplicateKey.user");
			return "newaccount";
		} else {
			user.setEnabled(true);
			usersService.createUser(user);
		}

		return "accountcreated";
	}

	@ResponseBody
	@RequestMapping(value = "changeRole", method = RequestMethod.GET)
	public void changeRole(@RequestParam("username") String username, @RequestParam(ControllerConstants.ROLE) String role) {
		User user = usersService.getUser(username);
		user.setRole(roleDao.getRole(role));
		usersService.update(user);
	}

}
