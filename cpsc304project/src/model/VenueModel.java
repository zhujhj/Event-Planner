package model;

public class VenueModel {
    private final String name;
    private final String address;
	private final int capacity;
	private final int id;
	
	public VenueModel(String name, String address, int capacity, int id) {
        this.name = name;
        this.address = address;
		this.capacity = capacity;
        this.id = id;
	}

    public String getName() {
		return name;
	}

    public String getAddress() {
        return address;
    }

    public int getCapacity() {
		return capacity;
	}

	public int getId() {
		return id;
	}
}
