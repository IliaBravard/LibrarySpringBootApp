package library; // The package where this test class is located at

// Including the needed imports
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import library.beans.Book;
import library.repositories.BookRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class BookRepositoryTest {

	@Autowired
	BookRepository repo;

	@Autowired
	private TestEntityManager manager;

	@Test
	public void testCreateBook() {

		// ARRANGE
		Book toTest = new Book("978-0-230-10952-0", "The Famine Plot", "Tim Pat Coogan");

		// ACT
		Book saved = repo.save(toTest);
		Book existing = manager.find(Book.class, saved.getBookID());

		// ASSERT
		assertThat(existing.getAuthor()).isEqualTo(toTest.getAuthor()); // Comparing the results and testing
	}
}