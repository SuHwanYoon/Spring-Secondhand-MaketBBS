package dao;

import java.util.List;

import model.Condition;
import model.ImageBBS;
import model.TradeBBS;

public interface UploadDao {
	Integer tradecount();
	List<TradeBBS> tradeList(Condition con);
	
	Integer maxTradeBBS();
	void writeTradeBBS(TradeBBS tbbs);
	
	Integer imgcount();
	List<ImageBBS> imageList(Condition con);
	
	Integer maxImageBBS();
	void writeImageBBS(ImageBBS bbs);
}
