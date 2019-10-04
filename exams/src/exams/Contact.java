package exams;

public class Contact {
	String name;
	String phoneNumber;

	Contact(String givenName, String givenPhoneNumber) {
		name = givenName;
		phoneNumber = givenPhoneNumber;
	}
	public String getName() {
		return name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public int[] getPhoneNumberArray() {
		int[] phoneArray = new int[10];
		int b = 0;
		for (int i = 0; i < phoneNumber.length(); i++) {
			if (phoneNumber.charAt(i) == '-') {
				i++;
			}
			phoneArray[b] = (int) phoneNumber.charAt(i);
			b++;
		}
		return phoneArray;
	}
}