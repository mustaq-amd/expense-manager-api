package in.mustaq.expensetrackerapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.mustaq.expensetrackerapi.entity.JwtResponse;
import in.mustaq.expensetrackerapi.entity.LoginModel;
import in.mustaq.expensetrackerapi.entity.User;
import in.mustaq.expensetrackerapi.entity.UserModel;
import in.mustaq.expensetrackerapi.service.CustomUserDetialsService;
import in.mustaq.expensetrackerapi.service.UserService;
import in.mustaq.expensetrackerapi.util.JwtTokenUtil;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetialsService customUserDetialsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/*
	 * @PostMapping("/login") public ResponseEntity<HttpStatus> login(@RequestBody
	 * LoginModel loginModel) {
	 * 
	 * Authentication authentication = authenticationManager .authenticate(new
	 * UsernamePasswordAuthenticationToken(loginModel.getEmail(),
	 * loginModel.getPassword()));
	 * 
	 * SecurityContextHolder.getContext().setAuthentication(authentication);
	 * 
	 * return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	 * 
	 * }
	 */

	@PostMapping("/loginn")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginModel loginModel) throws Exception {

		authenticate(loginModel.getEmail(), loginModel.getPassword());

		// we need to generate Jwt token

		final UserDetails userDetails = customUserDetialsService.loadUserByUsername(loginModel.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);

		return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);

	}

	private void authenticate(String email, String password) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

		} catch (DisabledException e) {
			throw new Exception("User Disabled");
		} catch (BadCredentialsException e) {
			throw new Exception("Bad Credentials");
		}

	}

	@PostMapping("/register")
	public ResponseEntity<User> createUser(@Valid @RequestBody UserModel user) {

		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);

	}

}
