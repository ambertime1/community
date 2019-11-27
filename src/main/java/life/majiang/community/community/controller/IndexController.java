package life.majiang.community.community.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.User;
import life.majiang.community.community.service.QuestionService;

@Controller
public class IndexController {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/")
	public String index(Model model, HttpServletRequest request) {
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
		List<QuestionDTO> questionList = questionService.list();
		model.addAttribute("questions",questionList);
		return "index";
	}
}
