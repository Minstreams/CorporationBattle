package Asset;

import java.util.TimerTask;

/**
 * 状态机基类
 */
public class StateMachine extends TimerTask {
    /**
     * 当前状态
     */
    private State currentState = null;

    /**
     * 初始化时指定一个初始状态
     *
     * @param initState 要指定的初始状态
     */
    public StateMachine(State initState) {
        this.currentState = initState;
        //执行初始状态的进入方法
        this.currentState.Enter(this);
    }

    /**
     * 改变当前状态为一个新状态
     *
     * @param state 新状态
     */
    public void ChangeToState(State state) {
        //执行上一状态的退出方法
        currentState.Exit(this);
        currentState = state;
        //执行新状态的进入方法
        currentState.Enter(this);
    }

    /**
     * 每次刷新时调用一次当前状态的execute方法
     */
    public void Update() {
        currentState.Execute(this);
    }

    /**
     * 不停调用刷新方法
     */
    @Override
    public void run() {
        Update();
    }
}
