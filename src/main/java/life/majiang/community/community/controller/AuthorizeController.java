package life.majiang.community.community.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import life.majiang.community.community.dto.AccessTokenDTO;
import life.majiang.community.community.dto.GithubUser;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.User;
import life.majiang.community.community.provider.GithubProvider;

@Controller
public class AuthorizeController {
	@Autowired
	private GithubProvider githubProvider;
	@Autowired
	private GithubUser githubUser;
	@Value("${github.client.id}")
	private String clientId;
	@Value("${github.client.secret}")
	private String clientSecret;
	@Value("${github.redirect.uri}")
	private String redirectUri;
	
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/callback")
	public String callback(@RequestParam(name="code") String code, 
							@RequestParam(name="state")String state,
							HttpServletRequest request) {
		
		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
		accessTokenDTO.setClient_id(clientId);
		accessTokenDTO.setCode(code);
		accessTokenDTO.setRedirect_uri(redirectUri);
		accessTokenDTO.setClient_secret(clientSecret);
		accessTokenDTO.setState(state);
		String accessToken = githubProvider.getAccessToken(accessTokenDTO);
		//githubProvider.getUser(accessToken);
		githubUser = githubProvider.getUser(accessToken);
		System.out.println(githubUser);
		System.out.println(githubUser.getId());
		//if user is not null, successful login, 
		if(githubUser !=null) {
			User user = new User();
			user.setToken(UUID.randomUUID().toString());
			user.setName(githubUser.getLogin());
			user.setAccountId(String.valueOf(githubUser.getId()));
			user.setGmtCreate(System.currentTimeMillis());
			user.setGmtModified(user.getGmtCreate());
			userMapper.insert(user);
			request.getSession().setAttribute("user",githubUser);
			return "redirect:/";
		}else {
		return "redirect:/";
		}
	}

}
