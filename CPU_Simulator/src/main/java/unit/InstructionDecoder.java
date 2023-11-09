package unit;

import helper.instruction.*;
import lombok.Data;
import register.*;

import java.util.*;

import static helper.instruction.Instruction.ZERO_16;
import static helper.instruction.Instruction.ZERO_3;

@Data
public class InstructionDecoder implements Unit {
    private Map<String, Unit> units;
    private Map<String, Register> registers;
    private String[] dataRegisters;
    private String writeAddress;
    private String writeData;
    private Instruction instruction;
    private String readData1;
    private String readData2;
    private String extendedImmediate;
    private String func;
    private String rd;
    private String rt;
    private boolean sa;
    private boolean extendOperation;
    private boolean registerWrite;

    public InstructionDecoder() {
        units = new HashMap<>();
        registers = new HashMap<>();
        dataRegisters = new String[8];
        Arrays.fill(dataRegisters, ZERO_16);
        writeAddress = ZERO_3;
        writeData = ZERO_16;
        instruction = new Instruction(ZERO_16);
        readData1 = ZERO_16;
        readData2 = ZERO_16;
        extendedImmediate = ZERO_16;
        func = ZERO_3;
        rd = ZERO_3;
        rt = ZERO_3;
    }

    @Override
    public void run() {
        if (extendOperation) {
            extendedImmediate = Character.toString(instruction.getInstruction().charAt(9))
                    + instruction.getInstruction().charAt(9)
                    + instruction.getInstruction().charAt(9)
                    + instruction.getInstruction().charAt(9)
                    + instruction.getInstruction().charAt(9)
                    + instruction.getInstruction().charAt(9)
                    + instruction.getInstruction().charAt(9)
                    + instruction.getInstruction().charAt(9)
                    + instruction.getInstruction().charAt(9)
                    + instruction.getInstruction().substring(9);
        } else {
            extendedImmediate = "000000000" + instruction.getInstruction().substring(9);
        }

        if (registerWrite)
            dataRegisters[Integer.parseInt(writeAddress, 2)] = writeData;

        readData1 = dataRegisters[Integer.parseInt(instruction.getInstruction().substring(3, 6), 2)];
        readData2 = dataRegisters[Integer.parseInt(instruction.getInstruction().substring(6, 9), 2)];
        func = instruction.getInstruction().substring(13);
        sa = instruction.getInstruction().charAt(12) == '1';
        rt = instruction.getInstruction().substring(6, 9);
        rd = instruction.getInstruction().substring(9, 12);
    }

    @Override
    public void update() {
        instruction = ((IfId) registers.get("IfId")).getInstruction();
        instruction.setInstructionStage(InstructionStage.DECODE);
        extendOperation = ((Control) units.get("Control")).isExtendedOperation();
        writeAddress = ((MemWb) registers.get("MemWb")).getRd();
        writeData = ((WriteBack) units.get("WriteBack")).getResult();
        registerWrite = ((MemWb) registers.get("MemWb")).isRegisterWrite();
    }

    @Override
    public void initializeUnits(Map<String, Unit> units) {
        this.units = units;
    }

    @Override
    public void initializeRegisters(Map<String, Register> registers) {
        this.registers = registers;
    }
}
