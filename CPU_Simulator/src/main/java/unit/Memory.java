package unit;

import helper.instruction.Instruction;
import lombok.Data;
import register.Register;

import java.util.HashMap;
import java.util.Map;

import static helper.instruction.Instruction.ZERO_16;

@Data
public class Memory implements Unit {
    private Map<String, Unit> units;
    private Map<String, Register> registers;
    private Instruction instruction;
    private String aluResultIn;
    private String readData2;
    private String aluResultOut;
    private String memoryData;
    private boolean memoryWrite;

    public Memory() {
        units = new HashMap<>();
        registers = new HashMap<>();
        instruction = new Instruction(ZERO_16);
        aluResultIn = ZERO_16;
        readData2 = ZERO_16;
        aluResultOut = ZERO_16;
        memoryData = ZERO_16;
    }

    @Override
    public void run() {
    }

    @Override
    public void update() {
    }

    @Override
    public void initializeUnits(Map<String, Unit> units) {

    }

    @Override
    public void initializeRegisters(Map<String, Register> registers) {

    }
}
