package Test;

import CostumeTerminal.CostumeLauncher;
import CostumeTerminal.CostumePhotoWall;
import Asset.InputState;
import Asset.InputStateMachine;

import java.util.Timer;

public class test {
    public static void main(String[] args) {
        //创建inputTimer线程
        Timer inputTimer = new Timer();
        //实例化输入系统
        InputStateMachine inputMachine = new InputStateMachine(new InputState());
        //以1毫秒的间隔反复调用输入状态机中的run方法
        inputTimer.schedule(inputMachine, 0, (long) 1);

        //创建timer线程
        Timer timer = new Timer();
        //新建客户端，并设定初始状态为照片墙
        CostumeLauncher launcher = new CostumeLauncher(new CostumePhotoWall());
        //以1毫秒的间隔反复调用客户端状态机中的run方法
        timer.schedule(launcher, 0, (long) 1);


    }
}
