package links.model;

import com.eriklievaart.toolkit.lang.api.check.Check;

public class Link implements Comparable<Link> {
	private static final String UNKNOWN_CATEGORY = "unknown";

	private final String name;
	private final String url;
	private String category;
	private String password = "";
	private String user;

	public Link(String name, String url) {
		this(name, url, UNKNOWN_CATEGORY);
	}

	public Link(String name, String url, String category) {
		Check.noneNull(name, url, category);
		this.name = name;
		this.url = url;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getCategory() {
		return category;
	}

	void setCategory(String value) {
		this.category = value;
	}

	public static boolean isValidCategory(String category) {
		return category != null && category.toLowerCase().matches("[a-z 0-9]++");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		Check.notNull(password);
		this.password = password;
	}

	public void setUser(String user) {
		Check.notNull(user);
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	@Override
	public int compareTo(Link o) {
		return name.compareTo(o.getName());
	}

	@Override
	public String toString() {
		return name;
	}
}
