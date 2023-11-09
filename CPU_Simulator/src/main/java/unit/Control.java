package unit;

import lombok.Data;
import register.*;

import java.util.*;

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
        instruction = "000";
        aluOperation = "000";
    }

    @Override
    public void run() {
        aluOperation = "000";
        registerDestination = false;
        extendedOperation = false;
        branch = false;
        jump = false;
        memoryWrite = false;
        memoryToRegister = false;
        registerWrite = false;
        aluSource = false;

        switch (instruction) {
            case "000": //R
                registerDestination = true;
                registerWrite = true;
                aluOperation = "000";

                break;
            case "001": //ADDI
                extendedOperation = true;
                aluSource = true;
                registerWrite = true;
                aluOperation = "100";

                break;
            case "010": //LW
                extendedOperation = true;
                aluSource = true;
                memoryToRegister = true;
                registerWrite = true;
                aluOperation = "100";

                break;
            case "011": //SW
                extendedOperation = true;
                aluSource = true;
                memoryWrite = true;
                aluOperation = "100";

                break;
            case "100": //BEQ
                extendedOperation = true;
                branch = true;
                aluOperation = "001";

                break;
            case "101": //ORI
                aluSource = true;
                registerWrite = true;
                aluOperation = "101";

                break;
            case "110": //SLTI
                extendedOperation = true;
                aluSource = true;
                registerWrite = true;
                aluOperation = "110";

                break;
            case "111": //J
                jump = true;

                break;
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
}
