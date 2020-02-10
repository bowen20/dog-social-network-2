package com.spring.security.web;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.security.model.User;
import com.spring.security.service.IdService;
import com.spring.security.service.UserService;
import com.spring.security.storage.StorageService;
import com.spring.security.web.dto.SlideDto;
import com.spring.security.web.dto.UserRegistrationDto;

@Controller
public class MainController {

	@Autowired
	private IdService idService;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private UserService userService;
	
	public MainController(IdService theIdService, StorageService theStorageService, UserService theUserService) {
		idService = theIdService;
		storageService = theStorageService;
		userService = theUserService;
	}
	
    @ModelAttribute("slide")
    public SlideDto slideDto() {
        return new SlideDto(0);
    }
	
	@GetMapping("/")
    public String root() {
        return "redirect:/users/list/" + idService.getSession().toString();
    }
	
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    
    @PostMapping("/slider")
    public String slideshow(@ModelAttribute("slide") @Valid SlideDto slideDto, BindingResult result, Model theModel) {
    	
    	return "redirect:/slides/" + slideDto.getId();
    }
    
    @GetMapping("/slides/{id}")
    public String slides(@ModelAttribute("slide") @Valid SlideDto slideDto, BindingResult result, Model theModel) {
    	Stream<Path> images = storageService.loadAll();
    	List<Path> theImages = images.collect(Collectors.toList());
    	int theIndex = slideDto.getIndex();
    	theIndex++;
		theIndex = theIndex % theImages.size();
		slideDto.setIndex(theIndex);
		String theImage = "/pictures/" + theImages.get(theIndex);
		theModel.addAttribute("image", theImage);
		List<User> theUsers = userService.findAll();
		theModel.addAttribute("users", theUsers);
    	return "slide-users";
    }
}