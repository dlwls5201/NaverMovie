package com.tistory.black_jin0427.navermovie;

import org.junit.Test;

import java.util.ArrayList;

import static java.sql.DriverManager.println;

public class GenericJavaTest {

    interface Output<T> {
        boolean isArgument(T argument);
    }

    ArrayList<Output<String>> items = new ArrayList<>();

    @Test
    void sample() {

        items.add(new Output<String>() {
            @Override
            public boolean isArgument(String argument) {
                return true;
            }
        });

        items.add(new Output<String>() {
            @Override
            public boolean isArgument(String argument) {
                return false;
            }
        });
    }

    /**
     *  extends는 out에 대응 됩니다.
     *  오로지 읽기만 가능합니다.
     */
    private void printAll(ArrayList<? extends Output<String>> items) {

        /*items.add(new Output<String>() {
            @Override
            public boolean isArgument(String argument) {
                return false;
            }
        });*/

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isArgument("")) {
                println("item : " + items.get(i));
            }
        }

        //items.add(null);
    }

    private void printAll(ArrayList<Output<String>> items, Output<? super Output<String>> filter) {
        for (int i = 0; i < items.size(); i++) {
            if (filter.isArgument(items.get(i))) {
                System.out.println("item : " + items.get(i));
            }
        }
    }

    /**
     *  super는 in 대응 됩니다.
     *  오로지 쓰기만 가능합니다.
     */
    private void addItem(ArrayList<? super Output<String>> items) {

        items.add(new Output<String>() {
            @Override
            public boolean isArgument(String argument) {
                return false;
            }
        });

       /* for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isArgument("")) {
                println("item : " + items.get(i));
            }
        }*/

        //items.add(null);
    }

}
