package CostumeTerminal;

import Asset.State;
import Asset.StateMachine;
import Model.DataBase;

/**
 * 客户端，状态机载体
 */
public class CostumeLauncher extends StateMachine {
    /**
     * 数据库
     */
    public DataBase base = new DataBase();

    /**
     * 初始化状态机和数据库，并加载数据
     *
     * @param state
     */
    public CostumeLauncher(State<CostumeLauncher> state) {
        super(state);
    }
}
