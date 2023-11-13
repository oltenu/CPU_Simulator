package component;

import component.register.Register;
import component.unit.Unit;

import java.util.Map;

public interface Component {
    void update();

    void initializeUnits(Map<String, Unit> units);

    void initializeRegisters(Map<String, Register> registers);
}
