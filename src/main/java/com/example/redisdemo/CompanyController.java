package com.example.redisdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompanyController {
	
	private final CompanyService service;
	
	@GetMapping("/{id}")
	ResponseEntity<Company> get(@PathVariable final Long id){
		return ResponseEntity.ok(service.findOne(id));
	}
	
	@GetMapping
	ResponseEntity<List<Company>> get(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@PostMapping
	ResponseEntity<Company> post(@RequestBody Company company){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(company));
	}
}
