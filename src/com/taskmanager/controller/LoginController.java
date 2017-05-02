/**
 * 
 */
package com.taskmanager.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taskmanager.data.LoginStatusEnum;
import com.taskmanager.data.UserDetails;
import com.taskmanager.services.LoginService;

/**
 * @author dwarakak
 *
 */
@Controller
@SessionAttributes("loginUser")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 
	 */
	@Autowired
	private LoginService loginService;

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String login(Model model) {
		return "login";
	}
	

	/**
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/")
	public String welcomeLogin(RedirectAttributes redirectAttributes) {
		return "redirect:/login";
	}

	/**
	 * @param model
	 * @param httpSession
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/logOut")
	public String logOut(Model model, HttpSession httpSession,
			RedirectAttributes attributes) {
		httpSession.removeAttribute("loginUser");
		model.asMap().clear();
		httpSession.invalidate();
		return "redirect:/login";
	}
	/**
	 * @return
	 */
	@RequestMapping(value = "/task/{taskId}/logOut")
	public String logOutOff() {
		return "redirect:/logOut";
	}
	/**
	 * @return
	 */
	@RequestMapping(value = "/{empId}/logOut")
	public String logOutOffUser() {
		return "redirect:/logOut";
	}
	
	/**
	 * @param empId
	 * @param model
	 * @param httpSession
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/{empId}/login")
	public String logIn(@PathVariable String empId, Model model, HttpSession httpSession,
			RedirectAttributes attributes) {
		
		logger.info("Logged In user: '{}' ", empId);
		return "redirect:/login";
	}

	/**
	 * @param empId
	 * @param passwd
	 * @param model
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/validateLogin")
	public String validateLogin(@RequestParam("empId") String empId,
			@RequestParam("passwd") String passwd, Model model,
			RedirectAttributes attributes) {
		final LoginStatusEnum validationStatus = loginService.validateCredentials(
				empId, passwd);
		if(LoginStatusEnum.INVALID_CREDENTIALS == validationStatus){
			model.addAttribute("errorMessage", "Invalid credentials!");
			return "login";
		} else if(LoginStatusEnum.INACTIVE == validationStatus){
			model.addAttribute("errorMessage", "User "+empId+" is inactive, contact your administrator for further details.");
			return "login";
		}
		final UserDetails userDetails = loginService.getUserRole(empId);
		model.addAttribute("loginUser", userDetails);
		return "redirect:/taskList";
	}
}
