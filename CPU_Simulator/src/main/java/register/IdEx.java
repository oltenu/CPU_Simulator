package register;

import helper.instruction.Instruction;
import lombok.Data;
import unit.Unit;

import java.util.HashMap;
import java.util.Map;

@Data
public class IdEx implements Register{
    private Map<String, Unit> units;
    private Map<String, Register> registers;
    private Instruction instruction;
    private String readData1;
    private String readData2;
    private String extendedImmediate;
    private String func;
    private String rd;
    private String rt;
    private String pcIncrement;
    private String aluOperation;
    private boolean registerDestination;
    private boolean aluSource;
    private boolean branch;
    private boolean memoryWrite;
    private boolean memoryToRegister;
    private boolean registerWrite;
    private boolean sa;

    public IdEx(){
        units = new HashMap<>();
        registers = new HashMap<>();
        instruction = new Instruction("0000000000000000");
        readData1 = "0000000000000000";
        readData2 = "0000000000000000";
        extendedImmediate = "0000000000000000";
        func = "0000000000000000";
        rd = "0000000000000000";
        rt = "0000000000000000";
        pcIncrement = "0000000000000000";
        aluOperation = "0000000000000000";
    }

    @Override
    public void update() {

    }
}
