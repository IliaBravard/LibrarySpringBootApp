package library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import library.beans.UserRole;
import library.repositories.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class GenreRepositoryTest {

	@Autowired
	UserRepository repo;

	@Autowired
	private TestEntityManager manager;

	@Test
	public void testCreateRole() {
		// ARRANGE
		UserRole roleIT = new UserRole("IT Specialist");
		UserRole roleManager = new UserRole("Library Manager");
		UserRole roleK12Student = new UserRole("K-12 Student");
		UserRole roleCollegeStudent = new UserRole("College Student");
		UserRole roleLibrarian = new UserRole("Librarian");
		UserRole roleVisitor = new UserRole("Visitor");

		// ACT
		manager.persist(roleIT);
		manager.persist(roleManager);
		manager.persist(roleK12Student);
		manager.persist(roleCollegeStudent);
		manager.persist(roleLibrarian);
		manager.persist(roleVisitor);
	}

}