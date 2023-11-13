package helper;

import helper.instruction.Instruction;

import java.io.*;
import java.util.*;

import static helper.instruction.Instruction.ZERO_16;

public class CodeLoader {
    public static final Instruction ZERO_INSTRUCTION = new Instruction(ZERO_16, -1);


    public static List<Instruction> loadCode(String filePath) {
        List<Instruction> codeList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                codeList.add(new Instruction(line.replaceAll("_", "")));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return codeList;
    }
}