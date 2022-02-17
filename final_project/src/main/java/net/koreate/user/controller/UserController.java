package net.koreate.user.controller;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import net.koreate.user.service.UserService;
import net.koreate.user.vo.UserDTO;
import net.koreate.user.vo.UserVO;

@Controller
@RequestMapping("user")
public class UserController {

	@Inject
	UserService us;
	
	@GetMapping("/signIn")
	public String signIn() {
		return "/user/signIn";
	}

	@GetMapping("/signUp")
	public String signUp() {
		return "user/signUp";
	}
	
	@GetMapping("/info")
	public String userInfo() {
		return "user/info";
	}
	
	@GetMapping("/signUpdate")
	public String signUpdate() {
		return "user/signUpdate";
	}
	
	@PostMapping("/signUp")
	public String postSignUp() {
		return "user/signUp";
	}
	
	// 회원가입 버튼 눌렀을때
	@PostMapping("signUpPost")
	public String signUp(UserVO vo,ModelAndView mav,RedirectAttributes rttr) throws Exception{
		us.signUp(vo);
		System.out.println(vo);
		rttr.addFlashAttribute("message","회원가입 성공");
		return "redirect:/user/signIn";
	}
	// 로그인 버튼 클릭
	@PostMapping("signInPost")
	public ModelAndView signIn(UserDTO dto,ModelAndView mav) throws Exception{
	//	us.signIn(dto);
		mav.addObject("logDTO",dto);
		System.out.println(dto);
		mav.setViewName("redirect:/");
		return mav;
	}
	// 로그아웃
	@GetMapping("/signOut")
	public String signOut(HttpSession session,
			HttpServletRequest request,
			HttpServletResponse response,
			@CookieValue(name="signInCookie", required=false) Cookie signInCookie) throws Exception{
		if(session.getAttribute("userInfo") != null) {
			session.removeAttribute("userInfo");
			session.removeAttribute("invalidate");
			session.invalidate();
			/*
			if(signInCookie != null) {
				System.out.println("signInCookie ID : "+signInCookie.getName());
				signInCookie.setMaxAge(0);
				signInCookie.setPath("/");
				response.addCookie(signInCookie);
			}
			*/
		}
		Cookie cookie = WebUtils.getCookie(request, "signInCookie");
		if(cookie != null) {
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		return "redirect:/";
	}
	
	// 회원정보 수정하기
	@PostMapping("signUpdatePost")
	public String signUpdatePost(UserVO vo,ModelAndView mav) throws Exception {
		us.updateSign(vo);
		System.out.println("mav : "+mav);
		
		return null;
	}
	
}
