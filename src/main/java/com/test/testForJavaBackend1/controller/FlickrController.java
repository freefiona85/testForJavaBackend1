package com.test.testForJavaBackend1.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.apis.FlickrApi.FlickrPerm;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.test.testForJavaBackend1.handler.ConstantsGlobal;

@RestController
public class FlickrController {

	final String urls = "https://www.flickr.com/services/oauth/request_token";
	private static final String PROTECTED_RESOURCE_URL = "https://www.flickr.com/services/rest/";
	private static final String userId="193198752@N05";
	private OAuth1RequestToken token = new OAuth1RequestToken(urls, PROTECTED_RESOURCE_URL);

	public OAuth1RequestToken getToken() {
		return token;
	}

	public void setToken(OAuth1RequestToken token) {
		this.token = token;
	}
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public @ResponseBody String searchFlickr(@RequestParam("search") String search, @RequestParam("page") String page) throws InterruptedException, ExecutionException, IOException {
		OAuth10aService serv = new ServiceBuilder(ConstantsGlobal.API_KEY).apiSecret(ConstantsGlobal.API_SECRET).build(FlickrApi.instance(FlickrPerm.READ));
		final OAuthRequest requestOauth = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
		requestOauth.addQuerystringParameter("method", "flickr.photos.search");
		requestOauth.addQuerystringParameter("tags", search);
		requestOauth.addQuerystringParameter("format", "json");
		requestOauth.addQuerystringParameter("page", page);
		requestOauth.addQuerystringParameter("per_page", "10");
		requestOauth.addQuerystringParameter("format", "json");
		requestOauth.addQuerystringParameter("api_key", ConstantsGlobal.API_KEY);
		Response rest = serv.execute(requestOauth);
		return rest.getBody();
	}

	@GetMapping("/")
	public void home(HttpServletRequest request, HttpServletResponse response)
			throws IOException, InterruptedException, ExecutionException {

		OAuth10aService service = new ServiceBuilder(ConstantsGlobal.API_KEY).apiSecret(ConstantsGlobal.API_SECRET)
				.callback("http://localhost:9002/interview/call").build(FlickrApi.instance(FlickrApi.FlickrPerm.READ));
		
		//OAuthRequest resourceRequest = new OAuthRequest(Verb.GET, urls);
		// resourceRequest = service.getRequestToken();
		setToken(service.getRequestToken());
		String urlsAuth = service.getAuthorizationUrl(getToken());
		response.sendRedirect(urlsAuth + "&perms=read");
	}

	@RequestMapping(value = "/call", method = RequestMethod.GET)
	public String requestCall(@RequestParam("oauth_token") String requestParam,
			@RequestParam("oauth_verifier") String oauthVerifier)
			throws IOException, InterruptedException, ExecutionException {
		OAuth10aService service = new ServiceBuilder(ConstantsGlobal.API_KEY).apiSecret(ConstantsGlobal.API_SECRET)
				.callback("http://localhost:9002/interview/call").build(FlickrApi.instance(FlickrApi.FlickrPerm.READ));
		final OAuth1AccessToken accessToken = service.getAccessToken(getToken(), oauthVerifier);
		final OAuthRequest requestOauth = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
		requestOauth.addQuerystringParameter("method", "flickr.people.getPublicPhotos");
		requestOauth.addQuerystringParameter("user_id", userId);
		requestOauth.addQuerystringParameter("format", "json");
		service.signRequest(accessToken, requestOauth);
		Response rest = service.execute(requestOauth);
		System.out.println(rest.getBody());
		return rest.getBody();

	}

}
