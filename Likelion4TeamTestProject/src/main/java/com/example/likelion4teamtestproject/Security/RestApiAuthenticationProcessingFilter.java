package com.example.likelion4teamtestproject.Security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
public class RestApiAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    protected RestApiAuthenticationProcessingFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    protected RestApiAuthenticationProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    protected RestApiAuthenticationProcessingFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl, authenticationManager);
    }

    protected RestApiAuthenticationProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher, AuthenticationManager authenticationManager) {
        super(requiresAuthenticationRequestMatcher, authenticationManager);
    }

// 추가된부분
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("successful");

        // JWT 생성 및 반환
        String token = createToken(authResult);
        response.setHeader("Authorization", "Bearer " + token);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("{\"token\": \"" + token + "\"}");
    }

    private String createToken(Authentication authResult) {
        // JWT 생성 로직 구현 (예: JWT 라이브러리 사용)
        // 예시: String token = JWT.create().withSubject(authResult.getName()).sign(Algorithm.HMAC512("SECRET_KEY"));
        // 실제 JWT 생성 코드를 여기에 추가하세요.
        return "generated-jwt-token"; // 임시 토큰 값
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {

        if (!request.getMethod().equals(HttpMethod.POST.toString()))
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());

        JSONObject jsonObject = makeJSONObject(request);

        String email = jsonObject.getString("email");
        email = (email != null) ? email : "";
        String password = jsonObject.getString("password");
        password = (password != null) ? password : "";

        return this.getAuthenticationManager().authenticate(UsernamePasswordAuthenticationToken.unauthenticated(email,password));

    }


    private JSONObject makeJSONObject(HttpServletRequest request) throws AuthenticationException, IOException, JSONException {
        return new JSONObject(new JSONTokener(request.getReader()));
    }


    @Override
    public void setFilterProcessesUrl(String filterProcessesUrl) {
        super.setFilterProcessesUrl(filterProcessesUrl);
    }

    @Override
    protected AuthenticationSuccessHandler getSuccessHandler() {
        return super.getSuccessHandler();
    }
}
