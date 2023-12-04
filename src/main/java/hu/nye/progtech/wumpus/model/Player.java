package hu.nye.progtech.wumpus.model;

/**
 * Player class.
 */
public class Player {
    private final String nickName;

    public Player(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }
}
