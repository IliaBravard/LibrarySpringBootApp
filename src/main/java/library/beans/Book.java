package library.beans; // The package where this bean class is located at

// Including the needed imports
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long bookID; // The book's id number

	@Column(name = "ISBN", unique = true)
	private String isbn; // The book's isbn number

	@Column(name = "Title")
	private String title; // The book's title

	@Column(name = "Author")
	private String author; // The book's author

	@Column(name = "book_cover")
	private String bookCover;

	@Column(name = "num_of_pages")
	private String numOfPages;

	@ManyToOne
	@JoinColumn(name = "genreID")
	private Genre genre;

	/**
	 * This is the nondefault constructor that sets the fields of this class.
	 * 
	 * @param number - the book's international number
	 * @param name   - the book's title
	 * @param writer - the book's author
	 */
	public Book(String number, String name, String writer) {
		setIsbn(number);
		setTitle(name);
		setAuthor(writer);
	}

	@Transient
	public String getBookCoverPath() {
		if (bookCover == null) {
			return null;
		}
		return "/src/main/resources/static/thumbnails/" + bookID + "/" + bookCover;
	}
}