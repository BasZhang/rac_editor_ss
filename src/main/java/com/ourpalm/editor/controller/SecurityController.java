package com.ourpalm.editor.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Function;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Collections2;

@Controller
public class SecurityController {

	@Autowired
	private UserDetailsManager userManager;

	@PreAuthorize(value = "hasRole('SUPER')")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin() {
		return "admin";
	}

	@RequestMapping(value = "/forgetPsw", method = RequestMethod.GET)
	public String resetPswd() {
		return "forgetPsw";
	}

	@ResponseBody
	@RequestMapping(value = "/s_getAuth", method = RequestMethod.POST)
	public Object getAuth() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String[] authorities = Collections2.transform(userDetails.getAuthorities(), new Function<GrantedAuthority, String>() {
			@Override
			public String apply(GrantedAuthority arg0) {
				return arg0.getAuthority();
			}
		}).toArray(ArrayUtils.EMPTY_STRING_ARRAY);
		return authorities;
	}

	private Md5PasswordEncoder md5 = new Md5PasswordEncoder();

	@ResponseBody
	@RequestMapping(value = "/s_changePsw", method = RequestMethod.POST)
	public Object changePsw(String oldPsw, String newPsw) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 用username做盐值，根配置中的<security:salt-source user-property="username"/>
		// 保持一致
		try {
			userManager.changePassword(oldPsw, md5.encodePassword(newPsw, userDetails.getUsername()));
		} catch (AuthenticationException e) {
			return false;
		}
		return true;
	}

	@ResponseBody
	@RequestMapping(value = "/s_addUser", params = { "username", "password", "type" })
	public String addUser(String username, String password, String type) {
		if (!userManager.userExists(username)) {
			List<GrantedAuthority> createAuthorityList = null;
			if (type.endsWith("ADMIN")) {
				createAuthorityList = AuthorityUtils.createAuthorityList("ADMIN", "DEV", "COMM");
			} else if (type.endsWith("DEV")) {
				createAuthorityList = AuthorityUtils.createAuthorityList("DEV", "COMM");
			} else {
				createAuthorityList = AuthorityUtils.createAuthorityList("COMM");
			}
			UserDetails user = new User(username, md5.encodePassword(password, username), true, true, true, true, createAuthorityList);
			userManager.createUser(user);
		}
		return "ok";
	}

	@Bean(name = "guavaCache")
	public Cache<String, String> getGuavaCache() {
		Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(10000).expireAfterWrite(5, TimeUnit.MINUTES).build();
		return cache;
	}

	@Autowired
	private Cache<String, String> cache;

	@Autowired
	private JavaMailSender mailSender;

	@ResponseBody
	@RequestMapping(value = "/s_forgetPsw", method = RequestMethod.POST)
	public Object foretPsw(String username, String email, HttpServletRequest request) {
		try {
			if (!userManager.userExists(username)) {
				return "用户不存在！";
			} else {
				String token = md5.encodePassword(username, System.currentTimeMillis());
				cache.put(username, token);
				String url = request.getLocalAddr() + ":" + request.getLocalPort() + request.getContextPath() + "/resetPsw?username=" + username + "&token=" + token;
				System.out.println(url);
				try {
					SimpleMailMessage mail1 = new SimpleMailMessage();
					mail1.setFrom("racingsender@163.com");// 发送人名片
					mail1.setTo(email);// 收件人邮箱
					mail1.setReplyTo("racingsender@163.com");
					mail1.setSubject("Change password");// 邮件主题
					mail1.setSentDate(new Date());// 邮件发送时间
					mail1.setText(url);
					SimpleMailMessage[] mailMessages = { mail1 };
					mailSender.send(mailMessages);
				} catch (Exception e) {
					e.printStackTrace();
					return "失败！请找管理员人工处理。";
				}
				return "请进入您的邮箱查看链接，5分钟后此次重置密码请求将失效。";
			}
		} catch (AuthenticationException e) {
			return "失败！请找管理员人工处理。";
		}
	}

	@RequestMapping(value = "/resetPsw", method = RequestMethod.GET)
	public String resetPsw(String username, String token, Model model) {
		model.addAttribute("username", username);
		model.addAttribute("token", token);
		return "resetPsw";
	}

	@ResponseBody
	@RequestMapping(value = "/s_resetPsw", method = RequestMethod.POST)
	public Object resetPsw(String username, String token, String psw) {
		String tokenInCache = cache.getIfPresent(username);
		if (tokenInCache == null || !tokenInCache.equals(token)) {
			return "无效的请求";
		} else {
			UserDetails userDetails = userManager.loadUserByUsername(username);
			// 用username做盐值，根配置中的<security:salt-source user-property="username"/>
			// 保持一致
			UserDetails user = new User(username, md5.encodePassword(psw, username), true, true, true, true, userDetails.getAuthorities());
			userManager.updateUser(user);
			cache.invalidate(username);
			return "修改成功，请登录。";
		}
	}

}
