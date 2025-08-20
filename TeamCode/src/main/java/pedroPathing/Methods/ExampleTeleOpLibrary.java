package pedroPathing.Methods;

import static pedroPathing.Hardware.*;
import static pedroPathing.States.*;
import com.pedropathing.follower.Follower;

public class ExampleTeleOpLibrary {
    public static Thread AutoPilotThread;
    public static Runnable AutoPilot(Follower Originfollower){
        follower.breakFollowing();
        follower = Originfollower;
        AutoPilotThread = new Thread(() -> {
            /*Write your autopilot init code here*/
            AutoPilotIsRunning=true;
            while (!gamepad.left_stick_x.justStartUsed()&&!RequestAutoPilotStop) {
                /*Write your loop code here*/
                gamepad.update();
            }
            synchronized (lock) {
                follower.breakFollowing();
                follower.startTeleopDrive();
                AutoPilotIsRunning=false;
            }
        });
        AutoPilotThread.start();
        return null;
    }
    public static void Cross(){
        if(gamepad.cross.Pressed()){
            Claw.Switch();
        }
    }
    public static void Circle(){
        if(gamepad.circle.Pressed()){

        }
    }

    public static void Square(){
        if(gamepad.square.Pressed()){

        }
    }

    public static void Triangle(){
        if(gamepad.triangle.Pressed()){

        }
    }

    public static void LeftBumper(){
        if(gamepad.left_bumper.Pressed()){

        }
    }

    public static void RightBumper(){
        if(gamepad.right_bumper.Pressed()){

        }
    }

    public static void Dpad_up(){
        if(gamepad.dpad_up.Pressed()){

        }
    }

    public static void Dpad_down(){
        if(gamepad.dpad_down.Pressed()){

        }
    }

    public static void Dpad_left(){
        if(gamepad.dpad_left.Pressed()){

        }
    }

    public static void Dpad_right(){
        if(gamepad.dpad_right.Pressed()){

        }
    }

    public static void LeftStickButton(){
        if(gamepad.left_stick_button.Pressed()){

        }
    }

    public static void RightStickButton(){
        if(gamepad.right_stick_button.Pressed()){

        }
    }

    public static void Share(){
        if(gamepad.share.Pressed()){

        }
    }

    public static void Options(){
        if(gamepad.options.Pressed()){

        }
    }

    public static void PS(){
        if(gamepad.ps.Pressed()){
        }
    }
    public static void TouchpadButton(){
        if(gamepad.touch_pad.Pressed()){

        }
    }
    public static void Touchpad_finger_1(){
        if(gamepad.touchpad_finger_1.Pressed()){

        }
    }

    public static void Touchpad_finger_2(){
        if(gamepad.touchpad_finger_2.Pressed()){

        }
    }
    public static void Update(){
        // 按键检测
        Cross();
        Circle();
        Square();
        Triangle();
        LeftBumper();
        RightBumper();
        // D-pad 检测
        Dpad_up();
        Dpad_down();
        Dpad_left();
        Dpad_right();

        // 摇杆按钮检测
        LeftStickButton();
        RightStickButton();

        // 功能按钮检测
        Share();
        Options();
        PS();

        // 触控板按钮和手指检测
        TouchpadButton();
        Touchpad_finger_1();
        Touchpad_finger_2();
    }
    private ExampleTeleOpLibrary(){}
}
