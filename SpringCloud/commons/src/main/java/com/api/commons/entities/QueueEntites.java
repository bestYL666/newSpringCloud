package com.api.commons.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  RabbitMq 队列实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueueEntites {
    /**
     * 生产者队列
     */
    private  final  String PRO_QUE = "product";

    /**
     * 消费者队列名称
     */
    private  final  String CONSUME_QUE = "consume";
    private  String host = "192.168.86.149";
    private  String userName = "root";
    private  String pwd = "root";


    public String getPRO_QUE() {
        return PRO_QUE;
    }

    public String getCONSUME_QUE() {
        return CONSUME_QUE;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
