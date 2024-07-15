package myProject;

public class customer {
	private int id;
	private String fullname;
	private String dob;
	private String email;
	private int phone;
	private String address;

	public customer(int id, String fullname, String dob, int phone, String email, String address) {

		this.id = id;
		this.fullname = fullname;
		this.dob = dob;
		this.phone = phone;
		this.email = email;
		this.address = address;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

}
