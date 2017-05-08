package com.rajendarreddyj.codejam2008.qualificationA;

import java.util.Scanner;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class SmallInputSolution {
    public static void main(final String[] argv) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int s = sc.nextInt();
            sc.nextLine();
            String[] engines = new String[s];
            for (int j = 0; j < s; j++) {
                engines[j] = sc.nextLine();
            }
            int q = sc.nextInt();
            sc.nextLine();
            String[] queries = new String[q];
            for (int j = 0; j < q; j++) {
                queries[j] = sc.nextLine();
            }
            int sol = 0;
            int[] array = new int[s];
            int num = 0;
            for (int j = 0; j < q; j++) {
                for (int k = 0; k < s; k++) {
                    if (engines[k].equals(queries[j])) {
                        if (array[k] == 0) {
                            array[k]++;
                            num++;
                            break;
                        }
                    }
                }
                if (num == s) {
                    sol++;
                    j--;
                    num = 0;
                    array = new int[s];
                    continue;
                }
            }
            System.out.printf("Case #%d: %d\n", i + 1, sol);
        }
        sc.close();
    }
}
