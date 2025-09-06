package pedroPathing;

import com.bear27570.yuan.BotFactory.Gamepad.GamepadEx;
import com.bear27570.yuan.BotFactory.Motor.MotorEx;
import com.bear27570.yuan.BotFactory.Servo.CRServoEx;
import com.bear27570.yuan.BotFactory.Servo.ServoEx;
import com.pedropathing.follower.Follower;

public class Hardware {
    public static ServoEx Claw;
    public static CRServoEx Example;
    public static MotorEx Lift;
    public static GamepadEx gamepad;

    public static Follower follower;
    private Hardware(){}
}
