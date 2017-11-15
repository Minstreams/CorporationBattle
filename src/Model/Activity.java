package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动类
 */
public class Activity {
    private String name;
    private String time;
    private String position;
    private List<Corporation> hostCorporation = new ArrayList<Corporation>();
    private String headline;
    private String remark1 = "";
    private String remark2 = "";
    private String remark3 = "";

    private DataBase base;

    public Activity(String input, DataBase base) {
        this.base = base;
        this.Set(input);
    }

    public void Set(String input) {
        String[] data = input.split(",");
        name = data[0];
        time = data[1];
        position = data[2];
        //赋值主办方
        String[] cNames = data[3].split("、");
        for (String cName : cNames) {
            hostCorporation.add(base.FindCorporationWithName(cName));
        }
        headline = data[4];
        if (data.length > 5) remark1 = data[5];
        if (data.length > 6) remark2 = data[6];
        if (data.length > 7) remark3 = data[7];
    }

    public String toString() {
        String out = name + "," + time + "," + position + ",";
        //输出主办方
        for (int i = 0; i < hostCorporation.size() - 1; i++) {
            out += hostCorporation.get(i).getName() + "、";
        }
        out += hostCorporation.get(hostCorporation.size() - 1).getName() + "," + headline + ",";
        out += remark1 + "," + remark2 + "," + remark3;
        return out;
    }

    /**
     * 输出简略信息
     */
    public void output() {
        System.out.println(time + " \t" + name);
    }

    /**
     * 输出详细信息
     */
    public void outputInformation() {
        String out = "\n【" + name + "】举办于" + time + " " + position
                + "\n【主办社团】 ";
        for (Corporation cor : hostCorporation) {
            out += cor.getName() + " ";
        }
        out += "\n【简介】" + headline + "\n";
        System.out.print(out);
    }

    /**
     * 后台状态时输出最详细信息
     *
     * @return
     */
    public void outputSecretImformation() {
        String out = "【" + name + "】举办于" + time + " " + position
                + "\n【主办社团】 ";
        for (Corporation cor : hostCorporation) {
            out += cor.getName() + " ";
        }
        out += "\n【简介】" + headline + "\n";
        System.out.print(out);
        if (!remark1.equals("")) System.out.println(remark1);
        if (!remark2.equals("")) System.out.println(remark2);
        if (!remark3.equals("")) System.out.println(remark3);
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }
}
