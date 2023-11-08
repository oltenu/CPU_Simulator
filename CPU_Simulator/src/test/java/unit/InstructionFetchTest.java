package unit;

import helper.CodeLoader;
import helper.instruction.Instruction;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import register.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InstructionFetchTest {
    private static Map<String, Unit> units;
    private static Map<String, Register> registers;
    private static IfId ifId;
    private static ExMem exMem;
    private static Control control;
    @InjectMocks
    private InstructionFetch instructionFetch;

    @BeforeAll
    public static void setUp() {
        units = new HashMap<>();
        registers = new HashMap<>();
        ifId = Mockito.mock(IfId.class);
        registers.put("IfId", ifId);
        exMem = Mockito.mock(ExMem.class);
        registers.put("ExMem", exMem);
        control = Mockito.mock(Control.class);
        units.put("Control", control);
    }

    @BeforeEach
    public void beforeEach() {
        instructionFetch = new InstructionFetch();
        Instruction.resetGlobalId();

        instructionFetch.initializeRegisters(registers);
        instructionFetch.initializeUnits(units);
        instructionFetch.loadCode("src/test/resources/test.txt");
    }

    @Test
    public void testCodeLoading() {
        //given
        Instruction.resetGlobalId();
        List<Instruction> expectedInstructions = CodeLoader.loadCode("src/test/resources/test.txt");

        //when
        instructionFetch.run();
        List<Instruction> actualInstructions = instructionFetch.getInstructions();

        //then
        assertEquals(expectedInstructions, actualInstructions);
    }

    @Test
    public void testProgramCounterIncrementation() {
        //given
        String expectedPcIncrement = "0000000000000011";

        //when
        instructionFetch.run();
        instructionFetch.run();
        instructionFetch.run();
        String actualPcIncrement = instructionFetch.getPcIncrement();

        //then
        assertEquals(expectedPcIncrement, actualPcIncrement);
    }

    @Test
    public void testBranch() {
        //given
        String expectedPcIncrement = "0000000000000110";

        //when
        instructionFetch.run();
        when(exMem.getBranchAddress()).thenReturn("0000000000000101");
        when(exMem.isBranch()).thenReturn(true);
        when(exMem.isZero()).thenReturn(true);
        when(ifId.getInstruction()).thenReturn("0000000000000000");
        when(ifId.getPcIncrement()).thenReturn("0000000000000000");
        when(control.isJump()).thenReturn(false);
        instructionFetch.update();
        instructionFetch.run();
        String actualPcIncrement = instructionFetch.getPcIncrement();

        //then
        assertEquals(expectedPcIncrement, actualPcIncrement);
    }

    @Test
    public void testJumpWhenBranchIsFalse() {
        //given
        String expectedPcIncrement = "0000000000000111";

        //when
        instructionFetch.run();
        when(exMem.getBranchAddress()).thenReturn("0000000000000101");
        when(exMem.isBranch()).thenReturn(false);
        when(exMem.isZero()).thenReturn(false);
        when(ifId.getInstruction()).thenReturn("0000000000000110");
        when(ifId.getPcIncrement()).thenReturn("0000000000000000");
        when(control.isJump()).thenReturn(true);
        instructionFetch.update();
        instructionFetch.run();
        String actualPcIncrement = instructionFetch.getPcIncrement();

        //then
        assertEquals(expectedPcIncrement, actualPcIncrement);
    }

    @Test
    public void testJumpWhenBranchIsTrue() {
        //given
        String expectedPcIncrement = "0000000000000111";

        //when
        instructionFetch.run();
        when(exMem.getBranchAddress()).thenReturn("0000000000000101");
        when(exMem.isBranch()).thenReturn(true);
        when(exMem.isZero()).thenReturn(true);
        when(ifId.getInstruction()).thenReturn("0000000000000110");
        when(ifId.getPcIncrement()).thenReturn("0000000000000000");
        when(control.isJump()).thenReturn(true);
        instructionFetch.update();
        instructionFetch.run();
        String actualPcIncrement = instructionFetch.getPcIncrement();

        //then
        assertEquals(expectedPcIncrement, actualPcIncrement);
    }
}
