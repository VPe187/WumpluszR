package hu.nye.progtech.wumpus.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerVOTest {
    private final String PLAYER_NAME = "Kecske";

    @Test
    public void testSetNickName() {
        // given
        PlayerVO underTest = PlayerVO.builder().withNickName(PLAYER_NAME).build();
        // when
        String result = underTest.getNickName();
        // then
        assertEquals(PLAYER_NAME, result);
    }

}