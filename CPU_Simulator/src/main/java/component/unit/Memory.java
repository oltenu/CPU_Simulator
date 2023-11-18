package component.unit;

import helper.instruction.Instruction;
import helper.instruction.InstructionStage;
import lombok.Data;
import component.register.ExMem;
import component.register.Register;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static helper.CodeLoader.ZERO_INSTRUCTION;
import static helper.instruction.Instruction.ZERO_16;

@Data
public class Memory implements Unit {
    private Map<String, Unit> units;
    private Map<String, Register> registers;
    private String[] memory;
    private Instruction instruction;
    private String aluResultIn;
    private String readData2;
    private String aluResultOut;
    private String memoryData;
    private boolean memoryWrite;

    public Memory() {
        units = new HashMap<>();
        registers = new HashMap<>();
        memory = new String[32];
        Arrays.fill(memory, ZERO_16);
        instruction = ZERO_INSTRUCTION;
        aluResultIn = ZERO_16;
        readData2 = ZERO_16;
        aluResultOut = ZERO_16;
        memoryData = ZERO_16;
    }

    @Override
    public void run() {
        aluResultOut = aluResultIn;

        if (memoryWrite) {
            memory[Integer.parseInt(aluResultIn.substring(11), 2)] = readData2;
        }

        memoryData = memory[Integer.parseInt(aluResultIn.substring(11), 2)];
    }

    @Override
    public void update() {
        instruction = ((ExMem) registers.get("ExMem")).getInstruction();
        instruction.setInstructionStage(InstructionStage.MEMORY);
        aluResultIn = ((ExMem) registers.get("ExMem")).getAluResult();
        readData2 = ((ExMem) registers.get("ExMem")).getReadData2();
        memoryWrite = ((ExMem) registers.get("ExMem")).isMemoryWrite();
    }

    @Override
    public void initializeUnits(Map<String, Unit> units) {
        this.units = units;
    }

    @Override
    public void initializeRegisters(Map<String, Register> registers) {
        this.registers = registers;
    }

    public void printMemory() {
        System.out.println("Memory:");
        for (int i = 0; i < memory.length; i++) {
            System.out.printf("%d: %s%n", i, memory[i]);
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return String.format("Memory:%n aluResOut: %s%n memoryData: %s%n", aluResultOut, memoryData);
    }
}
