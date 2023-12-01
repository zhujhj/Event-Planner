package model;

public class GuestModel {
    private final String name;
	private final String email;
    private final int ticketNumber;
    private final int phoneNumber;
	
	public GuestModel(String name, String email, int ticketNumber, int phoneNumber) {
        this.name = name;
        this.email = email;
		this.ticketNumber = ticketNumber;
        this.phoneNumber = phoneNumber;
	}

    public String getName() {
		return name;
	}

	public String getEmail() {
        return email;
    }

    public int getTicketNumber() {
		return ticketNumber;
	}

    public int getPhoneNumber() {
		return phoneNumber;
	}
}
