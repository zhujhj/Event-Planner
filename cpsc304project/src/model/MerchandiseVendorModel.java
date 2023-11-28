package model;

public class MerchandiseVendorModel {
    private final String name;
	private final String brandsCarried;
	
	public MerchandiseVendorModel(String name,  String brandsCarried) {
        this.name = name;
		this.brandsCarried = brandsCarried;
	}

    public String getName() {
		return name;
	}

	public String getBrandsCarried() {
        return brandsCarried;
    }
}
