package CostumeTerminal;

import Asset.InputStateMachine;
import Asset.State;
import Model.Activity;
import Model.Corporation;

/**
 * 后台状态
 */
public class AdministratorMode extends State<CostumeLauncher> {
    @Override
    public void Enter(CostumeLauncher machine) {
        System.out.println("------------------进入后台------------------");
        System.out.println("【输入“退出后台”退出后台！】");
    }

    @Override
    public void Execute(CostumeLauncher machine) {
        //获取输入字符串
        String input = InputStateMachine.getInputString();
        if(input.equals("退出后台")){
            machine.ChangeToState(new CostumePhotoWall());
        }
    }

    @Override
    public void Exit(CostumeLauncher machine) {
        //退出后台时清空键盘缓冲
        InputStateMachine.getAnyKeyDown();
        System.out.println("------------------退出后台------------------");
    }
}
