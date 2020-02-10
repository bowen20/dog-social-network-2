package com.spring.security.web;

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

import com.spring.security.model.Sessiontracker;
import com.spring.security.model.User;
import com.spring.security.service.SessionService;
import com.spring.security.service.UserService;
import com.spring.security.web.dto.UpdateDto;
import com.spring.security.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/showFormForUpdate")
public class UpdateController {

	@Autowired
	private SessionService sessionService;

	@Autowired
	private UserService userService;
	

    @ModelAttribute("user")
    public UpdateDto updateDto() {

        return new UpdateDto();
    }
	
	@GetMapping("/{id}")
	public String showFormForUpdate(Model model, @PathVariable Long id)
	{
		Sessiontracker sessiontracker = sessionService.findById(id);
		String email = sessiontracker.getEmail();
		User theUser = userService.findByEmail(email);
		model.addAttribute("user", theUser);
		return "update";
	}
	
	@PostMapping
    public String updateUserAccount(@ModelAttribute("user") @Valid UpdateDto userDto,
            BindingResult result){
if (result.hasErrors()){
return "update";
}
userService.update(userDto);
return "redirect:/users/list/1";
}

}
