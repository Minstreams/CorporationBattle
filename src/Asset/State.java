package Asset;

/**
 * 状态基类
 */
public abstract class State<T> {
    /**
     * 此方法将在进入状态时被调用
     *
     * @param machine 关联的状态机
     */
    public abstract void Enter(T machine);

    /**
     * 此方法将被不停循环调用
     *
     * @param machine 关联的状态机
     */
    public abstract void Execute(T machine);

    /**
     * 此方法将在退出状态时被调用
     *
     * @param machine 关联的状态机
     */
    public abstract void Exit(T machine);
}
