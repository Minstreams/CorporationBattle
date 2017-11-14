package CostumeTerminal;

import Asset.InputStateMachine;
import Asset.State;

import java.util.Random;

/**
 * 照片墙状态
 */
public class CostumePhotoWall extends State<CostumeLauncher> {
    //上一次显示照片的时间
    private long timeMillis = 0;
    //用于生成随机数
    private Random random = new Random();


    @Override
    public void Enter(CostumeLauncher machine) {
        System.out.println("------------------进入照片墙状态------------------");
        //初始化赋值,减4000是为了能在接下来一秒内就显示第一张照片
        timeMillis = System.currentTimeMillis() - 4000;
    }

    @Override
    public void Execute(CostumeLauncher machine) {
        //当前时间
        long time = System.currentTimeMillis();
        //如果当前时间距离上次显示照片过了5秒
        if (time - timeMillis > 5000) {
            //记录新的显示照片时间
            timeMillis = time;
            //随机生成一个社团编号
            int corporationNum = random.nextInt(machine.base.getCorporations().size());
            //随机生成一个照片编号
            int photoNum = random.nextInt(10);
            //获取对应的照片并输出
            System.out.println(machine.base.getCorporations().get(corporationNum).getPhoto(photoNum));
        }
        //如果有任何键输入
        if(InputStateMachine.getAnyKeyDown()){
            //切换到主界面状态
            machine.ChangeToState(new CostumeSearch());
        }
    }

    @Override
    public void Exit(CostumeLauncher machine) {
        System.out.println("------------------退出照片墙状态------------------");
    }
}
