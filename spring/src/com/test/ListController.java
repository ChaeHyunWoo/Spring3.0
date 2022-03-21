package com.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

//�ִ� �����͸� ó�����ִ� ��Ʈ�ѷ� - ����� ���ö� DTO�� �������µ� DTO�� ���� �׸��� �ʿ��ѵ� �װ� ListFormController�̴�.
//�׸��� �ڵ����� command���� ����.
public class ListController extends AbstractCommandController {
	
	
	public ListController() {
		setCommandClass(ListCommand.class);
		setCommandName("listCommand");
		//ListCommand listCommand = new listCommand(); �� ���� �ǹ�
		
	}
	
	
	//������ ��ü�����Ұ� command�� �޾��ش�.
	@Override
	protected ModelAndView handle(HttpServletRequest request, 
			HttpServletResponse response, Object command,  //���µ����Ͱ� command�� ����.
			BindException errors)throws Exception {
		
		ListCommand vo = (ListCommand)command;//command�� Object�ϱ� �ٿ�ĳ����
		
		String message = "�̸�: " + vo.getUserName();
		message += ", ���̵�: " + vo.getUserId();
		
		//�����ͳѱ�
		request.setAttribute("message", message);
		
		/*�𵨿���䰡 ����ġ���� test/view��� �ֹ���,,,? ���
				���� ��û�� �ϸ�*/
		
		return new ModelAndView("test/write_ok"); //���⼭ ��ü�����ϴ� ������ �޸𸮿� �÷��� �ϱ� �����̴�.
		/*dispa 1  controller 2 
		write 3  dsipa 4  listconroller 5 ������ ��ü���� 6
		7 command�� �ʾ�� 8 wrtie_ok�� �Ѿ��*/
	}

}