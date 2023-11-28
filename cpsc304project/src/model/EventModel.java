package model;

import javax.print.attribute.standard.DateTimeAtCompleted;

public class EventModel {
    private final String name;
	private final String description;
	private final int id;
    private final DateTimeAtCompleted time;
	
	public EventModel(String name, String description, int id, DateTimeAtCompleted time) {
		this.name = name;
		this.description = description;
        this.id = id;
        this.time = time;
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

    public DateTimeAtCompleted getTime() {
        return time;
    }
}
