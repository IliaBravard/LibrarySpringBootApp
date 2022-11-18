package library.beans; // The package where this bean is located at

// Including the needed imports
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// Including the imports for Lombok
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Auto-generating the boilerplate code for this bean
@NoArgsConstructor // Telling Lombok to generate the default, no argument constructor
@Entity
@Table(name = "users")

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long userID; // The user's ID number (the primary key for this bean/entity)

	@Column(name = "First_Name", nullable = false)
	private String firstName; // The user's first name

	@Column(name = "Last_Name", nullable = false)
	private String lastName; // The user's last name

	@Column(name = "Username", nullable = false, unique = true)
	private String emailAddress;

	@Column(name = "Password", nullable = false)
	private String password; // The user's login password

	@Column(name = "Phone_Number", nullable = false)
	private String phone; // The user's phone number

	/**
	 * This is the nondefault constructor that auto-generates the ID for each user
	 * and sets the rest of his/her attributes.
	 * 
	 * @param fName    - the user's first name
	 * @param lName    - the user's last name
	 * @param username - the user's username (email)
	 * @param password - the user's password
	 * @param phone    - the user's phone number
	 */
	public User(String fName, String lName, String username, String password, String phone) {
		setFirstName(fName);
		setLastName(lName);
		setEmailAddress(username);
		setPassword(password);
		setPhone(phone);
	}
}