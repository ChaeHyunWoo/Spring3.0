package com.exe.springdi4;

//import org.springframework.context.support.GenericXmlApplicationContext;

public class ServiceConsumer {
	
	
	MessageService ms; //�����ڸ� ���� ������ ����
	TimeService ts; // �޼��带 ���� ������ ����
	JobService js;// �޼��带 ���� ������ ����
	
	//������ ����(DI) - �����ڸ� ����
	public ServiceConsumer(MessageService ms) {
		this.ms = ms;
	}
	
	//������ ����(DI) - �޼��带 ����
	public void setTimeService(TimeService ts) {
		this.ts = ts;
	}
	
	//������ ����(DI) - �޼��带 ����
	public void setJobService(JobService js) {
		this.js = js;
	}
	
	public void consumerService() {
	
		/*
		 * GenericXmlApplicationContext context = new
		 * GenericXmlApplicationContext("app-context.xml");
		 * 
		 * 
		 * MessageService ms = (MessageService)context.getBean("messageService");
		 */
	
	//��ȯ���� String
	String message = ms.getMessage();
	System.out.println(message);
	
	
	//String time = ts.getTimeString();
	//System.out.println(time);
	
	
	//�ִ� ��ü������ ����� �����־ ȣ�⸸ �ظ� ����
	js.getJob();
	
	}
	

}