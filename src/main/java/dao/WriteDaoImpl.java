package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Bbs;
import model.ImageBBS;
import model.TradeBBS;

@Repository
public class WriteDaoImpl implements WriteDao {
	@Autowired
	private SqlSession session;
	
	
	public List<TradeBBS> findTrade(String title) {
		return session.selectList("mapper.home.findTrade",title);
	}

	public List<ImageBBS> findImage(String title) {
		return session.selectList("mapper.home.findImage",title);
	}

	public void updateTradeOrderNum(TradeBBS trade) {
		session.update("mapper.home.updateTradeOrderNum",trade);
	}

	public void updateOrderNum(ImageBBS image) {
		session.update("mapper.home.updateOrderNum",image);
	}

	public Integer BBSCount() {
		return session.selectOne("mapper.home.selectBBSCount");
	}

	public List<Bbs> readBBSList(Integer pageNo) {
		return session.selectList("mapper.home.readBBSList",pageNo);
	}

	public Integer maxseqno() {
		Integer Maxseqno =  session.selectOne("mapper.home.maxseqno");
		if(Maxseqno == null) return 0;//작성한 글이 없을때
		else return Maxseqno;
	}

	public void writeBBS(Bbs bbs) {
		session.insert("mapper.home.writeBBS",bbs);
	}
	
}
