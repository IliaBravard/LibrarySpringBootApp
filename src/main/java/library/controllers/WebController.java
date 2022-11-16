package library.controllers; // The package where this controller class is located at

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// Including the needed imports
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// Allows access to the bean class
import library.beans.User;
import library.repositories.UserRepository;

@Controller // Tells SpringBoot that this is the controller class
public class WebController {

	@Autowired
	private UserRepository repo; // Allows for the use of JPA persistence

	/**
	 * This method redirects the user to the home page.
	 * 
	 * @return the index page
	 */
	@GetMapping("")
	public String viewHome() {
		return "index";
	}

	/**
	 * This method redirects the user to the registration form.
	 * 
	 * @param model - the model interface
	 * @return the signup page
	 */
	@GetMapping("/register")
	public String viewRegistration(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	/**
	 * This method redicrects the user to the login page.
	 * 
	 * @return the sign in page
	 */
	@GetMapping("/login")
	public String viewSystem() {
		return "signin";
	}

	/**
	 * This method registers a new user and adds it to the database.
	 * 
	 * @param registeredUser - the user to be added
	 * @return the successful registration page
	 */
	@PostMapping("/processRegistration")
	public String processRegistration(User registeredUser) {

		// Encrypting the user's password in the local database
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(registeredUser.getPassword());
		registeredUser.setPassword(encodedPassword);
		repo.save(registeredUser);
		return "success";
	}

	/**
	 * This method redirects the user to the user's list.
	 * 
	 * @param model - the model interface
	 * @return the user's list page
	 */
	@GetMapping("/list")
	public String viewUsers(Model model) {
		List<User> listOfUsers = repo.findAll();
		model.addAttribute("listOfUsers", listOfUsers);
		return "users";
	}
}