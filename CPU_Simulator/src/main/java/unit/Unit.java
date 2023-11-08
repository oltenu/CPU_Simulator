package unit;

import register.Register;

import java.util.Map;

public interface Unit {
    void run();

    void update();

    void initializeUnits(Map<String, Unit> units);
    void initializeRegisters(Map<String, Register> registers);
}
