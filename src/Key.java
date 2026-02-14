import java.util.*;

public class Key {
    int collectedShards;
    int requiredCount;

    public Key(int requiredCount) {
        this.requiredCount = requiredCount;
        this.collectedShards = 0;
    }
    boolean isComplete() {
        System.out.println("NUMBER OF COLLECTED SHARDS IS" + collectedShards);
        return collectedShards == requiredCount;
    };

    public void addShardToKey() {
        collectedShards += 1;
    }

    public int getCollectedShardCount() {
        return this.collectedShards;
    }

    public int getRequiredShardCount() {
        return this.requiredCount;
    }

}
