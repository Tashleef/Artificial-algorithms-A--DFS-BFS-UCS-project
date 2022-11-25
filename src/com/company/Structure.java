package com.company;

import javafx.util.Pair;

import java.util.ArrayList;

public class Structure {
    ArrayList<ArrayList<Character>> map = new ArrayList<>(),temp = new ArrayList<>();
    ArrayList<ArrayList<Boolean>> vis = new ArrayList<>();
    char castle = '2',king = '3',wall = '1' ,free = '0';
    int xKing = 0,yKing = 0,xCastle = 0,yCastle = 0,castleX = 0, castleY = 0,kingX,kingY;
    Structure(ArrayList<ArrayList<Character>> arrayList){
        this.map = arrayList;
        for(int i = 0;i<map.size();i++){
            ArrayList arr = new ArrayList<>();
            for(int j = 0;j<map.get(i).size();j++){
                arr.add(false);
                if(map.get(i).get(j) == king) {
                    yKing = i; xKing = j;
                }
                if(map.get(i).get(j) == castle){
                    xCastle = j;
                    yCastle = i;
                }
            }
            vis.add(arr);
        }
        this.temp = map;

        yKing = fall(yKing,xKing);
        yCastle = fall(yCastle,xCastle);
        castleX = xCastle;
        castleY = yCastle;
        kingX = xKing;
        kingY = yKing;
    }

    public void move(int x1,int y1,int x2,int y2){
        char x = map.get(y1).get(x1);
        map.get(y1).set(x1,map.get(y2).get(x2));
        map.get(y2).set(x2,x);
    }

    public int fall(int i,int j){
        char x = map.get(i).get(j);
        while(map.get(i+1).get(j) != wall){ // make the castle fall if he is in high area
            move(j,i,j,i+1);
            i++;
        }
        return i;
    }

    public void print(){
        for(int i = 1;i<map.size()-1;i++){
            for(int j = 1;j<map.get(i).size()-1;j++){
                System.out.print(map.get(i).get(j));
            }
            System.out.println();
        }
        System.out.println();
    }

    public  ArrayList<Pair<Integer,Integer>> nextMove(int i,int j){

        ArrayList <Pair<Integer,Integer>> list = new ArrayList<Pair<Integer, Integer>>();

        int column = j;
        while(map.get(i).get(column-1) != wall){
            column--;
            int row = fall(i,column);
            Pair <Integer,Integer> p = new Pair<Integer, Integer>(row,column);
            list.add(p);
        }

        column = j;
        while(map.get(i).get(column+1) != wall){
            column++;
            int row = fall(i,column);
            Pair <Integer,Integer> p = new Pair<Integer, Integer>(row,column);
            list.add(p);
        }

        return list;
    }

    public void reset(){
        xCastle = castleX;
        yCastle = castleY;
        xKing = kingX;
        yKing = kingY;
        map = temp;
    }

    public boolean isEqual(int i,int j){
        return vis.get(i).get(j);
    }
    public void equal(int i,int j){
        vis.get(i).set(j, true);
    }

    public void clear(){
        for(int i = 0;i<vis.size();i++){
            for(int j = 0;j<vis.get(i).size();j++){
                vis.get(i).set(i,false);
            }
        }
        map = temp;

    }

    public boolean isFinal(int y,int x){
        return x == xKing && y == yKing;
    }
}
