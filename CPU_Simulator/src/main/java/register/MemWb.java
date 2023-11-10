package register;

import helper.instruction.Instruction;
import lombok.Data;
import unit.Memory;
import unit.Unit;

import java.util.HashMap;
import java.util.Map;

import static helper.instruction.Instruction.ZERO_16;
import static helper.instruction.Instruction.ZERO_3;

@Data
public class MemWb implements Register{
    private Map<String, Unit> units;
    private Map<String, Register> registers;
    private Instruction instruction;
    private String aluResult;
    private String memoryData;
    private String rd;
    private boolean memoryToRegister;
    private boolean registerWrite;

    public MemWb(){
        units = new HashMap<>();
        registers = new HashMap<>();
        instruction = new Instruction(ZERO_16);
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
}
