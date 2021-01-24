/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Owner
 */
public class SynchronizedQueue {
    public static void main(String[] args) {
        Queue queue = new Queue();
        int repetition = 100;
        int producer_delay = 150;
        int consumer_delay = 200;
        
        ProducerRunnable pr = new ProducerRunnable(producer_delay, repetition, queue);
        ConsumerRunnable cr = new ConsumerRunnable(consumer_delay, repetition, queue);
        Thread pt = new Thread(pr);
        Thread ct = new Thread(cr);
        pt.start();
        ct.start();
    }
}
