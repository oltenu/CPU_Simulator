package register;

import helper.instruction.Instruction;
import lombok.Data;
import unit.Unit;

import java.util.HashMap;
import java.util.Map;

import static helper.instruction.Instruction.ZERO_16;
import static helper.instruction.Instruction.ZERO_3;

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
        instruction = new Instruction(ZERO_16);
        branchAddress = ZERO_16;
        aluResult = ZERO_16;
        rd = ZERO_3;
        readData2 = ZERO_16;
    }

    @Override
    public void update() {

    }
}
