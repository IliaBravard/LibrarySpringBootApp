package library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import library.beans.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("SELECT b FROM Book b WHERE CONCAT(b.isbn, ' ', b.title, ' ', b.author) LIKE %?1%")
	List<Book> findByKeyword(String keyword);
	
	@Query("SELECT b FROM Book b WHERE b.returnStatus = 0")
	List<Book> findByReturnStatus();
}
