package company.predicates;

/**
 * This class is needed for GUI purposes
 */
public class PredicateInfo {

    private final Predicates condition;
    private final String conditionDetails;

    public PredicateInfo(Predicates condition, String conditionDetails) {
        this.condition = condition;
        this.conditionDetails = conditionDetails;
    }

    public Predicates getCondition() {
        return condition;
    }

    public String getConditionDetails() {
        return conditionDetails;
    }

    public String toString() {
        String output = "";
        switch(condition) {
            case EMAIL:
                output = "This manager employs developers only with " + conditionDetails + " email domain";
                break;
            case GENDER:
                output =  "This manager employs only " + conditionDetails + " developers";
                break;
            case COUNTRY:
                output =  "This manager employs only developers from " + conditionDetails;
                break;
            case UNIVERSITY:
                output = "This manager employs only " + conditionDetails + " graduates";
                break;
        }
        return output + "\n";
    }
}
