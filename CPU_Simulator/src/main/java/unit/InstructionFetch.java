package unit;

import helper.CodeLoader;
import helper.instruction.*;
import lombok.Data;
import register.*;

import java.util.*;

@Data
public class InstructionFetch implements Unit {
    private Map<String, Unit> units;
    private Map<String, Register> registers;
    private List<Instruction> instructions;
    private Instruction instruction;
    private String pcIncrement;
    private String branchAddress;
    private String jumpAddress;
    private boolean jump;
    private boolean pcSource;
    private int counter;

    public InstructionFetch() {
        units = new HashMap<>();
        instructions = new ArrayList<>();
        instruction = new Instruction("0000000000000000");
        pcIncrement = "0000000000000000";
        branchAddress = "0000000000000000";
        jumpAddress = "0000000000000000";
        counter = 0;
    }

    @Override
    public void run() {

        switch (pcSource + "-" + jump) {
            case "true-true", "false-true":
                counter = Integer.parseInt(jumpAddress, 2);

                break;
            case "true-false":
                counter = Integer.parseInt(branchAddress, 2);

                break;
            case "false-false":
                counter = Integer.parseInt(pcIncrement, 2);

                break;
        }
        instruction = instructions.get(counter);

        pcIncrement = String.format("%016d", Integer.valueOf(Integer.toBinaryString(counter + 1)));
    }

    @Override
    public void update() {
        setBranchAddress(((ExMem) registers.get("ExMem")).getBranchAddress());
        boolean branch = ((ExMem) registers.get("ExMem")).isBranch();
        boolean zero = ((ExMem) registers.get("ExMem")).isZero();
        pcSource = branch && zero;
        jumpAddress = ((IfId) registers.get("IfId")).getPcIncrement().substring(0, 2)
                + ((IfId) registers.get("IfId")).getInstruction().substring(3);
        jump = ((Control) units.get("Control")).isJump();
    }

    public void initializeUnits(Map<String, Unit> units) {
        this.units = units;
    }

    @Override
    public void initializeRegisters(Map<String, Register> registers) {
        this.registers = registers;
    }

    public void loadCode(String filePath) {
        instructions = CodeLoader.loadCode(filePath);
    }
}
