package com.example.pool;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @description: ftp配置类
 * @author: wujie
 * @create: 2020-10-16 14:24
 **/
public class FTPClientConfig extends GenericObjectPoolConfig<FTPClient> {

    /**
     * 主机名
     */
    private String host;
    /**
     * 端口
     */
    private int port = 21;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 超时时间（ms）
     */
    private int connectTimeOut = 5000;
    /**
     * 字符集
     */
    private String controlEncoding = "utf-8";
    /**
     * 缓冲区大小
     */
    private int bufferSize = 1024;
    /**
     * 传输数据格式 2表binary二进制数据
     */
    private int fileType = 2;
    private int dataTimeout = 120 * 1000;
    private boolean useEPSVwithIPv4 = false;
    /**
     * 是否启用被动模式
     */
    private boolean passiveMode = true;
    /**
     * 开启线程数
     */
    private int threadNum = 10;
    /**
     * 传输文件类型
     */
    private int transferFileType = FTPClient.BINARY_FILE_TYPE;
    /**
     * 是否上传文件重命名
     */
    private boolean renameUploaded = false;
    /**
     * 重试次数
     */
    private int retryTimes = 3;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getConnectTimeOut() {
        return connectTimeOut;
    }

    public void setConnectTimeOut(int connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }

    public String getControlEncoding() {
        return controlEncoding;
    }

    public void setControlEncoding(String controlEncoding) {
        this.controlEncoding = controlEncoding;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getDataTimeout() {
        return dataTimeout;
    }

    public void setDataTimeout(int dataTimeout) {
        this.dataTimeout = dataTimeout;
    }

    public boolean isUseEPSVwithIPv4() {
        return useEPSVwithIPv4;
    }

    public void setUseEPSVwithIPv4(boolean useEPSVwithIPv4) {
        this.useEPSVwithIPv4 = useEPSVwithIPv4;
    }

    public boolean isPassiveMode() {
        return passiveMode;
    }

    public void setPassiveMode(boolean passiveMode) {
        this.passiveMode = passiveMode;
    }

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public int getTransferFileType() {
        return transferFileType;
    }

    public void setTransferFileType(int transferFileType) {
        this.transferFileType = transferFileType;
    }

    public boolean isRenameUploaded() {
        return renameUploaded;
    }

    public void setRenameUploaded(boolean renameUploaded) {
        this.renameUploaded = renameUploaded;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    @Override
    public String toString() {
        return "{\"host\":\"" + host + "\", \"port\":\"" + port + "\", \"username\":\"" + username
                + "\", \"password\":\"" + password + "\", \"connectTimeOut\":\"" + connectTimeOut
                + "\", \"controlEncoding\":\"" + controlEncoding + "\", \"bufferSize\":\"" + bufferSize
                + "\", \"fileType\":\"" + fileType + "\", \"dataTimeout\":\"" + dataTimeout
                + "\", \"useEPSVwithIPv4\":\"" + useEPSVwithIPv4 + "\", \"passiveMode\":\"" + passiveMode
                + "\", \"threadNum\":\"" + threadNum + "\", \"transferFileType\":\"" + transferFileType
                + "\", \"renameUploaded\":\"" + renameUploaded + "\", \"retryTimes\":\"" + retryTimes + "\"}";
    }

}
