package objects;

public class Config implements Comparable<Config> {
	
	 String name;
	 boolean active;
	 Integer order;
	 boolean descending;
	 
	public Config(String name, boolean active, Integer order, boolean descending) {
		this.name = name;
		this.active = active;
		this.order = order;
		this.descending = descending;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public boolean getDescending() {
		return descending;
	}
	public void setDescending(boolean descending) {
		this.descending = descending;
	}

	@Override
	public int compareTo(Config config) {
		if (this.order < config.order) {
            return -1;
        }
        if (this.order > config.order) {
            return 1;
        }
        return 0;
	}
}
