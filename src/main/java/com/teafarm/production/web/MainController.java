package com.teafarm.production.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teafarm.production.util.JwtUtil;
import com.teafarm.production.web.dto.AuthRequest;
import com.teafarm.production.web.dto.ErrorResponse;
import com.teafarm.production.web.dto.JwtToken;

@RestController
@CrossOrigin("*")
public class MainController {
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
		}catch (BadCredentialsException ex) {
	        return ResponseEntity
	                .status(HttpStatus.UNAUTHORIZED) // 401
	                .body(new ErrorResponse("BAD_CREDENTIALS", "Invalid username or password"));
	    }
	    catch (AccountExpiredException ex) {
	        return ResponseEntity
	                .status(HttpStatus.LOCKED) // 423
	                .body(new ErrorResponse("ACCOUNT_EXPIRED", "User account has expired"));
	    }
	    catch (DisabledException ex) {
	        return ResponseEntity
	                .status(HttpStatus.FORBIDDEN) // 403
	                .body(new ErrorResponse("ACCOUNT_DISABLED", "User account is disabled"));
	    }
	    catch (LockedException ex) {
	        return ResponseEntity
	                .status(HttpStatus.LOCKED) // 423
	                .body(new ErrorResponse("ACCOUNT_LOCKED", "User account is locked"));
	    }
	    catch (Exception ex) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
	                .body(new ErrorResponse("AUTH_ERROR", "Authentication failed"));
	    }
		String token= jwtUtil.generateToken(authRequest.getUsername());
		return ResponseEntity.ok(new JwtToken(token));
	}
}
