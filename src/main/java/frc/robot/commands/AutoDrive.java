package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BaseDrive;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transporting;
import frc.robot.subsystems.StoreBall;
import frc.robot.subsystems.Takeballarm;

public class AutoDrive extends CommandBase {
    private final BaseDrive m_baseDrive;
    private final Shooter m_shooter;
    private final StoreBall m_storeBall;
    private final Transporting m_trans;
    private final Takeballarm m_arm;
    double driveSpeed = .6;
    double startTime;
    boolean done = false;

    int stage = 1;

    public AutoDrive(BaseDrive baseDrive, Shooter shooter, StoreBall storeBall, Transporting trans, Takeballarm arm) {
        m_baseDrive = baseDrive;
        m_shooter = shooter;
        m_storeBall = storeBall;
        m_trans = trans;
        m_arm = arm;

        addRequirements(m_baseDrive);
        addRequirements(m_shooter);
        addRequirements(m_trans);
        addRequirements(m_storeBall);
        addRequirements(m_arm);
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }
    
    @Override
    public void execute(){

       switch (stage) {
            case 1:
                // Do something
                // m_baseDrive.TankDriveRaw(-0.5, -0.5);
                m_arm.armup();
                m_shooter.Shooting();
                if (System.currentTimeMillis() - startTime > 1500) { // If something happened, move on to next stage
                    m_arm.stoparm();
                    startTime = System.currentTimeMillis();
                    stage++;
                }
                break;
            case 2:
                // Do another thing;
                m_storeBall.Spin();
                m_trans.Slap();
                if (System.currentTimeMillis() - startTime > 6000){
                    m_storeBall.StopSpin();
                    m_trans.StopSlapping();
                    m_shooter.StopShooting();
                    startTime = System.currentTimeMillis();
                    stage++;
                }
                break;
            case 3:
                m_baseDrive.TankDriveRaw(0.5,0.5);
                if (System.currentTimeMillis() - startTime > 5000){
                    m_baseDrive.StopAuto();
                    stage++;
                }
                break;
            default:
                // stop all
                m_baseDrive.StopAuto();
                m_shooter.StopShooting();
                m_storeBall.StopSpin();
                m_trans.StopSlapping();
                
                done = true;
                break;
        }
    }
    
    
    public void interrupted() {
        m_baseDrive.StopAuto();
        m_shooter.StopShooting();
        m_storeBall.StopSpin();
        m_trans.StopSlapping();
    }
    @Override
    public boolean isFinished() {
        return done;
    }
}
