public class Player {
    Form playerForm;
    Position position;

    public Player(Position position, Form playerForm) {
        this.position = position;
        this.playerForm = playerForm;
    }
    public Position getPlayerPosition() {
        return this.position;
    }

    public void setPlayerPosition(Position p) {
        this.position = p;
    }

    public Form getPlayerForm() {
        return this.playerForm;
    }
} 
