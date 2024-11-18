import java.util.ArrayList;
import java.util.List;

public class ThreadPoolExecutor {
    private int maximumPoolSize;
    private List<Thread> threads;
    private int currThreadCount;
    public ThreadPoolExecutor(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
        currThreadCount = 0;
        threads = new ArrayList<>();
    }
    public void submit(Runnable runnable){
        submit(new Thread(runnable));
    }
        /*
        Проверка
        Прибавка
        Запуск
        Убавка
         */
    public void submit(Thread thread){

        if (currThreadCount < maximumPoolSize){
            //Запускаемся и прибавляем к количеству потоков
            while (currThreadCount < maximumPoolSize) thread.wait();
            ++currThreadCount;
        } else {
            /*
            Уходим в ожидание пинга
            Когда пинганули, просыпаемся и занимаем свободный слот
             */
        }
        //Пингуем всех ожидающих, мол, все сделали
        //Убавляем количество
        thread.notifyAll();
        --currThreadCount;
    }
}
