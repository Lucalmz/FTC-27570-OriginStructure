package pedroPathing.Methods;

import static pedroPathing.Methods.ExampleTeleOpLibrary.*;

import com.bear27570.yuan.BotFactory.Action;
import com.bear27570.yuan.BotFactory.Gamepad.GamepadEx;
import com.bear27570.yuan.BotFactory.Servo.ServoFactory;
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
        Claw = new ServoFactory.ServoBuilder("Claw",0,false,OriginhardwareMap)
                .addAction(Open,0.3)
                .addAction(Close,0.7)
                .addAction(OpenMore,0)
                .addAction(LaxClose,0.55)
                .setSwitcher(Open,Close)
                .build();
        gamepad = GamepadEx.GetGamepadEx(OriginGamepad);
        follower = Follower.getInstance(OriginhardwareMap, FConstants.class, LConstants.class);
    }
    public static Runnable update(){
        gamepad.update();
        if(!AutoPilotIsRunning) {
            Thread ChassisThread = new Thread(()->{
                while(StructureActing) {
                    follower.setTeleOpMovementVectors(-gamepad.left_stick_y.PressPosition(), -gamepad.left_stick_x.PressPosition(), -gamepad.right_stick_x.PressPosition());
                    follower.update();
                }
            });
            ChassisThread.start();
            synchronized (lock){
                StructureActing=true;
            }
            Update();
            synchronized (lock){
                StructureActing=false;
            }
        }
        return null;
    }

    public static Runnable ClawController(Action action){
        Claw.act(action);
        return null;
    }
    private ExampleAlgorithmLibrary(){}
}
