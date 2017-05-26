package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {

	@Id

	@GeneratedValue
	private Long id;

	private String firstName;

	private String lastName;

	private String winGames;

	private String  allGames;

	private String  commonScore;



	protected Customer() {
	}

//	public Customer(String firstName, String lastName, String finScore) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.finScore = finScore;
//	}

	public Customer(String firstName, String lastName, String winGames, String allGames, String commonScore) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.winGames = winGames;
		this.allGames = allGames;
		this.commonScore = commonScore;
	}



	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getWinGames() {
		return winGames;
	}

	public void setWinGames(String winGames) {
		this.winGames = winGames;
	}

	public String getAllGames() {
		return allGames;
	}

	public void setAllGames(String allGames) {
		this.allGames = allGames;
	}

	public String getCommonScore() {
		return commonScore;
	}

	public void setCommonScore(String commonScore) {
		this.commonScore = commonScore;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", winGames='" + winGames + '\'' +
				", allGames='" + allGames + '\'' +
				", commonScore='" + commonScore + '\'' +
				'}';
	}
//	public String getFinScore() {
//		return finScore;
//	}
//
//	public void setFinScore(String finScore) {
//		this.finScore = finScore;
//	}

	//	@Override
//	public String toString() {
//		return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id,
//				firstName, lastName);
//	}

}
