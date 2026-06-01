package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.ExpansionUnit;

@TeleOp
public class MainController extends OpMode {
    ExpansionUnit expansionUnit = new ExpansionUnit(500);


    @Override
    public void init(){
        expansionUnit.init(hardwareMap);
    }

    @Override
    public void loop(){
        expansionUnit.update();

        if(gamepad1.circleWasPressed() && !gamepad1.circleWasReleased()){
            if(expansionUnit.isExpanded()){
                expansionUnit.contract();
            }

            else expansionUnit.extend();
        }

        if(gamepad1.crossWasPressed() && !gamepad1.crossWasReleased()){
            if(expansionUnit.isDisabled()){
                expansionUnit.enable();
            }

            else{
                expansionUnit.disable();
            }
        }
    }
}
