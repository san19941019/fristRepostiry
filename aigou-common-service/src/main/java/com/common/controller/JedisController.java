package com.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.bo.ResultBo;

@Controller
@RequestMapping("/common")
public class JedisController {
	
	@RequestMapping("/jedis")
	@ResponseBody
	public ResultBo putJedis() {
		return null;
				}
}
