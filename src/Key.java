import java.util.*;

public class Key {
    Set<Form> collectedShards;
    int requiredCount;

    public Key(int requiredCount) {
        this.requiredCount = requiredCount;
        this.collectedShards = new HashSet<>();
    }
    boolean isComplete() {
        return collectedShards.size() == requiredCount;
    };

    public void addShardToKey(Shard shard) {
        collectedShards.add(shard.getShardForm());
    }

}
