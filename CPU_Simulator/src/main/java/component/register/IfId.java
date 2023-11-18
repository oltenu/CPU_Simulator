package component.register;

import helper.instruction.Instruction;
import lombok.Data;
import component.unit.InstructionFetch;
import component.unit.Unit;

import java.util.HashMap;
import java.util.Map;

import static helper.CodeLoader.ZERO_INSTRUCTION;
import static helper.instruction.Instruction.ZERO_16;

@Data
public class IfId implements Register {
    private Map<String, Unit> units;
    private Map<String, Register> registers;
    private Instruction instruction;
    private String pcIncrement;

    public IfId() {
        units = new HashMap<>();
        registers = new HashMap<>();
        instruction = ZERO_INSTRUCTION;
        pcIncrement = ZERO_16;
    }

    @Override
    public void update() {
        instruction = ((InstructionFetch) units.get("InstructionFetch")).getInstruction();
        pcIncrement = ((InstructionFetch) units.get("InstructionFetch")).getPcIncrement();
    }

    @Override
    public void initializeUnits(Map<String, Unit> units) {
        this.units = units;
    }

    @Override
    public void initializeRegisters(Map<String, Register> registers) {
        this.registers = registers;
    }

    @Override
    public String toString() {
        return "IfId{" +
                "instruction=" + instruction +
                ", pcIncrement='" + pcIncrement + '\'' +
                '}' + '\n';
    }
}
