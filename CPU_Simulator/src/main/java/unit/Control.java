package unit;

import helper.instruction.Instruction;
import lombok.Data;
import register.Register;

import java.util.HashMap;
import java.util.Map;

@Data
public class Control implements Unit {
    private Map<String, Register> registers;
    private Instruction instruction;
    private String aluOperation;
    private boolean registerDestination;
    private boolean extendedOperation;
    private boolean branch;
    private boolean jump;
    private boolean memoryWrite;
    private boolean memoryToRegister;
    private boolean registerWrite;

    public Control(){
        registers = new HashMap<>();
        instruction = new Instruction("0000000000000000");
        aluOperation = "0000000000000000";
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
