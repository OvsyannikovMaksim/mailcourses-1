package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class itemRep {

    public static List<item> mData = new ArrayList<>();;
    private static volatile itemRep mInstance;

    // конструктор класса
    private itemRep() {
        mData = initializeData();
    }

    // получение состояние класса
    public static itemRep getInstance() {
        if (mInstance == null) {
            synchronized (itemRep.class) {
                if (mInstance == null) {
                    mInstance = new itemRep();
                }
            }
        }
        return mInstance;
    }


    // получить список элементов
    public List<item> list() {
        return mData;
    }

    // получить элемент по индексу
    public item item(int index) {
        return mData.get(index);
    }

    public static void add(){
        int num = mData.size()+1;
        int colour = num%2 == 0 ? 0: 1;
        final item item = new item();
        item.name = "" + num;
        item.colour = colour;

        mData.add(item);
    }

    // инициализация списка элементов по правилам: цвет четного красный(0), нечетного синий (1)
    protected List<item> initializeData(){
        final List<item> data = new ArrayList<>();

        for(int position = 1; position<=100; position++) {

            String name = "" + position;
            final int colour;
            if (position%2==1) {
                colour = 1;
            } else {
                colour = 0;
            }

            final item item = new item();
            item.name = name;
            item.colour = colour;

            data.add(item);
        }
        return data;
    }
}
