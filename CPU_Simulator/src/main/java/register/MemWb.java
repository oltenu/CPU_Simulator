package register;

import helper.instruction.Instruction;
import lombok.Data;
import unit.Unit;

import java.util.HashMap;
import java.util.Map;

@Data
public class MemWb implements Register{
    private Map<String, Unit> units;
    private Map<String, Register> registers;
    private Instruction instruction;
    private String aluResult;
    private String memoryData;
    private String rd;
    private boolean memoryToRegister;
    private boolean registerWrite;

    public MemWb(){
        units = new HashMap<>();
        registers = new HashMap<>();
        instruction = new Instruction("0000000000000000");
        aluResult = "0000000000000000";
        memoryData = "0000000000000000";
        rd = "0000000000000000";
    }

    @Override
    public void update() {

    }
}
