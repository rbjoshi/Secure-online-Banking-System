package com.security.app.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.security.app.model.User;
import com.security.app.service.UserManager;
import com.security.app.util.AuthService;
import com.security.app.util.StaticMethods;
import com.security.app.util.SystemLoggerService;

@Controller
public class SystemLogController {
	private static final Logger logger = SystemLoggerService.logger;
	@Autowired
	private UserManager userManager;
	
	
	@RequestMapping(value="/system-logs", method=RequestMethod.GET) 
	public ModelAndView generateLogFiles(HttpSession session) throws IOException{
		ModelAndView model = new ModelAndView();
		Map<String, List<String>> map = SystemLoggerService.getFileContentMap();
		if(StaticMethods.isAnnonymous(AuthService.getAuthName())){
			model.setViewName(StaticMethods.INDEX);
			return model;
		}
			 
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(user!=null){
			if(!StaticMethods.isAdmin(user)) {
				model.setViewName("redirect:/dashboard");
				return model;
			}
		}
		else if(user==null){
			model.setViewName("redirect:/index");
			return model;
		}
		
		model.addObject("fileNames",map.keySet());
		model.setViewName("/systemLogs");
		return model;
	}
	
	@RequestMapping(value="/donwload-system-log", method=RequestMethod.GET) 
	public ModelAndView generateLogFiles(@RequestParam(value = "filename", required = true) String fileName,HttpSession session) throws IOException{
		ModelAndView model = new ModelAndView();
		Map<String, List<String>> map = SystemLoggerService.getFileContentMap();
		
		if(StaticMethods.isAnnonymous(AuthService.getAuthName())){
			model.setViewName(StaticMethods.INDEX);
			return model;
		}
		
		List<String> list = map.get(fileName);
		if(fileName == null || list == null || list.isEmpty()) {
			model.addObject("error","Didn't find the logs for "+fileName+"..!!");
			model.setViewName("admin");
			return model;
		}
		model.addObject("content",list);
		model.addObject("date",fileName);
		model.setViewName("/logViewer");
		return model;
	}
}
