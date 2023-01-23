package com.lcwd.electronic.store.controllers;

import com.lcwd.electronic.store.dtos.JwtRequest;
import com.lcwd.electronic.store.dtos.JwtResponse;
import com.lcwd.electronic.store.dtos.UserDto;
import com.lcwd.electronic.store.exceptions.BadApiRequest;
import com.lcwd.electronic.store.security.JwtHelper;
import com.lcwd.electronic.store.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private ModelMapper mapper;



    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request)
    {
        this.doAuthenticate(request.getEmail(),request.getPassword());

        UserDetails userDetails=userDetailsService.loadUserByUsername(request.getEmail());

        UserDto userDto=mapper.map(userDetails,UserDto.class);

        String token = this.jwtHelper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder().jwtToken(token).user(userDto).build();

        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(email,password);

        try {
            authenticationManager.authenticate(authenticationToken);

        }catch (BadCredentialsException e)
        {
            throw new BadApiRequest(" Invalid username or password !!");
        }

    }


    @GetMapping("/current")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal)
    {
        String name = principal.getName();
        return new ResponseEntity<>(mapper.map(userDetailsService.loadUserByUsername(name),UserDto.class), HttpStatus.OK);
    }



}
