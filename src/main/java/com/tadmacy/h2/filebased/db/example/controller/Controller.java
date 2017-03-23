package com.tadmacy.h2.filebased.db.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tadmacy.h2.filebased.db.example.model.User;
import com.tadmacy.h2.filebased.db.example.model.UserDao;

@CrossOrigin(origins = "*")
@RestController
public class Controller {

    private static Logger logger = LoggerFactory.getLogger(Controller.class);
	
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public HttpEntity<String> sanityCheck() {
    	
    	logger.info("SanityCheck entered...");
    	
		// Return some HTML
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.TEXT_PLAIN);
    	
    	String msg = "It's alive! It's alive!";
    	
    	return new HttpEntity<String>(msg, headers);	
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUser/{id:[\\d]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User getUser(@PathVariable("id") long id) {

    	logger.info("getUser entered: id= " + id);
    	
    	return userDao.findOne(id);   	
    }
    
	@RequestMapping(value="/getUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Iterable<User> getUsers() {
    	
    	logger.info("findAll entered...");
    	
		return userDao.findAll();
	}

	@RequestMapping(value="/saveUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User saveUser(@RequestBody User u) {
    	
    	logger.info("saveUser entered...");
    	
		User newUser = new User();
		newUser = userDao.save(u);
		return newUser;
	}
}