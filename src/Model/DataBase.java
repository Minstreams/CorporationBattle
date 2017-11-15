package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于加载储存所有数据的类
 */
public class DataBase {
    private List<Person> persons = new ArrayList<Person>();
    private List<Corporation> corporations = new ArrayList<Corporation>();
    private List<Activity> activities = new ArrayList<Activity>();

    /**
     * 实例化时加载
     */
    public DataBase() {
        this.Load();
    }

    /**
     * 清理时存储
     *
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.Save();
    }

    /**
     * 遍历输出社团活动表列
     */
    public void Output() {
        System.out.println("【以下是全部社团】：");
        for (Corporation corporation : corporations) {
            corporation.output();
        }
        System.out.println("【以下是全部活动】：");
        for (Activity activity : activities) {
            activity.output();
        }
    }

    /**
     * 读取数据
     */
    public void Load() {
        System.out.println("开始载入数据...");
        //载入人员信息
        List<String> personListString = CSVUtils.importCsv(new File("data\\用户表.csv"));
        for (int i = 2; i < personListString.size(); i++) {
            persons.add(new Person(personListString.get(i)));
        }
        //载入社团信息
        List<String> corporationListString = CSVUtils.importCsv(new File("data\\社团表.csv"));
        for (int i = 2; i < corporationListString.size(); i++) {
            corporations.add(new Corporation(corporationListString.get(i), this));
        }
        //载入活动信息
        List<String> activityListString = CSVUtils.importCsv(new File("data\\活动表.csv"));
        for (int i = 2; i < activityListString.size(); i++) {
            activities.add(new Activity(activityListString.get(i), this));
        }
        //载入社团里的活动信息
        for (int i = 2; i < corporationListString.size(); i++) {
            corporations.get(i - 2).SetActivityList(corporationListString.get(i));
        }

        System.out.println("数据载入成功！");
    }

    /**
     * 储存数据
     */
    public void Save() {
        System.out.println("开始保存数据...");
        //储存人员信息
        List<String> personListString = new ArrayList<>();
        personListString.add("用户表（人员信息自拟，请参见社团成员）,,");
        personListString.add("姓名,学院,班级");
        for (Person person : persons) {
            personListString.add(person.toString());
        }
        CSVUtils.exportCsv(new File("data\\用户表.csv"), personListString);

        //储存社团信息
        List<String> corporationListString = new ArrayList<>();
        corporationListString.add("社团表,,,,,,,");
        corporationListString.add("社团名,创建时间,负责人,联系电话,邮箱,社团成员,举办的活动,简介");
        for (Corporation corporation : corporations) {
            corporationListString.add(corporation.toString());
        }
        CSVUtils.exportCsv(new File("data\\社团表.csv"), corporationListString);

        //储存活动信息
        List<String> activityListString = new ArrayList<>();
        activityListString.add("活动表,,,,,,,");
        activityListString.add("活动名称,开始时间,地点,主办社团,宣传标语,备注,,");
        for (Activity activity : activities) {
            activityListString.add(activity.toString());
        }
        CSVUtils.exportCsv(new File("data\\活动表.csv"), activityListString);
        System.out.println("数据保存成功！");
    }

    /**
     * 通过名字找到对应的人
     *
     * @param name 名字
     * @return 人
     */
    public Person FindPersonWithName(String name) {
        for (Person person : persons) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        System.out.println("【Error！】Cannot find " + name + "!");
        return null;
    }

    /**
     * 通过名字找到对应的社团
     *
     * @param name 名字
     * @return 社团
     */
    public Corporation FindCorporationWithName(String name) {
        for (Corporation corporation : corporations) {
            if (corporation.getName().equals(name)) {
                return corporation;
            }
        }
        //System.out.println("【Error！】Cannot find " + name + "!");
        return null;
    }

    /**
     * 通过名字找到对应的活动
     *
     * @param name 名字
     * @return 活动
     */
    public Activity FindActivityWithName(String name) {
        for (Activity activity : activities) {
            if (activity.getName().equals(name)) {
                return activity;
            }
        }
        //System.out.println("【Error！】Cannot find " + name + "!");
        return null;
    }

    //以下是getter，setter

    public List<Person> getPersons() {
        return persons;
    }

    public List<Corporation> getCorporations() {
        return corporations;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void setCorporations(List<Corporation> corporations) {
        this.corporations = corporations;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
