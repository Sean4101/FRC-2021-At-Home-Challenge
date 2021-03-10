package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TheGreatCylinder extends SubsystemBase {
    private DoubleSolenoid m_valve1;
    private DoubleSolenoid m_valve2;

    public TheGreatCylinder(DoubleSolenoid CylinderValve1, DoubleSolenoid CylinderValve2) {
        m_valve1 = CylinderValve1;
        m_valve2 = CylinderValve2;
    }


    public void Push() {
        m_valve1.set(Value.kForward);
        m_valve2.set(Value.kReverse);
    }
    public void Pull() {
        m_valve1.set(Value.kReverse);
        m_valve2.set(Value.kForward);
    }
}
