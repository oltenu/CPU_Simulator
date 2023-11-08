package register;

import helper.instruction.Instruction;
import lombok.Data;
import unit.Unit;

import java.util.HashMap;
import java.util.Map;

@Data
public class IfId implements Register{
    private Map<String, Unit> units;
    private Map<String, Register> registers;
    private Instruction instruction;
    private String pcIncrement;

    public IfId(){
        units = new HashMap<>();
        registers = new HashMap<>();
        instruction = new Instruction("0000000000000000");
        pcIncrement = "0000000000000000";
    }

    @Override
    public void update() {

    }
}
