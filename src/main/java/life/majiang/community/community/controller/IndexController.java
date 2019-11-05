package life.majiang.community.community.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.User;

@Controller
public class IndexController {
	@Autowired
	UserMapper userMapper;
	
	@GetMapping("/")
	public String index(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if( "token".equals(cookie.getName())) {
				String token = cookie.getValue();
				User user = userMapper.findByToken(token);
				if(user != null) {
					request.getSession().setAttribute("user", user);
				}
				break;
			}
			
		}
		


		return "index";
	}
	

}
