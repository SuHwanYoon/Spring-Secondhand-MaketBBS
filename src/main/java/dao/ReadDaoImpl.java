package dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Bbs;
import model.ImageBBS;
import model.TradeBBS;

@Repository
public class ReadDaoImpl implements ReadDao {
	@Autowired
	private SqlSession session;
	
	
	public void updateTrade(TradeBBS bbs) {
		session.update("mapper.home.updateTrade",bbs);
	}


	public void deleteTrade(Integer num) {
		session.delete("mapper.home.deleteTrade",num);
	}


	public TradeBBS tradebbsDetail(Integer num) {
		return session.selectOne("mapper.home.tradebbsDetail",num);
	}


	public void updateImg(ImageBBS bbs) {
		session.update("mapper.home.updateImg",bbs);
	}


	public void deleteImg(Integer num) {
		session.delete("mapper.home.deleteImg",num);
	}


	public ImageBBS imagebbsDetail(Integer num) {
		return session.selectOne("mapper.home.imagebbsDetail",num);
	}


	public Bbs selectDetail(Integer seqno) {
		return session.selectOne("mapper.home.selectDetail",seqno);
	}

}
