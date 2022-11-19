package library.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "roles")
public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long roleID; // The book's id number

	@Column(name = "Role", unique = true)
	private String roleName; // The book's isbn number

	public UserRole(String role) {
		setRoleName(role);
	}
	
	public String toString() {
		return this.getRoleName();
	}
}