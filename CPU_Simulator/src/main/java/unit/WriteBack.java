package unit;

import helper.instruction.Instruction;
import lombok.Data;
import register.Register;

import java.util.HashMap;
import java.util.Map;

@Data
public class WriteBack implements Unit {
    private Map<String, Unit> units;
    private Map<String, Register> registers;
    private Instruction instruction;
    private String aluRes;
    private String memoryData;
    private String result;
    private boolean memoryToRegister;

    public WriteBack(){
        units = new HashMap<>();
        registers = new HashMap<>();
        instruction = new Instruction("0000000000000000");
        aluRes = "0000000000000000";
        memoryData = "0000000000000000";
        result = "0000000000000000";
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