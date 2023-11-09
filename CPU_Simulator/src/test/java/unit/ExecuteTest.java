package unit;

import helper.instruction.Instruction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import register.IdEx;
import register.Register;

import java.util.HashMap;
import java.util.Map;

import static helper.instruction.Instruction.ZERO_16;
import static helper.instruction.Instruction.ZERO_3;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class ExecuteTest {
    private static Map<String, Unit> units;
    private static Map<String, Register> registers;
    private static IdEx idEx;
    @InjectMocks
    private Execute execute;

    @BeforeAll
    public static void setUp(){
        units = new HashMap<>();
        registers = new HashMap<>();
        idEx = Mockito.mock(IdEx.class);
        registers.put("IdEx", idEx);
    }

    @BeforeEach
    public void beforeEach(){
        execute = new Execute();

        execute.initializeUnits(units);
        execute.initializeRegisters(registers);
    }

    @Test
    public void testRWaWithRd(){
        //given
        String expected = "101";

        //when
        when(idEx.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(idEx.getReadData1()).thenReturn(ZERO_16);
        when(idEx.getReadData2()).thenReturn(ZERO_16);
        when(idEx.getExtendedImmediate()).thenReturn(ZERO_16);
        when(idEx.getFunc()).thenReturn(ZERO_3);
        when(idEx.isSa()).thenReturn(false);
        when(idEx.isRegisterDestination()).thenReturn(true);
        when(idEx.isAluSource()).thenReturn(false);
        when(idEx.getAluOperation()).thenReturn(ZERO_3);
        when(idEx.getRd()).thenReturn("101");
        when(idEx.getRt()).thenReturn(ZERO_3);
        when(idEx.getPcIncrement()).thenReturn(ZERO_16);

        execute.update();
        execute.run();

        String actual = execute.getRWa();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testRWaWithRt(){
        //given
        String expected = "110";

        //when
        when(idEx.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(idEx.getReadData1()).thenReturn(ZERO_16);
        when(idEx.getReadData2()).thenReturn(ZERO_16);
        when(idEx.getExtendedImmediate()).thenReturn(ZERO_16);
        when(idEx.getFunc()).thenReturn(ZERO_3);
        when(idEx.isSa()).thenReturn(false);
        when(idEx.isRegisterDestination()).thenReturn(false);
        when(idEx.isAluSource()).thenReturn(false);
        when(idEx.getAluOperation()).thenReturn(ZERO_3);
        when(idEx.getRd()).thenReturn(ZERO_3);
        when(idEx.getRt()).thenReturn("110");
        when(idEx.getPcIncrement()).thenReturn(ZERO_16);

        execute.update();
        execute.run();

        String actual = execute.getRWa();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testZero(){
        //given

        //when
        when(idEx.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(idEx.getReadData1()).thenReturn(ZERO_16);
        when(idEx.getReadData2()).thenReturn(ZERO_16);
        when(idEx.getExtendedImmediate()).thenReturn(ZERO_16);
        when(idEx.getFunc()).thenReturn(ZERO_3);
        when(idEx.isSa()).thenReturn(false);
        when(idEx.isRegisterDestination()).thenReturn(false);
        when(idEx.isAluSource()).thenReturn(false);
        when(idEx.getAluOperation()).thenReturn(ZERO_3);
        when(idEx.getRd()).thenReturn(ZERO_3);
        when(idEx.getRt()).thenReturn(ZERO_3);
        when(idEx.getPcIncrement()).thenReturn(ZERO_16);

        execute.update();
        execute.run();

        boolean actual = execute.isZero();

        //then
        assertTrue(actual);
    }

    @Test
    public void testBranchAddress(){
        //given
        String expected = "0000000000011110";

        //when
        when(idEx.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(idEx.getReadData1()).thenReturn(ZERO_16);
        when(idEx.getReadData2()).thenReturn(ZERO_16);
        when(idEx.getExtendedImmediate()).thenReturn("0000000000001100");
        when(idEx.getFunc()).thenReturn(ZERO_3);
        when(idEx.isSa()).thenReturn(false);
        when(idEx.isRegisterDestination()).thenReturn(false);
        when(idEx.isAluSource()).thenReturn(false);
        when(idEx.getAluOperation()).thenReturn(ZERO_3);
        when(idEx.getRd()).thenReturn(ZERO_3);
        when(idEx.getRt()).thenReturn(ZERO_3);
        when(idEx.getPcIncrement()).thenReturn("0000000000010010");

        execute.update();
        execute.run();

        String actual = execute.getBranchAddress();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testAddWithImmediate(){
        //given
        String expected = "0000000000011110";

        //when
        when(idEx.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(idEx.getReadData1()).thenReturn("0000000000010010");
        when(idEx.getReadData2()).thenReturn(ZERO_16);
        when(idEx.getExtendedImmediate()).thenReturn("0000000000001100");
        when(idEx.getFunc()).thenReturn(ZERO_3);
        when(idEx.isSa()).thenReturn(false);
        when(idEx.isRegisterDestination()).thenReturn(false);
        when(idEx.isAluSource()).thenReturn(true);
        when(idEx.getAluOperation()).thenReturn("100");
        when(idEx.getRd()).thenReturn(ZERO_3);
        when(idEx.getRt()).thenReturn(ZERO_3);
        when(idEx.getPcIncrement()).thenReturn(ZERO_16);

        execute.update();
        execute.run();

        String actual = execute.getAluResult();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testSubtract(){
        //given
        String expected = "0000000000000110";

        //when
        when(idEx.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(idEx.getReadData1()).thenReturn("0000000000010010");
        when(idEx.getReadData2()).thenReturn("0000000000001100");
        when(idEx.getExtendedImmediate()).thenReturn(ZERO_16);
        when(idEx.getFunc()).thenReturn("001");
        when(idEx.isSa()).thenReturn(false);
        when(idEx.isRegisterDestination()).thenReturn(false);
        when(idEx.isAluSource()).thenReturn(false);
        when(idEx.getAluOperation()).thenReturn("000");
        when(idEx.getRd()).thenReturn(ZERO_3);
        when(idEx.getRt()).thenReturn(ZERO_3);
        when(idEx.getPcIncrement()).thenReturn(ZERO_16);

        execute.update();
        execute.run();

        String actual = execute.getAluResult();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testShiftLeftLogic(){
        //given
        String expected = "0000000000100100";

        //when
        when(idEx.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(idEx.getReadData1()).thenReturn("0000000000010010");
        when(idEx.getReadData2()).thenReturn(ZERO_16);
        when(idEx.getExtendedImmediate()).thenReturn(ZERO_16);
        when(idEx.getFunc()).thenReturn("010");
        when(idEx.isSa()).thenReturn(false);
        when(idEx.isRegisterDestination()).thenReturn(false);
        when(idEx.isAluSource()).thenReturn(false);
        when(idEx.getAluOperation()).thenReturn("000");
        when(idEx.getRd()).thenReturn(ZERO_3);
        when(idEx.getRt()).thenReturn(ZERO_3);
        when(idEx.getPcIncrement()).thenReturn(ZERO_16);

        execute.update();
        execute.run();

        String actual = execute.getAluResult();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testShiftRightLogic(){
        //given
        String expected = "0000000000001001";

        //when
        when(idEx.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(idEx.getReadData1()).thenReturn("0000000000010010");
        when(idEx.getReadData2()).thenReturn(ZERO_16);
        when(idEx.getExtendedImmediate()).thenReturn(ZERO_16);
        when(idEx.getFunc()).thenReturn("011");
        when(idEx.isSa()).thenReturn(false);
        when(idEx.isRegisterDestination()).thenReturn(false);
        when(idEx.isAluSource()).thenReturn(false);
        when(idEx.getAluOperation()).thenReturn("000");
        when(idEx.getRd()).thenReturn(ZERO_3);
        when(idEx.getRt()).thenReturn(ZERO_3);
        when(idEx.getPcIncrement()).thenReturn(ZERO_16);

        execute.update();
        execute.run();

        String actual = execute.getAluResult();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testAND(){
        //given
        String expected = "0000000000000010";

        //when
        when(idEx.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(idEx.getReadData1()).thenReturn("0000000000010010");
        when(idEx.getReadData2()).thenReturn("1001101001001110");
        when(idEx.getExtendedImmediate()).thenReturn(ZERO_16);
        when(idEx.getFunc()).thenReturn("100");
        when(idEx.isSa()).thenReturn(false);
        when(idEx.isRegisterDestination()).thenReturn(false);
        when(idEx.isAluSource()).thenReturn(false);
        when(idEx.getAluOperation()).thenReturn("000");
        when(idEx.getRd()).thenReturn(ZERO_3);
        when(idEx.getRt()).thenReturn(ZERO_3);
        when(idEx.getPcIncrement()).thenReturn(ZERO_16);

        execute.update();
        execute.run();

        String actual = execute.getAluResult();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testOR(){
        //given
        String expected = "0000000001011110";

        //when
        when(idEx.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(idEx.getReadData1()).thenReturn("0000000000010010");
        when(idEx.getReadData2()).thenReturn("0000000001001110");
        when(idEx.getExtendedImmediate()).thenReturn(ZERO_16);
        when(idEx.getFunc()).thenReturn("101");
        when(idEx.isSa()).thenReturn(false);
        when(idEx.isRegisterDestination()).thenReturn(false);
        when(idEx.isAluSource()).thenReturn(false);
        when(idEx.getAluOperation()).thenReturn("000");
        when(idEx.getRd()).thenReturn(ZERO_3);
        when(idEx.getRt()).thenReturn(ZERO_3);
        when(idEx.getPcIncrement()).thenReturn(ZERO_16);

        execute.update();
        execute.run();

        String actual = execute.getAluResult();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testSetOnLessThan(){
        //given
        String expected = "0000000000000001";

        //when
        when(idEx.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(idEx.getReadData1()).thenReturn("0000000000010010");
        when(idEx.getReadData2()).thenReturn("1001101001001110");
        when(idEx.getExtendedImmediate()).thenReturn(ZERO_16);
        when(idEx.getFunc()).thenReturn("110");
        when(idEx.isSa()).thenReturn(false);
        when(idEx.isRegisterDestination()).thenReturn(false);
        when(idEx.isAluSource()).thenReturn(false);
        when(idEx.getAluOperation()).thenReturn("000");
        when(idEx.getRd()).thenReturn(ZERO_3);
        when(idEx.getRt()).thenReturn(ZERO_3);
        when(idEx.getPcIncrement()).thenReturn(ZERO_16);

        execute.update();
        execute.run();

        String actual = execute.getAluResult();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testShiftRightArithmetic(){
        //given
        String expected = "1100000000001001";

        //when
        when(idEx.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(idEx.getReadData1()).thenReturn("1000000000010010");
        when(idEx.getReadData2()).thenReturn(ZERO_16);
        when(idEx.getExtendedImmediate()).thenReturn(ZERO_16);
        when(idEx.getFunc()).thenReturn("111");
        when(idEx.isSa()).thenReturn(false);
        when(idEx.isRegisterDestination()).thenReturn(false);
        when(idEx.isAluSource()).thenReturn(false);
        when(idEx.getAluOperation()).thenReturn("000");
        when(idEx.getRd()).thenReturn(ZERO_3);
        when(idEx.getRt()).thenReturn(ZERO_3);
        when(idEx.getPcIncrement()).thenReturn(ZERO_16);

        execute.update();
        execute.run();

        String actual = execute.getAluResult();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testBranchAndEqual(){
        //given

        //when
        when(idEx.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(idEx.getReadData1()).thenReturn("0000000000000011");
        when(idEx.getReadData2()).thenReturn(ZERO_16);
        when(idEx.getExtendedImmediate()).thenReturn("0000000000000011");
        when(idEx.getFunc()).thenReturn(ZERO_3);
        when(idEx.isSa()).thenReturn(false);
        when(idEx.isRegisterDestination()).thenReturn(false);
        when(idEx.isAluSource()).thenReturn(true);
        when(idEx.getAluOperation()).thenReturn("001");
        when(idEx.getRd()).thenReturn(ZERO_3);
        when(idEx.getRt()).thenReturn(ZERO_3);
        when(idEx.getPcIncrement()).thenReturn(ZERO_16);

        execute.update();
        execute.run();

        boolean result = execute.isZero();

        //then
        assertTrue(result);
    }

    @Test
    public void testORI(){
        //given
        String expected = "0000000001011110";

        //when
        when(idEx.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(idEx.getReadData1()).thenReturn("0000000000010010");
        when(idEx.getReadData2()).thenReturn(ZERO_16);
        when(idEx.getExtendedImmediate()).thenReturn("0000000001001110");
        when(idEx.getFunc()).thenReturn(ZERO_3);
        when(idEx.isSa()).thenReturn(false);
        when(idEx.isRegisterDestination()).thenReturn(false);
        when(idEx.isAluSource()).thenReturn(true);
        when(idEx.getAluOperation()).thenReturn("101");
        when(idEx.getRd()).thenReturn(ZERO_3);
        when(idEx.getRt()).thenReturn(ZERO_3);
        when(idEx.getPcIncrement()).thenReturn(ZERO_16);

        execute.update();
        execute.run();

        String actual = execute.getAluResult();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testSetOnLessThanImmediate(){
        //given
        String expected = "0000000000000000";

        //when
        when(idEx.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(idEx.getReadData1()).thenReturn("0000000000000001");
        when(idEx.getReadData2()).thenReturn(ZERO_16);
        when(idEx.getExtendedImmediate()).thenReturn("0000000000000000");
        when(idEx.getFunc()).thenReturn(ZERO_3);
        when(idEx.isSa()).thenReturn(false);
        when(idEx.isRegisterDestination()).thenReturn(false);
        when(idEx.isAluSource()).thenReturn(true);
        when(idEx.getAluOperation()).thenReturn("110");
        when(idEx.getRd()).thenReturn(ZERO_3);
        when(idEx.getRt()).thenReturn(ZERO_3);
        when(idEx.getPcIncrement()).thenReturn(ZERO_16);

        execute.update();
        execute.run();

        String actual = execute.getAluResult();

        //then
        assertEquals(expected, actual);
    }
}
