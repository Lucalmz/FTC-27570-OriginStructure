package pedroPathing;

public class States {
    public static volatile Boolean AutoPilotIsRunning;
    public static volatile Boolean StructureActing;
    public static volatile Boolean RequestAutoPilotStop;
    public static final Object lock = new Object();
    private States(){}
}
