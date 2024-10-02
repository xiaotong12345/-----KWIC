package com.example;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

//主程序-子程序软件体系结构
@Service
public class zhuChengXu {
    public ArrayList<String> kwicList = new ArrayList<String>();
    private ArrayList<String> lineTxt = new ArrayList<String>();
    private BufferedReader inputFile;
    private BufferedWriter outputFile;


    public static void main(String[] args) {

        zhuChengXu kwic = new zhuChengXu();
        kwic.input("D:\\文件\\软件体系结构\\input.txt");
        kwic.shift();
        kwic.alphabetizer();
        kwic.output("D:\\文件\\软件体系结构\\output.txt");
        System.out.println(kwic.toString());
    }


    public void input(String fileName) {
        try {
            inputFile = new BufferedReader(new FileReader(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String line;
        try {
            while ((line = inputFile.readLine()) != null) {
                lineTxt.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void output(String filename){
        Iterator<String> it = kwicList.iterator();
        try {
            outputFile = new BufferedWriter(new FileWriter(filename));
            while (it.hasNext()) {
                outputFile.write(it.next()+"\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (outputFile!=null) {
                    outputFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

        public void shift() {
        for (String line : lineTxt) {
            String[] tokens = line.split(" ");
            int count = tokens.length;

            for (int i = 0; i < count; i++) {
                StringBuilder lineBuffer = new StringBuilder();
                int index = i;
                for (int f = 0; f < count; f++) {
                    if (index >= count)
                        index = 0;
                    lineBuffer.append(tokens[index]);
                    lineBuffer.append(" ");
                    index++;
                }
                String tmp = lineBuffer.toString().trim();
                kwicList.add(tmp);
            }
        }
    }


    public void alphabetizer() {

        Collections.sort(this.kwicList, new AlphabetizerComparator());
    }


    //字母分隔符比较器
    private class AlphabetizerComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if (o1 == null && o2 == null) {
                throw new NullPointerException();
            }
            int compareValue = 0;
            char o1c = o1.toLowerCase().charAt(0); //忽略大小写
            char o2c = o2.toLowerCase().charAt(0); //忽略大小写
            compareValue = o1c - o2c;
            return compareValue;

        }

    }


}
