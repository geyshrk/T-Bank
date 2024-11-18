import com.beust.jcommander.Parameter;

import java.util.List;

public class Args {

    @Parameter(names = "--mode")
    public String mode;

    @Parameter(names = "--urls", splitter = SemicolonParameterSplitter.class)
    public List<String> urls;

    @Parameter(names = "--count")
    public String count;

    @Parameter(names = "--folder")
    public String folder;
}
