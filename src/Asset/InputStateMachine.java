package Asset;

/**
 * 以状态机形式封装的输入系统，目前只有一个状态
 */
public class InputStateMachine extends StateMachine {
    /**
     * 用于缓冲记录是否有键按下
     */
    private static boolean anyKeyDown = false;
    /**
     * 用于缓冲记录输入的字符串
     */
    private static String inputString = "";

    public InputStateMachine(State<InputStateMachine> initState) {
        super(initState);
    }

    /**
     * 当有任何键按下时返回true，并清空键盘缓冲
     *
     * @return 是否有键按下
     */
    public static boolean getAnyKeyDown() {
        if (anyKeyDown) {
            anyKeyDown = false;
            return true;
        }
        return false;
    }

    /**
     * 获取输入的字符，并清空字符缓冲
     *
     * @return
     */
    public static String getInputString() {
        String out = inputString;
        inputString = "";
        return out;
    }

    public void setAnyKeyDown(boolean anyKeyDown) {
        this.anyKeyDown = anyKeyDown;
    }

    public void setInputString(String inputString) {
        InputStateMachine.inputString = inputString;
    }
}
