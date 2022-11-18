package library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import library.beans.Issuance;

public interface IssuanceRepository extends JpaRepository<Issuance, Long> {

}
