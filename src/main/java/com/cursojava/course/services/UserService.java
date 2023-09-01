package com.cursojava.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursojava.course.entities.User;
import com.cursojava.course.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id){
		Optional<User> obj =  repository.findById(id);
		return obj.get();
	}
	
	// Operação básica para inserir no banco de dados um novo objeto do tipo User
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	//Oparação para deletar um usuário
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	

}
