package helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationTest {
    @Test
    public void testAdd() {
        //given
        String expected = "0000000000001100";

        //when
        String actual = Operation.addBinary("0000000000000101", "0000000000000111");

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testSubtraction() {
        //given
        String expected = "0000000000000010";

        //when
        String actual = Operation.subtractBinary("0000000000000111", "0000000000000101");

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testShiftLeftLogic() {
        //given
        String expected = "0010001000111010";

        //when
        String actual = Operation.shiftLeftLogic("0001000100011101");

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testShiftRightLogic() {
        //given
        String expected = "0001000100011101";

        //when
        String actual = Operation.shiftRightLogic("0010001000111010");

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testShiftRightArithmetic() {
        //given
        String expected = "1100100010001110";

        //when
        String actual = Operation.shiftRightArithmetic("1001000100011101");

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testOR() {
        //given
        String expected = "0000000000000111";

        //when
        String actual = Operation.OR("0000000000000101", "0000000000000111");

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testAND() {
        //given
        String expected = "0000000000000101";

        //when
        String actual = Operation.AND("0000000000000101", "0000000000000111");

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testSetOnLessThanImmediate() {
        //given
        String expected = "0000000000000001";

        //when
        String actual = Operation.setOnLessThanImmediate("0000000000000101", "0000000000000111");

        //then
        assertEquals(expected, actual);
    }
}
