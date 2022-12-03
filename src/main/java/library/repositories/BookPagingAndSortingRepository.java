package library.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import library.beans.Book;

public interface BookPagingAndSortingRepository extends PagingAndSortingRepository<Book, Long> {

}
