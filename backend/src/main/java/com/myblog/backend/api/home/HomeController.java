package com.myblog.backend.api.home;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeController {

	@GetMapping("/home")
	public ResponseEntity<String> home() {
		return ResponseEntity.ok("Ok!");
	}
}
