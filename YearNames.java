package project4;

/**
 * This class represents all of the names given to people in a single year,
 * stored in a binary search tree. It contains information about the year and
 * how many names there were in that year.
 * 
 * @author Thomas Guo
 * 
 * @throws IllegalArgumentException
 *             If a Name object is added to the BST that already contains the
 *             Name object or if a name object the user wants to get a count of
 *             is invalid.
 * @throws NullPointerException
 *             If a null Name object is passed to the add or
 *             containsIgnoreCount,
 */

public class YearNames extends MyBST<Name> {
	private int year;
	private int totalBabies;

	/**
	 * Constructor for the YearNames object. Sets the year data field equal to
	 * the year of the file.
	 */
	public YearNames(int year) {
		this.year = year;
	}

	/**
	 * First checks to see if the BST already contains the Name object. If it
	 * does, then an exception is thrown. If it doesn't, then the Name object is
	 * added to the BST using the add method in the MyBST super class.
	 * 
	 * @param name
	 *            name to be added to the MyArrayList
	 * 
	 * @return true if the name object was added and false it was not
	 * @throws IllegalArgumentException
	 *             if the BST already contains the Name object.
	 * 
	 * @throws NullPointerException
	 *             if the passed Name object is null
	 */

	public boolean add(Name name) throws IllegalArgumentException, NullPointerException {
		if (name == null) {
			throw new NullPointerException();
		}
		if (this.containsIgnoreCount(name))
			throw new IllegalArgumentException();
		else {
			totalBabies += name.getCount();
			return super.add(name);
		}
	}

	/**
	 * Checks to see if a Name objects already exists in the BST. Only compares
	 * name and gender of the Name object, ignores the count.
	 * 
	 * @param name
	 *            name object that is checked for existence in the BST
	 * 
	 * @return true if the BST contains the name ignoring count and false if it
	 *         doesn't.
	 * @throws NullPointerException
	 *             if the Name object passed is null.
	 */
	public boolean containsIgnoreCount(Name name) throws NullPointerException {
		if (name == null) {
			throw new NullPointerException();
		}

		// start comparing from the root
		Node<Name> current = root;
		while (current != null) {
			// if the name object equals a name object in the tree, returns true
			if (name.equalsIgnoreCount(current.getData())) {
				return true;
			}
			// if the name object is less than the current node, move onto the
			// left side of the tree
			else if (name.compareTo(current.getData()) < 0) {
				current = current.getLeft();
			}
			// otherwise move onto the right side of the tree
			else if (name.compareTo(current.getData()) > 0) {
				current = current.getRight();
			}
		}
		// returns false if no matching name object was found
		return false;
	}

	/**
	 * Finds how many babies were born with a given name, separating male and
	 * female genders of the same name.
	 * 
	 * @param name
	 *            name of the Name object to be counted
	 * @param gender
	 *            gender of the Name object to be counted
	 * @return int containing how many babies were born with a given name.
	 * @throws IllegalArgumentException
	 *             if name or gender parameters are invalid.
	 */
	public int getCountByName(String name, String gender) throws IllegalArgumentException {
		if (name == null) {
			throw new IllegalArgumentException("name is null");
		}
		if (gender == null) {
			throw new IllegalArgumentException("gender is null");
		}
		if (!(gender.equals("F") || gender.equals("f") || gender.equals("M") || gender.equals("m"))) {
			throw new IllegalArgumentException("gender is invalid");
		}

		// sets the name to lower case so it matches data field of Name object.
		name = name.toLowerCase();
		// creates a name object with the given name and gender and a count of
		// 0, which is ignored
		Name nameO = new Name(name, gender, 0);
		int counter = 0;

		Node<Name> current = root;
		while (current != null) {
			// if the name object equals a name object in the tree, returns that
			// object's count
			if (nameO.equalsIgnoreCount(current.getData())) {
				return current.getData().getCount();
			}
			// if the name object is less than the current node, move onto the
			// left side of the tree
			else if (nameO.compareTo(current.getData()) < 0) {
				current = current.getLeft();
			}
			// otherwise move onto the right side of the tree
			else {
				current = current.getRight();
			}
		}
		return counter;
	}

	/**
	 * Finds the percentage of babies that were born with a given name,
	 * separating male and female genders of the same name.
	 * 
	 * @param name
	 *            name of the Name object to be counted
	 * @param gender
	 *            gender of the Name object to be counted
	 * @return double containing the percentage of babies that were born with a
	 *         given name.
	 * @throws IllegalArgumentException
	 *             if name or gender parameters are invalid.
	 */
	public double getFractionByName(String name, String gender) throws IllegalArgumentException {
		int countName = getCountByName(name, gender);

		if (totalBabies == 0) {
			throw new IllegalArgumentException("total babies is 0");
		}

		return 100.0 * countName / totalBabies;

	}

	/**
	 * Converts YearNames object to a String
	 * 
	 * @return String containing information about the YearNames object's year
	 *         and total number of babies.
	 */
	@Override
	public String toString() {
		return "Year: " + year + ", Total babies: " + totalBabies;
	}
}
