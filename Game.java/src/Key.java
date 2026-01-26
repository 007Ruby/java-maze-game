import java.util.*;

public class Key {
    Set<ShardType> collected;
    int requiredCount;

    boolean isComplete() {
        return collected.size() == requiredCount;
    };

}
