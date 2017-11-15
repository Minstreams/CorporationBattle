package Model;

import java.text.Collator;
import java.util.Comparator;

/**
 * 储存所有社团比较的类
 */
public class CorporationComparator {
    /**
     * 比较名字
     */
    public final class CompareName implements Comparator<Corporation> {
        //用于按拼音排序
        Comparator pinyinComparator = Collator.getInstance(java.util.Locale.CHINA);
        /**
         * 顺序或倒序
         */
        private boolean inOrder;

        public CompareName(boolean inOrder) {
            this.inOrder = inOrder;
        }

        @Override
        public int compare(Corporation o1, Corporation o2) {
            if (inOrder)
                return pinyinComparator.compare(o1.getName(),o2.getName());
            return pinyinComparator.compare(o2.getName(),o1.getName());
        }
    }

    /**
     * 比较日期
     */
    public final class CompareDate implements Comparator<Corporation> {
        /**
         * 顺序或倒序
         */
        private boolean inOrder;

        public CompareDate(boolean inOrder) {
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
