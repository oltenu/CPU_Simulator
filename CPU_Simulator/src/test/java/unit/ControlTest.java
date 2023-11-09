package unit;

import helper.instruction.Instruction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import register.IfId;
import register.Register;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ControlTest {
    private static Map<String, Unit> units;
    private static Map<String, Register> registers;
    private static IfId ifId;
    @InjectMocks
    private Control control;

    @BeforeAll
    public static void setUp(){
        units = new HashMap<>();
        registers = new HashMap<>();
        ifId = Mockito.mock(IfId.class);
        registers.put("IfId", ifId);
    }

    @BeforeEach
    public void beforeEach(){
        control = new Control();

        control.initializeUnits(units);
        control.initializeRegisters(registers);
    }

    @Test
    public void testR(){
        //given

        //when
        when(ifId.getInstruction()).thenReturn(new Instruction("0000000000000000"));
        control.update();
        control.run();

        boolean expected = (control.getAluOperation().equals("000"))
                && control.isRegisterDestination()
                && !control.isExtendedOperation()
                && !control.isBranch()
                && !control.isJump()
                && !control.isMemoryWrite()
                && !control.isMemoryToRegister()
                && control.isRegisterWrite()
                && !control.isAluSource();

        //then
        assertTrue(expected);
    }

    @Test
    public void testAddi(){
        //given

        //when
        when(ifId.getInstruction()).thenReturn(new Instruction("0010000000000000"));
        control.update();
        control.run();

        boolean expected = (control.getAluOperation().equals("100"))
                && !control.isRegisterDestination()
                && control.isExtendedOperation()
                && !control.isBranch()
                && !control.isJump()
                && !control.isMemoryWrite()
                && !control.isMemoryToRegister()
                && control.isRegisterWrite()
                && control.isAluSource();

        //then
        assertTrue(expected);
    }

    @Test
    public void testLw(){
        //given

        //when
        when(ifId.getInstruction()).thenReturn(new Instruction("0100000000000000"));
        control.update();
        control.run();

        boolean expected = (control.getAluOperation().equals("100"))
                && !control.isRegisterDestination()
                && control.isExtendedOperation()
                && !control.isBranch()
                && !control.isJump()
                && !control.isMemoryWrite()
                && control.isMemoryToRegister()
                && control.isRegisterWrite()
                && control.isAluSource();

        //then
        assertTrue(expected);
    }

    @Test
    public void testSw(){
        //given

        //when
        when(ifId.getInstruction()).thenReturn(new Instruction("0110000000000000"));
        control.update();
        control.run();

        boolean expected = (control.getAluOperation().equals("100"))
                && !control.isRegisterDestination()
                && control.isExtendedOperation()
                && !control.isBranch()
                && !control.isJump()
                && control.isMemoryWrite()
                && !control.isMemoryToRegister()
                && !control.isRegisterWrite()
                && control.isAluSource();

        //then
        assertTrue(expected);
    }

    @Test
    public void testBeq(){
        //given

        //when
        when(ifId.getInstruction()).thenReturn(new Instruction("1000000000000000"));
        control.update();
        control.run();

        boolean expected = (control.getAluOperation().equals("001"))
                && !control.isRegisterDestination()
                && control.isExtendedOperation()
                && control.isBranch()
                && !control.isJump()
                && !control.isMemoryWrite()
                && !control.isMemoryToRegister()
                && !control.isRegisterWrite()
                && !control.isAluSource();

        //then
        assertTrue(expected);
    }

    @Test
    public void testOri(){
        //given

        //when
        when(ifId.getInstruction()).thenReturn(new Instruction("1010000000000000"));
        control.update();
        control.run();

        boolean expected = (control.getAluOperation().equals("101"))
                && !control.isRegisterDestination()
                && !control.isExtendedOperation()
                && !control.isBranch()
                && !control.isJump()
                && !control.isMemoryWrite()
                && !control.isMemoryToRegister()
                && control.isRegisterWrite()
                && control.isAluSource();

        //then
        assertTrue(expected);
    }

    @Test
    public void testSlti(){
        //given

        //when
        when(ifId.getInstruction()).thenReturn(new Instruction("1100000000000000"));
        control.update();
        control.run();

        boolean expected = (control.getAluOperation().equals("110"))
                && !control.isRegisterDestination()
                && control.isExtendedOperation()
                && !control.isBranch()
                && !control.isJump()
                && !control.isMemoryWrite()
                && !control.isMemoryToRegister()
                && control.isRegisterWrite()
                && control.isAluSource();

        //then
        assertTrue(expected);
    }

    @Test
    public void testJ(){
        //given

        //when
        when(ifId.getInstruction()).thenReturn(new Instruction("1110000000000000"));
        control.update();
        control.run();

        boolean expected = (control.getAluOperation().equals("000"))
                && !control.isRegisterDestination()
                && !control.isExtendedOperation()
                && !control.isBranch()
                && control.isJump()
                && !control.isMemoryWrite()
                && !control.isMemoryToRegister()
                && !control.isRegisterWrite()
                && !control.isAluSource();

        //then
        assertTrue(expected);
    }
}
