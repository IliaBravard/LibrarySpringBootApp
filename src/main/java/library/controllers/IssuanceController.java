package library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import library.beans.Book;
import library.beans.Issuance;
import library.beans.User;
import library.repositories.BookRepository;
import library.repositories.UserRepository;
import library.services.IssuanceService;

@Controller
public class IssuanceController {

	@Autowired
	private IssuanceService service;

	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/addIssuances")
	public String addUser(Model model) {
		List<Book> books = bookRepo.findByReturnStatus();
		List<User> users = userRepo.findAll();
		model.addAttribute("bookList", books);
		model.addAttribute("userList", users);
		model.addAttribute("issuance", new Issuance());
		return "addIssuanceRecord";
	}

	@PostMapping("/processIssuanceAddition")
	public String processIssuanceAddition(Issuance toAdd, Book toIssue) {
		toIssue.setReturnStatus(true);
		service.save(toAdd);
		return "index";
	}
}