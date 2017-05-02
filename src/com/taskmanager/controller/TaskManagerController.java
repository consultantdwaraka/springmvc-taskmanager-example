/**
 * 
 */
package com.taskmanager.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskmanager.data.ProductDetails;
import com.taskmanager.data.TaskDetails;
import com.taskmanager.data.UserDetails;
import com.taskmanager.data.validator.TaskValidator;
import com.taskmanager.services.ProductService;
import com.taskmanager.services.TaskService;
import com.taskmanager.services.UserService;

/**
 * @author dwarakak
 *
 */
@Controller
public class TaskManagerController {

	private static final Logger logger = LoggerFactory.getLogger(TaskManagerController.class);
	/**
	 * 
	 */
	@Autowired
	private TaskService taskService;

	/**
	 * 
	 */
	@Autowired
	private TaskValidator taskValidator;

	/**
	 * 
	 */
	@Autowired
	private UserService userService;

	/**
	 * 
	 */
	@Autowired
	private ProductService productService;

	/**
	 * @param binder
	 */
	@InitBinder
	public void dateEditor(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		binder.setValidator(taskValidator);
	}

	/**
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/taskList")
	public String get(Model model, HttpSession session) {
		
		final UserDetails userDetails = (UserDetails) session
				.getAttribute("loginUser");
		logger.info("Getting task List for user '{}'.", userDetails.getEmpName());
		model.addAttribute("taskList", taskService.findTaskList(userDetails));
		return "taskList";
	}

	/**
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/task/{taskId}/taskList")
	public String getTaskList(Model model, RedirectAttributes redirectAttributes) {
		return "redirect:/taskList";
	}
	
	/**
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/{empId}/taskList")
	public String getTaskList2(Model model, RedirectAttributes redirectAttributes) {
		return "redirect:/taskList";
	}
	
	/**
	 * @param taskId
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/taskList/task/{taskId}/getTask")
	public @ResponseBody String getTask(@PathVariable("taskId") Long taskId, Model model, RedirectAttributes redirectAttributes) {
		TaskDetails taskDetails = taskService.getTaskList(taskId);
		ObjectMapper mapper = new ObjectMapper();
		String taskDetailsStr = new String();
		try {
			taskDetailsStr = mapper.writeValueAsString(taskDetails);
		} catch (JsonProcessingException e) {
			logger.error("Converting task details to json. "+ e);
		}
		return taskDetailsStr;
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/newTask")
	public String newTask(Model model) {
		model.addAttribute("newTaskForm", new TaskDetails());
		return "newTask";
	}
	
	/**
	 * @param taskId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/task/{taskId}/editTask", method = RequestMethod.GET)
	public String editTaskId(@PathVariable("taskId") Long taskId, Model model) {
		TaskDetails taskDetails = taskService.getTaskList(taskId);
		model.addAttribute("newTaskForm", taskDetails);
		return "editTask";
	}

	/**
	 * @param taskId
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/taskList/task/{taskId}/deleteTask", method = RequestMethod.GET)
	public String deleteTask(@PathVariable("taskId") Long taskId,
			RedirectAttributes attributes) {
		taskService.deleteTask(taskId);
		logger.info("deleteTask():- Deleting task '{}'", taskId);
		return "redirect:/taskList";
	}

	/**
	 * @return
	 */
	@ModelAttribute("workList")
	public List<String> workTypeList() {
		List<String> workList = new ArrayList<String>();
		final List<ProductDetails> productList = productService
				.getProductList();
		for (ProductDetails productDetails : productList) {
			workList.add(productDetails.getProductName());
		}
		return workList;
	}

	/**
	 * @return
	 */
	@ModelAttribute("taskStatus")
	public List<String> taskStatusList() {
		List<String> workList = new ArrayList<String>();
		workList.add("New");
		workList.add("In-progress");
		workList.add("Completed");
		workList.add("Overdue");
		workList.add("Pre-completed");
		workList.add("Approved");
		return workList;
	}

	/**
	 * @return
	 */
	@ModelAttribute("empList")
	public List<String> employeeList() {
		final List<String> list = new ArrayList<String>();
		final List<UserDetails> userList = userService.getUserList();
		for (UserDetails userDetails : userList) {
			list.add(userDetails.getEmpId());
		}
		return list;
	}

	/**
	 * @param taskDetails
	 * @param resultBinding
	 * @param model
	 * @param attributes
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/createTask")
	public String createTask(
			@Validated @ModelAttribute("newTaskForm") TaskDetails taskDetails,
			BindingResult resultBinding, Model model,
			RedirectAttributes attributes, HttpSession httpSession) {
		logger.info("Creating new task");
		if (resultBinding.hasErrors()) {
			return "newTask";
		}
		if (taskDetails != null && taskDetails.getTaskId() != null
				&& taskDetails.getTaskId() != 0) {
			taskService.updateTask(taskDetails);
		} else {
			UserDetails userDetails = (UserDetails) httpSession
					.getAttribute("loginUser");
			taskDetails.setTaskCreatedBy(userDetails.getEmpId());
			taskDetails.setTaskCreationDate(new Date());
			taskService.createTask(taskDetails);
		}
		logger.info("createTask(): Created new task '{}'."+taskDetails.getTaskId());
		return "redirect:/taskList";
	}

	/**
	 * @param taskId
	 * @param taskDetails
	 * @param resultBinding
	 * @param model
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/task/{taskId}/createTask")
	public String SaveModifiedTask(@PathVariable String taskId,
			@Validated @ModelAttribute("newTaskForm") TaskDetails taskDetails,
			BindingResult resultBinding, Model model,
			RedirectAttributes attributes) {
		if (resultBinding.hasErrors()) {
			return "newTask";
		}
		if (taskDetails != null && taskDetails.getTaskId() != null
				&& taskDetails.getTaskId() != 0) {
			taskService.updateTask(taskDetails);
		} else {
			taskService.createTask(taskDetails);
		}
		logger.info("SaveModifiedTask(): Saved task '{}'."+taskDetails.getTaskId());
		return "redirect:/taskList";
	}

	/**
	 * 
	 */
	@ExceptionHandler(Exception.class)
	public void exceptionHandler() {

	}
}
