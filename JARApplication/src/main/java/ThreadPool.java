
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadPool {
    Queue<Runnable> queue = new ConcurrentLinkedQueue<>();
    private volatile boolean isRunning = true;


    public ThreadPool(int threadNum) {
        for (int i = 0; i < threadNum; i++) {
            new Thread(new TaskExecutor()).start();
        }
    }
    public void execute(Runnable r) {
        if (isRunning) {
            queue.add(r);
        }
    }
    public void shutdown() {
        isRunning = false;
    }
    private class TaskExecutor implements Runnable {
        @Override
        public void run() {
            while(isRunning) {
                Runnable task = queue.poll();
                if (task != null) {
                    task.run();
                }
            }
        }
    }
}