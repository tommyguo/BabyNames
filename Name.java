package project4;

/**
 * This class represents the name in a given year that contains information
 * about the name, gender, and the number of people that were given that name in
 * a given year.
 * 
 * @author Thomas Guo
 * 
 * @throws IllegalArgumentException
 *             If the Name object is attempted to be constructed using invalid
 *             data.
 */
public class Name implements Comparable<Name> {
	private String name;
	private String gender;
	private int count;

	/**
	 * Constructor for the Name object.
	 * 
	 * @param name
	 *            name that the object represents
	 * @param gender
	 *            gender of the name
	 * @param count
	 *            number of babies born with that name in a given year
	 * @throws IllegalArgumentException
	 *             if the name is an empty string, the gender is not m or f, and
	 *             the count is negative.
	 */
	public Name(String name, String gender, int count) throws IllegalArgumentException {
		// checks for various Illegal ArgumentExceptions
		if (name == "")
			throw new IllegalArgumentException("Name has an empty string");
		else if (!(gender.equals("M")) && !(gender.equals("F")) && !(gender.equals("m")) && !(gender.equals("f")))
			throw new IllegalArgumentException("Name has an invalid gender indicator");
		else if (count < 0)
			throw new IllegalArgumentException("Name has an invalid count");
		else {
			// sets all strings to lower case to make program case insensitive.
			this.name = name.toLowerCase();
			this.gender = gender.toLowerCase();
			this.count = count;
		}
	}

	/**
	 * Compares two name objects using name as the primary key, gender as the
	 * secondary key, and count as the ternary key.
	 * 
	 * @param otherName
	 *            name that is compared to the Name object that the method is
	 *            called on.
	 * 
	 * @return 1 if the Name object on which the method is called is greater, -1
	 *         if it is less, and 0 if the two are equal.
	 */
	@Override
	public int compareTo(Name otherName) {
		if (name.compareTo(otherName.name) > 0) {
			return 1;
		} else if (name.compareTo(otherName.name) < 0) {
			return -1;
		}
		/*
		 * if the previous to if statements are not true, then the two names are
		 * the same and we need to compare using gender
		 */

		else {
			if ((gender.compareTo(otherName.gender) > 0)) {
				return 1;
			} else if (gender.compareTo(otherName.gender) < 0) {
				return -1;
			}
			/*
			 * if the previous to if statements are not true, then the two
			 * genders are the same and we need to compare using count
			 */
			else {
				if (count > otherName.count) {
					return 1;
				} else if (count < otherName.count) {
					return -1;
				} else
					return 0;
			}
		}
	}

	/**
	 * Assumes that the objects are of type Name. Compares two Name objects to
	 * see if they are equal.
	 * 
	 * @param otherName
	 *            name to be compared to the name object on which the method was
	 *            called to see if they are equal.
	 * 
	 * @return true if the names are equal with matching name, count, and gender
	 *         data fields. false otherwise.
	 * 
	 */
	@Override
	public boolean equals(Object otherName) {
		if (this == otherName)
			return true;
		if (otherName == null)
			return false;
		if (getClass() != otherName.getClass())
			return false;
		Name other = (Name) otherName;
		if (count != other.count)
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Assumes that the objects are of type Name. Compares two Name objects to
	 * see if they are equal. Only takes into account name and gender data
	 * fields, ignores count.
	 * 
	 * @param otherName
	 *            name to be compared to the name object on which the method was
	 *            called to see if they are equal.
	 * 
	 * @return true if the names are equal with matching name and gender data
	 *         fields. false otherwise.
	 * 
	 */
	public boolean equalsIgnoreCount(Object otherName) {
		if (this == otherName)
			return true;
		if (otherName == null)
			return false;
		if (getClass() != otherName.getClass())
			return false;
		Name other = (Name) otherName;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Getter for the name data field.
	 * 
	 * @return String containing name data field of the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the count data field.
	 * 
	 * @return int containing the count data field of the name.
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Converts Name object to a String.
	 * 
	 * @return String containing information about the Name's name, gender, and
	 *         count data field.
	 */
	@Override
	public String toString() {
		return "Name: " + name + ". Gender: " + gender + ". Count: " + count;
	}
}
