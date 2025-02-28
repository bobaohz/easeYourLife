//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ease.st.parser;

import com.ease.st.model.STInfo;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class STExtractor {
    private static final Logger log = LoggerFactory.getLogger(STExtractor.class);
    private static final String BASE_URL = "http://hq.sinajs.cn/list=";

    public STExtractor() {
    }

    public List<STInfo> pullData(String stList) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = (new Request.Builder()).url("http://hq.sinajs.cn/list=" + stList).header("Referer", "https://finance.sina.com.cn").build();
        Call call = client.newCall(request);
        Response response = call.execute();
        return this.parse(response);
    }

    private List<STInfo> parse(Response response) throws IOException {
        List<STInfo> stInfoList = new ArrayList();
        String[] array = response.body().string().split("var ");
        if (array != null && array.length >= 1) {
            Stream.of(array).forEach((line) -> {
                STInfo stInfo = STInfo.parse(line);
                if (stInfo != null) {
                    stInfoList.add(stInfo);
                }

            });
        }

        return stInfoList;
    }
}
