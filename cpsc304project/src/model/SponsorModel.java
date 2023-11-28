package model;

public class SponsorModel {
    private final String name;
    private final int amount;
	private final String request;
	private final int phoneNumber;
    private final String email;
	
	public SponsorModel(String name, int amount, String request, int phoneNumber, String email) {
		this.name = name;
        this.amount = amount;
		this.request = request;
        this.phoneNumber = phoneNumber;
        this.email = email;
	}

    public String getName() {
		return name;
	}

    public int getAmount() {
		return amount;
	}

	public String getRequest() {
		return request;
	}

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
