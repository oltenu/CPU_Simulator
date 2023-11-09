package unit;

import helper.instruction.Instruction;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import register.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InstructionDecoderTest {
    private static Map<String, Unit> units;
    private static Map<String, Register> registers;
    private static IfId ifId;
    private static MemWb memWb;
    private static Control control;
    private static WriteBack writeBack;
    @InjectMocks
    private InstructionDecoder instructionDecoder;

    @BeforeAll
    public static void setUp(){
        units = new HashMap<>();
        registers = new HashMap<>();
        ifId = Mockito.mock(IfId.class);
        registers.put("IfId", ifId);
        memWb = Mockito.mock(MemWb.class);
        registers.put("MemWb", memWb);
        control = Mockito.mock(Control.class);
        units.put("Control", control);
        writeBack = Mockito.mock(WriteBack.class);
        units.put("WriteBack", writeBack);
    }

    @BeforeEach
    public void beforeEach(){
        instructionDecoder = new InstructionDecoder();

        instructionDecoder.initializeRegisters(registers);
        instructionDecoder.initializeUnits(units);
    }

    @Test
    public void testExtendedImmediateWithExtendOperation(){
        //given
        String expectedExtendedImmediate = "1111111111010111";

        //when
        when(ifId.getInstruction()).thenReturn(new Instruction("0000000001010111"));
        when(control.isExtendedOperation()).thenReturn(true);
        when(memWb.getRd()).thenReturn("000");
        when(memWb.isRegisterWrite()).thenReturn(false);
        when(writeBack.getResult()).thenReturn("0000000000000000");

        instructionDecoder.update();
        instructionDecoder.run();

        String actualExtendedImmediate = instructionDecoder.getExtendedImmediate();

        //then
        assertEquals(expectedExtendedImmediate, actualExtendedImmediate);
    }

    @Test
    public void testExtendedImmediateWithoutExtendOperation(){
        //given
        String expectedExtendedImmediate = "0000000001010111";

        //when
        when(ifId.getInstruction()).thenReturn(new Instruction("0000000001010111"));
        when(control.isExtendedOperation()).thenReturn(false);
        when(memWb.getRd()).thenReturn("000");
        when(memWb.isRegisterWrite()).thenReturn(false);
        when(writeBack.getResult()).thenReturn("0000000000000000");

        instructionDecoder.update();
        instructionDecoder.run();

        String actualExtendedImmediate = instructionDecoder.getExtendedImmediate();

        //then
        assertEquals(expectedExtendedImmediate, actualExtendedImmediate);
    }

    @Test
    public void testWriteRegister(){
        //given
        String expectedRegisterValue = "0100101110001011";

        //when
        when(ifId.getInstruction()).thenReturn(new Instruction("0000000001010111"));
        when(control.isExtendedOperation()).thenReturn(false);
        when(memWb.getRd()).thenReturn("100");
        when(memWb.isRegisterWrite()).thenReturn(true);
        when(writeBack.getResult()).thenReturn("0100101110001011");

        instructionDecoder.update();
        instructionDecoder.run();

        String actualRegisterValue = (instructionDecoder.getDataRegisters())[4];

        //then
        assertEquals(expectedRegisterValue, actualRegisterValue);
    }

    @Test
    public void testReadData1(){
        //given
        String expectedReadData1 = "0100101110001011";
        (instructionDecoder.getDataRegisters())[2] = expectedReadData1;

        //when
        when(ifId.getInstruction()).thenReturn(new Instruction("0000100000000000"));
        when(control.isExtendedOperation()).thenReturn(false);
        when(memWb.getRd()).thenReturn("000");
        when(memWb.isRegisterWrite()).thenReturn(false);
        when(writeBack.getResult()).thenReturn("0000000000000000");

        instructionDecoder.update();
        instructionDecoder.run();

        String actualReadData1 = instructionDecoder.getReadData1();

        //then
        assertEquals(expectedReadData1, actualReadData1);
    }

    @Test
    public void testReadData2(){
        //given
        String expectedReadData1 = "0100101110001011";
        (instructionDecoder.getDataRegisters())[3] = expectedReadData1;

        //when
        when(ifId.getInstruction()).thenReturn(new Instruction("0000000110000000"));
        when(control.isExtendedOperation()).thenReturn(false);
        when(memWb.getRd()).thenReturn("000");
        when(memWb.isRegisterWrite()).thenReturn(false);
        when(writeBack.getResult()).thenReturn("0000000000000000");

        instructionDecoder.update();
        instructionDecoder.run();

        String actualReadData1 = instructionDecoder.getReadData2();

        //then
        assertEquals(expectedReadData1, actualReadData1);
    }

    @Test
    public void testFunc(){
        //given
        String expectedFunc = "101";

        //when
        when(ifId.getInstruction()).thenReturn(new Instruction("0000000000000101"));
        when(control.isExtendedOperation()).thenReturn(false);
        when(memWb.getRd()).thenReturn("000");
        when(memWb.isRegisterWrite()).thenReturn(false);
        when(writeBack.getResult()).thenReturn("0000000000000000");

        instructionDecoder.update();
        instructionDecoder.run();

        String actualFunc = instructionDecoder.getFunc();

        //then
        assertEquals(expectedFunc, actualFunc);
    }

    @Test
    public void testSa(){
        //given
        boolean expectedSa = true;

        //when
        when(ifId.getInstruction()).thenReturn(new Instruction("0000000000001000"));
        when(control.isExtendedOperation()).thenReturn(false);
        when(memWb.getRd()).thenReturn("000");
        when(memWb.isRegisterWrite()).thenReturn(false);
        when(writeBack.getResult()).thenReturn("0000000000000000");

        instructionDecoder.update();
        instructionDecoder.run();

        boolean actualSa = instructionDecoder.isSa();

        //then
        assertEquals(expectedSa, actualSa);
    }

    @Test
    public void testRt(){
        //given
        String expectedRt = "100";

        //when
        when(ifId.getInstruction()).thenReturn(new Instruction("0000001000000000"));
        when(control.isExtendedOperation()).thenReturn(false);
        when(memWb.getRd()).thenReturn("000");
        when(memWb.isRegisterWrite()).thenReturn(false);
        when(writeBack.getResult()).thenReturn("0000000000000000");

        instructionDecoder.update();
        instructionDecoder.run();

        String actualRt = instructionDecoder.getRt();

        //then
        assertEquals(expectedRt, actualRt);
    }

    @Test
    public void testRd(){
        //given
        String expectedRd = "010";

        //when
        when(ifId.getInstruction()).thenReturn(new Instruction("0000000000100000"));
        when(control.isExtendedOperation()).thenReturn(false);
        when(memWb.getRd()).thenReturn("000");
        when(memWb.isRegisterWrite()).thenReturn(false);
        when(writeBack.getResult()).thenReturn("0000000000000000");

        instructionDecoder.update();
        instructionDecoder.run();

        String actualRd = instructionDecoder.getRd();

        //then
        assertEquals(expectedRd, actualRd);
    }
}
