import java.util.ArrayList;


public class Person{
	private Person pairingPreference;
	private boolean satisfied;
	private String fullName;
	private String assignedRoomName; //Null if not in room
	private ArrayList<Person> incomingPreferences; //Who else wants this person
	private String firstName;
	private String lastName;
	
	
	public Person(String firstName, String lastName) {
		this.fullName = firstName;
		this.satisfied = false;
		this.incomingPreferences = new ArrayList<Person>();
	}
	
	public boolean checkSatisfied(Person other) {
		if(this.pairingPreference.equals(other)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setPreference(Person pairingPreference) {
		this.pairingPreference = pairingPreference;
		pairingPreference.addIncomingPreferences(this);
	}
	
	public void assignRoom(String roomName) {
		this.assignedRoomName = roomName;
	}
	
	public String getRoomName() {
		return this.assignedRoomName;
	}
	
	public String getPreferenceName() {
		return pairingPreference.getName();
	}
	
	public void setSatisfied(Boolean satisfiedStatus) {
		satisfied = satisfiedStatus;
	}
	
	public boolean isSatisfied() {
		return satisfied;
	}
	
	public String getName() {
		return this.fullName;
	}
	
	public ArrayList<Person> getIncomingPreferences() {
		return this.incomingPreferences;
	}
	
	private void addIncomingPreferences(Person incomingPreference) {
		incomingPreferences.add(incomingPreference);
	}
	
	public boolean isFirstNameEqual(String name) {
		if(name.equals(firstName)){
			return true;
		} else {
			return false;
		}
	}
}
