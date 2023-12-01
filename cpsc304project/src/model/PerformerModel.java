package model;

public class PerformerModel {
    private final String agent;
    private final String name;
	private final int phoneNumber;
    private final String email;
	
	public PerformerModel(String agent, String name, int phoneNumber, String email) {
        this.agent = agent;
		this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
	}

    public String getAgent() {
		return agent;
	}

    public String getName() {
		return name;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

    public String getEmail() {
        return email;
    }
}
