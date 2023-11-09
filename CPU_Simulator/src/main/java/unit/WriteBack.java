package unit;

import helper.instruction.Instruction;
import lombok.Data;
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