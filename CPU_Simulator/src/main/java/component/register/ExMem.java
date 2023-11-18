package component.register;

import helper.instruction.Instruction;
import lombok.Data;
import component.unit.Execute;
import component.unit.Unit;

import java.util.HashMap;
import java.util.Map;

import static helper.CodeLoader.ZERO_INSTRUCTION;
import static helper.instruction.Instruction.ZERO_16;
import static helper.instruction.Instruction.ZERO_3;

@Data
public class ExMem implements Register {
    private Map<String, Unit> units;
    private Map<String, Register> registers;
    private Instruction instruction;
    private String branchAddress;
    private String aluResult;
    private String rd;
    private String readData2;
    private boolean branch;
    private boolean memoryWrite;
    private boolean memoryToRegister;
    private boolean registerWrite;
    private boolean zero;

    public ExMem() {
        units = new HashMap<>();
        registers = new HashMap<>();
        instruction = ZERO_INSTRUCTION;
        branchAddress = ZERO_16;
        aluResult = ZERO_16;
        rd = ZERO_3;
        readData2 = ZERO_16;
    }

    @Override
    public void update() {
        instruction = ((IdEx) registers.get("IdEx")).getInstruction();
        branchAddress = ((Execute) units.get("Execute")).getBranchAddress();
        aluResult = ((Execute) units.get("Execute")).getAluResult();
        rd = ((Execute) units.get("Execute")).getRWa();
        readData2 = ((IdEx) registers.get("IdEx")).getReadData2();
        branch = ((IdEx) registers.get("IdEx")).isBranch();
        memoryWrite = ((IdEx) registers.get("IdEx")).isMemoryWrite();
        memoryToRegister = ((IdEx) registers.get("IdEx")).isMemoryToRegister();
        registerWrite = ((IdEx) registers.get("IdEx")).isRegisterWrite();
        zero = ((Execute) units.get("Execute")).isZero();
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
        return "ExMem{" +
                ", instruction=" + instruction +
                ", branchAddress='" + branchAddress + '\'' +
                ", aluResult='" + aluResult + '\'' +
                ", rd='" + rd + '\'' +
                ", readData2='" + readData2 + '\'' +
                ", branch=" + branch +
                ", memoryWrite=" + memoryWrite +
                ", memoryToRegister=" + memoryToRegister +
                ", registerWrite=" + registerWrite +
                ", zero=" + zero +
                '}' + '\n';
    }
}
