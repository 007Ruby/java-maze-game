public class Shard {
    Form shardForm;
    Position position;

    public Shard(Position position, Form shardForm) {
        this.position = position;
        this.shardForm = shardForm;
    }
    public Position getShardPosition () {
        return this.position;
    }

    public Form getShardForm() {
        return this.shardForm;
    }
}
