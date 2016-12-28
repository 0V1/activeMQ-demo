package com.demo.topic;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * @ClassName: JMSConsumer
 * @Description: 点对点的消息模型，消息的消费者（接收者）
 * @author qinf QQ:908247035
 * @date 2016年12月27日
 * @version V1.0
 */
@Component("topicReceiver2")
public class TopicReceiver2 implements MessageListener{

	@Override
	public void onMessage(Message message) {

		try {
			System.out.println("topicReceiver2  收到消息"+((TextMessage)message).getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
