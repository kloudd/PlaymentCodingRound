import java.util.Set;

public class Directory {
	private String name;
	private Set<Integer> data;
	private Directory parent;
	private Set<Directory> children;

	public Directory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Integer> getData() {
		return data;
	}

	public void setData(Set<Integer> data) {
		this.data = data;
	}

	public Directory getParent() {
		return parent;
	}

	public void setParent(Directory parent) {
		this.parent = parent;
	}

	public Set<Directory> getChildren() {
		return children;
	}

	public void setChildren(Set<Directory> children) {
		this.children = children;
	}

}