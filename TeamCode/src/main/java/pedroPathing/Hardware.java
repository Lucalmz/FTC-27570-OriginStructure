package pedroPathing;

import com.bear27570.yuan.BotFactory.Gamepad.GamepadEx;
import com.bear27570.yuan.BotFactory.Servo.ServoFactory;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import pedroPathing.Methods.ExampleAlgorithmLibrary;

public class Hardware {
    public static ServoFactory Claw;

    public static GamepadEx gamepad;

    public static Follower follower;
    private Hardware(){}
}
