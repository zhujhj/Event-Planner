package model;

public class FoodVendorModel {
    private final String name;
	private final int alcoholLicenseNumber;
	private final String style;
	
	public FoodVendorModel(String name, int alcoholLicenseNumber, String style) {
        this.name = name;
        this.alcoholLicenseNumber = alcoholLicenseNumber;
		this.style = style;
	}

    public String getName() {
		return name;
	}

    public int getAlcoholLicenseNumber() {
		return alcoholLicenseNumber;
	}

	public String getStyle() {
        return style;
    }
}
