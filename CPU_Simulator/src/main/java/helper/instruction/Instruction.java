package helper.instruction;

import lombok.*;

@Data
@EqualsAndHashCode
public class Instruction {
    private String instruction;
    private InstructionStage instructionStage;

    public Instruction(String instruction) {
        this.instruction = instruction;
        this.instructionStage = InstructionStage.FETCH;
    }
}
