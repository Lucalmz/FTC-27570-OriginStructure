package pedroPathing;

import android.provider.SyncStateContract;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.util.Constants;
import  com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import pedroPathing.constants.ExampleAlgorithmLibrary;
import pedroPathing.constants.ExampleTeleOpLibrary;
import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;

/**
 * This is an example teleop that showcases movement and robot-centric driving.
 *
 * @author Luca Li - 27570 B.E.A.R
 * @version 1.0, 2025/7/17
 */

@TeleOp(name = "Example TeleOpCode with thread control", group = "Examples")
public class TeleOpCode extends OpMode {
    private Follower follower;
    private final Pose startPose = new Pose(0,0,0);
    private boolean IsAutoPilotRunning = false;

    @Override
    public void init() {
        follower = new Follower(hardwareMap, FConstants.class, LConstants.class);
        follower.setStartingPose(startPose);
        ExampleAlgorithmLibrary.init(gamepad1,hardwareMap);
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        follower.startTeleopDrive();
    }

    @Override
    public void loop() {
        Thread autoPilotThread = ExampleTeleOpLibrary.AutoPilotThread;
        if(autoPilotThread.getState()!= Thread.State.NEW){
            IsAutoPilotRunning = true;
            return;
        }
        Thread ControllerThread = new Thread(()->{
            if(IsAutoPilotRunning){
                Thread.currentThread().interrupt();
            }
            ExampleAlgorithmLibrary.update();
        });
        Thread ChassisControlThread = new Thread(() ->{
            while(!(ControllerThread.getState() == Thread.State.TERMINATED)) {
                follower.setTeleOpMovementVectors(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, true);
                follower.update();
                if(IsAutoPilotRunning||Thread.currentThread().isInterrupted()){
                    follower.breakFollowing();
                    try {
                        autoPilotThread.join();
                    } catch (InterruptedException ignored) {}
                    break;
                }
            }
        });
        ChassisControlThread.start();
        ControllerThread.start();
        /* Telemetry Outputs of our Follower */
        telemetry.addData("X", follower.getPose().getX());
        telemetry.addData("Y", follower.getPose().getY());
        telemetry.addData("Heading in Degrees", Math.toDegrees(follower.getPose().getHeading()));

        /* Update Telemetry to the Driver Hub */
        telemetry.update();

    }

    @Override
    public void stop() {
    }
}