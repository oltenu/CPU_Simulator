package component.register;

import helper.instruction.Instruction;
import lombok.Data;
import component.unit.Memory;
import component.unit.Unit;

import java.util.HashMap;
import java.util.Map;

import static helper.CodeLoader.ZERO_INSTRUCTION;
import static helper.instruction.Instruction.ZERO_16;
import static helper.instruction.Instruction.ZERO_3;

@Data
public class MemWb implements Register {
    private Map<String, Unit> units;
    private Map<String, Register> registers;
    private Instruction instruction;
    private String aluResult;
    private String memoryData;
    private String rd;
    private boolean memoryToRegister;
    private boolean registerWrite;

    public MemWb() {
        units = new HashMap<>();
        registers = new HashMap<>();
        instruction = ZERO_INSTRUCTION;
        aluResult = ZERO_16;
        memoryData = ZERO_16;
        rd = ZERO_3;
    }

    @Override
    public void update() {
        instruction = ((ExMem) registers.get("ExMem")).getInstruction();
        aluResult = ((Memory) units.get("Memory")).getAluResultOut();
        memoryData = ((Memory) units.get("Memory")).getMemoryData();
        rd = ((ExMem) registers.get("ExMem")).getRd();
        memoryToRegister = ((ExMem) registers.get("ExMem")).isMemoryToRegister();
        registerWrite = ((ExMem) registers.get("ExMem")).isRegisterWrite();
    }

    @Override
    public void initializeUnits(Map<String, Unit> units) {
        this.units = units;
    }

    @Override
    public void initializeRegisters(Map<String, Register> registers) {
        this.registers = registers;
    }

    @Override
    public String toString() {
        return "MemWb{" +
                "instruction=" + instruction +
                ", aluResult='" + aluResult + '\'' +
                ", memoryData='" + memoryData + '\'' +
                ", rd='" + rd + '\'' +
                ", memoryToRegister=" + memoryToRegister +
                ", registerWrite=" + registerWrite +
                '}' + '\n';
    }
}
