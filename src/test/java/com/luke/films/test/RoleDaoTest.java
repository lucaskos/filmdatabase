package com.luke.films.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.luke.films.config.ApplicationConfigCore;
import com.luke.films.config.HibernateConfig;
import com.luke.user.model.RoleDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfigCore.class, HibernateConfig.class })
@WebAppConfiguration("WebContent")
@ActiveProfiles(profiles = "dev")
public class RoleDaoTest {
	@Autowired
	private RoleDao roleDao;
	
	@Test
	public void test() {
		System.out.println(roleDao.getAllRoles());
		System.out.println(roleDao.getRole("ROLE_USER"));
	}
}
