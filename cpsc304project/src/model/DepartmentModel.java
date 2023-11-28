package model;

public class DepartmentModel {
    private final String name;
	private final String description;
	private final int id;
	
	public DepartmentModel(String name, String description, int id) {
		this.name = name;
		this.description = description;
        this.id = id;
	}

    public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}
}
