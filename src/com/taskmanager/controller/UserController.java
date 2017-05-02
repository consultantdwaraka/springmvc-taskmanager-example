/**
 * 
 */
package com.taskmanager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taskmanager.data.UserDetails;
import com.taskmanager.data.validator.UserValidator;
import com.taskmanager.services.UserService;

/**
 * @author dwarakak
 *
 */
@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	/**
	 * @param binder
	 */
	@InitBinder
	public void dateEditor(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

	/**
	 * @return
	 */
	@RequestMapping("/userList")
	public String employeeList(Model model) {
		logger.info("employeeList(): Retriving employee list. ");
		final List<UserDetails> userList = userService.getUserList();
		model.addAttribute("userList", userList);
		return "userList";
	}
	/**
	 * @return
	 */
	@RequestMapping("/{empId}/userList")
	public String employeeList(Model model, RedirectAttributes redirectAttributes) {
		return "redirect:/userList";
	}
	
	/**
	 * @return
	 */
	@RequestMapping("/task/{empId}/userList")
	public String employeeList2(Model model, RedirectAttributes redirectAttributes) {
		return "redirect:/userList";
	}

	/**
	 * @return
	 */
	@RequestMapping("/newUser")
	public String newEmployee(Model model) {
		final UserDetails newUser = new UserDetails();
		model.addAttribute("newEmpForm", newUser);
		return "newUser";
	}

	/**
	 * @return
	 */
	@RequestMapping("/createUser")
	public String createEmployee(
			@Validated @ModelAttribute("newEmpForm") UserDetails userDetails,
			BindingResult resultBinding, Model model,
			RedirectAttributes attributes, HttpSession httpSession) {
		if (resultBinding.hasErrors()) {
			return "newUser";
		}
		userService.createUser(userDetails);
		logger.info("createEmployee(): Created employee '{}'. ", userDetails.getEmpId());
		return "redirect:/userList";
	}

	/**
	 * @return
	 */
	@ModelAttribute("userStatus")
	public List<String> userStatus() {
		List<String> workList = new ArrayList<String>();
		workList.add("Active");
		workList.add("Inactive");
		return workList;
	}

	/**
	 * @return
	 */
	@ModelAttribute("userRole")
	public List<String> userRole() {
		List<String> workList = new ArrayList<String>();
		workList.add("Boss");
		workList.add("Manager");
		workList.add("Administrator");
		workList.add("User");
		return workList;
	}

	@RequestMapping(value = "/{empId}/deleteEmp", method = RequestMethod.GET)
	public String deleteUser(@PathVariable String empId,
			RedirectAttributes attributes) {
		System.out.println("EMP ID: "+empId);
		userService.deleteUser(empId);
		logger.info("deleteUser(): Deleted employee '{}'. ", empId);
		return "redirect:/userList";
	}
	
	@RequestMapping(value = "/userList/{empId}/deleteEmp", method = RequestMethod.GET)
	public String deleteUser1(@PathVariable String empId,
			RedirectAttributes attributes) {
		System.out.println("EMP ID: "+empId);
		userService.deleteUser(empId);
		logger.info("deleteUser1(): Deleted employee '{}'. ", empId);
		return "redirect:/userList";
	}
	
	@RequestMapping(value = "/{empId}/editEmp", method = RequestMethod.GET)
	public String editUser(@PathVariable String empId, Model model,
							RedirectAttributes attributes){
		UserDetails taskDetails = userService.getUserDetails(empId);
		model.addAttribute("newEmpForm", taskDetails);
		return "editUser";
	}
	
	/**
	 * @param taskId
	 * @param taskDetails
	 * @param resultBinding
	 * @param model
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/{taskId}/createUser")
	public String SaveModifiedTask(@PathVariable String taskId,
			@Validated @ModelAttribute("newEmpForm") UserDetails userDetails,
			BindingResult resultBinding, Model model,
			RedirectAttributes attributes) {
		if (resultBinding.hasErrors()) {
			return "newUser";
		}
		if (userDetails != null && userDetails.getEmpId() != null
				&& !userDetails.getEmpId().isEmpty()) {
			userService.updateUser(userDetails);
		} else {
			userService.createUser(userDetails);
		}
		logger.info("editUser(): Modified employee '{}'. ", userDetails.getEmpId());
		return "redirect:/userList";
	}

}
