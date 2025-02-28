package com.ease.st;


import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.ease.st.model.STInfo;
import com.ease.st.parser.STExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.collections4.CollectionUtils;

import static java.lang.Thread.sleep;

public class STJob {


    private static final Logger log = LoggerFactory.getLogger(STJob.class);

    private static final String DEFAULT_ST_LIST = "sh000001,sz399001,sz399006,sz001227,sh600030";

    public STJob() {
    }

    public void run(String stListStr, long perPeriodInMs) {
        Timer timer = new Timer();
        TimerTask task = new ExtractorTask(stListStr);
        timer.schedule(task, 500L, perPeriodInMs);
    }

    static class ExtractorTask extends TimerTask {
        private final String stListStr;

        public ExtractorTask(String stListStr) {
            this.stListStr = stListStr;
        }

        @Override
        public void run() {
            STExtractor stExtractor = new STExtractor();
            List<STInfo> result = null;

            try {
                result = stExtractor.pullData(this.stListStr);
                if (!CollectionUtils.isEmpty(result)) {
                    STJob.log.info(String.format(".........%s.........", Calendar.getInstance().getTime()));
                    result.forEach((stInfo) -> {
                        STJob.log.info("{}", stInfo.toRichString());
                    });
                    //STJob.log.info("\n");
                }
            } catch (IOException var4) {
                STJob.log.error("Got exception - ", var4);
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        //sh601398，gsbk
        //sz002958, qnbk
        //sz002151，sold

        String stList = DEFAULT_ST_LIST;
        if (args != null && args.length > 0) {
            stList = args[0];
            log.info("The ST you have interesting in is: {}", stList);
        } else {
            log.info("You didn't provide the ST list, so we will check for default st list: {}", stList);
            log.info("You can provide your list with below format as the 1st parameter: {}", "sh000001,sz399001,sz399006,sz001227,sh600030");
        }

        STJob job = new STJob();
        job.run(stList, 5000);
        while (true) {
            sleep(100000L);
        }

    }
}
