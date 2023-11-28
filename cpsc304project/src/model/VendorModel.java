package model;

public class VendorModel {
    private final String name;
    private final String location;
	private final String hours;
	
	public VendorModel(String name, String location, String hours) {
        this.name = name;
        this.location = location;
		this.hours = hours;
    }

    public String getName() {
		return name;
	}

    public String getLocation() {
        return location;
    }

    public String getHours() {
		return hours;
	}
}
