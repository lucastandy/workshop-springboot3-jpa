package com.cursojava.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cursojava.course.entities.User;
import com.cursojava.course.repositories.UserRepository;
import com.cursojava.course.services.exceptions.DatabaseException;
import com.cursojava.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id){
		Optional<User> obj =  repository.findById(id);
		// O método orElseThrow() vai tentar trazer o id, caso ele exista. Se ele não existir, ele lançará um erro.
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	// Operação básica para inserir no banco de dados um novo objeto do tipo User
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	//Oparação para deletar um usuário
	public void delete(Long id) {
	
		try {
			if(!repository.existsById(id)) throw new ResourceNotFoundException(id);
	        repository.deleteById(id);
		}catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		
			
		
	}
	// Operação básica para atualizar os usuários
	public User update(Long id, User obj) {
		User entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	// Atualiazando os dados do entity com base no obj
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	
	}
	
}
