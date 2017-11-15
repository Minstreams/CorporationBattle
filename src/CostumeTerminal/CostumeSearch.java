package CostumeTerminal;

import Asset.InputStateMachine;
import Asset.State;
import Model.Activity;
import Model.Corporation;

/**
 * 主界面状态
 */
public class CostumeSearch extends State<CostumeLauncher> {
    //是否为阻塞状态
    private boolean blocked = false;

    //上一次键盘响应的时间
    private long timeMillis = 0;

    /**
     * 筛选模式
     */
    private enum FilterMode {
        BeforeDate, AfterDate, ByCorporation
    }

    @Override
    public void Enter(CostumeLauncher machine) {
        System.out.println("------------------进入主界面状态------------------");
        //输出表列
        machine.base.Output();
        ShowTips();
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
        if (InputStateMachine.getAnyKeyDown()) {
            //重置上次键盘响应时间
            timeMillis = time;
        }
        //如果阻塞则直接返回
        if (blocked)
            return;
        //获取输入的字符串
        String input = InputStateMachine.getInputString();
        //如果输入不为空
        if (!input.isEmpty()) {
            if (input.equals("进入后台")) {
                //切换到后台状态
                machine.ChangeToState(new AdministratorMode());
                return;
            }
            if (input.equals("筛选")) {
                //开始筛选
                Filter(machine);
                return;
            }
            //用一个社团变量，一个活动变量分别查找是否有相应的社团或活动
            Corporation cor = machine.base.FindCorporationWithName(input);
            Activity act = machine.base.FindActivityWithName(input);
            //如果有就输出
            if (cor != null) {
                cor.outputInformation();
            } else if (act != null) {
                act.outputInformation();
            }
            //否则显示没有找到相关信息
            else {
                System.out.println("没有找到相关信息");
            }
            //结束给予提示
            ShowTips();
        }
    }

    /**
     * 开始筛选
     */
    private void Filter(CostumeLauncher machine) {
        //开始阻塞
        blocked = true;

        FilterMode filterMode;
        System.out.println("【输入“按社团”“日期前”“日期后”选择筛选模式】");
        while (true) {
            String input = InputStateMachine.readLine();
            if (input.equals("按社团")) {
                filterMode = FilterMode.ByCorporation;
                System.out.println("【请输入社团名】");
                break;
            }
            if (input.equals("日期前")) {
                filterMode = FilterMode.BeforeDate;
                System.out.println("【请输入日期：xxxx-xx-xx】");
                break;
            }
            if (input.equals("日期后")) {
                filterMode = FilterMode.AfterDate;
                System.out.println("【请输入日期：xxxx-xx-xx】");
                break;
            }
            System.out.println("无效指令！");
            System.out.println("【输入“按社团”“日期前”“日期后”选择筛选模式】");
        }

        //用于指示循环跳出
        boolean end = false;
        while (!end) {
            String input = InputStateMachine.readLine();
            switch (filterMode) {
                case AfterDate:
                    for (Activity activity : machine.base.getActivities()) {
                        if (activity.getTime().compareTo(input) >= 0) {
                            activity.outputInformation();
                        }
                    }
                    end = true;
                    break;
                case BeforeDate:
                    for (Activity activity : machine.base.getActivities()) {
                        if (activity.getTime().compareTo(input) <= 0) {
                            activity.outputInformation();
                        }
                    }
                    end = true;
                    break;
                case ByCorporation:
                    Corporation corporation = machine.base.FindCorporationWithName(input);
                    if(corporation == null){
                        System.out.println("找不到相关社团!");
                        System.out.println("【请输入社团名】");
                    }else{
                        System.out.println("【以下是"+corporation.getName()+"举办的活动】");
                        for (Activity activity:corporation.getActivityList()) {
                            activity.outputInformation();
                        }
                        end = true;
                    }
                    break;
            }
        }

        System.out.println("【筛选完毕!】");
        ShowTips();
        //结束阻塞
        blocked = false;
    }

    /**
     * 显示提示
     */
    private void ShowTips() {
        System.out.println();
        System.out.println("【输入“筛选”进行筛选！】");
        System.out.println("【输入社团或活动名查看详情！】");
        System.out.println("【输入“进入后台”进入后台！】");
    }

    @Override
    public void Exit(CostumeLauncher machine) {
        System.out.println("------------------退出主界面状态------------------");
    }
}
