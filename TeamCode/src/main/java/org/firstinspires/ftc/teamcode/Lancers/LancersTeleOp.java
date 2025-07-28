package org.firstinspires.ftc.teamcode.Lancers;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
@Config
public class LancersTeleOp extends LinearOpMode {
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


        /*
        // These are only needed if we use slides
        final DcMotor slidesMotor = hardwareMap.dcMotor.get(LancersBotConfig.SLIDES_MOTOR);
        slidesMotor.setDirection(DcMotorSimple.Direction.FORWARD);
         */


        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            currentRunTimeStamp = System.currentTimeMillis();

            final double speedMultiplier = gamepad1.a ? 1.0d : 0.8d;

            // Gamepad positions; Motors are swapped
            final double ly = -respectDeadZones(gamepad1.left_stick_y) * speedMultiplier; // Remember, Y stick value is reversed
            final double lx = respectDeadZones(gamepad1.left_stick_x) * speedMultiplier; // Counteract imperfect strafing
            final double rx = respectDeadZones(gamepad1.right_stick_x) * speedMultiplier;

            final double denominator = Math.max(Math.abs(ly) + Math.abs(lx) + Math.abs(rx), 1);

            final double frontLeftPower = (ly + lx + rx) / denominator;
            final double backLeftPower = (ly - lx + rx) / denominator;
            final double frontRightPower = (ly - lx - rx) / denominator;
            final double backRightPower = (ly + lx - rx) / denominator;

            leftFront.setPower(-frontLeftPower*0.9);
            leftRear.setPower(-backLeftPower*0.9);
            rightFront.setPower(frontRightPower*0.9);
            rightRear.setPower(backRightPower*0.9);

        }
    }

    public static final double DEAD_ZONE_LIMIT = 0.15d;

    /**
     * Stick return is unreliable near inside, toss signals that are less than a threshold to maintain stationary behaviour when sticks may or may not be being minimally actuated by using this method to wrap a double value.
     * @param input
     * @return
     */

    public static double respectDeadZones(double input) {
        if (Math.abs(input) < DEAD_ZONE_LIMIT) {
            return 0.0d;
        } else {
            return input;
        }
    }
}
