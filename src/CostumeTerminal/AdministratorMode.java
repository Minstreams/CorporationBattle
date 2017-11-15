package CostumeTerminal;

import Asset.InputStateMachine;
import Asset.State;
import Model.Activity;
import Model.ActivityComparator;
import Model.Corporation;
import Model.CorporationComparator;

import java.util.Collections;

/**
 * 后台状态
 */
public class AdministratorMode extends State<CostumeLauncher> {
    /**
     * 排序模式
     */
    private enum SortMode {
        ByName, ByDate
    }

    /**
     * 排序对象
     */
    private enum SortTarget {
        SortCorporation, SortActivity
    }

    private boolean isSorting = false;

    @Override
    public void Enter(CostumeLauncher machine) {
        System.out.println("------------------进入后台------------------");
        //输出表列
        machine.base.Output();
        ShowTips();
    }

    @Override
    public void Execute(CostumeLauncher machine) {
        if (isSorting) return;
        //获取输入字符串
        String input = InputStateMachine.getInputString();
        if (!input.isEmpty()) {
            if (input.equals("退出后台")) {
                machine.ChangeToState(new CostumePhotoWall());
                return;
            }
            if (input.equals("排序")) {
                StartSort(machine);
                return;
            }
            //用一个社团变量，一个活动变量分别查找是否有相应的社团或活动
            Corporation cor = machine.base.FindCorporationWithName(input);
            Activity act = machine.base.FindActivityWithName(input);
            //如果有就输出
            if (cor != null) {
                cor.outputSecretInformation();
            } else if (act != null) {
                act.outputSecretImformation();
            }
            //否则显示没有找到相关信息
            else {
                System.out.println("无效指令!");
            }
            //结束给予提示
            ShowTips();
        }
    }


    private void StartSort(CostumeLauncher machine) {
        isSorting = true;
        System.out.println("【输入“社团”或“活动”选择排序对象】");
        SortTarget sortTarget;
        while (true) {
            String input = InputStateMachine.readLine();
            if (input.equals("社团")) {
                sortTarget = SortTarget.SortCorporation;
                break;
            } else if (input.equals("活动")) {
                sortTarget = SortTarget.SortActivity;
                break;
            }
            System.out.println("输入无效哦~");
        }

        System.out.println("【输入“名称”或“时间”选择排序方式】");
        SortMode sortMode;
        while (true) {
            String input = InputStateMachine.readLine();
            if (input.equals("名称")) {
                sortMode = SortMode.ByName;
                break;
            } else if (input.equals("时间")) {
                sortMode = SortMode.ByDate;
                break;
            }
            System.out.println("输入无效哦~");
        }

        System.out.println("【是否顺序排？（是/否）】");
        boolean byOrder;
        while (true) {
            String input = InputStateMachine.readLine();
            if (input.equals("是")) {
                byOrder = true;
                break;
            } else if (input.equals("否")) {
                byOrder = false;
                break;
            }
            System.out.println("输入无效哦~");
        }

        switch (sortMode) {
            case ByName:
                if (sortTarget == SortTarget.SortCorporation) {
                    Collections.sort(machine.base.getCorporations(), new CorporationComparator().new ComparaName(byOrder));
                } else {
                    Collections.sort(machine.base.getActivities(), new ActivityComparator().new ComparaName(byOrder));
                }
                break;
            case ByDate:
                if (sortTarget == SortTarget.SortCorporation) {
                    Collections.sort(machine.base.getCorporations(), new CorporationComparator().new ComparaDate(byOrder));
                } else {
                    Collections.sort(machine.base.getActivities(), new ActivityComparator().new ComparaDate(byOrder));
                }
                break;
        }
        System.out.println("【排序完毕】");
        machine.base.Save();
        machine.base.Output();
        ShowTips();
        isSorting = false;
    }

    /**
     * 显示提示
     */
    private void ShowTips(){
        System.out.println("");
        System.out.println("【输入社团名或活动名查询信息！】");
        System.out.println("【输入“排序”开始排序！】");
        System.out.println("【输入“退出后台”退出后台！】");
    }
    @Override
    public void Exit(CostumeLauncher machine) {
        //退出后台时清空键盘缓冲
        InputStateMachine.getAnyKeyDown();
        System.out.println("------------------退出后台------------------");
    }
}
