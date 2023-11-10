package unit;

import helper.instruction.Instruction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import register.ExMem;
import register.Register;

import java.util.HashMap;
import java.util.Map;

import static helper.instruction.Instruction.ZERO_16;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemoryTest {
    private static Map<String, Unit> units;
    private static Map<String, Register> registers;
    private static ExMem exMem;
    @InjectMocks
    private Memory memory;

    @BeforeAll
    public static void setUp() {
        units = new HashMap<>();
        registers = new HashMap<>();
        exMem = Mockito.mock(ExMem.class);
        registers.put("ExMem", exMem);
    }

    @BeforeEach
    public void beforeEach() {
        memory = new Memory();

        memory.initializeUnits(units);
        memory.initializeRegisters(registers);
    }

    @Test
    public void testResultOut() {
        //given
        String expected = "0011100110001010";

        //when
        when(exMem.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(exMem.getAluResult()).thenReturn("0011100110001010");
        when(exMem.getReadData2()).thenReturn(ZERO_16);
        when(exMem.isMemoryWrite()).thenReturn(false);

        memory.update();
        memory.run();

        String actual = memory.getAluResultOut();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testReadData() {
        //given
        String expected = "0011100110001010";

        //when
        (memory.getMemory())[2] = "0011100110001010";
        when(exMem.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(exMem.getAluResult()).thenReturn("0101010000000010");
        when(exMem.getReadData2()).thenReturn(ZERO_16);
        when(exMem.isMemoryWrite()).thenReturn(false);

        memory.update();
        memory.run();

        String actual = memory.getMemoryData();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void test() {
        //given
        String expected = "0011100110001010";

        //when
        when(exMem.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(exMem.getAluResult()).thenReturn("0101010000000010");
        when(exMem.getReadData2()).thenReturn("0011100110001010");
        when(exMem.isMemoryWrite()).thenReturn(true);

        memory.update();
        memory.run();

        String actual = (memory.getMemory())[2];

        //then
        assertEquals(expected, actual);
    }
}
