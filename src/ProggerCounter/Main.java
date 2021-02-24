package ProggerCounter;

import ProggerCounter.counter.Counter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Counter counter = new Counter();
        Window app = new Window();
        ActiveWindow activeWindow = new ActiveWindow();

        counter.readCount();

        Thread threadCounter = new Thread(new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis() / 1000;
                try {
                    long i = counter.getCount();
                    long delta = 0;
                    while (true) {
                        delta = System.currentTimeMillis() - time * 1000;
                        if ( delta >= 1000) {
                            if (app.isWork() || activeWindow.isCodeSign()) {
                                i++;
                                System.out.println(time);
                                System.out.println(delta);
                                counter.setCount(i);
                                counter.writeCount();
                            }
                            time++;
                            delta = 0;
                        }
                        Thread.sleep(100);
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
