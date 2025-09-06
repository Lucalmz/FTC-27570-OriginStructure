package pedroPathing.Methods;

import static pedroPathing.Methods.ExampleTeleOpLibrary.*;

import com.bear27570.yuan.BotFactory.Action;
import com.bear27570.yuan.BotFactory.Gamepad.GamepadEx;
import com.bear27570.yuan.BotFactory.Motor.MotorEx;
import com.bear27570.yuan.BotFactory.Servo.ServoEx;
import com.bear27570.yuan.BotFactory.Servo.CRServoEx;
import static com.bear27570.yuan.BotFactory.Action.*;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static pedroPathing.Hardware.*;
import static pedroPathing.States.*;


import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;

public class ExampleAlgorithmLibrary {
    public static void init(Gamepad OriginGamepad,HardwareMap OriginhardwareMap){
        Claw = new ServoEx.ServoBuilder("Claw",0,false,OriginhardwareMap)
                .addAction(Open,0.3)
                .addAction(Close,0.7)
                .addAction(OpenMore,0)
                .addAction(LaxClose,0.55)
                .setSwitcher(Open,Close)
                .build();
        Example = new CRServoEx.ServoBuilder("Example",0,false,OriginhardwareMap)
                .build();
        Lift = new MotorEx.MotorBuilder("LeftMotor",0,true,OriginhardwareMap)
                .addMotor("RightMotor",false)
                .addAction(Up,1200)
                .build();
        gamepad = GamepadEx.GetGamepadEx(OriginGamepad);
        follower = Follower.getInstance(OriginhardwareMap, FConstants.class, LConstants.class);
    }
    public static Runnable update(){
        gamepad.update();
        if(!AutoPilotIsRunning) {
            while(StructureActing) {
                    follower.setTeleOpMovementVectors(-gamepad.left_stick_y.PressPosition(), -gamepad.left_stick_x.PressPosition(), -gamepad.right_stick_x.PressPosition());
                    follower.update();
            }
            Update();
        }
        return null;
    }

    public static Runnable ClawController(Action action){
        Claw.act(action);
        return null;
    }
    private ExampleAlgorithmLibrary(){}
}
