/**
 * 
 */
package com.taskmanager.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author dwarakak
 *
 */
@Component
public class TaskManagerInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(TaskManagerInterceptor.class);
	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		Object loggedInUser = session.getAttribute("loginUser");
		if(loggedInUser == null){
			logger.warn("preHandle(): Session timed out. Redirecting to login.");
			response.sendRedirect("login");
		}
		return true;
	}

}
