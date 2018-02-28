package com.mzy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mzy.Util.RandomValidateCode;

@Controller
public class ImageController {

	@RequestMapping(value = "/toImg")
	public String toImg() {

		return "image/image";
	}

	// 登录获取验证码
	@RequestMapping("/getSysManageLoginCode")
	@ResponseBody
	public String getSysManageLoginCode(HttpServletResponse response, HttpServletRequest request) {
		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Set-Cookie", "name=value; HttpOnly");// 设置HttpOnly属性,防止Xss攻击
		response.setDateHeader("Expire", 0);
		RandomValidateCode randomValidateCode = new RandomValidateCode();
		try {
			randomValidateCode.getRandomcode(request, response, "imagecode");// 输出图片方法
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	// 验证码验证
	@RequestMapping(value = "/checkimagecode")
	@ResponseBody
	public String checkTcode(HttpServletRequest request, HttpServletResponse response) {
		String validateCode = request.getParameter("validateCode");
		System.out.println(validateCode);
		//String code = null;
		// 1:获取cookie里面的验证码信息
	/*	Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if ("imagecode".equals(cookie.getName())) {
				code = cookie.getValue();
				break;
			}
		}*/
		
		// 1:获取session验证码的信息
		String code1 = (String) request.getSession().getAttribute("imagecode_" + request.getSession().getId());
		System.out.println(code1);
		// 2:判断验证码是否正确                       				       不区分大小写
		if (!StringUtils.isEmpty(validateCode) && StringUtils.equalsIgnoreCase(validateCode, code1)) {
			return "ok";

		}
		return "error";
	}

}
