import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;

public class PreferenceSystem {
	static private ArrayList<Room> roomList;
	static private ArrayList<Person> fullList;
	static private ArrayList<Person> unassignedPeople;
	static private HashMap<String, Person> peopleHashMap;
	
	static private PriorityQueue<Room> roomPriorityList; //Sorted by vacancies
	static private PriorityQueue<Person> personPriorityList;
	
	public PreferenceSystem() {
		roomList = new ArrayList<Room>();
		fullList = new ArrayList<Person>();
		peopleHashMap = new HashMap<String,Person>();
	}
	
	public static void main(String[] args) {
		
		PreferenceSystem preferenceSystem = new PreferenceSystem();
		
		preferenceSystem.generateRoomList();
		preferenceSystem.generatePersonList();
		preferenceSystem.runAlgorithm();
		
	}
	
	public void runAlgorithm() {
		unassignedPeople = fullList;
		generateRoomPriority();
		Room roomWithMostVacancies = roomPriorityList.peek();
		while(!roomWithMostVacancies.isFull()) {
			generatePersonPriorityQueue(unassignedPeople, false);
			Person freeMostWantedPerson = personPriorityList.peek();
			if(freeMostWantedPerson == null) {
				//If there's no more free people
				break;
			} else {
				//If there is a free person, add him and then see who else wants them in their room
				addPersonToRoom(roomWithMostVacancies, freeMostWantedPerson);
				ArrayList<Person> groupWithMostWantedPerson = freeMostWantedPerson.getIncomingPreferences();
				generatePersonPriorityQueue(groupWithMostWantedPerson, true); //Sort incoming preferences from least wanted
				for(Person person:personPriorityList) {
					//Add the rest of the group in until the room is full starting with the people that are least wanted
					if(roomWithMostVacancies.isFull() || !unassignedPeople.contains(person)) {
						//If the room wanted is full or if the person wanted is already assigned somewhere
						break;
					} else {

						addPersonToRoom(roomWithMostVacancies, person);
					}
				}
			}
			generateRoomPriority();
			roomWithMostVacancies = roomPriorityList.peek();
		}
		
		printRooms();
	}
	
	public void printRooms() {
		for (Room room:roomList) {
			System.out.println(room);
		}
	}
	
	public void addPersonToRoom(Room room, Person person) {
		System.out.println(person.getName());
		room.addPerson(person);
		unassignedPeople.remove(person);
	}
	
	public void createPerson(String name) {
		Person newPerson = new Person(name);
		fullList.add(newPerson);
		unassignedPeople.add(newPerson);
		peopleHashMap.put(name, newPerson);
	}
	
	public void generatePersonPriorityQueue(ArrayList<Person> personList, boolean reverse) {
		Comparator<Person> cmp = new PersonComparator();
		if(reverse == true) {
				cmp = Collections.reverseOrder(cmp);
		}
		personPriorityList = new PriorityQueue<Person>(11, cmp);
		for(Person person:personList) {
			personPriorityList.add(person);
		}
	}
	
	public void generatePersonList() {
		Person testPerson1 = new Person("A");
		Person testPerson2 = new Person("B");
		Person testPerson3 = new Person("C");
		Person testPerson4 = new Person("D");
		Person testPerson5 = new Person("E");
		Person testPerson6 = new Person("F");
		Person testPerson7 = new Person("G");
		Person testPerson8 = new Person("H");
		
		fullList.add(testPerson1);
		fullList.add(testPerson2);
		fullList.add(testPerson3);
		fullList.add(testPerson4);
		fullList.add(testPerson5);
		fullList.add(testPerson6);
		fullList.add(testPerson7);
		fullList.add(testPerson8);
		
		
		testPerson1.setPreference(testPerson2);
		testPerson2.setPreference(testPerson1);
		testPerson3.setPreference(testPerson1);
		testPerson4.setPreference(testPerson4);
		testPerson5.setPreference(testPerson6);
		testPerson6.setPreference(testPerson7);
		testPerson7.setPreference(testPerson4);
		testPerson8.setPreference(testPerson1);
	}
	
	public void generateRoomList() {
		roomList.add(new Room(5, "Room A"));
		roomList.add(new Room(5, "Room B"));
		roomList.add(new Room(6, "Room C"));
	}
	
	private void generateRoomPriority() {
		roomPriorityList = new PriorityQueue<Room>();
		for (Room room:roomList) {
			roomPriorityList.add(room);
		}
	}
	
	public void addConnection(Person from, Person to) {
		from.setPreference(to);
	}
	
	public Person getPersonFromString(String name) {
		return peopleHashMap.get(name);
	}
	
}