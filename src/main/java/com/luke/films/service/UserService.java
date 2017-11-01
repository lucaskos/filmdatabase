package com.luke.films.service;

import java.util.ArrayList;
import java.util.List;

import com.luke.films.model.user.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luke.films.model.user.User;
import com.luke.films.model.user.dao.UserDao;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao usersDao;
	
	public void createUser(User user){
		System.out.println(user);
		usersDao.createUser(user);
	}
	
	public boolean checkUsername(User user) {
		if(usersDao.getUser(user.getUsername()) != null) 
			return true;
		return false;
	}
	//TODO Securing this method for ADMIN only
	
	public List<UserBean> getAllUsers() {
		List<User> allUsers = usersDao.getAllUsers();
        List<UserBean> userBeans = new ArrayList<>();

		if(!allUsers.isEmpty()) {
            userBeans = copyUserData(allUsers);
        }

		return userBeans;
	}

    private List<UserBean> copyUserData(List<User> allUsers) {

        List userBeanList = new ArrayList();
        for(User user : allUsers) {
            UserBean bean = new UserBean();
            bean.setEmail(user.getEmail());
            bean.setId(user.getId());
            bean.setUsername(user.getUsername());

            userBeanList.add(userBeanList);
        }

        return userBeanList;
    }

    public User getUser(String username) {
		return usersDao.getUser(username);
	}
	
	//TODO securing this method
	/*
	 * only user who account belongs can delete his account
	 * should redirect to new page when user deleting his account
	 */
	public void removeUser(User user) {
		usersDao.removeUser(user);
	}

	public void update(User user) {
		usersDao.update(user);
		
	}
	
}
