package unit;

import helper.instruction.Instruction;
import lombok.Data;
import register.Register;

import java.util.HashMap;
import java.util.Map;

@Data
public class Execute implements Unit {
    private Map<String, Unit> units;
    private Map<String, Register> registers;
    private Instruction instruction;
    private String readData1;
    private String readData2;
    private String extendedImmediate;
    private String func;
    private String branchAddress;
    private String aluRes;
    private String rWa;
    private String rd;
    private String rt;
    private String pcIncrement;
    private String aluOp;
    private boolean aluSrc;
    private boolean sa;
    private boolean registerDestination;
    private boolean zero;

    public Execute(){
        units = new HashMap<>();
        registers = new HashMap<>();
        instruction = new Instruction("0000000000000000");
        readData1 = "0000000000000000";
        readData2 = "0000000000000000";
        extendedImmediate = "0000000000000000";
        func = "0000000000000000";
        branchAddress = "0000000000000000";
        aluRes = "0000000000000000";
        rWa = "0000000000000000";
        rd = "0000000000000000";
        rt = "0000000000000000";
        pcIncrement = "0000000000000000";
        aluOp = "0000000000000000";
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
