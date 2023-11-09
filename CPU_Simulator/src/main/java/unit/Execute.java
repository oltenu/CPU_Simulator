package unit;

import helper.instruction.Instruction;
import helper.instruction.InstructionStage;
import lombok.Data;
import register.IdEx;
import register.Register;

import java.util.HashMap;
import java.util.Map;

import static helper.Operation.*;
import static helper.instruction.Instruction.ZERO_16;
import static helper.instruction.Instruction.ZERO_3;

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
    private String aluResult;
    private String rWa;
    private String rd;
    private String rt;
    private String pcIncrement;
    private String aluOperation;
    private boolean aluSource;
    private boolean sa;
    private boolean registerDestination;
    private boolean zero;

    public Execute() {
        units = new HashMap<>();
        registers = new HashMap<>();
        instruction = new Instruction(ZERO_16);
        readData1 = ZERO_16;
        readData2 = ZERO_16;
        extendedImmediate = ZERO_16;
        func = ZERO_3;
        branchAddress = ZERO_16;
        aluResult = ZERO_16;
        rWa = ZERO_3;
        rd = ZERO_3;
        rt = ZERO_3;
        pcIncrement = ZERO_16;
        aluOperation = ZERO_3;
    }

    @Override
    public void run() {
        String operand;
        String aluControl = ZERO_3;

        if (registerDestination)
            rWa = rd;
        else
            rWa = rt;

        if (aluSource)
            operand = extendedImmediate;
        else
            operand = readData2;

        aluControl = switch (aluOperation) {
            case "000" -> switch (func) {
                case "000" -> "000";
                case "001" -> "001";
                case "010" -> "010";
                case "011" -> "011";
                case "100" -> "100";
                case "101" -> "101";
                case "110" -> "110";
                case "111" -> "111";
                default -> aluControl;
            };
            case "100" -> "000";
            case "001" -> "001";
            case "101" -> "101";
            case "110" -> "110";
            default -> aluControl;
        };

        switch (aluControl) {
            case "000" -> aluResult = addBinary(readData1, operand);
            case "001" -> aluResult = subtractBinary(readData1, operand);
            case "010" -> aluResult = shiftLeftLogic(readData1);
            case "011" -> aluResult = shiftRightLogic(readData1);
            case "100" -> aluResult = AND(readData1, operand);
            case "101" -> aluResult = OR(readData1, operand);
            case "110" -> aluResult = setOnLessThan(readData1, operand);
            case "111" -> aluResult = shiftRightArithmetic(readData1);
        }

        zero = aluResult.equals(ZERO_16);
        branchAddress = addBinary(extendedImmediate, pcIncrement);
    }

    @Override
    public void update() {
        instruction = ((IdEx) registers.get("IdEx")).getInstruction();
        instruction.setInstructionStage(InstructionStage.EXECUTE);
        readData1 = ((IdEx) registers.get("IdEx")).getReadData1();
        readData2 = ((IdEx) registers.get("IdEx")).getReadData2();
        extendedImmediate = ((IdEx) registers.get("IdEx")).getExtendedImmediate();
        func = ((IdEx) registers.get("IdEx")).getFunc();
        sa = ((IdEx) registers.get("IdEx")).isSa();
        registerDestination = ((IdEx) registers.get("IdEx")).isRegisterDestination();
        aluSource = ((IdEx) registers.get("IdEx")).isAluSource();
        aluOperation = ((IdEx) registers.get("IdEx")).getAluOperation();
        rd = ((IdEx) registers.get("IdEx")).getRd();
        rt = ((IdEx) registers.get("IdEx")).getRt();
        pcIncrement = ((IdEx) registers.get("IdEx")).getPcIncrement();
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
