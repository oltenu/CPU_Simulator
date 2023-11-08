package register;

import helper.instruction.Instruction;
import lombok.Data;
import unit.Unit;

import java.util.HashMap;
import java.util.Map;

@Data
public class ExMem implements Register{
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

    public ExMem(){
        units = new HashMap<>();
        registers = new HashMap<>();
        instruction = new Instruction("0000000000000000");
        branchAddress = "0000000000000000";
        aluResult = "0000000000000000";
        rd = "0000000000000000";
        readData2 = "0000000000000000";
    }

    @Override
    public void update() {

    }
}
