package com.springboot.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.binding.User;
import com.springboot.entity.UserEntity;
import com.springboot.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/")
	public String loadRegistrationForm(Model model)
	{
		model.addAttribute("user", new User());
		return "index";
	}
	
	@PostMapping("/register")
	public String userData(@Valid User user, BindingResult result, Model model)
	{
		if(result.hasErrors())
		{
			return "index";
		}
		
			//System.out.println(user);
			UserEntity userEntity = new UserEntity();
			BeanUtils.copyProperties(user, userEntity);
			
			userRepo.save(userEntity);
			
			model.addAttribute("msg", "🎉 Your account has been created successfully!");
			
		
		    return "index";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model)
	{
		return "login";
	}
	
	@PostMapping("/login")
	public String loginUser(@ModelAttribute User user, HttpSession session, Model model) {

	    UserEntity dbUser = userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
	    //UserEntity dbUserName = userRepo.findByName(user.getName());

	    if (dbUser == null) {
	        model.addAttribute("msg", "Invalid Username or Password");
	        return "login";
	    }
	    session.setAttribute("loggedUser", dbUser.getUserId());  // store userId in session
	    session.setAttribute("loggedUserName", dbUser.getName());
	    return "redirect:/dashboard";
	}
	
	@GetMapping("/logout")
	public String logoutUser(HttpSession session, RedirectAttributes redirectAttrs) {

	    session.invalidate();  // <-- VERY IMPORTANT: clears logged user session

	    redirectAttrs.addFlashAttribute("msg", "You have been logged out successfully.");

	    return "redirect:/login"; // redirect so flash message works
	}



}
