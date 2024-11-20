import com.beust.jcommander.JCommander;

public class Main {
    public static void main(String[] mainArgs) throws InterruptedException {

        Args args = new Args();
        int count = 1;
        //Проверки аргументов?
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(mainArgs);
        if(args.mode.equals("multi-thread"))
            count = Integer.parseInt(args.count);

        System.out.println(args.mode + " " + count + " " + args.folder);
        for (String s : args.files) System.out.println(s);
        //Проверка на неподходящий мод?

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(count);
        for (String image : args.files) threadPool.submit(new ImageDownloader(args.folder, image));
        int lastState = -1;
        do {
            if (lastState != threadPool.getCompletedTasksCount()) {
                System.out.println(args.files.size() + " " + threadPool.getCompletedTasksCount());
                lastState = threadPool.getCompletedTasksCount();
            }
        } while (args.files.size() != threadPool.getCompletedTasksCount());
        System.out.println(args.files.size() + " " + threadPool.getCompletedTasksCount());
        System.out.println("Interrupting");
        threadPool.shutdown();
    }
}
