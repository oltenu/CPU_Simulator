package unit;

import helper.instruction.Instruction;
import helper.instruction.InstructionStage;
import lombok.Data;
import register.MemWb;
import register.Register;

import java.util.HashMap;
import java.util.Map;

import static helper.instruction.Instruction.ZERO_16;

@Data
public class WriteBack implements Unit {
    private Map<String, Unit> units;
    private Map<String, Register> registers;
    private Instruction instruction;
    private String aluRes;
    private String memoryData;
    private String result;
    private boolean memoryToRegister;

    public WriteBack() {
        units = new HashMap<>();
        registers = new HashMap<>();
        instruction = new Instruction(ZERO_16);
        aluRes = ZERO_16;
        memoryData = ZERO_16;
        result = ZERO_16;
    }

    @Override
    public void run() {
        if(memoryToRegister)
            result = memoryData;
        else
            result = aluRes;
    }

    @Override
    public void update() {
        instruction = ((MemWb) registers.get("MemWb")).getInstruction();
        instruction.setInstructionStage(InstructionStage.WRITE_BACK);
        aluRes = ((MemWb) registers.get("MemWb")).getAluResult();
        memoryData = ((MemWb) registers.get("MemWb")).getMemoryData();
        memoryToRegister = ((MemWb) registers.get("MemWb")).isMemoryToRegister();
    }

    @Override
    public void initializeUnits(Map<String, Unit> units) {
        this.units = units;
    }

    @Override
    public void initializeRegisters(Map<String, Register> registers) {
        this.registers = registers;
    }
}