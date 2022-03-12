package dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Loginuser;
import model.User;

@Repository//클래스를 자동생성, 환경설정에 선언하지 않음
public class UserDaoImpl implements UserDao {
	@Autowired//객체 자동주입
	private SqlSession session;//쿼리실행결과를 저장하기 위해 세션사용
	
	
	public void updateUser(User user) {
		session.update("mapper.home.updateUser",user);
	}


	public User selectUserImpo(String id) {
		return session.selectOne("mapper.home.selectUserImpo",id);
	}


	public Integer DUPcheck(String id) {
		return session.selectOne("mapper.home.DUPcheck",id);
	}


	public void entryUser(User user) {
		session.insert("mapper.home.entryUser",user);
	}


	public User findByIdPwd(Loginuser user) {
		return session.selectOne("mapper.home.selectUser",user);
	}
	
}
