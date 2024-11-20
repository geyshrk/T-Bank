import lombok.Getter;

import java.util.*;

public class ThreadPoolExecutor {
    private volatile boolean active;
    private final Queue<Runnable> queue;
    private final Set<Executor> executors;
    @Getter
    private volatile int completedTasksCount;
    public ThreadPoolExecutor(int poolSize) {
        queue = new ArrayDeque<>();
        executors = new HashSet<>();
        active = true;
        for (int i = 0; i < poolSize; i++) {
            Executor executor = new Executor();
            executor.start();
            executors.add(executor);
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
                    System.out.println("started: " + this);
                    task.run();
                    System.out.println("finished: " + this);
                    ++completedTasksCount;
                }
            }
        }
    }
}
