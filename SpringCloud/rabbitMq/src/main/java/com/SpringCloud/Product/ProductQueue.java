package com.SpringCloud.Product;
import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.api.commoms.exceptions.BussException;

/**
 * 生产者发送消息的方法
 */
public class ProductQueue<T> {



    public String outFun(String queueName,String host,String userName,String pwd,T mes) throws Exception {

            //建立一个连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            //建立连接
            factory.setHost(host);
            /**
             * 用户名  密码
             */
            factory.setUsername(userName);
            factory.setPassword(pwd);
            factory.setPort(5672);
            //创建连接
            Connection connection = factory.newConnection();

            Channel channel = connection.createChannel();
            /**
             * 生成队列
             * 参数:
             * 1.队列名称
             * 2.是否需要保存消息，队列里面的消息是否持久化  默认情况下消息存储在内存中   持久化表示存在磁盘中
             * 3.该队列是否只供一个消费者进行消费，是否进行消息共享，true表示可以多个消费者进行消费，false反之
             * 4.是否自动删除  最后一个消费者端开连接以后   该队列是否自动删除
             *5.其他参数
             */
            channel.queueDeclare(queueName,true,false,false,null);
            //发消息
            channel.basicPublish("",queueName,null, JSON.toJSONString(mes).getBytes());
            return "OK";


    }


}
