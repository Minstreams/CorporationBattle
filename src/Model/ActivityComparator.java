package Model;

import java.util.Comparator;

/**
 * 储存所有活动比较的类
 */
public class ActivityComparator {
    /**
     * 比较名字
     */
    public final class ComparaName implements Comparator<Activity> {
        /**
         * 顺序或倒序
         */
        private boolean inOrder;

        public ComparaName(boolean inOrder) {
            this.inOrder = inOrder;
        }

        @Override
        public int compare(Activity o1, Activity o2) {
            if (inOrder)
                return o1.getName().compareTo(o2.getName());
            return o2.getName().compareTo(o1.getName());
        }
    }

    /**
     * 比较日期
     */
    public final class ComparaDate implements Comparator<Activity> {
        /**
         * 顺序或倒序
         */
        private boolean inOrder;

        public ComparaDate(boolean inOrder) {
            this.inOrder = inOrder;
        }

        @Override
        public int compare(Activity o1, Activity o2) {
            if (inOrder)
                return o1.getTime().compareTo(o2.getTime());
            return o2.getTime().compareTo(o1.getTime());
        }
    }
}
