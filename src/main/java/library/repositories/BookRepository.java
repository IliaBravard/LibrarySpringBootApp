package library.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import library.beans.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("SELECT b FROM Book b WHERE CONCAT(b.isbn, ' ', b.title, ' ', b.author) LIKE %?1%")
	Page<Book> findByKeyword(String keyword, Pageable pageable);
}
