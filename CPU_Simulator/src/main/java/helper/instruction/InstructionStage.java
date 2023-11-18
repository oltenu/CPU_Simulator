package helper.instruction;

import lombok.Getter;

@Getter
public enum InstructionStage {
    FETCH("Instruction Fetch"),
    DECODE("Instruction Decoder"),
    EXECUTE("Execute"),
    MEMORY("Memory"),
    WRITE_BACK("Write Back"),
    FINISHED("Finished");

    private final String stageName;

    InstructionStage(String stageName) {
        this.stageName = stageName;
    }
}
