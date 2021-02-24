package ProggerCounter;

import ProggerCounter.counter.Counter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Counter counter = new Counter();
        Window app = new Window();
        ActiveWindow activeWindow = new ActiveWindow();

        counter.readCount();
        long count = counter.getCount();


        Thread threadCounter = new Thread(new Runnable() {
            @Override
            public void run() {
                long a = 0, b;
                try {
                    long i = count;
                    while (true) {
                        a = System.currentTimeMillis() - a;
                        System.out.println(a);
                        a = System.currentTimeMillis();
                        if (app.isWork() || activeWindow.isCodeSign()) {
                            i++;
                            counter.setCount(i);
                            counter.writeCount();
                        }
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }

            }
        });
        threadCounter.start();


        Thread threadWindow = new Thread(new Runnable() {
            @Override
            public void run() {
                app.setVisible(true);
                try {
                    while (true) {
                        counter.calc();
                        app.setCount(counter.getCount());
                        app.setDay(counter.getDay());
                        app.setHour(counter.getHour());
                        app.setMin(counter.getMin());
                        app.setStringCount(counter.getStrCount());
                        counter.setWork(app.isWork());
                        Thread.sleep(1000);
                        activeWindow.findActiveWindow();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadWindow.start();
    }
}
