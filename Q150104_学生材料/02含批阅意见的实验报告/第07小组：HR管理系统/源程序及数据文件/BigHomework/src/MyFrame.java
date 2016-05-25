import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFrame extends JFrame {
	Vector empList=new Vector();
	
MyFrame(){
		super("HR����ϵͳ");
		
		this.setTitle("HR����ϵͳ");
		this.setSize(700,320);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.readEmpFile();
		this.showEmpPanel();
		this.setVisible(true);

		this.setResizable(false);
		this.setSize(700,320);
		this.setLocationRelativeTo(this.getOwner());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		
		this.setVisible(true);
	}
	void readEmpFile(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Employee.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Employee emp=new Employee();
				String[] temp=s.split(" ");
				emp.setEmpNo(temp[0]);
				emp.setEmpName(temp[1]);
				emp.setEmpSex(temp[2]);
				emp.setEmpBirthday(temp[3]);
				emp.setDepNo(temp[4]);
				emp.setEmpTelephone(temp[5]);
				empList.add(emp);
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}
	
	void showEmpPanel(){
		EmpPanel empPanel=new EmpPanel();
		empPanel.empList=this.empList;
		this.add(empPanel,BorderLayout.CENTER);
		empPanel.showEmployee(0);
		this.setVisible(true);
	}
}




class EmpPanel extends JPanel implements ActionListener {
 

	Vector empList=new Vector();
	
	private JTextField empNo=new JTextField();											//ѧ��
	private JTextField empName=new JTextField();										//����
	private JTextField empSex=new JTextField();											//�Ա�
	private JTextField empBirthday=new JTextField();
	private JTextField empTelephone=new JTextField();
	private JTextField depNo=new JTextField();
	 
	private String[] btnStr={"��һ��","��һ��","��һ��","���һ��","���","�޸�","ɾ��"};
	private JButton[] btn =new JButton[btnStr.length];
	int count=0,current=0,inserting=0;

	
	EmpPanel(){
		this.setLayout(null);
		//��ʾ����
		JLabel lb1=new JLabel("���ţ�");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		empNo.setBounds(100,10, 100, 20);
		this.add(empNo);
		//��ʾ����
		JLabel lb2=new JLabel("������");
		lb2.setBounds(30, 50, 100, 20);
		this.add(lb2);
		empName.setBounds(100,50, 100, 20);
		this.add(empName);
		//��ʾ�Ա�
		JLabel lb3=new JLabel("�Ա�");
		lb3.setBounds(30, 90, 100, 20);
		this.add(lb3);
		empSex.setBounds(100,90, 100, 20);
		this.add(empSex);
		//��ʾ��������
		JLabel lb4=new JLabel("���գ�");
		lb4.setBounds(30, 130, 100, 20);
		this.add(lb4);
		empBirthday.setBounds(100,130, 100, 20);
		this.add(empBirthday);
		//��ʾ��������
		JLabel lb5=new JLabel("���ţ�");
		lb5.setBounds(30, 170, 100, 20);
		this.add(lb5);
		depNo.setBounds(100,170, 100, 20);
		this.add(depNo);
		//��ʾ��ϵ��ʽ
        JLabel lb6=new JLabel("��ϵ��ʽ��");
		lb6.setBounds(30, 210, 100, 20);
		this.add(lb6);
		empTelephone.setBounds(100,210, 100, 20);
		this.add(empTelephone);
		//��ʾ���ư�ť
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(50+i*90, 250, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
			
		}
		
	}
	
	void showEmployee(int offset){
		Employee emp=(Employee) empList.get(offset);
		this.empNo.setText(emp.getEmpNo());
		this.empName.setText(emp.getEmpName());
		this.empSex.setText(emp.getEmpSex());
		this.empBirthday.setText(emp.getEmpBirthday());
		this.depNo.setText(emp.getDepNo());
		this.empTelephone.setText(emp.getEmpTelephone());
		
	}


   public void actionPerformed(ActionEvent e) {
	count=this.empList.size();
	if(e.getSource()==this.btn[0]){
		this.showEmployee(0);
		current=0;
	}
	if(e.getSource()==this.btn[1] && current>0){
		this.showEmployee(current-1);
		current=current-1;
	}
	if(e.getSource()==this.btn[2] && current<count-1){
		this.showEmployee(current+1);
		current=current+1;
	}
	if(e.getSource()==this.btn[3]){
		this.showEmployee(count-1);
		current=count-1;
	}
	if(e.getSource()==this.btn[4]){
		if(this.inserting==0){
			this.empNo.setText("");
			this.empName.setText("");
			this.empSex.setText("");
			this.empBirthday.setText("");
			this.depNo.setText("");
			this.empTelephone.setText("");
			btn[4].setText("����");
			btn[5].setText("ȡ��");
			this.inserting=1;
		}else{
			Employee emp=new Employee();
			emp.setEmpNo(this.empNo.getText().trim());
			emp.setEmpName(this.empName.getText().trim());
			emp.setEmpSex(this.empSex.getText().trim());
			emp.setEmpBirthday(this.empBirthday.getText().trim());
			emp.setDepNo(this.depNo.getText().trim());
			emp.setEmpTelephone(this.empTelephone.getText().trim());
			empList.add(emp);
			count++;
			current=count-1;
			btn[4].setText("���");
			btn[5].setText("�޸�");
			this.inserting=0;
		}
		for(int i=0;i<btn.length;i++){
			if(i==4||i==5) continue;
			btn[i].setEnabled(!btn[i].isEnabled());
		}
	}

	if(e.getSource()==this.btn[5]){
		if(this.inserting==0){
          Employee emp= (Employee)empList.get(current);
			emp.setEmpNo(this.empNo.getText().trim());
			emp.setEmpName(this.empName.getText().trim());
			emp.setEmpSex(this.empSex.getText().trim());
			emp.setEmpBirthday(this.empBirthday.getText().trim());
			emp.setDepNo(this.depNo.getText().trim());
			emp.setEmpTelephone(this.empTelephone.getText().trim());
			
		}else{
			btn[4].setText("���");
			btn[5].setText("�޸�");
			for(int i=0;i<btn.length;i++){
				if(i==4||i==5) continue;
				btn[i].setEnabled(!btn[i].isEnabled());
			}
			this.inserting=0;
			this.showEmployee(current);
		}
	}
	if(e.getSource()==this.btn[6]){
		if(count==0)
			return;
		empList.remove(current);
		count--;
		if(count==0){
			this.empNo.setText("");
			this.empName.setText("");
			this.empSex.setText("");
			this.empBirthday.setText("");
			this.depNo.setText("");
			this.empTelephone.setText("");
		}else{
			if(current>count-1){
				this.showEmployee(current-1);
				current=current-1;
			}
			else
				this.showEmployee(current);
		}
	}
	this.repaint();
}
}


