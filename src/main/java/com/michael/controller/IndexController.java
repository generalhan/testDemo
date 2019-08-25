package com.michael.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.michael.entity.UserInfo;
import com.michael.service.BaseManager;

/**
 * 控制类demo
 * 
 * @author Administrator
 * 
 */
@Controller
public class IndexController {

	@Autowired
	private BaseManager baseManager;

	@RequestMapping(value = "index")
	public ModelAndView home(Model model, HttpServletRequest request) {
		// 查询所有
		List<UserInfo> users = baseManager.query("UserInfo.queryUsers", null);
		model.addAttribute("users", users);
		// 查询单个
		UserInfo user = baseManager.get("UserInfo.get", new Object[] { "2" });
		model.addAttribute("user", user);
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "insert/{name}")
	@ResponseBody
	public String insert(@PathVariable String name) {
		UserInfo user=new UserInfo();
		user.setNames(name);
		baseManager.insert(user);
		return "success";
	}
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(String name,String password) {
		
		return "login";
	}
}
