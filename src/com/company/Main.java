package com.company;
import java.util.*;
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(),m = scan.nextInt();
        ArrayList<ArrayList<Character>> arr = new ArrayList<>();
        for(int i = 0;i<=n+1;i++){
            ArrayList<Character> a = new ArrayList<>();
            for(int j = 0;j<=m+1;j++){
                a.add('1');
            }
            arr.add(a);
        }
        System.out.println();
        for(int i = 1;i<=n;i++){
            for(int j = 1;j<=m;j++){
                char c = scan.next().charAt(0);
                arr.get(i).set(j,c);
            }
        }

        for(int i  = 1;i<=n;i++){
            for(int j = 1;j<=m;j++){
                System.out.print(arr.get(i).get(j));
            }
            System.out.println();
        }
        System.out.println();

        Structure S = new Structure(arr);
        Logic l = new Logic(S);
        l.userMove();
    }
}