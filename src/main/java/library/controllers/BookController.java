package library.controllers; // The package where this controller class is located at

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@GetMapping("/index")
	public String showHomePage() {
		return "index";
	}

	@GetMapping("")
	public String viewLoginPage() {
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String defaultPageToShow() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		return "index";
	}

	@GetMapping("/loginSuccessful")
	public String toDelete() {
		return "index";
	}

	@GetMapping("/addBooks")
	public String viewAddBookPage(Model model) {
		List<Genre> genres = repo.findAll();
		model.addAttribute("book", new Book());
		model.addAttribute("genres", genres);
		return "addBookRecord";
	}

	@PostMapping("/processBookAddition")
	public String processBookAddition(@ModelAttribute("book") Book toAdd,
			@RequestParam("fileImage") MultipartFile multipartFile, RedirectAttributes ra) throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		toAdd.setBookCover(fileName);
		Book savedBook = service.save(toAdd);

		String uploadDirectory = "./src/main/resources/static/thumbnails/" + savedBook.getBookID();
		Path uploadPath = Paths.get(uploadDirectory);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			System.out.println(filePath.toFile().getAbsolutePath()); /* Diagnostic */

			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("The following file could not be saved: " + fileName);
		}

		ra.addFlashAttribute("message", "The book was saved successfully");

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
		return displayNextPage(model, keyword, 1, "title", "asc");
	}

	@GetMapping("/page/{pageNumber}")
	public String displayNextPage(Model model, @Param("keyword") String keyword,
			@PathVariable("pageNumber") int currentPage, @Param("sortField") String sortField,
			@Param("sortDir") String sortDir) {
		
		Page<Book> page = service.listAll(keyword, currentPage, sortField, sortDir);

		long totalElements = page.getTotalElements(); // The total number of book records
		int totalPages = page.getTotalPages(); // The total number of pages for the book records
		List<Book> listOfBooks = page.getContent();

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalElements", totalElements);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("listOfBooks", listOfBooks);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);
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