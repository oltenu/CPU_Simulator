package component.unit;

import helper.instruction.Instruction;
import helper.instruction.InstructionStage;
import lombok.Data;
import component.register.MemWb;
import component.register.Register;

import java.util.HashMap;
import java.util.Map;

import static helper.CodeLoader.ZERO_INSTRUCTION;
import static helper.instruction.Instruction.ZERO_16;
import static helper.instruction.Instruction.ZERO_3;

@Data
public class WriteBack implements Unit {
    private Map<String, Unit> units;
    private Map<String, Register> registers;
    private Instruction instruction;
    private String aluRes;
    private String memoryData;
    private String result;
    private String writeAddress;
    private boolean memoryToRegister;
    private boolean registerWrite;
    private Instruction instructionToFinish;

    public WriteBack() {
        units = new HashMap<>();
        registers = new HashMap<>();
        instruction = ZERO_INSTRUCTION;
        instructionToFinish = ZERO_INSTRUCTION;
        aluRes = ZERO_16;
        memoryData = ZERO_16;
        result = ZERO_16;
        writeAddress = ZERO_3;
    }

    @Override
    public void run() {
        if (memoryToRegister)
            result = memoryData;
        else
            result = aluRes;

        instructionToFinish = instruction;
    }

    @Override
    public void update() {
        instruction = ((MemWb) registers.get("MemWb")).getInstruction();
        instruction.setInstructionStage(InstructionStage.WRITE_BACK);
        aluRes = ((MemWb) registers.get("MemWb")).getAluResult();
        memoryData = ((MemWb) registers.get("MemWb")).getMemoryData();
        memoryToRegister = ((MemWb) registers.get("MemWb")).isMemoryToRegister();
        writeAddress = ((MemWb) registers.get("MemWb")).getRd();
        registerWrite = ((MemWb) registers.get("MemWb")).isRegisterWrite();
    }

    public void finishInstruction() {
        instructionToFinish.setInstructionStage(InstructionStage.FINISHED);
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
        return String.format("Write Back:%n result: %s%n", result);
    }
}