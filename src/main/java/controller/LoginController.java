package controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.UserCatalog;
import model.Loginuser;
import model.User;

@Controller
public class LoginController {
	@Autowired
	private UserCatalog userCatalog;
	
	
	

	
	@RequestMapping(value="/login/home.html",
			method=RequestMethod.POST)
	public ModelAndView doLogin(@Valid Loginuser loginuser,
			BindingResult br,HttpSession session) {//오류메세지,로그인 정보유지해서 찾기
		ModelAndView mav = new ModelAndView("home/home");
		if(br.hasErrors()) {
			mav.getModel().putAll(br.getModel());
			mav.addObject("RELOGIN","login.jsp");//오류메시지가 있는 jsp
			return mav;
		}
		User user = this.userCatalog.selectUser(loginuser);
		if(user == null) {//입력한 아이디비번검색 결과가 없으면
			mav.addObject("NOLOGIN","YES");
		}else {//검색결과가 있으면
			session.setAttribute("IDPWD", loginuser);
			session.setAttribute("LOGINUSER", user);
			mav.addObject("LOGIN","YES");
		}
		mav.addObject("BODY","loginResult.jsp");
		return mav;
	}
	
	@RequestMapping(value="/login/login.html")//홈에서보이는 login.jsp
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("home/login");//login.jsp가 출력된다
		mav.addObject(new Loginuser());
		return mav;
	}
}
