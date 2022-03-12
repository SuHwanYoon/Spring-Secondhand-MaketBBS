package logic;

import java.util.List;

import model.Bbs;
import model.Condition;
import model.ImageBBS;
import model.TradeBBS;

public interface WriteCatalog {
	List<TradeBBS> findTrade(String title);
	List<ImageBBS> findImage(String title);
	
	void updateTrade(TradeBBS bbs);
	void deleteTrade(Integer num);
	
	TradeBBS tradebbsDetail(Integer num);
	
	Integer tradecount();
	List<TradeBBS> tradeList(Condition con);
	
	void updateTradeOrderNum(TradeBBS trade);
	
	Integer maxTradeBBS();
	void writeTradeBBS(TradeBBS tbbs);
	
	void updateOrderNum(ImageBBS image);
	
	void updateImg(ImageBBS bbs);
	void deleteImg(Integer num);
	
	ImageBBS imagebbsDetail(Integer num);//이미지 게시글 상세 조회
	
	Integer imgcount();
	List<ImageBBS> imageList(Condition con);
	
	Integer maxImageBBS();
	void writeImageBBS(ImageBBS bbs);
	
	Bbs selectDetail(Integer seqno);
	
	Integer BBSCount();
	List<Bbs> readBBSList(Integer pageNo);
	
	Integer maxseqno();
	void writeBBS(Bbs bbs);
}
