package CostumeTerminal;

import Asset.InputStateMachine;
import Asset.State;
import Model.Activity;
import Model.Corporation;

/**
 * 主界面状态
 */
public class CostumeSearch extends State<CostumeLauncher> {

    //上一次键盘响应的时间
    private long timeMillis = 0;

    @Override
    public void Enter(CostumeLauncher machine) {
        System.out.println("------------------进入主界面状态------------------");
        //输出表列
        machine.base.Output();
        System.out.println("【输入社团或活动名查看详情！输入“进入后台”进入后台！】");
        //初始化时间
        timeMillis = System.currentTimeMillis();

        //提前调用一次，清空在照片墙输入的缓冲区
        InputStateMachine.getInputString();
    }

    @Override
    public void Execute(CostumeLauncher machine) {
        //当前时间
        long time = System.currentTimeMillis();
        //如果1分钟没有键输入
        if (time - timeMillis > 60000) {
            //切换到照片墙状态
            machine.ChangeToState(new CostumePhotoWall());
        }
        //如果有键盘输入
        if(InputStateMachine.getAnyKeyDown()){
            //重置上次键盘响应时间
            timeMillis = time;
        }
        //获取输入的字符串
        String input =InputStateMachine.getInputString();
        //如果输入不为空
        if(!input.isEmpty()){
            if(input.equals("进入后台")){
                //切换到后台状态
                machine.ChangeToState(new AdministratorMode());
                return;
            }
            //用一个社团变量，一个活动变量分别查找是否有相应的社团或活动
            Corporation cor = machine.base.FindCorporationWithName(input);
            Activity act = machine.base.FindActivityWithName(input);
            //如果有就输出
            if(cor != null){
                cor.outputInformation();
            }
            else if(act != null){
                act.outputInformation();
            }
            //否则显示没有找到相关信息
            else{
                System.out.println("没有找到相关信息");
            }
            //结束给予提示
            System.out.println("【输入社团或活动名查看详情！输入“进入后台”进入后台！】");
        }
    }

    @Override
    public void Exit(CostumeLauncher machine) {
        System.out.println("------------------退出主界面状态------------------");
    }
}
