package life.majiang.community.community.provider;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import life.majiang.community.community.dto.AccessTokenDTO;
import life.majiang.community.community.dto.GithubUser;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
public class GithubProvider {

	public String getAccessToken(AccessTokenDTO accessTokenDTO) {
		
		MediaType mediaType
	    = MediaType.get("application/json; charset=utf-8");

	OkHttpClient client = new OkHttpClient();

	 RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
	  Request request = new Request.Builder()
	      .url("https://github.com/login/oauth/access_token")
	      .post(body)
	      .build();
	  try (Response response = client.newCall(request).execute()){
			String string = response.body().string();
		    System.out.println(string);
		    return string;
		  }catch(Exception e) {
			  //
			  //e.printStackTrace();
		  }
		  return null;
	}

	
	public GithubUser getUser(String accessToken) {
			System.out.println("getUser called");
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder()
			      .url("https://api.github.com/user?"+accessToken)
			      .build();
		  try (Response response = client.newCall(request).execute()){
			  
			  String sstring = response.body().string();
			  System.out.println(sstring);
			  System.out.println("sstring");

			  GithubUser githubUser = JSON.parseObject(sstring, GithubUser.class);
			  System.out.println(githubUser);
			  System.out.println(githubUser.getId());
			  System.out.println(githubUser.toString());
			  return githubUser;
			  }catch(Exception e) {
				  e.printStackTrace();
			  }
		  	return null;
	}
}
