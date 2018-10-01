import java.util.ArrayList;
import java.lang.Comparable;

public class Room implements Comparable<Room>{
	private String roomName;
	private int size; //Number of people this room can hold
	private ArrayList<Person> occupants;
	private int happiness; //Number of preferences this room satisfies, max is size
	public Room(int size, String roomName) {
		occupants = new ArrayList<Person>();
		this.size = size;
		this.happiness = 0;
		this.roomName = roomName;
	}
	
	public boolean isFull() {
		if(occupants.size() == size) {
			return true;
		}
		return false;
	}
	
	public int getHappiness() {
		happiness = 0;
		for(Person occupant:occupants) {
			happiness += occupant.getHappiness();
		}
		return happiness;
	}
	
	public ArrayList<Person> getOccupants() {
		return occupants;
	}
	
	@Override
	public String toString() {
		String occupantsString = this.roomName + " Happiness: " + getHappiness() + ", assigned:\n";
		for(Person occupant:occupants) {
			if(occupant.getPreferences().size() > 0) {
				ArrayList<Person> preferences = occupant.getPreferences();
				for(int i = 0; i < preferences.size(); i++) {
					Person preference = preferences.get(i);
					if(i == 0) {
						occupantsString += occupant.getName() + "(" + occupant.getWantedness() + ")" + " -> " + preference.getName();
					}
					if(i != 0) {
						occupantsString += ", " + preference.getName();
					}
					if(i == preferences.size()-1) {
						occupantsString += " | Matches: " + occupant.getHappiness() + "\n";
					}
				}/*
				for(Person preference:occupant.getPreferences()) {
					occupantsString += occupant.getName() + "(" + occupant.getWantedness() + ")" + " -> " + preference.getName() + " Matches: " + occupant.getHappiness() + "\n";
				}*/
			} else {
				occupantsString += occupant.getName() + "(" + occupant.getWantedness() + ")" + " -> " + "None" + "\n";
			}
		}
		return occupantsString;
	}
	
	public int checkHappinessImprovementIfAdded(Person other) {
		int happinessCounter = 0;
		for(Person occupant:occupants) {
			if(occupant.checkSatisfied(other)) {
				//If the new person makes existing occupants more happy
				happinessCounter++;
			}
			if(other.checkSatisfied(occupant)) {
				//If the new person is happy because of existing occupants
				happinessCounter++;
			}
		}
		return happinessCounter;
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	public void addPerson(Person newPerson) {
		happiness = 0;
		for(Person occupant:occupants) {
			happiness += occupant.getHappiness();
			
			/*
			if(occupant.checkSatisfied(newPerson) && !occupant.isSatisfied()) {
				//If the new person makes existing occupants more happy
				happiness++;
				occupant.setSatisfied(true);
			}
			if(newPerson.checkSatisfied(occupant) && !newPerson.isSatisfied()) {
				//If the new person is happy because of existing occupants
				happiness++;
				newPerson.setSatisfied(true);
			}*/
		}
		occupants.add(newPerson);
		newPerson.assignRoom(this);
	}
	
	public void removePerson(Person oldOccupant) {
		oldOccupant.assignRoom(null);
		occupants.remove(oldOccupant);
		happiness = 0;
		for(Person occupant:occupants) {
			happiness += occupant.getHappiness();
			/*
			Old method to get happiness	
			if(occupant.checkSatisfied(oldOccupant) && occupant.isSatisfied()) {
				//If current occupants get sad because someone's getting removed
				happiness--;
			}
			if(oldOccupant.checkSatisfied(occupant) && oldOccupant.isSatisfied()) {
				//If the person being removed is sad
				happiness--;
			}
			*/
		}
	}
	
	public int getHappinessIfRemoved(Person person) {
		int happinessCounter = happiness;
		for(Person occupant:occupants) {
			//Decrement happiness counter for each person that gets sad
			if(occupant == person) {
				//continue;
			}
			if(occupant.checkSatisfied(person)) {
				happinessCounter--;
			}
			if(person.checkSatisfied(occupant)) {
				happinessCounter--;
			}
		}
		return happinessCounter;
	}

	public int getRemainingVacancies() {
		return this.size - occupants.size();
	}
	
	@Override
	public int compareTo(Room other) {
		return other.getRemainingVacancies() - this.getRemainingVacancies();
	}
}
