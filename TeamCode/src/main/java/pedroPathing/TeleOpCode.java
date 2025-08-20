package pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import  com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import static pedroPathing.Hardware.*;

import pedroPathing.Methods.ExampleAlgorithmLibrary;
import pedroPathing.Methods.ExampleTeleOpLibrary;
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
    private final Pose startPose = new Pose(0,0,0);
    @Override
    public void init() {
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
        ExampleAlgorithmLibrary.update();
        /* Telemetry Outputs of our Follower */
        telemetry.addData("X", follower.getPose().getX());
        telemetry.addData("Y", follower.getPose().getY());
        telemetry.addData("Heading in Degrees", Math.toDegrees(follower.getPose().getHeading()));

        /* Update Telemetry to the Driver Hub */
        telemetry.update();

    }

    @Override
    public void stop() {
        States.RequestAutoPilotStop = true;
        try {
            ExampleTeleOpLibrary.AutoPilotThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}