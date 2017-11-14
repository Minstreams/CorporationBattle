package Asset;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 输入系统目前唯一的状态
 */
public class InputState extends State<InputStateMachine> {
    /**
     * 获取控制台输入流
     */
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void Enter(InputStateMachine machine) {

    }

    @Override
    public void Execute(InputStateMachine machine) {
        try {
            //获取输入
            String line = br.readLine();
            //如果输入不为空
            if(line != null){
                //记录键盘缓冲
                machine.setAnyKeyDown(true);
                //记录字符串缓冲
                machine.setInputString(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Exit(InputStateMachine machine) {

    }
}
