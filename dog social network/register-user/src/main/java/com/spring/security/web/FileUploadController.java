package com.spring.security.web;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.security.model.Sessiontracker;
import com.spring.security.model.User;
import com.spring.security.service.SessionService;
import com.spring.security.service.UserService;
import com.spring.security.storage.StorageService;
import com.spring.security.web.dto.UpdateDto;
import com.spring.security.web.dto.UploadDto;

@Controller
public class FileUploadController {

    private final StorageService storageService;
    
	@Autowired
	private SessionService sessionService;

	@Autowired
	private UserService userService;

    @Autowired
    public FileUploadController(StorageService storageService, UserService userService) {
        this.storageService = storageService;
        this.userService = userService;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @ModelAttribute("user")
    public UploadDto uploadDto() {
        return new UploadDto();
    }
    
    @GetMapping("/upload/{id}")
    public String listUploadedFiles(@ModelAttribute("user") @Valid UploadDto uploadDto, @PathVariable Long id, Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));
		System.out.println("1");

		uploadDto.setId(id);
		System.out.println("2");
        return "upload";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@ModelAttribute("user") @Valid UploadDto uploadDto, @RequestParam("file") MultipartFile file, 
            RedirectAttributes redirectAttributes, Model model) {
    	System.out.println("3");
    	Sessiontracker theSession = sessionService.findById(uploadDto.getId());
    	User theUser = userService.findByEmail(theSession.getEmail());
        userService.upload(theUser.getEmail(), file.getOriginalFilename());
    	System.out.println("file is " + theUser.getFileName());
        storageService.store(file);
        System.out.println("6");
        return "redirect:/uploaded/" + uploadDto.getId();
    }

    @GetMapping("/uploaded/{id}")
    public String listFiles(@ModelAttribute("user") @Valid UploadDto uploadDto, @PathVariable Long id, Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));
        Sessiontracker theSession = sessionService.findById(uploadDto.getId());
    	User theUser = userService.findByEmail(theSession.getEmail());
		model.addAttribute("fileName", "/pictures/" + theUser.getFileName());
        return "upload";
    }

    
    
}