/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementation;

import tools.MyALU;
import utilitytypes.EnumOpcode;
import baseclasses.InstructionBase;
import baseclasses.PipelineRegister;
import baseclasses.PipelineStageBase;
import voidtypes.VoidLatch;
import baseclasses.CpuCore;
import baseclasses.Latch;
import cpusimulator.CpuSimulator;
import static utilitytypes.EnumOpcode.*;
import utilitytypes.ICpuCore;
import utilitytypes.IGlobals;
import utilitytypes.IPipeReg;
import static utilitytypes.IProperties.*;

//import com.sun.javaws.Globals;

import utilitytypes.IRegFile;
import utilitytypes.Logger;
import utilitytypes.Operand;
import voidtypes.VoidLabelTarget;

    public class FloatDiv extends PipelineStageBase {
        public FloatDiv(ICpuCore core) {
            super(core, "FloatDiv");
        }
        
        @Override
        public void compute(Latch input, Latch output) {
        	 if (input.isNull()) return;
             doPostedForwarding(input);
             InstructionBase ins = input.getInstruction();

             float source1 = ins.getSrc1().getFloatValue();
             float source2 = ins.getSrc2().getFloatValue();
             
             float result = source1 / source2;
             //IGlobals globals = (GlobalData)getCore().getGlobals();
             
             output.setResultFloatValue(result);
             output.setInstruction(ins);
             if(GlobalData.Counter16Cycle<=15) {
                 setResourceWait("Loop"+GlobalData.Counter16Cycle);
                 GlobalData.Counter16Cycle+=1;
                 }
                 else
                 GlobalData.Counter16Cycle=1;
        }
    }


