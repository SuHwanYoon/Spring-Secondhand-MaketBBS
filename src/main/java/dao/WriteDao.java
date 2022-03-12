package dao;

import java.util.List;

import model.Bbs;
import model.ImageBBS;
import model.TradeBBS;

public interface WriteDao {
	List<TradeBBS> findTrade(String title);
	List<ImageBBS> findImage(String title);
	
	void updateTradeOrderNum(TradeBBS trade);
	
	void updateOrderNum(ImageBBS image);
	
	Integer BBSCount();
	List<Bbs> readBBSList(Integer pageNo);
	Integer maxseqno();//가장큰 글번호를 찾는 메서드
	void writeBBS(Bbs bbs);//게시글 정보넣기
}
