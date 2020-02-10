package com.spring.security.service;

import com.spring.security.model.Role;
import com.spring.security.model.Sessionid;
import com.spring.security.model.Sessiontracker;
import com.spring.security.model.User;
import com.spring.security.repository.SessionRepository;
import com.spring.security.repository.UserRepository;
import com.spring.security.web.dto.UpdateDto;
import com.spring.security.web.dto.UserRegistrationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private SessionService sessionService;
    
    @Autowired
    private IdService idService;
      
	public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

	public User save(UserRegistrationDto registration){
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setAge(registration.getAge());
        user.setBreed(registration.getBreed());
        user.setColor(registration.getColor());
        user.setName(registration.getName());
        user.setZipCode(registration.getZipCode());
        user.setWeight(registration.getWeight());
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        idService.increment(email);
        System.out.println("loading user");
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User update(UpdateDto userdto) {
		User user = userRepository.findById(userdto.getId());
        user.setFirstName(userdto.getFirstName());
        user.setLastName(userdto.getLastName());
        user.setEmail(userdto.getEmail());
        user.setPassword(passwordEncoder.encode(userdto.getPassword()));
        user.setAge(userdto.getAge());
        user.setBreed(userdto.getBreed());
        user.setColor(userdto.getColor());
        user.setName(userdto.getName());
        user.setZipCode(userdto.getZipCode());
        user.setWeight(userdto.getWeight());
        user.setFileName(userdto.getFileName());
        return userRepository.save(user);
	}
	
	@Override
	public User upload(String email, String fileName) {
		User user = userRepository.findByEmail(email);
		user.setFileName(fileName);
		System.out.println("saving filename");
		return userRepository.save(user);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id);
	}
}
