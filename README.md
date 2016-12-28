# activeMQ-demo
activeMQ消息中间价的使用和整合进spring应用

1.【src\main\java\com\demo\location】这个目录下的demo是activeMQ入门示例。
1.1启动activeMQ服务，登录（http://127.0.0.1:8161/admin/ ）账号密码都是admin。这个页面就是activeMQ的监控页面。
1.2先运行JMSProducer（发送消息）类，然后查看activeMQ的监控，再运行JMSConsumer类就可以收到消息，再看监控。这一个简单示例帮助我们了解activeMQ是做什么的怎么做的。
2.剩下的所有代码就是spring整合activeMQ的示例加入了springMVC可以在页面进行演示
2.1【ActiveMQ.xml】中配置了Spring JmsTemplate，分别是queue（消息队列）和topic（订阅发布）两种模式的消息发送器和接收器。
2.2项目运行起来后访问首页即可进行两种消息模式的测试。
