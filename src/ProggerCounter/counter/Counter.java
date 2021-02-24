package ProggerCounter.counter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Counter {
    private long count;
    private int sec;
    private int min;
    private int hour;
    private int day;
    private String strCount;
    private boolean isWork;

    public void setWork(boolean work) {
        this.isWork = work;
    }
    public void setCount(long count) {
        this.count = count;
    }

    public long getCount() {
        return count;
    }
    public int getMin() { return min; }
    public int getHour() { return hour; }
    public int getDay() { return day; }
    public String getStrCount() { return strCount; }
    public boolean isWork() {
        return isWork;
    }


    public void readCount() throws IOException {
        FileReader fr = new FileReader("timer.txt");
        Scanner scan = new Scanner(fr);
        count = scan.nextInt();
    }

    public void writeCount() throws IOException {
        FileWriter fw = new FileWriter("timer.txt");
        fw.write(count + "");
        fw.close();
    }

    public void calc() {
        day = (int) (count / 86400);
        hour = (int) (count / 3600);
        min = (int) (count / 60);

        int d,h,m,s;
        d = (int) (count / 86400);
        h = (int) (count % 86400)/3600;
        m = (int) ((count - d * 86400 - h * 3600)/60);
        s = (int) ((count - d * 86400 - h * 3600 - m * 60));
        strCount = d + " д "
                + h + " ч "
                + m + " м "
                + s + " с";
    }

}
