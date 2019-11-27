package life.majiang.community.community.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.Question;
import life.majiang.community.community.model.User;

@Controller
public class PublishController {
	@Autowired
	private Question question;
	@Autowired
	private User user;
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/publish")
	public String publish() {
		return "publish";
	}
	
//	for input tag:
	
//	id是唯一标识符，不允许有重复值可以通过它的值来获得对应的html标签对象。相当于人的身份证具有唯一性

//	name：控件的名字，一个控件是否设置它的 name 属性是不会影响到这个网页的功能实现的。但是，当我们需要把这个控件所关联的数据传递到数据库时，就必须要设置 name 属性。相当于人的姓名，可以重名

//	value：控件的值

	@PostMapping("/publish")
	public String doPublish(
			@RequestParam(name="title")String title,
			@RequestParam(name="description")String description,
			@RequestParam(name="tag")String tag,
			HttpServletRequest request,
			Model model
			) {
		model.addAttribute("title",title);
		model.addAttribute("description",description);
		model.addAttribute("tag",tag);
		if(title == null || "".contentEquals(title)) {
			model.addAttribute("error","issue title cannot be empty");
			return "publish";
		}
		if(description == null || "".contentEquals(description)) {
			model.addAttribute("error","issue description cannot be empty");
			return "publish";
		}
		if(tag == null || "".contentEquals(tag)) {
			model.addAttribute("error","issue tag cannot be empty");
			return "publish";
		}
		
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if( "token".equals(cookie.getName())) {
				String token = cookie.getValue();
				user = userMapper.findByToken(token);
				if(user != null) {
					request.getSession().setAttribute("user", user);
				}
				break;
			}
			
		}
		if(user == null) {
			model.addAttribute("error","user not login");
			return "publish";
		}
		
		question.setTitle(title);
		question.setDescription(description);
		question.setTag(tag);
		question.setGmtCreate(System.currentTimeMillis());
		question.setGmtModified(question.getGmtCreate());
		question.setCreator(user.getId());
		questionMapper.create(question);
		return "redirect:/";
	}
}
