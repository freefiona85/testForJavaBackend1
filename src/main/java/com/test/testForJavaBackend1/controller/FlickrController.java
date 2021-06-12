package com.test.testForJavaBackend1.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.apis.FlickrApi.FlickrPerm;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.test.testForJavaBackend1.handler.ConstantsGlobal;

@RestController
public class FlickrController {

	final String urls = "https://www.flickr.com/services/oauth/request_token";
	protected static final String PROTECTED_RESOURCE_URL = "https://www.flickr.com/services/rest/";
	private OAuth1RequestToken token = new OAuth1RequestToken(urls, PROTECTED_RESOURCE_URL);
	OAuthRequest requestToken = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL); 

	public OAuth1RequestToken getToken() {
		return token;
	}

	public void setToken(OAuth1RequestToken token) {
		this.token = token;
	}
	@RequestMapping(value="/searchPhoto", method=RequestMethod.GET)
	public @ResponseBody String searchFlickr(@RequestParam("search") String search, @RequestParam("page") String page) throws InterruptedException, ExecutionException, IOException {
		OAuth10aService serv = new ServiceBuilder(ConstantsGlobal.API_KEY).apiSecret(ConstantsGlobal.API_SECRET).build(FlickrApi.instance(FlickrPerm.READ));
		requestToken.addQuerystringParameter("method", "flickr.photos.search");
		requestToken.addQuerystringParameter("tags", search);
		requestToken.addQuerystringParameter("format", "json");
		requestToken.addQuerystringParameter("page", page);
		requestToken.addQuerystringParameter("per_page", "10");
		requestToken.addQuerystringParameter("format", "json");
		requestToken.addQuerystringParameter("api_key", ConstantsGlobal.API_KEY);
		return serv.execute(requestToken).getBody();
	}
	
	@RequestMapping(value="list-comments", method=RequestMethod.GET)
	public @ResponseBody String getCommentsByPhotoId(@RequestParam("photoId") String photoId) throws IOException, InterruptedException, ExecutionException {
		OAuth10aService serv = new ServiceBuilder(ConstantsGlobal.API_KEY).apiSecret(ConstantsGlobal.API_SECRET).build(FlickrApi.instance(FlickrPerm.READ));
		requestToken.addQuerystringParameter("method", "flickr.photos.search");
		requestToken.addQuerystringParameter("photo_id", photoId);
		requestToken.addQuerystringParameter("format", "json");
		requestToken.addQuerystringParameter("api_key", ConstantsGlobal.API_KEY);
		return serv.execute(requestToken).getBody();
	}
	
	

	

}
