/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Owner
 */
public class ConsumerRunnable implements Runnable {
    private final int delay, repetition;
    private final Queue queue;
    
    public ConsumerRunnable(int d, int r, Queue q) {
        delay = d;
        repetition = r;
        queue = q;
    }

    @Override
    public void run() {
        try {
            for(int i = 1; i <= repetition; i++) {
                queue.dequeue();
                if(repetition == i)
                    System.out.println("Dequeued " + i + " items");
                Thread.sleep(delay);
            }
        } catch(InterruptedException e) {}
    }
    
}
