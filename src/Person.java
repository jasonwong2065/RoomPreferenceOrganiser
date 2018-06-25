
public class Person {
	private Person pairingPreference;
	private boolean satisfied;
	private String name;
	
	public Person(String name) {
		this.name = name;
		this.satisfied = false;
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
}
