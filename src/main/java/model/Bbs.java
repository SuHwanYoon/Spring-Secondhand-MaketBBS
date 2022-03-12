package model;

import org.hibernate.validator.constraints.NotEmpty;

public class Bbs {
	private Integer seqno;//
	@NotEmpty(message="제목을 입력하세요.")
	private String title;//
	private String id;//
	@NotEmpty(message="내용을 입력하세요.")
	private String content;//
	private String register_date;//
	public Integer getSeqno() {
		return seqno;
	}
	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegister_date() {
		return register_date;
	}
	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}

}
