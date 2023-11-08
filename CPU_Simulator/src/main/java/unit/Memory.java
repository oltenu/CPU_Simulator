package unit;

import helper.instruction.Instruction;
import lombok.Data;
import register.Register;

import java.util.HashMap;
import java.util.Map;

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

    public Memory(){
        units = new HashMap<>();
        registers = new HashMap<>();
        instruction = new Instruction("0000000000000000");
        aluResultIn = "0000000000000000";
        readData2 = "0000000000000000";
        aluResultOut = "0000000000000000";
        memoryData = "0000000000000000";
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
