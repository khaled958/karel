import stanford.karel.SuperKarel;
import java.awt.*;
public class Homework extends SuperKarel {
    int NumOfSteps=0,NumOfBeepers=0;
    public void run() {
        int  Column = getColumn(1),row = getRow(1);
        if(row==1 && Column==1 || row==2 && Column==1 || Column==2 && row==1){
            println("divide to 1");
        }
        else if(Column==1 && row>2){
            RowOrColumnEqual1(row,true);
        }
        else if(row==1 && Column>2){
            RowOrColumnEqual1(Column,false);}
        else if((row==2 && Column!=2) || (row!=2 && Column==2)){
            if(row==2 && Column!=2){
                if(Column%2==0){
                    Move(Column/2,true,"l");
                    turnLeft();move();turnRight();
                    NumOfSteps++;
                    Move(Column/2,true,"l");}
                else{
                    if(Column==3){
                        for(int i=0;i<row*Column;i++){
                            if(i%2==0){putBeeper();NumOfBeepers++;}
                            if(frontIsBlocked())turnLeft();
                            move();
                            NumOfSteps++;
                        }}
                    else{
                        Move(Column/2,true,"l");
                        turnLeft();putBeeper();move();turnRight();
                        NumOfSteps+=1;
                        NumOfBeepers+=1;
                        Move(Column/2+1,true,"l");}
                }}
            else{
                turnLeft();
                if(row%2==0){
                    Move(row/2,true,"l");
                    turnRight();move();turnLeft();
                    NumOfSteps++;
                    Move(row/2,true,"l");
                }
                else {
                    if(row==3){
                        turnRight();
                        for(int i=0;i<row*Column;i++){
                            if(i%2==0) {putBeeper();NumOfBeepers++;}
                            if(frontIsBlocked()) turnLeft();
                            move();NumOfSteps++;
                        }}
                    else {
                        Move(row/2,true,"l");
                        turnRight();putBeeper();move();turnLeft();
                        NumOfBeepers+=1;NumOfSteps+=1;
                        Move(row/2+1,true,"l");
                    }}}}
        else if(row%2!=0 && Column%2!=0){
            Move(Column/2,false,"f");
            Move(row/2,true,"f");
            Move(Column/2+1,true,"a");Move(Column/2,false,"f");
            Move(row/2+1,true,"a");Move(row/2,false,"f");
            Move(Column/2+1,true,"f");
        }
        else if((row%2==0 && Column%2!=0) ||(row%2!=0 && Column%2==0)){
            if(Column%2==0 && row%2!=0){
                Move(Column/2-1,false,"f");
                Move(row/2,true,"f");
                Move(Column/2,true,"a");Move(Column/2-1,false,"f");
                Move(row/2,true,"r");Move(row/2,true,"f");
                Move(Column/2,true,"a");Move(Column/2-1,false,"f");
                Move(row/2+1,true,"f");
            }
            else if(row%2==0 && Column%2!=0){
                Move(Column/2,false,"f");
                Move(row/2-1,true,"f");
                Move(Column/2,true,"r");Move(Column/2,true,"f");
                Move(row/2,true,"a");Move(row/2-1,false,"f");
                Move(Column/2,true,"r");Move(Column/2,true,"f");
            }}
        else if (Column % 2 == 0 && row % 2 == 0) {
            if(row==2 && Column==2){
                putBeeper();move();turnLeft();move();putBeeper();
                NumOfSteps+=2;
                NumOfBeepers+=2;
            }
            else if(row==Column && row>2 && Column>2){
                MoveIfRowEqualColumn(Column / 2-1,  false, true, false);
                MoveIfRowEqualColumn(row / 2, true, true, false);
                MoveIfRowEqualColumn(Column / 2, true, false, false);
                MoveIfRowEqualColumn(Column / 2-1, false, true, false);
                MoveIfRowEqualColumn(row / 2, true, false, false);
                MoveIfRowEqualColumn(row / 2-1, false, true, false);
                MoveIfRowEqualColumn(Column / 2, true, false, true);
            }
            else if(row!=Column){
                if(((row*Column)-(row+Column))%4==0 && row>Column){
                    MoveIfRowNotEqualColumn(Column/2-1,false,false,"left","",false);
                    MoveIfRowNotEqualColumn(row/2-2,true,true,"right","left",true);
                    MoveIfRowNotEqualColumn(row-(row/2-2)-1,true,true,"left","right",false);
                    MoveIfRowNotEqualColumn(row/2-1,true,false,"around","",false);
                    MoveIfRowNotEqualColumn(row/2-1,false,false,"right","",false);
                    MoveIfRowNotEqualColumn(Column/2,true,false,"around","",false);
                    MoveIfRowNotEqualColumn(Column/2,false,true,"right","left",false);
                    MoveIfRowNotEqualColumn(Column/2,true,false,"around","",false);
                }
                else if(((row*Column)-(row+Column))%4!=0 && ((row+Column-2)/4<=Column/2 || (row+Column-2)<=row/2) && row>Column){
                    Move(Column / 2 - 1, false, "left");
                    Move(row / 2 -1, true, "right");move();
                    Move(Column / 2, true, "around");
                    Move(Column / 2 - 1, false, "right");move();turnLeft();move();
                    Move(Column / 2, true, "around");
                    Move(Column / 2 - 1, false, "left");move();turnRight();move();turnLeft();
                    Move(row / 2 - 1, true, " ");}
                else{
                    Move((Column/2)-1,false,"f");
                    Move((row/2)-1,true,"f");
                    Move((Column/2)-1,true,"r");Move((Column/2)-1,true,"f");
                    Move((row/2)-1,true,"r");Move((row/2)-1,true,"f");
                    Move((Column/2)-1,true,"r");Move((Column/2)-1,true,"f");
                    Move(row/2,true,"");}
            }}
        println("Number Of Beepers: "+NumOfBeepers);
        println("Number Of Steps: "+NumOfSteps);
        NumOfBeepers=0;
        NumOfSteps=0;
    }
    private int getColumn(int Column){
        while (frontIsClear()) {move();NumOfSteps++;Column++;}
        return Column;
    }
    private int getRow(int Row){
        turnLeft();
        while (frontIsClear()) {move();NumOfSteps++;Row++;}turnLeft();
        return Row;}
    private  void MoveIfRowEqualColumn(int MoveRowOrColumn,boolean CheckIfWillPutBeeper,boolean CheckIfWillTurnLeft,boolean usebreak){
        while (MoveRowOrColumn >0){
            if(noBeepersPresent() && CheckIfWillPutBeeper) {putBeeper();NumOfBeepers++;}
            if(frontIsBlocked()) {turnAround();if(usebreak) break;}
            move();NumOfSteps++;
            MoveRowOrColumn--;

        }
        if(CheckIfWillTurnLeft) turnLeft();}
    private void MoveIfRowNotEqualColumn(int MoveRowOrColumn,boolean CheckIfWillPutBeepers,boolean CallIfTurn,String CheckIfWillTurn,String CheckIfWillTurn1,boolean CheckIfWillPutBeepers1){
        while (MoveRowOrColumn-->0){
            if(noBeepersPresent() && CheckIfWillPutBeepers){putBeeper();NumOfBeepers++;}
            if (frontIsBlocked()) break;
            move();NumOfSteps++;
            if(noBeepersPresent() && CheckIfWillPutBeepers1)
            {putBeeper();NumOfBeepers++;}
        }
        IfTurn(CheckIfWillTurn);
        if(CallIfTurn){move();NumOfSteps++;IfTurn(CheckIfWillTurn1);
        }}
    private void IfTurn(String CheckIfTurn){
        if (CheckIfTurn.equalsIgnoreCase("left")) {turnLeft();}
        else if (CheckIfTurn.equalsIgnoreCase("right")) {turnRight();}
        else if (CheckIfTurn.equalsIgnoreCase("Around")) {turnAround();}}
    private  void Move(int MoveKarel,boolean CheckIfWillPutBeepers,String choose){
        while (MoveKarel-->0){
            if(CheckIfWillPutBeepers && noBeepersPresent()) {putBeeper();NumOfBeepers++;}
            if(frontIsBlocked()) break;
            move();NumOfSteps++;
        }
        IfTurn(choose);
        if(choose.equalsIgnoreCase("f")){
            turnLeft();}
        else if(choose.equalsIgnoreCase("r")){
            turnRight();putBeeper();move();turnRight();
            NumOfSteps++;
            NumOfBeepers++;
        }
        else if(choose.equalsIgnoreCase("a")){
            turnAround();
        }}
    private void RowOrColumnEqual1(int MoveRowOrColumn,boolean CheckIfWillTurnLeft){
        int walk=0;
        if(CheckIfWillTurnLeft) turnLeft();
        if(MoveRowOrColumn%2==0){
            while (frontIsClear()) {
                if (walk == (MoveRowOrColumn / 2) || walk == (MoveRowOrColumn / 2 - 1)) {putBeeper();NumOfBeepers++;}
                move();NumOfSteps++;walk++;}}
        else{
            while (frontIsClear()) {
                if (walk == MoveRowOrColumn / 2){putBeeper();NumOfBeepers++;}
                move();NumOfSteps++;walk++;}
        }}
}
