package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Condition;
import model.ImageBBS;
import model.TradeBBS;

@Repository
public class UploadDaoImpl implements UploadDao {
	@Autowired
	private SqlSession session;
	
	
	public Integer tradecount() {
		return session.selectOne("mapper.home.tradecount");
	}

	public List<TradeBBS> tradeList(Condition con) {
		return session.selectList("mapper.home.tradeList",con);
	}

	public Integer maxTradeBBS() {
		return session.selectOne("mapper.home.maxTradeBBS");
	}

	public void writeTradeBBS(TradeBBS tbbs) {
		session.insert("mapper.home.writeTradeBBS",tbbs);
	}

	public Integer imgcount() {
		return session.selectOne("mapper.home.imgcount");
	}

	public List<ImageBBS> imageList(Condition con) {
		return session.selectList("mapper.home.imageList",con);
	}

	public Integer maxImageBBS() {
		return session.selectOne("mapper.home.maxImageBBS");
	}

	public void writeImageBBS(ImageBBS bbs) {
		session.insert("mapper.home.writeImageBBS",bbs);
	}
	
}
