package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * 社团类
 */
public class Corporation {
    private String name;
    private String foundingTime;
    private Person personInCharge;
    private String phoneNum;
    private String emailAddress;
    private List<Person> personList = new ArrayList<Person>();
    private List<Activity> activityList = new ArrayList<Activity>();
    private String introduction;

    private String[] photo = new String[10];
    /**
     * DataBase索引
     */
    private DataBase base;

    public Corporation(String input, DataBase base) {
        //初始化赋值
        this.base = base;
        Set(input);
        for (int i = 0; i < photo.length; i++) {
            photo[i] = "【" + name + "】照片" + (i + 1);
        }
    }

    /**
     * 根据传入字符串赋值
     *
     * @param input
     */
    public void Set(String input) {
        String[] data = input.split(",");
        name = data[0];
        foundingTime = data[1];
        personInCharge = base.FindPersonWithName(data[2]);
        phoneNum = data[3];
        emailAddress = data[4];
        //赋值personList
        String[] pNames = data[5].split("、");
        for (String pName : pNames) {
            personList.add(base.FindPersonWithName(pName));
        }
        //先不赋值activityList

        introduction = data[7];
    }

    /**
     * 赋值活动表列
     *
     * @param input
     */
    public void SetActivityList(String input) {
        String[] data = input.split(",");
        //赋值activityList
        String[] aNames = data[6].split("、");
        for (String aName : aNames) {
            activityList.add(base.FindActivityWithName(aName));
        }
    }

    public String toString() {
        String out = name + "," + foundingTime + "," + personInCharge.getName() + "," + phoneNum + "," + emailAddress + ",";

        //输入personlist
        for (int i = 0; i < personList.size() - 1; i++) {
            out += personList.get(i).getName() + "、";
        }
        out += personList.get(personList.size() - 1).getName() + ",";

        //输入activityList&introduction
        for (int i = 0; i < activityList.size() - 1; i++) {
            out += activityList.get(i).getName() + "、";
        }
        out += activityList.get(activityList.size() - 1).getName() + "," + introduction;

        return out;
    }

    /**
     * 输出简略信息
     */
    public void output() {
        System.out.println(name);
    }

    /**
     * 输出详细信息
     */
    public void outputInformation() {
        String out = "【" + name + "】成立于" + foundingTime
                + "\n【负责人】" + personInCharge.getName()
                + " ,院系 " + personInCharge.getDepartment()
                + " ,班级 " + personInCharge.getClassroom()
                + " ,联系电话 " + phoneNum
                + " ,邮箱 " + emailAddress
                + "\n【简介】" + introduction
                + "\n【举办活动】";
        for (Activity act : activityList) {
            out += act.getName() + " ";
        }
        out += "\n";
        System.out.print(out);
    }

    /**
     * 后台状态时输出最详细信息
     *
     * @return
     */
    public void outputSecretInformation() {
        String out = "\n【" + name + "】成立于" + foundingTime
                + "\n【负责人】" + personInCharge.getName()
                + " ,院系 " + personInCharge.getDepartment()
                + " ,班级 " + personInCharge.getClassroom()
                + " ,联系电话 " + phoneNum
                + " ,邮箱 " + emailAddress
                + "\n【简介】" + introduction
                + "\n【举办活动】";
        for (Activity act : activityList) {
            out += act.getName() + " ";
        }
        out += "\n【所有成员】";
        for (Person person : personList) {
            out += person.getName() + " ";
        }
        out += "\n";
        System.out.print(out);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto(int i) {
        return photo[i];
    }

    public String getFoundingTime() {
        return foundingTime;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }
}
