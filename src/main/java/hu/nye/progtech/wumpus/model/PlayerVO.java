package hu.nye.progtech.wumpus.model;

/**
 * Player class.
 */
public class PlayerVO {

    public static PlayerVOBuilder builder() {
        return new PlayerVOBuilder();
    }

    private final String nickName;

    public PlayerVO(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    /**
     * Player Value Object Class.
     */
    public static final class PlayerVOBuilder {
        private String nickName;

        private PlayerVOBuilder() {
        }

        public PlayerVOBuilder withNickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public PlayerVO build() {
            return new PlayerVO(nickName);
        }
    }
}
