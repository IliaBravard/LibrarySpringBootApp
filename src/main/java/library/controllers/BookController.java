package library.controllers; // The package where this controller class is located at

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import library.beans.Book;
import library.beans.Genre;
import library.repositories.GenreRepository;
import library.services.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService service;
	
	@Autowired
	private GenreRepository repo;

	@GetMapping("/addBooks")
	public String viewAddBookPage(Model model) {
		List<Genre> genres = repo.findAll();
		model.addAttribute("book", new Book());
		model.addAttribute("genres", genres);
		return "addBookRecord";
	}

	@PostMapping("/processBookAddition")
	public String processBookAddition(Book toAdd) {
		toAdd.setReturnStatus(false);
		service.save(toAdd);
		return "index";
	}

	/**
	 * This method redirects the user to the user's list.
	 * 
	 * @param model - the model interface
	 * @return the user's list page
	 */
	@GetMapping("/bookList")
	public String viewBooks(Model model, @Param("keyword") String keyword) {
		List<Book> listOfBooks = service.listAll(keyword);
		model.addAttribute("listOfBooks", listOfBooks);
		return "books";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView showEditPage(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("editBook");

		Book toEdit = service.get(id);
		mav.addObject("book", toEdit);
		return mav;
	}

	@PostMapping("/saveBook")
	public String saveBook(Model model, @ModelAttribute("toAdd") Book toAdd, String keyword) {
		service.save(toAdd);
		return "redirect:/bookList";
	}

	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable(name = "id") Long id) {
		service.delete(id);
		return "redirect:/bookList";
	}
}