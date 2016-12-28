package com.demo.location;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @ClassName: JMSProducer
 * @Description: 点对点的消息模型，消息的生产者（发送者） 
 * @author qinf QQ:908247035
 * @date 2016年12月27日
 * @version V1.0
 */
public class JMSProducer {

	//默认连接用户名
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
		
		//回话 接收或者发送消息的线程
		Session session;
		
		//消息目的地
		Destination destination;
		
		//消息的生产者
		MessageProducer messageProducer;
		
		//实例化连接工厂
		connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKERURL);
		
		try {
			//通过连接工厂获得连接
			connection = connectionFactory.createConnection();
			
			//启动链接
			connection.start();
			
			//创建session
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE); 
			
			//创建一个消息队列
			destination = session.createQueue("HelloWorld");
			
			//创建消息生产者
			messageProducer = session.createProducer(destination);

			//发送消息
			sendMessage(session,messageProducer);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			if(connection != null){
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 发送消息
	 * @param session
	 * @param messageProducer
	 * @throws Exception
	 */
	private static void sendMessage(Session session, MessageProducer messageProducer) throws Exception {
		for (int i = 0; i < 10; i++) {
			//创建一个文本消息
			TextMessage textMeasurer = session.createTextMessage("Active MQ 发送消息" + i);
			System.out.println("发送消息：Active MQ 发送消息" + i);
			//通过消息生产者发出消息
			messageProducer.send(textMeasurer);
		}
		
	}
	
}
