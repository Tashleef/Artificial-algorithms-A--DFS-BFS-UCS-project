package com.company;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Logic {
    Scanner scan = new Scanner(System.in);
    Structure s;
    Logic(Structure s){
        this.s = s;
    }

    public void userMove(){
        int i = s.yCastle,j = s.xCastle;
        while(!s.isFinal(i,j)){
            ArrayList<Pair<Integer,Integer>> moves = s.nextMove(i,j);
            System.out.println("choose cell to move");
            for(Pair<Integer,Integer> p:moves){
                System.out.println(p.getValue());
            }
            int x = -1;
            Pair<Integer,Integer> p = null;
            boolean isValid = false;
            while(!isValid){
                System.out.println("Enter The Cell");
                x = scan.nextInt();
                for(Pair<Integer,Integer> pa:moves){
                    if ((pa.getValue() == x)) {
                        isValid = true;
                        p = new Pair<>(pa.getKey(),pa.getValue());
                    }
                }
            }
            s.move(j,i,p.getValue(),p.getKey());
            i = p.getKey();
            j = p.getValue();
            System.out.println(s.xKing + " " + s.yKing);
            s.print();
        }
        System.out.println("You completed the game");
        s.reset();
    }

    public void dfs(int i,int j)  {
        if(s.isEqual(i,j)) return ;
        s.equal(i,j);
        s.print();
        ArrayList<Pair<Integer,Integer>> moves = s.nextMove(i,j);
        for(Pair<Integer,Integer> p:moves){
            s.move(j,i,p.getValue(),p.getKey());
            dfs(p.getKey(),p.getValue());
            s.move(p.getValue(),p.getKey(),j,i);
        }
    }

    public void bfs(int i,int j){
        Queue<Pair<Integer,Integer>> q = null;
        Pair <Integer,Integer> p = new Pair<>(i,j);
        q.add(p);
        while(!q.isEmpty()){
            p = q.peek();
            q.remove();
            ArrayList<Pair<Integer,Integer>> moves = s.nextMove(p.getKey(),p.getValue());
            for(Pair<Integer,Integer> move:moves){
                if(!s.isEqual(move.getKey(),move.getValue())){
                    s.equal(move.getKey(),move.getValue());
                    q.add(move);
                }
            }
        }
    }
}
