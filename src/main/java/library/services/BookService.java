package library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.beans.Book;
import library.repositories.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository repo;

	public List<Book> listAll(String keyword) {
		if (keyword != null) {
			return repo.findByKeyword(keyword);
		}
		return repo.findAll();
	}

	public Book save(Book toAdd) {
		Book saved = repo.save(toAdd);
		return saved;
	}

	public Book get(Long id) {
		return repo.findById(id).get();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}
