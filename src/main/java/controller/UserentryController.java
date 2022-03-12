package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.UserCatalog;
import model.Loginuser;
import model.User;

@Controller
public class UserentryController {
	@Autowired
	private UserCatalog userCatalog;
	
	
	@RequestMapping(value="/userentry/userModifydo.html")
	public ModelAndView userModify(User user,HttpServletRequest request) {
		user.setAddr(request.getParameter("addr"));
		user.setEmail(request.getParameter("email"));
		user.setJob(request.getParameter("job"));
		user.setPhone(request.getParameter("phone"));
		user.setPwd(request.getParameter("pwd"));
		this.userCatalog.updateUser(user);//업데이트실행
		this.userCatalog.selectUserImpo(request.getParameter("id"));
//		ModelAndView mav = new ModelAndView("home/home");
//		mav.addObject("USERUPDATE",user2);
//		mav.addObject("BODY","userUpdateResult.jsp");
		return new ModelAndView("redirect:/userentry/userSelect.html");
	}
	
	@RequestMapping(value="/userentry/userSelect.html")
	public ModelAndView userSelect() {
		ModelAndView mav = new ModelAndView("home/home");
		mav.addObject("USERUPDATE","YES");
		mav.addObject("BODY","userUpdateResult.jsp");
		return mav;
	}
	
	@RequestMapping(value="/userentry/userModifyForm.html")
	public ModelAndView myImpo(HttpSession session) {
		User userimpo = (User)session.getAttribute("LOGINUSER");
		ModelAndView mav = new ModelAndView("home/home");
		if(userimpo == null) {//로그인을 안한경우
			mav.addObject("MSG","로그인을 해야합니다");//메세지 전달
			mav.addObject("BODY","login.jsp");
			mav.addObject(new Loginuser());
		}else {//로그인을 한경우
			mav.addObject("USERIMPO",userimpo);
			mav.addObject("BODY","userModify.jsp");
			return mav;
		}		
		return  mav;
	}
	
	@RequestMapping(value="/userentry/entry.html")
	public ModelAndView entry(User user,HttpSession session) {
		this.userCatalog.entryUser(user);//db에 넣기
		session.setAttribute("LOGINUSER", user);//가입할때 넣은 정보
		//바뀌어야할 jsp는 로그인 성공메세지가 출력된는 jsp(loginResult.jsp)
		return new ModelAndView("redirect:/userentry/toHome.html");
	}
	
	@RequestMapping(value="/userentry/toHome.html")
	public ModelAndView toHome() {
		ModelAndView mav = new ModelAndView("home/home");
		mav.addObject("BODY","loginResult.jsp");
		return mav;
	}
	
	@RequestMapping(value="/userentry/idCheck.html")
	public ModelAndView idCheck(String USER_ID) {
		ModelAndView mav = new ModelAndView("home/idCheckResult");
		//중복검사
		Integer dupcount = this.userCatalog.DUPcheck(USER_ID);
		if(dupcount == 0) mav.addObject("DUP","NO");//중복이아님
		else mav.addObject("DUP","YES");//중복임
		mav.addObject("ID",USER_ID);//표시를 위해 폼에 입력한 아이디 저장
		return mav;
	}
	
	@RequestMapping(value="/userentry/userentry.html")
	public ModelAndView entryForm() {//폼을 띄우는 메서드
		ModelAndView mav = new ModelAndView("home/home");
		mav.addObject(new User());
		mav.addObject("BODY","userEntry.jsp");
		return mav;
	}
}
