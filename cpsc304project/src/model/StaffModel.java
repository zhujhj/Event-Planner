package model;

public class StaffModel {
    private final String role;
    private final String email;
    private final String name;
	private final int phoneNumber;
	private final int id;
	
	public StaffModel(String role, String email, String name, int phoneNumber, int id) {
        this.role = role;
        this.email = email;
		this.name = name;
		this.phoneNumber = phoneNumber;
        this.id = id;
	}

    public String getRole() {
		return role;
	}

    public String getEmail() {
		return email;
	}

    public String getName() {
		return name;
	}

    public int getPhoneNumber() {
		return phoneNumber;
	}

	public int getId() {
		return id;
	}
}
