import java.util.ArrayList;
import java.util.HashMap;

public class PreferenceSystem {
	private ArrayList<Room> roomList;
	private ArrayList<Person> peopleList;
	private ArrayList<Person> unassignedPeople;
	private HashMap<String, Person> peopleHashMap;
	
	public PreferenceSystem() {
		roomList = new ArrayList<Room>();
		peopleHashMap = new HashMap<String,Person>();
	}
	
	public static void main(String[] args) {
//		PreferenceSystem = new P
		
		Person testPerson1 = new Person("A");
		Person testPerson2 = new Person("B");
		Person testPerson3 = new Person("C");
	//	Person testPerson4 = new Person("D");
		
		testPerson1.setPreference(testPerson2);
		testPerson2.setPreference(testPerson1);
		testPerson3.setPreference(testPerson1);
		
		Room testRoom1 = new Room(5);
		
		testRoom1.addPerson(testPerson1);
		testRoom1.addPerson(testPerson2);
		testRoom1.addPerson(testPerson3);
		System.out.println(testRoom1);
	}
	
	public void createPerson(String name) {
		Person newPerson = new Person(name);
		peopleList.add(newPerson);
		unassignedPeople.add(newPerson);
	}
	
	public void addConnection(Person from, Person to) {
		from.setPreference(to);
	}
	
//	public Person getPerson(String name) {
		
//	}
	
}