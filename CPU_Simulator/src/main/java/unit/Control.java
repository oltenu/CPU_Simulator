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

    public Control(){
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

        switch (instruction){
            case "000":
                registerDestination = true;
                registerWrite = true;
                aluOperation = "000";

                break;
            case "001":
                extendedOperation = true;
                aluSource = true;
                registerWrite = true;
                aluOperation = "100";

                break;
            case "010":
                extendedOperation = true;
                aluSource = true;
                memoryToRegister = true;
                registerWrite = true;
                aluOperation = "100";

                break;
            case "011":
                extendedOperation = true;
                aluSource = true;
                memoryWrite = true;
                aluOperation = "100";

                break;
            case "100":
                extendedOperation = true;
                branch = true;
                aluOperation = "001";

                break;
            case "101":
                aluSource = true;
                registerWrite = true;
                aluOperation = "101";

                break;
            case "110":
                extendedOperation = true;
                aluSource = true;
                registerWrite = true;
                aluOperation = "110";

                break;
            case "111":
                jump = true;

                break;
        }
    }

    @Override
    public void update() {
        instruction = ((IfId) registers).getInstruction().getInstruction().substring(0, 3);
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
