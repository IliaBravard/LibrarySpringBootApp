package library.controllers; // The package where this controller class is located at

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// Including the needed imports
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// Allows access to the bean class
import library.beans.User;
import library.beans.UserRole;
import library.repositories.UserRepository;
import library.repositories.UserRoleRepository;
import library.services.UserService;

@Controller // Tells SpringBoot that this is the controller class
public class UserController {

	@Autowired
	private UserRepository repo; // Allows for the use of JPA persistence

	@Autowired
	private UserRoleRepository roleRepo;

	@Autowired
	private UserService service;

	/**
	 * This method redirects the user to the registration form.
	 * 
	 * @param model - the model interface
	 * @return the signup page
	 */
	@GetMapping("/register")
	public String viewRegistration(Model model) {
		List<UserRole> listOfRoles = roleRepo.findAll();
		model.addAttribute("listOfRoles", listOfRoles);
		model.addAttribute("user", new User());
		return "signup";
	}

	/**
	 * This method registers a new user and adds it to the database.
	 * 
	 * @param registeredUser - the user to be added
	 * @return the successful registration page
	 */
	@PostMapping("/processRegistration")
	public String processRegistration(User registeredUser, Model model) {

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

	@GetMapping("/remove/{id}")
	public String deleteBook(@PathVariable(name = "id") Long id) {
		service.delete(id);
		return "redirect:/list";
	}

}