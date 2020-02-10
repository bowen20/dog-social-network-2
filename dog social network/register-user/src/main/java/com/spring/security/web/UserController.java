package com.spring.security.web;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.security.config.SecurityConfig;
import com.spring.security.model.Sessiontracker;
import com.spring.security.model.User;
import com.spring.security.service.SessionService;
import com.spring.security.service.UserService;
import com.spring.security.storage.StorageService;
import com.spring.security.web.dto.SlideDto;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private StorageService storageService;
	
	public UserController(UserService theUserService) {
		userService = theUserService;
	}
	
	
	@GetMapping("/list/{id}")
	public String listUsers(@PathVariable Long id, Model theModel) {
		System.out.println("list users");
		SlideDto slideDto = new SlideDto(0);
		theModel.addAttribute("slide", slideDto);
		Stream<Path> images = storageService.loadAll();
		List<Path> theImages = images.collect(Collectors.toList());
		String theImage = "/pictures/" + theImages.get(0);
		theModel.addAttribute("image", theImage);
		List<User> theUsers = userService.findAll();
		theModel.addAttribute("users", theUsers);
	    slideDto.setId(id);
		return "list-users";
	}

}
