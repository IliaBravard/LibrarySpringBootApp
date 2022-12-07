package library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import library.beans.Book;
import library.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repo;

	public Page<Book> listAll(String keyword, int pageNumber, String sortField, String sortDir) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

		Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);
		if (keyword != null) {
			return repo.findByKeyword(keyword, pageable);
		}
		return repo.findAll(pageable);
	}

	public Book save(Book toAdd) {
		Book saved = repo.save(toAdd);
		return saved;
	}

	public Book get(Long id) {
		System.out.println(repo.findById(id).get());
		return repo.findById(id).get();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}
