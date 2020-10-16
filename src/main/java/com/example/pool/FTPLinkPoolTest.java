package com.example.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: 连接池测试类
 * @author: wujie
 * @create: 2020-10-16 14:34
 **/
public class FTPLinkPoolTest {
    private static Logger logger = LoggerFactory.getLogger(FTPClientPool.class);

    public static void main(String[] args) {
        //TODO Auto-generated method stub
        FTPClientConfig conf = new FTPClientConfig();
        conf.setHost("192.168.75.4");
        conf.setUsername("ftpuser");
        conf.setPassword("123456qwe");

        FTPClientFactory factory = new FTPClientFactory();
        factory.setFtpPoolConfig(conf);

        FTPClientPool pool = new FTPClientPool(factory);

        FTPClientHelper clientHelper = new FTPClientHelper();
        clientHelper.setFtpClientPool(pool);

//        String pathname = "/bdc";
//        FTPFile[] files = clientHelper.listFiles(pathname);
//        for (FTPFile file : files) {
//            String name = file.getName();
//            logger.info("name:{}", name);
//        }
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i < 100; i++) {
            poolExecutor.execute(() -> {
                byte[] b = clientHelper.retrieveFileStream("bdc/10/403/403$K$2586/0001.jpg");
                System.out.println(b.length);
            });
        }
        poolExecutor.shutdown();
        try {
            poolExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
