package logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ReadDao;
import dao.UploadDao;
import dao.WriteDao;
import model.Bbs;
import model.Condition;
import model.ImageBBS;
import model.TradeBBS;

@Service
public class WriteCatalogImpl implements WriteCatalog {
	@Autowired
	private UploadDao uploadDao;
	@Autowired
	private ReadDao readDao;
	@Autowired
	private WriteDao writeDao;
	
	
	public List<TradeBBS> findTrade(String title) {
		return this.writeDao.findTrade(title);
	}

	public List<ImageBBS> findImage(String title) {
		return this.writeDao.findImage(title);
	}

	public void updateTrade(TradeBBS bbs) {
		this.readDao.updateTrade(bbs);
	}

	public void deleteTrade(Integer num) {
		this.readDao.deleteTrade(num);
	}

	public TradeBBS tradebbsDetail(Integer num) {
		return this.readDao.tradebbsDetail(num);
	}

	public Integer tradecount() {
		return this.uploadDao.tradecount();
	}

	public List<TradeBBS> tradeList(Condition con) {
		return this.uploadDao.tradeList(con);
	}

	public void updateTradeOrderNum(TradeBBS trade) {
		this.writeDao.updateTradeOrderNum(trade);
	}

	public Integer maxTradeBBS() {
		return this.uploadDao.maxTradeBBS();
	}

	public void writeTradeBBS(TradeBBS tbbs) {
		this.uploadDao.writeTradeBBS(tbbs); 
	}

	public void updateOrderNum(ImageBBS image) {
		this.writeDao.updateOrderNum(image);
	}

	public void updateImg(ImageBBS bbs) {
		this.readDao.updateImg(bbs);
	}

	public void deleteImg(Integer num) {
		this.readDao.deleteImg(num);
	}

	public ImageBBS imagebbsDetail(Integer num) {
		return this.readDao.imagebbsDetail(num);
	}

	public Integer imgcount() {
		return this.uploadDao.imgcount();
	}

	public List<ImageBBS> imageList(Condition con) {
		return this.uploadDao.imageList(con);
	}

	public Integer maxImageBBS() {
		return this.uploadDao.maxImageBBS();
	}

	public void writeImageBBS(ImageBBS bbs) {
		this.uploadDao.writeImageBBS(bbs);
	}

	public Bbs selectDetail(Integer seqno) {
		return this.readDao.selectDetail(seqno);
	}

	public Integer BBSCount() {
		return this.writeDao.BBSCount();
	}

	public List<Bbs> readBBSList(Integer pageNo) {
		return this.writeDao.readBBSList(pageNo);
	}

	public Integer maxseqno() {
		return this.writeDao.maxseqno();
	}

	public void writeBBS(Bbs bbs) {
		this.writeDao.writeBBS(bbs);
	}
	
}
