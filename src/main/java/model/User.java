package model;

import org.hibernate.validator.constraints.NotEmpty;

public class User {
	@NotEmpty(message="계정을 입력하세요.")
	private String id;
	@NotEmpty(message="암호를 입력하세요.")
	private String pwd;
	@NotEmpty(message="이름을 입력하세요.")
	private String name;
	@NotEmpty(message="주소를 입력하세요.")
	private String addr;
	@NotEmpty(message="연락처를 입력하세요.")
	private String phone;
	private String gender;
	@NotEmpty(message="이메일을 입력하세요.")
	private String email;
	private String job;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
}
