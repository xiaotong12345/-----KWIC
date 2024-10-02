package com.example.duiXiang;

public class Main {
    public static void main(String[] args) {
        Input input = new Input();
        input.input("D:\\文件\\软件体系结构\\input.txt");
        Shift shift = new Shift(input.getLineTxt());
        shift.shift();
        Alphabetizer alphabetizer = new Alphabetizer(shift.getKwicList());
        alphabetizer.sort();
        Output output = new Output(alphabetizer.getKwicList());
        output.output("D:\\文件\\软件体系结构\\output.txt");

    }
}
