package component.unit;

import helper.CodeLoader;
import helper.instruction.*;
import lombok.Data;
import component.register.*;

import java.util.*;

import static helper.CodeLoader.ZERO_INSTRUCTION;
import static helper.instruction.Instruction.ZERO_16;

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
        instruction = ZERO_INSTRUCTION;
        pcIncrement = ZERO_16;
        branchAddress = ZERO_16;
        jumpAddress = ZERO_16;
        counter = -1;
    }

    @Override
    public void run() {
        jumpAddress = ((IfId) registers.get("IfId")).getPcIncrement().substring(0, 2)
                + ((IfId) registers.get("IfId")).getInstruction().getInstruction().substring(3);
        jump = ((Control) units.get("Control")).isJump();

        switch (pcSource + "-" + jump) {
            case "true-true", "false-true":
                counter = Integer.parseInt(jumpAddress, 2);

                break;
            case "true-false":
                counter = Integer.parseInt(branchAddress, 2);

                break;
            case "false-false":
                counter = counter + 1;

                break;
        }
        if (instructions.size() > counter)
            instruction = instructions.get(counter);
        else
            instruction = ZERO_INSTRUCTION;

        pcIncrement = String.format("%016d", Integer.valueOf(Integer.toBinaryString(counter + 1)));
    }

    @Override
    public void update() {
        setBranchAddress(((ExMem) registers.get("ExMem")).getBranchAddress());
        boolean branch = ((ExMem) registers.get("ExMem")).isBranch();
        boolean zero = ((ExMem) registers.get("ExMem")).isZero();
        pcSource = branch && zero;
    }

    public void initializeUnits(Map<String, Unit> units) {
        this.units = units;
    }

    @Override
    public void initializeRegisters(Map<String, Register> registers) {
        this.registers = registers;
    }

    public List<Instruction> loadCode(String filePath) {
        instructions = CodeLoader.loadCode(filePath);

        return instructions;
    }

    @Override
    public String toString() {
        return String.format("Instruction Fetch:%n %s %n %s %n%n", this.getInstruction(), this.getPcIncrement());
    }
}
