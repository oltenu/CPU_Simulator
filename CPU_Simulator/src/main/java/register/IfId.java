package register;

import helper.instruction.Instruction;
import lombok.Data;
import unit.InstructionFetch;
import unit.Unit;

import java.util.HashMap;
import java.util.Map;

import static helper.instruction.Instruction.ZERO_16;

@Data
public class IfId implements Register{
    private Map<String, Unit> units;
    private Map<String, Register> registers;
    private Instruction instruction;
    private String pcIncrement;

    public IfId(){
        units = new HashMap<>();
        registers = new HashMap<>();
        instruction = new Instruction(ZERO_16);
        pcIncrement = ZERO_16;
    }

    @Override
    public void update() {
        instruction = ((InstructionFetch) units.get("InstructionFetch")).getInstruction();
        pcIncrement = ((InstructionFetch) units.get("InstructionFetch")).getPcIncrement();
    }
}
