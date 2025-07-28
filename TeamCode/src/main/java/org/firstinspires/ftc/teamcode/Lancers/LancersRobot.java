package org.firstinspires.ftc.teamcode.Lancers;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
@Config
public class LancersRobot extends LinearOpMode {
    public static final String TAG = "LancerTeleOp";

    private long currentRunTimeStamp = -1;
    private long timeStampAtLastOpModeRun = -1;

    @Override
    public void runOpMode() throws InterruptedException {

        // initialize motor objects
        final DcMotor leftFront = hardwareMap.dcMotor.get(LancersBotConfig.FRONT_LEFT_MOTOR);
        final DcMotor leftRear = hardwareMap.dcMotor.get(LancersBotConfig.REAR_LEFT_MOTOR);
        final DcMotor rightFront = hardwareMap.dcMotor.get(LancersBotConfig.FRONT_RIGHT_MOTOR);
        final DcMotor rightRear = hardwareMap.dcMotor.get(LancersBotConfig.REAR_RIGHT_MOTOR);

        // set motor directions
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftRear.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightRear.setDirection(DcMotorSimple.Direction.FORWARD);

        final DcMotor slidesMotor = hardwareMap.dcMotor.get(LancersBotConfig.SLIDES_MOTOR);

    }
}
