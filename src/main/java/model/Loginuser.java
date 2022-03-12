package model;

import org.hibernate.validator.constraints.NotEmpty;

public class Loginuser {
	@NotEmpty(message="계정을 입력하세요.")
	private String id;
	@NotEmpty(message="암호를 입력하세요.")
	private String pwd;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
