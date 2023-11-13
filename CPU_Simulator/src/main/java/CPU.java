import helper.instruction.Instruction;
import helper.instruction.InstructionStage;
import component.register.*;
import component.unit.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CPU {
    private static InstructionFetch instructionFetch;
    private static Control control;
    private static InstructionDecoder instructionDecoder;
    private static Execute execute;
    private static Memory memory;
    private static WriteBack writeBack;
    private static IfId ifId;
    private static IdEx idEx;
    private static ExMem exMem;
    private static MemWb memWb;
    private static int clock = 0;
    private static List<Instruction> instructions;

    public static void main(String[] args) {
        // Units
        instructionFetch = new InstructionFetch();
        control = new Control();
        instructionDecoder = new InstructionDecoder();
        execute = new Execute();
        memory = new Memory();
        writeBack = new WriteBack();

        // Registers
        ifId = new IfId();
        idEx = new IdEx();
        exMem = new ExMem();
        memWb = new MemWb();

        // Maps
        Map<String, Unit> units = new HashMap<>();
        units.put("InstructionFetch", instructionFetch);
        units.put("Control", control);
        units.put("InstructionDecoder", instructionDecoder);
        units.put("Execute", execute);
        units.put("Memory", memory);
        units.put("WriteBack", writeBack);

        Map<String, Register> registers = new HashMap<>();
        registers.put("IfId", ifId);
        registers.put("IdEx", idEx);
        registers.put("ExMem", exMem);
        registers.put("MemWb", memWb);

        // Injecting Maps
        for (Unit unit : units.values()) {
            unit.initializeUnits(units);
            unit.initializeRegisters(registers);
        }

        for (Register register : registers.values()) {
            register.initializeUnits(units);
            register.initializeRegisters(registers);
        }

        Instruction.resetGlobalId();

        instructions = instructionFetch.loadCode("src/main/resources/program1.txt");

        while (checkInstructions(instructions)) {
            instructionFetch.update();
            control.update();
            instructionDecoder.update();
            execute.update();
            memory.update();
            writeBack.update();

            control.run();
            instructionFetch.run();
            instructionDecoder.run();
            execute.run();
            memory.run();
            writeBack.run();

            memWb.update();
            exMem.update();
            idEx.update();
            ifId.update();

            printFunction();
            waitForEnterKey();

            clock++;
        }

        System.out.println("FINISHED");
    }

    private static boolean checkInstructions(List<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            if (instruction.getInstructionStage() != InstructionStage.FINISHED)
                return true;
        }

        return false;
    }

    private static void waitForEnterKey() {
        System.out.println("Press Enter to continue...");
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNextLine()) {
        }
        scanner.nextLine();
    }

    private static void printFunction() {
        System.out.printf("CLOCK: %d%n%n", clock);
        System.out.println("Instructions:");
        instructions.forEach(System.out::println);
        System.out.println();
        System.out.println(instructionFetch);
        System.out.println(ifId);
        System.out.println(control);
        System.out.println(instructionDecoder);
        System.out.println(idEx);
        System.out.println(execute);
        System.out.println(exMem);
        System.out.println(memory);
        System.out.println(memWb);
        System.out.println(writeBack);
        instructionDecoder.printRegisters();
        memory.printMemory();
    }
}
