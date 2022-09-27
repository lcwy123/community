package com.nowcoder.community;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class WkTests {

    @Test
    public void testWk() throws IOException {
        String cmd = "D:/software/wkhtmltopdf/bin/wkhtmltopdf https://www.nowcoder.com D:/work/workspace/data/wk-pdfs/2.pdf";
        Runtime.getRuntime().exec(cmd); // 异步的
        System.out.println("ok");
    }

}
