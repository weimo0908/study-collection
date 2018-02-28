package com.mzy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author mzy:
* @createTime：2018年2月28日 下午2:35:47
* @类说明: 展示页面
*/
@Controller
public class PageController {
	
	@RequestMapping(value="/{page}")
	public String showPage(@PathVariable String page){
		return page;
	}

}
