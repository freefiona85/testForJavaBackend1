package com.test.testForJavaBackend1.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.test.testForJavaBackend1.handler.ConstantsGlobal;

@RestController
@RequestMapping(value = "/token")
public class FlickrWithTokenController extends FlickrController {

	private static final String userId = "193198752@N05";

	@RequestMapping(value = "/withToken", method = RequestMethod.GET)
	public void searchWithToken(HttpServletRequest request, HttpServletResponse response)
			throws IOException, InterruptedException, ExecutionException {
		OAuth10aService service = new ServiceBuilder(ConstantsGlobal.API_KEY).apiSecret(ConstantsGlobal.API_SECRET)
				.callback(ConstantsGlobal.getBaseUrl(request)+"/token/call").build(FlickrApi.instance(FlickrApi.FlickrPerm.READ));
		setToken(service.getRequestToken());
		String urlsAuth = service.getAuthorizationUrl(getToken());
		response.sendRedirect(urlsAuth + "&perms=read");
	}

	@RequestMapping(value = "/call", method = RequestMethod.GET)
	public String callBackRequest(@RequestParam("oauth_token") String requestParam,
			@RequestParam("oauth_verifier") String oauthVerifier)
			throws IOException, InterruptedException, ExecutionException {
		OAuth10aService service = new ServiceBuilder(ConstantsGlobal.API_KEY).apiSecret(ConstantsGlobal.API_SECRET).build(FlickrApi.instance(FlickrApi.FlickrPerm.READ));
		OAuth1AccessToken accessToken = service.getAccessToken(getToken(), oauthVerifier);
		requestToken.addQuerystringParameter("method", "flickr.people.getPublicPhotos");
		requestToken.addQuerystringParameter("user_id", userId);
		requestToken.addQuerystringParameter("format", "json");
		service.signRequest(accessToken, requestToken);
		Response rest = service.execute(requestToken);
		return rest.getBody();

	}

}
