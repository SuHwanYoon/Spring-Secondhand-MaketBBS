package controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import logic.WriteCatalog;
import model.Bbs;
import model.Cart;
import model.ImageBBS;
import model.ItemSet;
import model.Loginuser;
import model.TradeBBS;
import model.User;

@Controller
public class ReadController {
	@Autowired
	private WriteCatalog writeCatalog;
	
	@RequestMapping(value="/read/cartClear.html")
	public ModelAndView clear(HttpSession session) {
		Cart cart = (Cart)session.getAttribute("CART_KEY");
		if( cart == null) cart = new Cart();
		cart.clearAll();
		session.setAttribute("CART_KEY", cart);
		ModelAndView mav = new ModelAndView("home/home");//페이지 전환
		User loginUser = (User)session.getAttribute("LOGINUSER");
		if(loginUser != null)mav.addObject("loginUser",loginUser);
		mav.addObject("message","카트를 비웠습니다");
		mav.addObject("BODY","cartList.jsp");
		return mav;
	}
	
	@RequestMapping(value="/read/cartConfirm.html")
	public ModelAndView confirm(HttpSession session) {
		Cart cart = (Cart)session.getAttribute("CART_KEY");
		ModelAndView mav = new ModelAndView("home/home");
		mav.addObject("cart",cart);
		User loginUser = (User)session.getAttribute("LOGINUSER");
		if(loginUser !=  null) mav.addObject("loginUser",loginUser);
		mav.addObject("BODY","cartList.jsp");
		return mav;
	}
	
	@RequestMapping(value="/read/cartCheck.html")
	public ModelAndView cartLogin(HttpSession session,Integer pid
			,Integer quantity) {
		User user = (User)session.getAttribute("LOGINUSER");
		ModelAndView mav = new ModelAndView("home/home");
		if(user == null) {//로그인을 안한경우
			mav.addObject("MSG","로그인을 해야합니다");//메세지 전달
			mav.addObject("BODY","login.jsp");
			mav.addObject(new Loginuser());
		}else {//로그인을 한경우
			TradeBBS item = this.writeCatalog.tradebbsDetail(pid);
			//장바구니를 세션에서 찾는다. 없으면 생성한다
			Cart cart = (Cart)session.getAttribute("CART_KEY");
			if(cart == null)cart = new Cart();//카트가 없으면 생성
			cart.push(new ItemSet(item,quantity));
			session.setAttribute("CART_KEY", cart);
			session.setAttribute("ITEM_KEY", item);
			session.setAttribute("number", quantity);
			ModelAndView mav2 = new ModelAndView("redirect:/read/cartList.html");
			return mav2;
		}		
		return  mav;
	}
	
	@RequestMapping(value="/read/cartList.html")
	public ModelAndView cartList(HttpSession session) {
		Cart cart = (Cart)session.getAttribute("CART_KEY");
		User user = (User)session.getAttribute("LOGINUSER");
		ModelAndView mav = new ModelAndView("home/home"); //장바구니 결과가 출력되는jsp
		if(user == null) {//로그인을 안한경우
			mav.addObject("MSG","로그인을 해야합니다");//메세지 전달
			mav.addObject("BODY","login.jsp");
			mav.addObject(new Loginuser());
		}else {//로그인을 한경우
		TradeBBS selectedItem = (TradeBBS)session.getAttribute("ITEM_KEY");
		Integer quantity = (Integer)session.getAttribute("number");
		User loginUser = (User)session.getAttribute("LOGINUSER");
		if(loginUser != null) mav.addObject("loginUser",loginUser);
		mav.addObject("message",selectedItem.getTitle()+"을(를)"+
		quantity + "개 찜목록에 담았습니다.");
		mav.addObject("cart",cart);
		mav.addObject("BODY","cartList.jsp");
		}
		return mav;
	}
	
	@RequestMapping(value="/read/updateTradeDo.html")
	public ModelAndView updateImgDo(TradeBBS tradebbs,
			HttpSession session,HttpServletRequest request)throws Exception {
		ServletContext ctx = session.getServletContext();
		//HttpSession session = request.getSession();
		//ServletContext ctx = session.getServletContext();
		//ServletContext는 절대경로(upload폴더)를 획득하기위해서 생성한다
		//ServletContext객체의 getRealPath라는 메서드를 호출해서 절대경로 획득
		String filePath = ctx.getRealPath("/upload");//upload폴더의 절대경로
		System.out.println("업로드 경로:"+filePath);
		String encType = "euc-kr";
		MultipartRequest multipart = new MultipartRequest(request,filePath,
				5*1024*1024,encType,new DefaultFileRenamePolicy());//cos라이브러리 업로드객체생성
		String fileName = multipart.getFilesystemName("IMAGENAME");//파일가지고있는 파라미터 업로드실행
		Integer seqno = Integer.parseInt(multipart.getParameter("writing_id"));
		TradeBBS origin = this.writeCatalog.tradebbsDetail(seqno);
		ModelAndView mav = new ModelAndView("home/home");
		if(origin.getPassword().equals(multipart.getParameter("password"))) {
			//등록된 암호와 입력한 암호가 일치하는 경우
			//파일업로드가 성공한 경우 fileName에 파일이름이 들어간다
			//fileName에 파일이름이 있으면 이미지 변경
			//fileName 에 파일이름이 없으면 이미지는 변경하지 않겠다는것
			if(fileName == null || fileName.equals("")) {//파일이름이없는경우
				tradebbs.setImage_name(origin.getImage_name());//기존의 이름설정
			}else {//파일이름이 있는경우
				tradebbs.setImage_name(fileName);//새파일 이름 설정
			}
			tradebbs.setTitle(multipart.getParameter("title"));
			tradebbs.setPrice(Integer.parseInt(multipart.getParameter("price")));
			tradebbs.setContent(multipart.getParameter("content"));
			tradebbs.setWriting_id(seqno);//기존글번호 다시 설정
			this.writeCatalog.updateTrade(tradebbs);//db에서 업데이트 실행
			mav.addObject("BODY","tradeUpdateResult.jsp?seqno="+seqno);
		}else {//암호가 일치하지 않는 경우
			mav.addObject("BODY","tradeUpdateResult.jsp?id="+seqno);
		}
			return mav;
	}
	
	@RequestMapping(value="/read/updateTrade.html")
	public ModelAndView updateTradeForm(Integer id) {
		ModelAndView mav = new ModelAndView("home/home");
		TradeBBS tradebbs = this.writeCatalog.tradebbsDetail(id);
		mav.addObject(tradebbs);
		mav.addObject("BODY","updateTradeForm.jsp");
		return mav;
	}
	
	@RequestMapping(value="/read/deleteTradeDo.html"
			,method=RequestMethod.POST)
	public ModelAndView tradeDeleteDo(TradeBBS tradebbs) {
		//글번호로 해당들의 암호를 찾는다.
		//탐색한 암호와 입력한 암호가 동일한 경우 삭제를 진행한다
		ModelAndView mav = new ModelAndView("home/home");
		TradeBBS origin = this.writeCatalog.tradebbsDetail(tradebbs.getWriting_id());
		if(origin.getPassword().equals(tradebbs.getPassword())) {
			//등록된 암호와 입력된 암호가 동일한 경우
			this.writeCatalog.deleteTrade(tradebbs.getWriting_id());
			mav.addObject("BODY","tradeDeleteResult.jsp");
		}else {//등록된 암호와 입력된 암호가 일치하지 않는경우
			mav.addObject("BODY","tradeDeleteResult.jsp?id="+tradebbs.getWriting_id());
		}
		return mav;
	}
	
	@RequestMapping(value="/read/deleteTrade.html")
	public ModelAndView tradeDelete(Integer id) {
		ModelAndView mav = new ModelAndView("home/home");
		TradeBBS tradebbs = this.writeCatalog.tradebbsDetail(id);
		mav.addObject(tradebbs);
		mav.addObject("BODY","tradeDeleteForm.jsp");
		return mav;
	}
	
	@RequestMapping(value="/read/tradeRead.html")
	public ModelAndView tradeDetail(Integer pid) {
		ModelAndView mav = new ModelAndView("home/home");
		TradeBBS tradebbs = this.writeCatalog.tradebbsDetail(pid);//해당하는 글번호의 게시물 정보
		mav.addObject("TRADE",tradebbs);
		mav.addObject("BODY","tradeRead.jsp");
		return mav;
	}
	
	@RequestMapping(value="/read/updateImageDo.html")
	public ModelAndView updateImgDo(ImageBBS imagebbs,
			HttpSession session,HttpServletRequest request)throws Exception {
		ServletContext ctx = session.getServletContext();
		//HttpSession session = request.getSession();
		//ServletContext ctx = session.getServletContext();
		//ServletContext는 절대경로(upload폴더)를 획득하기위해서 생성한다
		//ServletContext객체의 getRealPath라는 메서드를 호출해서 절대경로 획득
		String filePath = ctx.getRealPath("/upload");//upload폴더의 절대경로
		System.out.println("업로드 경로:"+filePath);
		String encType = "euc-kr";
		MultipartRequest multipart = new MultipartRequest(request,filePath,
				5*1024*1024,encType,new DefaultFileRenamePolicy());//cos라이브러리 업로드객체생성
		String fileName = multipart.getFilesystemName("IMAGENAME");//파일가지고있는 파라미터 업로드실행
		Integer seqno = Integer.parseInt(multipart.getParameter("writing_id"));
		ImageBBS origin = this.writeCatalog.imagebbsDetail(seqno);
		ModelAndView mav = new ModelAndView("home/home");
		if(origin.getPassword().equals(multipart.getParameter("password"))) {
			//등록된 암호와 입력한 암호가 일치하는 경우
			//파일업로드가 성공한 경우 fileName에 파일이름이 들어간다
			//fileName에 파일이름이 있으면 이미지 변경
			//fileName 에 파일이름이 없으면 이미지는 변경하지 않겠다는것
			if(fileName == null || fileName.equals("")) {//파일이름이없는경우
				imagebbs.setImage_name(origin.getImage_name());//기존의 이름설정
			}else {//파일이름이 있는경우
				imagebbs.setImage_name(fileName);//새파일 이름 설정
			}
			imagebbs.setTitle(multipart.getParameter("title"));
			imagebbs.setEmail(multipart.getParameter("email"));
			imagebbs.setContent(multipart.getParameter("content"));
			imagebbs.setWriting_id(seqno);//기존글번호 다시 설정
			this.writeCatalog.updateImg(imagebbs);//db에서 업데이트 실행
			mav.addObject("BODY","imageUpdateResult.jsp?seqno="+seqno);
		}else {//암호가 일치하지 않는 경우
			mav.addObject("BODY","imageUpdateResult.jsp?id="+seqno);
		}
			return mav;
	}
	
	@RequestMapping(value="/read/updateImage.html")
	public ModelAndView updateForm(Integer id) {
		ModelAndView mav = new ModelAndView("home/home");
		ImageBBS imagebbs = this.writeCatalog.imagebbsDetail(id);
		mav.addObject(imagebbs);
		mav.addObject("BODY","updateForm.jsp");
		return mav;
	}
	
	@RequestMapping(value="/read/deleteImageDo.html"
			,method=RequestMethod.POST)
	public ModelAndView imageDeleteDo(ImageBBS imageBBS) {
		//글번호로 해당들의 암호를 찾는다.
		//탐색한 암호와 입력한 암호가 동일한 경우 삭제를 진행한다
		ModelAndView mav = new ModelAndView("home/home");
		ImageBBS origin = this.writeCatalog.imagebbsDetail(imageBBS.getWriting_id());
		if(origin.getPassword().equals(imageBBS.getPassword())) {
			//등록된 암호와 입력된 암호가 동일한 경우
			this.writeCatalog.deleteImg(imageBBS.getWriting_id());
			mav.addObject("BODY","imageDeleteResult.jsp");
		}else {//등록된 암호와 입력된 암호가 일치하지 않는경우
			mav.addObject("BODY","imageDeleteResult.jsp?id="+imageBBS.getWriting_id());
		}
		return mav;
	}
	
	@RequestMapping(value="/read/deleteImage.html")
	public ModelAndView imageDelete(Integer id) {
		ModelAndView mav = new ModelAndView("home/home");
		ImageBBS imagebbs = this.writeCatalog.imagebbsDetail(id);
		mav.addObject(imagebbs);
		mav.addObject("BODY","deleteForm.jsp");
		return mav;
	}
	
	@RequestMapping(value="/read/imageRead.html")
	public ModelAndView imageDetail(Integer pid) {
		ModelAndView mav = new ModelAndView("home/home");
		ImageBBS imagebbs = this.writeCatalog.imagebbsDetail(pid);//해당하는 글번호의 게시물 정보
		mav.addObject("IMG",imagebbs);
		mav.addObject("BODY","imageRead.jsp");
		return mav;
	}
	
	@RequestMapping(value="/read/readDetail.html")
	public ModelAndView bbsDetail(Integer SEQNO) {
		ModelAndView mav = new ModelAndView("home/home");
		Bbs bbs = this.writeCatalog.selectDetail(SEQNO);
		mav.addObject("BBS",bbs);//조회결과를 모델앤뷰에저장
		mav.addObject("BODY","bbsRead.jsp");
		return mav;
	}
}
