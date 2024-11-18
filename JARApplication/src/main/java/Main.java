import com.beust.jcommander.JCommander;

public class Main {
    public static void main(String[] mainArgs) {

        Args args = new Args();
        int count = 1;
        //Проверки аргументов?
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(mainArgs);
        //Проверка на неподходящий мод?
        if(args.mode.equals("multi-thread")) count = Integer.parseInt(args.count);

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(count);
        for (String url : args.urls) threadPool.submit(new ImageDownloader(args.folder, url));
    }
}
