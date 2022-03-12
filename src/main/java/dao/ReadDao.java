package dao;

import model.Bbs;
import model.ImageBBS;
import model.TradeBBS;

public interface ReadDao {
	void updateTrade(TradeBBS bbs);
	void deleteTrade(Integer num);
	
	TradeBBS tradebbsDetail(Integer num);
	
	void updateImg(ImageBBS bbs);
	void deleteImg(Integer num);
	
	ImageBBS imagebbsDetail(Integer num);//이미지 게시글 상세 조회
	
	Bbs selectDetail(Integer seqno);
}
