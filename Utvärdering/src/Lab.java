import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Lab {

        private static PrintWriter printW;

        public static void main(String[] args) {

                LinkedList<Integer> unsortedNumbers = new LinkedList<Integer>();

                File file = new File(args[0]);

                try {

                        Scanner sc = new Scanner(file);

                        while (sc.hasNextLine()) {
                                unsortedNumbers.add(sc.nextInt());
                        }
                        sc.close();
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }

                try {

                        String title = "Sorting times: (sorted " + args[2] + " times), ";

                        printW = new PrintWriter(args[1].toString());
                        printW.println(title);

                } catch (IOException e) {
                        e.printStackTrace();
                }

                LinkedList<Integer> listToSort;
                for(int i = 0; i < Integer.parseInt(args[2]); i++){
                        listToSort = new LinkedList<Integer>();
                        listToSort.addAll(unsortedNumbers);

                        long timeBefore = System.nanoTime();
                        Collections.sort(listToSort);
                        long timeAfter = System.nanoTime();

                        printW.println(i + 1 + ", " + (timeAfter - timeBefore));
                }
                System.out.println("Done");
                printW.close();
        }
}
