package dao;

import model.Loginuser;
import model.User;

public interface UserDao {
	void updateUser(User user);
	
	User selectUserImpo(String id);
	
	Integer DUPcheck(String id);////계정으로 갯수를 찾는 중복검사를 위한 메서드
	
	void entryUser(User user);//가입자 정보 넣기 메서드
	User findByIdPwd(Loginuser user);//로그인 위한 메서드
					//로그인 여부를 검사하기위해서 리턴함수				
}
