package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@RequestMapping(value="/home/intro.html")
	public ModelAndView intro(String BODY) {//home.jsp의 BODY파라미터수신
		ModelAndView mav = new ModelAndView("home/home");
		mav.addObject("BODY",BODY);
		return mav;
	}
	
	@RequestMapping(value="home/home.html")// 
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
}
