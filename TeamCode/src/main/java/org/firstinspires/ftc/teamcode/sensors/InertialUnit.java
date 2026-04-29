package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class InertialUnit {

    public enum Axis{Y, X, Z};
    private final IMU imu;

    public InertialUnit(HardwareMap hardwareMap){
        imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(
                new IMU.Parameters(
                        new RevHubOrientationOnRobot(
                                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
                        )
                )
        );
    }

    public double getAngle(Axis axis, AngleUnit unit){
        YawPitchRollAngles angles = imu.getRobotYawPitchRollAngles();
        return axis == Axis.Y ? angles.getYaw(unit) :
                axis == Axis.X ? angles.getPitch(unit) :
                        axis == Axis.Z ? angles.getRoll(unit) :
                                0;


    }

    public double getHeading(AngleUnit unit){
        return getAngle(Axis.Y, unit);
    }

    public void resetHeading(){
        imu.resetYaw();
    }
}
