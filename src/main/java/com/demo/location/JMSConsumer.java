package com.demo.location;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @ClassName: JMSConsumer
 * @Description: 点对点的消息模型，消息的消费者（接收者）
 * @author qinf QQ:908247035
 * @date 2016年12月27日
 * @version V1.0
 */
public class JMSConsumer {

	//默认连接用户
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	
	//默认连接密码
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	
	//默认连接URL
	private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public static void main(String[] args) {
		//连接工厂
		ConnectionFactory connectionFactory;
		
		//连接
		Connection connection = null;
		
		//会话
		Session session;
		
		//消息目的地
		Destination destination;
		
		//消息消费者
		MessageConsumer consumer;
		
		//实例化工厂
		connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKERURL);
		
		try {
			//创建连接
			connection = connectionFactory.createConnection();

			//启动连接
			connection.start();
			
			//创建会话
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			//创建消息队列
			destination = session.createQueue("HelloWorld");
			
			//创建消费者
			consumer = session.createConsumer(destination);
			
			while(true){
				//参数：接收消息的超时时间，为0的话则不超时，receive返回下一个消息，但是超时了或者消费者被关闭，返回null
				TextMessage message = (TextMessage) consumer.receive(10000);
				if(message != null){
					System.out.println("收到的消息："+message.getText());
				}else{
					System.out.println("没有收到消息");
					break;
				}
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
