/*
* 
*/
package com.auth.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.auth.util.Log;
import com.auth.dao.UserLoginDAO;
import com.auth.util.ESIGNProperties;

// TODO: Auto-generated Javadoc
/**
 * Home Controller Demo AUTH.
 */
@Controller
public class HomeController {

	
	/** The user login DAO. */
	@Autowired
	private UserLoginDAO userLogindao;
	
	/** The finger print. */
	String asaResponseXML = null, fingerpostion = null, biotransactionid = null, errorcode = null,
			errordescription = null, txtAadhaarNo = null, fingerPrint = null;

	/**
	 * Home.
	 *
	 * @param user
	 *            the user
	 * @param session
	 *            the session
	 * @return the model and view
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView Home(HttpSession session) throws Exception {

		String propFilePath = "";
		ESIGNProperties.load(propFilePath);
		int access = userLogindao.isAcessDetails(session.getAttribute("user_login_name").toString(), session);
		Log.subaua.info("User Login ::" + session.getAttribute("user_login_name"));
		if (session.getAttribute("user_login_name") != null && access == 1) {
			return new ModelAndView("ekyc");
		} else {
			return new ModelAndView("redirect:/login");

		}

	}

}