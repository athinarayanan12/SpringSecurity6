package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.models.ApplicationUser;
import com.app.models.LoginResponseDTO;
import com.app.models.RegisterDTO;
import com.app.service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegisterDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }
    
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegisterDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
    
    @GetMapping("/newSession")
    public String newSession(HttpServletRequest request){
    	//String level = (String) session.getAttribute("admin");
    	
    	HttpSession session = request.getSession();
		if(session.isNew()){
			System.out.println("New session is jutst created");
		}else{
			System.out.println("This is old session");
		}
    	return "Test ok";
    }
    @GetMapping("/oldsession")
    public String oldsession(HttpServletRequest request){
    	//String level = (String) session.getAttribute("admin");
    	
    	HttpSession session = request.getSession(false);
		if(session.isNew()){
			System.out.println("New session is jutst created");
		}else{
			System.out.println("This is old session");
		}
    	return "Test ok";
    }
}   
