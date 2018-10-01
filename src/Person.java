import java.util.ArrayList;


public class Person{
	private ArrayList<Person> preferences;
	private int happiness;
	private String fullName;
	private Room assignedRoom; //Null if not in room
	private ArrayList<Person> incomingPreferences; //Who else wants this person
	private int wantedness; //How wanted this person is

	public Person(String firstName, String lastName) {
		this.preferences= new ArrayList<Person>();
		this.fullName = firstName+ " " +lastName;
		this.happiness = 0;
		this.incomingPreferences = new ArrayList<Person>();
		this.assignedRoom = null;
	}
	
	public int calculateHappiness(ArrayList<Person> otherGroup) {
		ArrayList<Person> common = new ArrayList<Person>(otherGroup);
		common.retainAll(preferences);
		return common.size();
	}
	
	public boolean checkSatisfied(Person other) {
		return preferences.contains(other);
	}
	
	public int getHappiness() {
		ArrayList<Person> common = new ArrayList<Person>(assignedRoom.getOccupants());
		common.retainAll(preferences);
		happiness = common.size();
		return happiness;
	}
	
	public int getWantedness() {
		wantedness = incomingPreferences.size();
		return wantedness;
	}
	
	public void addPreference(Person preference) {
		this.preferences.add(preference);
		preference.addIncomingPreferences(this);
	}
	
	public void assignRoom(Room room) {
		this.assignedRoom = room;
	}
	
	public Room getRoom() {
		return this.assignedRoom;
	}
	
	public ArrayList<Person> getPreferences() {
		return preferences;
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
	
	@Override
	public String toString() {
		return this.getName();
	}
	
	/*public boolean isFirstNameEqual(String name) {
		if(name.equals(firstName)){
			return true;
		} else {
			return false;
		}
	}*/
}
