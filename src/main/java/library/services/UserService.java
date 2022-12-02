package library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.beans.User;
import library.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public User save(User toAdd) {
		User saved = repo.save(toAdd);
		return saved;
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public User get(Long id) {
		return repo.findById(id).get();
	}
}
