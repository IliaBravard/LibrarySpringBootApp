package library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import library.beans.Genre;


public interface GenreRepository extends JpaRepository<Genre, Long> {

}