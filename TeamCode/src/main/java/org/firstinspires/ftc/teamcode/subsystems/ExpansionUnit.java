package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ExpansionUnit{
    private DcMotorEx linearSlideMotor;
    private final int extendTicks;
    private boolean isDisabled;
    private boolean isExpanded;
    private int targetPosition;

    public ExpansionUnit(int extendTicks){
        this.extendTicks = extendTicks;
    }

    public void init(HardwareMap hwMap){
        linearSlideMotor = hwMap.get(DcMotorEx.class, "LinearSlideMotor");
        linearSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideMotor.setPower(0);
        linearSlideMotor.setTargetPosition(0);
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearSlideMotor.setPositionPIDFCoefficients(5.0);
        linearSlideMotor.setTargetPositionTolerance(5);
    }

    public void extend(){
        if(isDisabled) return;
        targetPosition = extendTicks;
    }

    public void contract(){
        if(isDisabled) return;
        targetPosition = 0;
    }

    public boolean isExpanded(){return isExpanded;}

    public boolean isBusy(){return Math.abs(linearSlideMotor.getCurrentPosition() - targetPosition) > 5;} //Checks if its expanding

    public void disable(){ //Stop intake from moving no matter what you do
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlideMotor.setPower(0);
        isDisabled = true;
    }

    public void enable(){ //Re-enable intake
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        isDisabled = false;
    }

    public boolean isDisabled(){return isDisabled;} //Check if disabled or not

    public void update(){
        if(!isDisabled()) {
            linearSlideMotor.setTargetPosition(targetPosition);
            linearSlideMotor.setPower(0.8);
        }

        if(targetPosition != 0){
            if(Math.abs(linearSlideMotor.getCurrentPosition() - targetPosition) <= 5){
                isExpanded = true;
            }
        }

        else{
            if(Math.abs(linearSlideMotor.getCurrentPosition() - targetPosition) <= 5){
                isExpanded = false;
            }
        }

    }
}
