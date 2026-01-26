import java.util.*;

public class Key {
    Set<Form> collectedShards;
    int requiredCount;

    boolean isComplete() {
        return collectedShards.size() == requiredCount;
    };

}
