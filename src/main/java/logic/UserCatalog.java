package logic;

import model.Loginuser;
import model.User;

public interface UserCatalog {//dao객체메서드를 그대로
	void updateUser(User user);
	
	User selectUserImpo(String id);
	
	Integer DUPcheck(String id);//중복검사위해 특정계정의 갯수를 찾는 메서드
	User selectUser(Loginuser lu);//아이디비번으로 유저정보찾는 메서드
	void entryUser(User user);//가입자 정보넣기 메서드
}
