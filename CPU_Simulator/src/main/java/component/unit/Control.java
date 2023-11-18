package component.unit;

import lombok.Data;
import component.register.*;

import java.util.*;

import static helper.instruction.Instruction.ZERO_3;

@Data
public class Control implements Unit {
    private Map<String, Unit> units;
    private Map<String, Register> registers;
    private String instruction;
    private String aluOperation;
    private boolean registerDestination;
    private boolean extendedOperation;
    private boolean branch;
    private boolean jump;
    private boolean aluSource;
    private boolean memoryWrite;
    private boolean memoryToRegister;
    private boolean registerWrite;

    public Control() {
        units = new HashMap<>();
        registers = new HashMap<>();
        instruction = ZERO_3;
        aluOperation = ZERO_3;
    }

    @Override
    public void run() {
        aluOperation = ZERO_3;
        registerDestination = false;
        extendedOperation = false;
        branch = false;
        jump = false;
        memoryWrite = false;
        memoryToRegister = false;
        registerWrite = false;
        aluSource = false;

        switch (instruction) {
            case "000" -> { //R
                registerDestination = true;
                registerWrite = true;
                aluOperation = "000";
            }
            case "001" -> { //ADDI
                extendedOperation = true;
                aluSource = true;
                registerWrite = true;
                aluOperation = "100";
            }
            case "010" -> { //LW
                extendedOperation = true;
                aluSource = true;
                memoryToRegister = true;
                registerWrite = true;
                aluOperation = "100";
            }
            case "011" -> { //SW
                extendedOperation = true;
                aluSource = true;
                memoryWrite = true;
                aluOperation = "100";
            }
            case "100" -> { //BEQ
                extendedOperation = true;
                branch = true;
                aluOperation = "001";
            }
            case "101" -> { //ORI
                aluSource = true;
                registerWrite = true;
                aluOperation = "101";
            }
            case "110" -> { //SLTI
                extendedOperation = true;
                aluSource = true;
                registerWrite = true;
                aluOperation = "110";
            }
            case "111" -> //J
                    jump = true;
        }
    }

    @Override
    public void update() {
        instruction = ((IfId) registers.get("IfId")).getInstruction().getInstruction().substring(0, 3);
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
        return String.format("Control:%n registerDestination: %s %n extendedImmediate: %s %n aluSource: %s %n branch: %s %n jump: %s %n aluOperation: %s %n memoryWrite: %s %n memoryToRegister: %s %n registerWrite: %s%n", registerDestination, extendedOperation, aluSource, branch, jump, aluOperation, memoryWrite, memoryToRegister, registerWrite);
    }
}
