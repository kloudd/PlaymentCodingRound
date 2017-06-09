import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//This class is a full tree structure of the complete directory we have in the systm
public class CompleteDirectory {

	public static final String ROOT = "/";
	public static final String DIR_PATTERN = "([a-zA-z0-9_.]+)";
	private Directory root;
	private Directory currentNode;

	public CompleteDirectory() {
		root = new Directory(ROOT);
		root.setChildren(new HashSet<Directory>());
		currentNode = root;
	}

	// Use to add directory to the Complete Directory tree if the path given is
	// absolute
	public boolean addChild(String path) {
		String pathLastButOneDir = path
				.substring(0, path.lastIndexOf(ROOT) + 1);
		String name = path.substring(path.lastIndexOf(ROOT) + 1, path.length());

		Directory node = find(pathLastButOneDir);
		if (node != null && !(name == null || name.length() == 0)) {
			Directory newNode = new Directory(name);
			Set<Directory> children = node.getChildren();
			if (children == null) {
				children = new HashSet<Directory>();
				node.setChildren(children);
			} else {
				for (Directory child : children) {
					if (child.getName().equals(name))
						return false;
				}
			}
			newNode.setParent(node);
			children.add(newNode);
			return true;
		}
		return false;
	}

	// Use to add directory to the Complete Directory tree if the path given is
	// relative
	public boolean addChildRelativeDirectory(String path) {
		String name;
		Directory node;
		if (path.contains(ROOT)) {
			String pathLastButOneDir = path.substring(0,
					path.lastIndexOf(ROOT) + 1);
			name = path.substring(path.lastIndexOf(ROOT) + 1, path.length());
			node = findRelative(currentNode, pathLastButOneDir);
			if (node == null) {
				return false;
			}
		} else {
			node = currentNode;
			name = path;
		}
		if (node != null) {
			Directory newNode = new Directory(name);
			Set<Directory> children = node.getChildren();
			if (children == null) {
				children = new HashSet<Directory>();
			} else {
				for (Directory child : children) {
					if (child.getName().equals(name))
						return false;
				}
			}
			newNode.setParent(node);
			children.add(newNode);
		}
		return true;
	}

	// boolean function for 'is given path traversable or not' using absolute
	// path
	public boolean traverse(String path) {
		Directory node = find(path);
		if (node != null) {
			currentNode = node;
			return true;
		}
		return false;
	}

	// boolean function for 'is given path traversable or not' using relative
	// path
	public boolean traverseRelative(String path) {
		Directory node = findRelative(currentNode, path);
		if (node != null) {
			currentNode = node;
			return true;
		}
		return false;
	}

	// Find the directory given in the absolute path
	private Directory find(String path) {

		if (path.equals("/"))
			return root;

		Directory node = root;

		Pattern reg = Pattern.compile(DIR_PATTERN);
		List<String> dirs = new ArrayList<String>();
		Matcher m = reg.matcher(path);
		while (m.find())
			dirs.add(m.group());

		int i = 0;
		while (i < dirs.size()) {
			Set<Directory> children = node.getChildren();
			boolean found = false;
			if (children != null && children.size() > 0) {
				for (Directory child : children) {
					if (child.getName().equals(dirs.get(i))) {
						found = true;
						node = child;
						break;
					}
				}
			}
			i++;
			if (!found) {
				node = null;
				break;
			}
		}
		return node;
	}

	// Find the directory given in the absolute path
	private Directory findRelative(Directory node, String path) {
		Directory currentNode = node;

		Pattern reg = Pattern.compile(DIR_PATTERN);
		List<String> dirs = new ArrayList<String>();
		Matcher m = reg.matcher(path);
		while (m.find())
			dirs.add(m.group());

		int i = 0;
		while (i < dirs.size()) {
			Set<Directory> children = currentNode.getChildren();
			boolean found = false;
			if (children != null) {
				for (Directory child : children) {
					if (child.getName().equals(dirs.get(i).trim())) {
						found = true;
						node = child;
						break;
					}
				}
			}
			i++;
			if (!found) {
				node = null;
				break;
			}
		}
		return node;
	}

	// Returns the current directory path used for 'pwd'
	public String getCurrentDirectoryPath() {
		Directory current = currentNode;
		List<String> parents = new ArrayList<String>();
		while (current != root) {
			parents.add(current.getName());
			current = current.getParent();
			if (current != root) {
				parents.add(ROOT);
			}
		}
		String path = "/";
		for (int i = parents.size() - 1; i >= 0; i--) {
			path += parents.get(i);
		}
		return path;
	}

	public boolean deleteDirectory(String path) {

		Directory node = find(path);
		if (node != null && node != root) {
			node.getParent().getChildren().remove(node);
			node.setChildren(null);
			node = null;
			currentNode = root;
			return true;
		}
		return false;
	}

	public boolean deleteDirectoryRelative(String path) {

		Directory node = findRelative(currentNode, path);
		if (node != null) {
			node.setChildren(null);
			return true;
		} else
			return false;
	}

	// getters and setters

	public Directory getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Directory currentNode) {
		this.currentNode = currentNode;
	}

	public boolean clear() {
		root.setChildren(null);
		currentNode = root;
		return true;
	}
}
