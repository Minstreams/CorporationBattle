package Model;

import java.util.Comparator;

/**
 * 储存所有社团比较的类
 */
public class CorporationComparator {
    /**
     * 比较名字
     */
    public final class ComparaName implements Comparator<Corporation> {
        /**
         * 顺序或倒序
         */
        private boolean inOrder;

        public ComparaName(boolean inOrder) {
            this.inOrder = inOrder;
        }

        @Override
        public int compare(Corporation o1, Corporation o2) {
            if (inOrder)
                return o1.getName().compareTo(o2.getName());
            return o2.getName().compareTo(o1.getName());
        }
    }

    /**
     * 比较日期
     */
    public final class ComparaDate implements Comparator<Corporation> {
        /**
         * 顺序或倒序
         */
        private boolean inOrder;

        public ComparaDate(boolean inOrder) {
            this.inOrder = inOrder;
        }

        @Override
        public int compare(Corporation o1, Corporation o2) {
            if (inOrder)
                return o1.getFoundingTime().compareTo(o2.getFoundingTime());
            return o2.getFoundingTime().compareTo(o1.getFoundingTime());
        }
    }
}
