package com.exe.springdi4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("serviceConsumer")
public class ServiceConsumer {
	
	@Autowired //this.ms = Autowired�� ������ ��ü/Autowired + Qualifier ��ģ�� @Resource�̴�.
	@Qualifier("messageService") //��Ȯ�ϰ� �̸��� �������༭ �� ã�����ְ�����.
	MessageService ms; // MessageService ms = new MessageService();
	
	@Autowired
	TimeService ts;
	
	@Autowired
	JobService js;
	
	
	
	
	public void consumerService() {
	
	//��ȯ���� String
	String message = ms.getMessage();
	System.out.println(message);
	
	String time = ts.getTimeString();
	System.out.println(time);
	
	//�ִ� ��ü������ ����� �����־ ȣ�⸸ �ظ� ����
	js.getJob();
	
	}
	

}