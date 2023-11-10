package unit;

import helper.instruction.Instruction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import register.MemWb;
import register.Register;

import java.util.HashMap;
import java.util.Map;

import static helper.instruction.Instruction.ZERO_16;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WriteBackTest {
    private static Map<String, Unit> units;
    private static Map<String, Register> registers;
    private static MemWb memWb;
    @InjectMocks
    private WriteBack writeBack;

    @BeforeAll
    public static void setUp(){
        units = new HashMap<>();
        registers = new HashMap<>();
        memWb = Mockito.mock(MemWb.class);
        registers.put("MemWb", memWb);
    }

    @BeforeEach
    public void beforeEach(){
        writeBack = new WriteBack();

        writeBack.initializeUnits(units);
        writeBack.initializeRegisters(registers);
    }

    @Test
    public void testWhenMemoryToRegister(){
        //given
        String expected = "0110010000111001";

        //when
        when(memWb.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(memWb.getAluResult()).thenReturn(ZERO_16);
        when(memWb.getMemoryData()).thenReturn("0110010000111001");
        when(memWb.isMemoryToRegister()).thenReturn(true);

        writeBack.update();
        writeBack.run();

        String actual = writeBack.getResult();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testWhenNotMemoryToRegister(){
        //given
        String expected = "1010000000111001";

        //when
        when(memWb.getInstruction()).thenReturn(new Instruction(ZERO_16));
        when(memWb.getAluResult()).thenReturn("1010000000111001");
        when(memWb.getMemoryData()).thenReturn(ZERO_16);
        when(memWb.isMemoryToRegister()).thenReturn(false);

        writeBack.update();
        writeBack.run();

        String actual = writeBack.getResult();

        //then
        assertEquals(expected, actual);
    }
}
