package com.example.pool;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @description: 处理类
 * @author: wujie
 * @create: 2020-10-16 14:32
 **/
public class FTPClientHelper implements AutoCloseable {
    private static Logger logger = LoggerFactory.getLogger(FTPClientHelper.class);
    private FTPClientPool pool;

    public void setFtpClientPool(FTPClientPool ftpClientPool) {
        this.pool = ftpClientPool;
    }

    /**
     * 列出目录下的所有文件
     *
     * @param pathname
     * @return
     * @throws Exception FTPFile[]
     * @author: tompai
     * @createTime: 2019年12月31日 下午1:56:02
     * @history:
     */
    public FTPFile[] listFiles(String pathname) {
        FTPClient client = getClient();
        try {
            return client.listFiles(pathname);
        } catch (IOException e) {
            //TODO Auto-generated catch block
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            pool.returnObject(client);
        }
        return null;
    }

    /**
     * 下载 remote文件流
     *
     * @param remote
     * @return byte[]
     * @author: tompai
     * @createTime: 2019年12月31日 下午1:52:07
     * @history:
     */
    public byte[] retrieveFileStream(String remote) {
        FTPClient client = getClient();
        InputStream in = null;
        //byte[] result=new Byte[]
        try {
            in = client.retrieveFileStream(remote);
            byte[] bytes = IOUtils.toByteArray(in);
            in.close();
            client.completePendingCommand();
            return bytes;
        } catch (IOException e) {
            logger.error(e.getMessage());
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            pool.returnObject(client);
        }
        return null;
    }

    /**
     * 上传文件
     *
     * @param remote
     * @param local
     * @return
     * @throws Exception boolean
     * @author: tompai
     * @createTime: 2019年12月31日 下午1:53:07
     * @history:
     */
    public boolean storeFile(String remote, InputStream local) throws Exception {
        FTPClient client = getClient();
        try {
            return client.storeFile(remote, local);
        } finally {
            pool.returnObject(client);
        }
    }

    /**
     * 创建目录 单个不可递归
     *
     * @param pathname
     * @return
     * @throws Exception boolean
     * @author: tompai
     * @createTime: 2019年12月31日 下午1:52:24
     * @history:
     */
    public boolean makeDirectory(String pathname) throws Exception {
        FTPClient client = getClient();
        try {
            return client.makeDirectory(pathname);
        } finally {
            pool.returnObject(client);
        }
    }

    /**
     * 删除目录，单个不可递归
     *
     * @param pathname
     * @return
     * @throws Exception boolean
     * @author: tompai
     * @createTime: 2019年12月31日 下午1:52:42
     * @history:
     */
    public boolean removeDirectory(String pathname) throws Exception {
        FTPClient client = getClient();
        try {
            return client.removeDirectory(pathname);
        } finally {
            pool.returnObject(client);
        }
    }

    /**
     * 删除文件 单个 ，不可递归
     *
     * @param pathname
     * @return
     * @throws Exception boolean
     * @author: tompai
     * @createTime: 2019年12月31日 下午1:52:54
     * @history:
     */
    public boolean deleteFile(String pathname) throws Exception {

        FTPClient client = getClient();
        try {
            return client.deleteFile(pathname);
        } finally {
            pool.returnObject(client);
        }
    }

    /**
     * 获取一个连接对象
     *
     * @return FTPClient
     * @author: tompai
     * @createTime: 2019年12月31日 下午1:45:46
     * @history:
     */
    private FTPClient getClient() {
        FTPClient client = null;
        try {
            client = pool.borrowObject();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return client;
    }

    /**
     * 释放连接
     *
     * @param client void
     * @author: tompai
     * @createTime: 2019年12月31日 下午1:45:03
     * @history:
     */
    private void releaseClient(FTPClient client) {
        if (client == null) {
            return;
        }
        try {
            pool.returnObject(client);
        } catch (Exception e) {
            logger.error("Could not return the ftpClient to the pool", e);
            // destoryFtpClient
            if (client.isAvailable()) {
                try {
                    client.logout();
                    client.disconnect();
                    pool.getPool().invalidateObject(client);
                } catch (Exception io) {
                    logger.error(io.getMessage());
                }
            }
        }
    }

    @Override
    public void close() throws Exception {
        // TODO Auto-generated method stub
        logger.info("---Resources Closed---.");
    }
}
