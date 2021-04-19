package com.example.redisdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
	
	final CompanyRepository repository;

	Company save(final Company company){
		return repository.save(company);
	}
	
	@Cacheable(cacheNames = "Company")
	public Company findOne(final Long id){
		return repository.findById(id)
				.orElseThrow(EntityNotFoundException::new);
	}
	
	List<Company> findAll(){
		return repository.findAll();
	}
}

