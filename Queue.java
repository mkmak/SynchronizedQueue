
import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Owner
 */
public class Queue {
    private int nodeCount;
    private final Lock queueChangeLock;
    private final Condition queueEmptyCondition, queueFullCondition;
    private boolean isFull, isEmpty;
    
    public Queue() {
        isFull = false;
        isEmpty = false;
        nodeCount = 0;
        queueChangeLock = new ReentrantLock();
        queueEmptyCondition = queueChangeLock.newCondition();
        queueFullCondition = queueChangeLock.newCondition();
    }
    
    public void enqueue() throws InterruptedException {
        queueChangeLock.lock();
        try {
            while(nodeCount == 10) {
                if(!isFull) {
                    isFull = true;
                    System.out.println("Waiting on dequeue...");
                }
                queueFullCondition.await();
            }
            nodeCount++;
            isEmpty = false;
            System.out.println("Enqueing " + new Date().toString() + " nodeCount = " + nodeCount);
            queueEmptyCondition.signalAll();
        } finally {
            queueChangeLock.unlock();
        }
    }
    
    public void dequeue() throws InterruptedException {
        queueChangeLock.lock();
        try {
            while(nodeCount == 0) {
                if(!isEmpty) {
                    isEmpty = true;
                    System.out.println("Waiting on enqueue...");
                }
                queueEmptyCondition.await();
            }
            nodeCount--;
            isFull = false;
            System.out.println("Dequeing " + new Date().toString() + " nodeCount = " + nodeCount);
            queueFullCondition.signalAll();
        } finally {
            queueChangeLock.unlock();
        }
    }  
}
