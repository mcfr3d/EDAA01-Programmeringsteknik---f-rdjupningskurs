import java.util.LinkedList;

public class ListSorter {

        private LinkedList<Integer> list;

        public ListSorter(LinkedList<Integer> list) {
                this.list = list;
        }

        public void sort() {
                for (int i = 1; i < list.size(); i++) {
                        int nextValue = list.get(i);
                        int nextPos = i;

                        while (nextPos > 0 && new Integer(nextValue).compareTo(list.get(nextPos - 1)) < 0) {
                                list.set(nextPos, list.get(nextPos - 1));
                                nextPos--;
                        }
                        list.set(nextPos, nextValue);
                }
        }
}
