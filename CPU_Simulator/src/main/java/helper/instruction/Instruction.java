package helper.instruction;

import lombok.*;

@Data
@EqualsAndHashCode
public class Instruction {
    private static int globalId = 1;
    public static final String ZERO_16 = "0000000000000000";
    public static final String ZERO_3 = "000";
    private int id;
    private String instruction;
    private InstructionStage instructionStage;

    public Instruction(String instruction) {
        this.id = globalId++;
        this.instruction = instruction;
        this.instructionStage = InstructionStage.FETCH;
    }

    public Instruction(String instruction, int id) {
        this.id = id;
        this.instruction = instruction;
        this.instructionStage = InstructionStage.FETCH;
    }

    public static void resetGlobalId() {
        globalId = 1;
    }
}
