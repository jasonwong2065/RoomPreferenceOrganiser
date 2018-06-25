import java.util.ArrayList;

public class Room {
	private int size; //Number of people this room can hold
	private ArrayList<Person> occupants;
	private int happiness; //Number of preferences this room satisfies, max is size
	public Room(int size) {
		occupants = new ArrayList<Person>();
		this.size = size;
		this.happiness = 0;
	}
	
	public boolean isFull() {
		if(occupants.size() == size) {
			return true;
		}
		return false;
	}
	
	public int getHappiness() {
		return happiness;
	}
	
	@Override
	public String toString() {
		String occupantsString = "Happiness: " + getHappiness() + ", Room occupants:\n";
		for(Person occupant:occupants) {
			occupantsString += occupant.getName() + " -> " + occupant.getPreferenceName() + " Satisfied: " + occupant.isSatisfied() + "\n";
		}
		return occupantsString;
	}
	
	public int checkHappinessIfAdded(Person other) {
		int happinessCounter = happiness;
		for(Person occupant:occupants) {
			if(occupant.checkSatisfied(other) && !occupant.isSatisfied()) {
				//If the new person makes existing occupants more happy
				happinessCounter++;
			}
			if(other.checkSatisfied(occupant) && !other.isSatisfied()) {
				//If the new person is happy because of existing occupants
				happinessCounter++;
			}
		}
		return happinessCounter;
	}
	
	public void addPerson(Person newPerson) {
		for(Person occupant:occupants) {
			if(occupant.checkSatisfied(newPerson) && !occupant.isSatisfied()) {
				//If the new person makes existing occupants more happy
				happiness++;
				occupant.setSatisfied(true);
			}
			if(newPerson.checkSatisfied(occupant) && !newPerson.isSatisfied()) {
				//If the new person is happy because of existing occupants
				happiness++;
				newPerson.setSatisfied(true);
			}
		}
		occupants.add(newPerson);
	}
	
	public void removePerson(Person oldOccupant) {
		for(Person occupant:occupants) {
			if(occupant.checkSatisfied(oldOccupant) && occupant.isSatisfied()) {
				//If current occupants get sad because someone's getting removed
				happiness--;
			}
			if(oldOccupant.checkSatisfied(occupant) && oldOccupant.isSatisfied()) {
				//If the person being removed is sad
				happiness--;
			}
		}
		occupants.remove(oldOccupant);
	}
}
