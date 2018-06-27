import java.util.ArrayList;


public class Person{
	private Person pairingPreference;
	private boolean satisfied;
	private String name;
	private String assignedRoomName; //Null if not in room
	private ArrayList<Person> incomingPreferences; //Who else wants this person
	
	
	public Person(String name) {
		this.name = name;
		this.satisfied = false;
		this.incomingPreferences = new ArrayList<Person>();
	}
	
	public Person(String name, Person pairingPreference) {
		this(name);
		this.pairingPreference = pairingPreference;
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
		return this.name;
	}
	
	public ArrayList<Person> getIncomingPreferences() {
		return this.incomingPreferences;
	}
	
	private void addIncomingPreferences(Person incomingPreference) {
		incomingPreferences.add(incomingPreference);
	}
}
