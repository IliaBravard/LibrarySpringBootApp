package library.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "issuances")
public class Issuance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long issuanceID; // The book issuance ID

	@Column(name = "Date_Of_Issuance")
	private Date dateIssued;

	@Column(name = "Fees")
	private double lateFee;

	@Column(name = "Date_Of_Return")
	private Date dateReturned;

	@ManyToOne
	@JoinColumn(name = "bookID")
	private Book book;

	@OneToOne
	@JoinColumn(name = "userID")
	private User user;

	public Issuance(Date issuanceDate, double fee, Date returnDate) {
		setDateIssued(issuanceDate);
		setLateFee(fee);
		setDateReturned(returnDate);
	}
}