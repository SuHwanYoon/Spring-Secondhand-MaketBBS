package logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import model.Loginuser;
import model.User;

@Service//로직 객체 를자동생성
public class UserCatalogImpl implements UserCatalog {
	@Autowired
	private UserDao userDao;
	
	
	public void updateUser(User user) {
		this.userDao.updateUser(user);
	}


	public User selectUserImpo(String id) {
		return this.userDao.selectUserImpo(id);
	}


	public Integer DUPcheck(String id) {
		return userDao.DUPcheck(id);
	}


	public void entryUser(User user) {
		userDao.entryUser(user);
	}


	public User selectUser(Loginuser lu) {
		return userDao.findByIdPwd(lu);
	}
	
}
