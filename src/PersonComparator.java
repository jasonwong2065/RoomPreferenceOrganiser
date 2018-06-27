import java.util.Comparator;
public class PersonComparator implements Comparator<Person> {
	@Override
	public int compare(Person first, Person second) {
		return second.getIncomingPreferences().size() - first.getIncomingPreferences().size();
	}

}
