package com.example.pool;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: 构建连接池
 * @author: wujie
 * @create: 2020-10-16 14:31
 **/
public class FTPClientPool {
    private static Logger logger = LoggerFactory.getLogger(FTPClientPool.class);
    private GenericObjectPool<FTPClient> pool;
    private FTPClientFactory factory;

    public FTPClientPool(FTPClientFactory clientFactory) {
        this.factory = clientFactory;
        pool = new GenericObjectPool<FTPClient>(clientFactory, clientFactory.getFtpPoolConfig());
    }

    public FTPClientFactory getClientFactory() {
        return factory;
    }

    public GenericObjectPool<FTPClient> getPool() {
        return pool;
    }

    /**
     * 借 获取一个连接对象
     *
     * @return
     * @throws Exception
     */
    public FTPClient borrowObject() throws Exception {
        FTPClient client = pool.borrowObject();
        if (!client.sendNoOp()) {
            // 使池中的对象无效
            client.logout();
            client.disconnect();
            pool.invalidateObject(client);
            client = factory.create();
            pool.addObject();
        }
        return client;

    }

    /**
     * 还 归还一个连接对象
     *
     * @param ftpClient
     */
    public void returnObject(FTPClient ftpClient) {

        if (ftpClient != null) {
            pool.returnObject(ftpClient);
        }
    }

}
