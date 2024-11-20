package ru.msnigirev.tbank.jar;

import lombok.Getter;

import java.util.*;

public class ThreadPoolExecutor {
    private volatile boolean active;
    private final Queue<Runnable> queue;
    @Getter
    private volatile int completedTasksCount;
    public ThreadPoolExecutor(int poolSize) {
        queue = new ArrayDeque<>();
        active = true;
        for (int i = 0; i < poolSize; i++) {
            Executor executor = new Executor();
            executor.start();
        }
    }
    public void submit(Runnable task){
        if (task == null) throw new NullPointerException();
        queue.add(task);
    }
    public void shutdown(){
        active = false;
    }
    private class Executor extends Thread{
        @Override
        public void run() {
            while (active){
                Runnable task = null;
                synchronized (queue) {
                    task = queue.poll();
                }
                if (task != null) {
                    task.run();
                    ++completedTasksCount;
                }
            }
        }
    }
}
