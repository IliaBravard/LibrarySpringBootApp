package library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.beans.Issuance;
import library.repositories.IssuanceRepository;

@Service
public class IssuanceService {
	
	@Autowired
	private IssuanceRepository repo;
	
	public void save(Issuance toAdd) {
		repo.save(toAdd);
	}
}
