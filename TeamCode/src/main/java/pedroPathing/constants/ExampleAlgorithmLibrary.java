package pedroPathing.constants;

import static pedroPathing.constants.ExampleTeleOpLibrary.*;

import com.pedropathing.BotFactory.Action;
import com.pedropathing.BotFactory.Gamepad.GamepadEx;
import com.pedropathing.BotFactory.Servo.ServoFactory;
import static com.pedropathing.BotFactory.Action.*;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ExampleAlgorithmLibrary {
    private static HardwareMap hardwareMap;
    public static GamepadEx gamepad;
    public static ServoFactory Claw;

    public static void init(Gamepad OriginGamepad,HardwareMap OriginhardwareMap){
        hardwareMap = OriginhardwareMap;
        Claw = new ServoFactory.ServoBuilder("Claw",0,false,hardwareMap)
                .addAction(Open,0.3)
                .addAction(Close,0.7)
                .addAction(OpenMore,0)
                .addAction(LaxClose,0.55)
                .setSwitcher(Open,Close)
                .build();
        gamepad = GamepadEx.GetGamepadEx(OriginGamepad);
    }
    public static Runnable update(){
        gamepad.update();
        Update();
        return null;
    }
    public static Runnable ClawController(Action action){
        Claw.act(action);
        return null;
    }
}
