package library; // The package where this test class is located at

// Including the needed imports
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import library.beans.Issuance;
import library.repositories.IssuanceRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class IssuanceRepositoryTest {

	@Autowired
	IssuanceRepository repo;

	@Autowired
	private TestEntityManager manager;

	@Test
	public void testCreateBook() {

		// ARRANGE
		Issuance toTest = new Issuance(new Date(11-17-2022), 25.36, new Date(12-31-2022));

		// ACT
		Issuance saved = repo.save(toTest);
		Issuance existing = manager.find(Issuance.class, saved.getIssuanceID());

		// ASSERT
		assertThat(existing.getIssuanceID()).isEqualTo(toTest.getIssuanceID()); // Comparing the results and testing
	}
}