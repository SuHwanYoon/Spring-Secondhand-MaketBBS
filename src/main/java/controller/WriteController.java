package controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import logic.WriteCatalog;
import model.Bbs;
import model.Condition;
import model.ImageBBS;
import model.Loginuser;
import model.TradeBBS;
import model.User;

@Controller
public class WriteController {
	@Autowired
	private WriteCatalog writeCatalog;//쿼리사용위한 객체
	
//	@RequestMapping(value="/write/search.html")
//	public ModelAndView search(String itemName) {
//		ModelAndView mav = new ModelAndView("home/home");
//		//입력된 상품명으로 상품검색
//		if(itemName == null || itemName.equals("")) {
//			mav.addObject("BODY","imageList.jsp");
//			return mav;
//		}//상품명이 없는경우는 다시 목록을 출력한다
//		List<ImageBBS> searchImageList = this.writeCatalog.findImage(itemName);
//		if(searchImageList == null || searchImageList.isEmpty()) {
//			mav.addObject("BODY","imageList.jsp");
//			return mav;
//		}//검색된 상품이 없는 경우는 다시 목록을 출력한다
//		mav.addObject("imageList",searchImageList);//검색된 상품의 목록
//		mav.addObject("BODY","imageList.jsp");
//		return mav;//새로운 검색결과로 상품을 출력한다
//	}
	
	@RequestMapping(value="/write/tradeSearch.html")
	public ModelAndView TradeSerchList(Integer PAGE_NUM,String itemName) {
		if(PAGE_NUM == null) PAGE_NUM = 1;
		//DB에서 이미지 게시글 검색(한페이지당 5개)
		int currentPage = PAGE_NUM;
		int totalPageCount = 0;
		int startRow = 0; int endRow = 0;
		int count = this.writeCatalog.tradecount();//전체글 갯수검색//바꿀거
		if(count > 0 ) {//글갯수가 0 보다 크다면
			totalPageCount = count/5;//페이지의 갯수
			if(count%5 >0) totalPageCount++;//글갯수가5로 나누어떨어지지 않으면 페이지가 증가
			startRow = (currentPage -1)*5+1;
			endRow = currentPage*5;
			if(endRow > count) endRow = count;//게시물이 5개보다적을때를 위한코딩
		}
		Condition con = new Condition();
		con.setStartRow(startRow);con.setEndRow(endRow);
		List<TradeBBS> tradeSerchList = this.writeCatalog.findTrade(itemName);//목록게시글검색//바꿀거
		ModelAndView mav = new ModelAndView("home/home");
		if(itemName == null || itemName.equals("")) {
			mav.addObject("BODY","imageList.jsp");
			return mav;
		}//상품명이 없는경우는 다시 목록을 출력한다
		if(tradeSerchList == null || tradeSerchList.isEmpty()) {
			mav.addObject("BODY","imageList.jsp");
			return mav;
		}//검색된 상품이 없는 경우는 다시 목록을 출력한다
		mav.addObject("tradeList",tradeSerchList);
		mav.addObject("count",count);
		mav.addObject("startRow",startRow);
		mav.addObject("endRow",endRow);
		mav.addObject("pageCount",totalPageCount);
		mav.addObject("BODY","tradeList.jsp");//바꿀거
		mav.addObject("currentPage",currentPage);
		return mav;
	}
	
	@RequestMapping(value="/write/search.html")
	public ModelAndView searchList(Integer PAGE_NUM,String itemName) {
		if(PAGE_NUM == null) PAGE_NUM = 1;
		//DB에서 이미지 게시글 검색(한페이지당 5개)
		int currentPage = PAGE_NUM;
		int totalPageCount = 0;
		int startRow = 0; int endRow = 0;
		int count = this.writeCatalog.imgcount();//전체글 갯수검색
		if(count > 0 ) {//글갯수가 0 보다 크다면
			totalPageCount = count/5;//페이지의 갯수
			if(count%5 >0) totalPageCount++;//글갯수가5로 나누어떨어지지 않으면 페이지가 증가
			startRow = (currentPage -1)*5+1;
			endRow = currentPage*5;
			if(endRow > count) endRow = count;//게시물이 5개보다적을때를 위한코딩
		}
		Condition con = new Condition();
		con.setStartRow(startRow);con.setEndRow(endRow);
		List<ImageBBS> searchImageList = this.writeCatalog.findImage(itemName);//목록게시글검색
		ModelAndView mav = new ModelAndView("home/home");
		if(itemName == null || itemName.equals("")) {
			mav.addObject("BODY","imageList.jsp");
			return mav;
		}//상품명이 없는경우는 다시 목록을 출력한다
		if(searchImageList == null || searchImageList.isEmpty()) {
			mav.addObject("BODY","imageList.jsp");
			return mav;
		}//검색된 상품이 없는 경우는 다시 목록을 출력한다
		mav.addObject("imageList",searchImageList);
		mav.addObject("count",count);
		mav.addObject("startRow",startRow);
		mav.addObject("endRow",endRow);
		mav.addObject("pageCount",totalPageCount);
		mav.addObject("BODY","imageList.jsp");
		mav.addObject("currentPage",currentPage);
		return mav;
	}
	
	@RequestMapping(value="/write/replyTradeForm.html")
	public ModelAndView replyTradeForm(Integer id,Integer parentid,
			Integer groupid,HttpSession session) {
		User user = (User)session.getAttribute("LOGINUSER");
		ModelAndView mav = new ModelAndView("home/home");
		if(user == null) {//로그인을 안한경우
			mav.addObject("MSG","게시글을 올리려면 로그인을 해야합니다");//메세지 전달
			mav.addObject("BODY","login.jsp");
			mav.addObject(new Loginuser());
		}else {//로그인을 한경우
			TradeBBS tradebbs = this.writeCatalog.tradebbsDetail(id);//글번호로 원글정보검색
			tradebbs.setContent(null);//답글작성을 위해 내용을 지운다
			tradebbs.setWriter_name(null);//작성자를 지운다
			tradebbs.setGroup_id(groupid);
			tradebbs.setParent_id(parentid);
			mav.addObject(tradebbs);
			mav.addObject("title","RE]");
			mav.addObject("BODY","tradeWriteForm.jsp");
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value="/write/tradeupload.html",
			method=RequestMethod.POST)
	public ModelAndView tradeupload(TradeBBS tradebbs,
			HttpServletRequest request,HttpSession session,
			Integer order_no,Integer group_id,Integer parent_id) throws Exception{//이미지예외처리
		//업로드폴더경로획득->경로지정->enctype설정->업로드객체생성
		//->파일의파라미터지정->파일이름설정->최대글번호검색->글번호설정
		ServletContext ctx = request.getSession().getServletContext();
		//HttpSession session = request.getSession();
		//ServletContext ctx = session.getServletContext();
		//ServletContext는 절대경로(upload폴더)를 획득하기위해서 생성한다
		//ServletContext객체의 getRealPath라는 메서드를 호출해서 절대경로 획득
		String filePath = ctx.getRealPath("/upload");//upload폴더의 절대경로
		String encType = "euc-kr";
		MultipartRequest multipart = new MultipartRequest(
				request,filePath,5*1024*1024,encType,
				new DefaultFileRenamePolicy());//업로드객체생성
		String fileName = multipart.getFilesystemName("IMAGENAME");
		tradebbs.setImage_name(fileName);//파일이름설정
		Integer maxId = this.writeCatalog.maxTradeBBS();//최대글번호검색
		int max_id = 0;
		if(maxId == null)max_id++;//검색해서 기존글이 없는경우
		else max_id = maxId+1;//기존글이 있는경우 
		tradebbs.setWriting_id(max_id);//글쓸대의글번호설정
		//글을쓸때 이글이 원글인지 답글인지를 찾는다
		if(Integer.parseInt(multipart.getParameter("parent_id")) == 0) {//원글인 경우
			tradebbs.setGroup_id(max_id);//원글인 경우 글번호가 그룹번호 
			tradebbs.setOrder_no(0);//원글인경우 순서번호는 0
			tradebbs.setParent_id(0);//원글인 경우 부모글번호는 0
		}else {//답글인 경우
			int groupId = Integer.parseInt(multipart.getParameter("group_id"));
			tradebbs.setGroup_id(groupId);//그룹번호는 그냥 그대로 설정
			int orderNo = Integer.parseInt(multipart.getParameter("order_no"));
			tradebbs.setOrder_no(orderNo);;//
			int parentId = Integer.parseInt(multipart.getParameter("parent_id"));
			tradebbs.setParent_id(parentId);;//
			//순서번호를 update한다
			this.writeCatalog.updateTradeOrderNum(tradebbs);
		}
		User user = (User)session.getAttribute("LOGINUSER");//작성자표시위한 객체
		tradebbs.setWriter_name(user.getId());//작성자 설정
		tradebbs.setTitle(multipart.getParameter("title"));
		tradebbs.setPrice(Integer.parseInt(multipart.getParameter("price")));
		tradebbs.setContent(multipart.getParameter("content"));
		tradebbs.setPassword(multipart.getParameter("password"));
		//작성일 설정
		Calendar c = Calendar.getInstance();//달력객체 생성
		long times = c.getTimeInMillis();//오늘까지의 초를 획득
		Timestamp ts = new Timestamp(times);//초를 이용해서 객체생성
		String ymdhms = String.valueOf(ts);//위의 객체를 문자열로 전환
		tradebbs.setRegister_date(ymdhms);//작성일 설정
		this.writeCatalog.writeTradeBBS(tradebbs);//데이터를 게시판에 업로드
		return new ModelAndView("redirect:/write/tradeList.html");//이미지 게시판 출력 매핑
	}
	
	@RequestMapping(value="/write/tradeList.html")
	public ModelAndView TradeList(Integer PAGE_NUM) {
		if(PAGE_NUM == null) PAGE_NUM = 1;
		//DB에서 이미지 게시글 검색(한페이지당 5개)
		int currentPage = PAGE_NUM;
		int totalPageCount = 0;
		int startRow = 0; int endRow = 0;
		int count = this.writeCatalog.tradecount();//전체글 갯수검색//바꿀거
		if(count > 0 ) {//글갯수가 0 보다 크다면
			totalPageCount = count/5;//페이지의 갯수
			if(count%5 >0) totalPageCount++;//글갯수가5로 나누어떨어지지 않으면 페이지가 증가
			startRow = (currentPage -1)*5+1;
			endRow = currentPage*5;
			if(endRow > count) endRow = count;//게시물이 5개보다적을때를 위한코딩
		}
		Condition con = new Condition();
		con.setStartRow(startRow);con.setEndRow(endRow);
		List<TradeBBS> tradeList = this.writeCatalog.tradeList(con);//목록게시글검색//바꿀거
		ModelAndView mav = new ModelAndView("home/home");
		mav.addObject("tradeList",tradeList);
		mav.addObject("count",count);
		mav.addObject("startRow",startRow);
		mav.addObject("endRow",endRow);
		mav.addObject("pageCount",totalPageCount);
		mav.addObject("BODY","tradeList.jsp");//바꿀거
		mav.addObject("currentPage",currentPage);
		return mav;
	}
	
	@RequestMapping(value="/write/tradeForm.html")
	public ModelAndView tradeForm(HttpSession session) {// 로그인 검사위한 세션
		User user = (User)session.getAttribute("LOGINUSER");
		ModelAndView mav = new ModelAndView("home/home");
		if(user == null) {//로그인을 안한경우
			mav.addObject("MSG","게시글을 올리려면 로그인을 해야합니다");//메세지 전달
			mav.addObject("BODY","login.jsp");
			mav.addObject(new Loginuser());
		}else {//로그인을 한경우
			mav.addObject("BODY","tradeWriteForm.jsp");
			mav.addObject(new TradeBBS());
		}
		return mav;
	}
	
	@RequestMapping(value="/write/replyForm.html")
	public ModelAndView replyForm(Integer id,Integer parentid,
			Integer groupid) {
		ModelAndView mav = new ModelAndView("home/home");
		ImageBBS imagebbs = this.writeCatalog.imagebbsDetail(id);//글번호로 원글정보검색
		imagebbs.setContent(null);//답글작성을 위해 내용을 지운다
		imagebbs.setWriter_name(null);//작성자를 지운다
		imagebbs.setEmail(null);//이메일을 지운다
		imagebbs.setGroup_id(groupid);
		imagebbs.setParent_id(parentid);
		mav.addObject(imagebbs);
		mav.addObject("title","RE]"+imagebbs.getTitle());
		mav.addObject("BODY","imageWriteForm.jsp");
		return mav;
	}
	
	@RequestMapping(value="/write/upload.html",
			method=RequestMethod.POST)
	public ModelAndView imageupload(ImageBBS imagebbs,
			HttpServletRequest request,HttpSession session,
			Integer order_no,Integer group_id,Integer parent_id) throws Exception{//이미지예외처리
		//업로드폴더경로획득->경로지정->enctype설정->업로드객체생성
		//->파일의파라미터지정->파일이름설정->최대글번호검색->글번호설정
		ServletContext ctx = request.getSession().getServletContext();
		//HttpSession session = request.getSession();
		//ServletContext ctx = session.getServletContext();
		//ServletContext는 절대경로(upload폴더)를 획득하기위해서 생성한다
		//ServletContext객체의 getRealPath라는 메서드를 호출해서 절대경로 획득
		String filePath = ctx.getRealPath("/upload");//upload폴더의 절대경로
		String encType = "euc-kr";
		MultipartRequest multipart = new MultipartRequest(
				request,filePath,5*1024*1024,encType,
				new DefaultFileRenamePolicy());//업로드객체생성
		String fileName = multipart.getFilesystemName("IMAGENAME");
		imagebbs.setImage_name(fileName);//파일이름설정
		Integer maxId = this.writeCatalog.maxImageBBS();//최대글번호검색
		int max_id = 0;
		if(maxId == null)max_id++;//검색해서 기존글이 없는경우
		else max_id = maxId+1;//기존글이 있는경우 
		imagebbs.setWriting_id(max_id);//글쓸대의글번호설정
		//글을쓸때 이글이 원글인지 답글인지를 찾는다
		if(Integer.parseInt(multipart.getParameter("parent_id")) == 0) {//원글인 경우
			imagebbs.setGroup_id(max_id);//원글인 경우 글번호가 그룹번호 
			imagebbs.setOrder_no(0);//원글인경우 순서번호는 0
			imagebbs.setParent_id(0);//원글인 경우 부모글번호는 0
		}else {//답글인 경우
			int groupId = Integer.parseInt(multipart.getParameter("group_id"));
			imagebbs.setGroup_id(groupId);//그룹번호는 그냥 그대로 설정
			int orderNo = Integer.parseInt(multipart.getParameter("order_no"));
			imagebbs.setOrder_no(orderNo);;//
			int parentId = Integer.parseInt(multipart.getParameter("parent_id"));
			imagebbs.setParent_id(parentId);;//
			//순서번호를 update한다
			this.writeCatalog.updateOrderNum(imagebbs);
		}
		User user = (User)session.getAttribute("LOGINUSER");//작성자표시위한 객체
		imagebbs.setWriter_name(user.getId());//작성자 설정
		imagebbs.setTitle(multipart.getParameter("title"));
		imagebbs.setEmail(multipart.getParameter("email"));
		imagebbs.setContent(multipart.getParameter("content"));
		imagebbs.setPassword(multipart.getParameter("password"));
		//작성일 설정
		Calendar c = Calendar.getInstance();//달력객체 생성
		long times = c.getTimeInMillis();//오늘까지의 초를 획득
		Timestamp ts = new Timestamp(times);//초를 이용해서 객체생성
		String ymdhms = String.valueOf(ts);//위의 객체를 문자열로 전환
		imagebbs.setRegister_date(ymdhms);//작성일 설정
		this.writeCatalog.writeImageBBS(imagebbs);//데이터를 게시판에 업로드
		return new ModelAndView("redirect:/write/imageList.html");//이미지 게시판 출력 매핑
	}
	
	@RequestMapping(value="/write/imageList.html")
	public ModelAndView ImageList(Integer PAGE_NUM) {
		if(PAGE_NUM == null) PAGE_NUM = 1;
		//DB에서 이미지 게시글 검색(한페이지당 5개)
		int currentPage = PAGE_NUM;
		int totalPageCount = 0;
		int startRow = 0; int endRow = 0;
		int count = this.writeCatalog.imgcount();//전체글 갯수검색
		if(count > 0 ) {//글갯수가 0 보다 크다면
			totalPageCount = count/5;//페이지의 갯수
			if(count%5 >0) totalPageCount++;//글갯수가5로 나누어떨어지지 않으면 페이지가 증가
			startRow = (currentPage -1)*5+1;
			endRow = currentPage*5;
			if(endRow > count) endRow = count;//게시물이 5개보다적을때를 위한코딩
		}
		Condition con = new Condition();
		con.setStartRow(startRow);con.setEndRow(endRow);
		List<ImageBBS> imageList = this.writeCatalog.imageList(con);//목록게시글검색
		ModelAndView mav = new ModelAndView("home/home");
		mav.addObject("imageList",imageList);
		mav.addObject("count",count);
		mav.addObject("startRow",startRow);
		mav.addObject("endRow",endRow);
		mav.addObject("pageCount",totalPageCount);
		mav.addObject("BODY","imageList.jsp");
		mav.addObject("currentPage",currentPage);
		return mav;
	}
	
	@RequestMapping(value="/write/imageForm.html")
	public ModelAndView imageForm(HttpSession session) {
		ModelAndView mav = new ModelAndView("home/home");
		User user = (User)session.getAttribute("LOGINUSER");
		if(user == null) {//로그인 안한경우
			mav.addObject("MSG","게시글을 올리려면 로그인을 해야합니다");
			mav.addObject("BODY","login.jsp");
			mav.addObject(new Loginuser());
		}else {//한경우
			mav.addObject("BODY","imageWriteForm.jsp");
			mav.addObject(new ImageBBS());
		}
		return mav;
	}
	//에러메시지출력 ,게시물올리는 컨트롤러
	@RequestMapping(value="/write/write.html",method=RequestMethod.POST)
	public ModelAndView writebbs(@Valid Bbs bbs,BindingResult br,
			HttpSession session) {
		ModelAndView mav = new ModelAndView("home/home");
		if(br.hasErrors()) {
			mav.addObject("BODY","bbsInput.jsp");
			mav.getModel().putAll(br.getModel());//에러메시지를 넣는다
			return mav;
		}
		//글작성을 위한 코딩
		Integer seqno = this.writeCatalog.maxseqno() + 1;//게시물 쓸때 글번호설정
		bbs.setSeqno(seqno);//글번호 설정
		User user = (User)session.getAttribute("LOGINUSER");
		bbs.setId(user.getId());//게시물 작성자에 로그인한 아이드를 넣음
		//작성일 위한 코딩
		Calendar c = Calendar.getInstance();//달력객체 생성
		long times = c.getTimeInMillis();//오늘까지의 초를 획득
		Timestamp ts = new Timestamp(times);//초를 이용해서 객체생성
		String ymdhms = String.valueOf(ts);
		bbs.setRegister_date(ymdhms);//작성일을 설정
		this.writeCatalog.writeBBS(bbs);//게시글 작성
		return new ModelAndView("redirect:/write/read.html");//리다이렉트를 안해주면 포워드로 된다
														//글작성후 목록으로 전환
	}
	
	@RequestMapping(value="/write/read.html")
	public ModelAndView readList(Integer pageNo) {//메뉴에서의 목록,페이지별 목록을 조회
		int currentPage = 0;//
		if(pageNo == null) currentPage = 1;
		else currentPage = pageNo;//어떤 페이지를 조회할것인가를 생각
		List<Bbs> bbsList = this.writeCatalog.readBBSList(currentPage);
		Integer totalCount = this.writeCatalog.BBSCount();
		int pageCount = totalCount / 5;
		if(totalCount%5>0) pageCount++;
		int startRow = (currentPage -1)*5 +1;//페이지별 게시물의 시작번호
		int endRow = currentPage*5;
		if(endRow > totalCount) endRow = totalCount;//게시물이 5개보다적을때를 위한코딩
		ModelAndView mav = new ModelAndView("home/home");
		mav.addObject("STARTROW",startRow);
		mav.addObject("ENDROW",endRow);
		mav.addObject("BBSS",bbsList);
		mav.addObject("PAGES",pageCount);
		mav.addObject("BODY","bbsList.jsp");
		return mav;
	}
	
	@RequestMapping(value="/write/writeForm.html")
	public ModelAndView writeForm(HttpSession session) {// 로그인 검사위한 세션
		User user = (User)session.getAttribute("LOGINUSER");
		ModelAndView mav = new ModelAndView("home/home");
		if(user == null) {//로그인을 안한경우
			mav.addObject("MSG","게시글을 올리려면 로그인을 해야합니다");//메세지 전달
			mav.addObject("BODY","login.jsp");
			mav.addObject(new Loginuser());
		}else {//로그인을 한경우
			mav.addObject("BODY","bbsInput.jsp");
			mav.addObject(new Bbs());
		}
		return mav;
	}
}
