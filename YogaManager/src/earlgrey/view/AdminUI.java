package earlgrey.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import earlgrey.service.AdminService;
import earlgrey.service.AdminServiceImpl;
import earlgrey.vo.MemberVO;
import earlgrey.vo.TeacherVO;

public class AdminUI {
	private Scanner scan;
	private AdminService service;
	private boolean flag;
	public AdminUI() {
		this.service = new AdminServiceImpl();
		this.scan = new Scanner(System.in);
	}
	public void init() throws SQLException {
		int choice = showMenu();
		switch(choice) {
			case 1:  System.out.println("수강생의 이메일을 입력 : "); String email = this.scan.next();
				MemberVO member = this.service.readMember(email);
				if(member != null)System.out.println(member);
				if(member == null) {
					System.out.println("해당하는 수강생을 찾을 수 없습니다.");
					
				}break;
				//회원 조회하기
			case 2: System.out.println("강사의 사번을 입력:"); int empno = scan.nextInt();
				TeacherVO teacher = this.service.readTeacher(empno);
				//강사 조회하기
				if(teacher != null) System.out.println(teacher);
				if(teacher == null) {
					System.out.println("해당하는 강사를 찾을 수없습니다.");
				
				}break;
			case 3: 
				ArrayList<MemberVO>list = this.service.readAllMember();
				if(!list.isEmpty()) {
					System.out.println();
					System.out.println("---------------수강생 정보 -------------");
					System.out.println("수강생 이메일\t\t수강생 이름\t\t수강생 전화번호");
				}
				for(int i = 0; i <list.size(); i++) {
					System.out.print(list.get(i).getEmail()+"\t\t"+
					list.get(i).getName()+"\t\t"+list.get(i).getPhone()+"\n");
				}
				System.out.println();
				if(list.isEmpty()) {
					System.out.println("회원 정보가 없습니다.");
				
				}break;
				//모든 회원 조회하기
			case 4: 
				ArrayList<TeacherVO>list1 =this.service.readAllTeacher();
				if(!list1.isEmpty()) {
					System.out.println();
					System.out.println("----------------선생님 정보 ---------------");
					System.out.println("선생님 사번\t\t선생님 이름\t\t선생님 전화번호\t\t");
				}
				for(int i = 0; i< list1.size(); i++) {
					System.out.println(list1.get(i).getEmpno()+"\t\t"+list1.get(i).getName()+
							"\t\t"+list1.get(i).getPhone());
				}
				System.out.println();
				if(list1.isEmpty()) {
					System.out.println("강사 정보가 없습니다.");
				
				}break;
				//모든 사 조회하기
			case 5:  
				System.out.println("<<업데이트 하고자 하는 수강생의 정보를 입력  >>");
				System.out.println("이메일 :");String email1 = scan.next();
				MemberVO mb = this.service.readMember(email1);
				System.out.println(mb);
			    System.out.println("<<바꿀 정보 입력>>");
				System.out.println("이름 : ");String name = scan.next();
				System.out.println("핸드폰 : ");String phone = scan.next();
				MemberVO member1= new MemberVO(email1, name, phone);
				int row = this.service.updateMember(member1);
				if(row ==1) {System.out.println("업데이트 성공");}
				if(row==0) {
					System.out.println("해당 수강생의 업데이트에 실패하였습니다.  ");
				}
				System.out.println();
				break;
				//회원 정보 업데이트
			case 6://강사 추가	this.empno = empno;
				//this.name = name;
				//this.phone = phone;
				System.out.println("<<강사 추가>>");
				System.out.println("사번 입력:  "); int empno1 = scan.nextInt();
				System.out.println("이름 입력 : "); String name1 = scan.next();
				System.out.println("전화번호 :  "); String phone1 = scan.next();
				TeacherVO teacher1 = new TeacherVO(empno1, name1, phone1);
				row = this.service.insertTeacher(teacher1);
				if(row == 1) {
					System.out.println("강사 추가 성공  ");
					
				}else {System.out.println("강사 추가에 실패하였습니다.");}
				break;
			case 7:System.out.println("삭제할 강사의 사번을 입력 : ");
			int empno2 = scan.nextInt();
			row = this.service.deleteTeahcer(empno2);
			if(row == 1) {
				System.out.println("강사 삭제 성공  ");
				
			}else {System.out.println("강사 삭제에 실패하였습니다.");}
			break;
			case 0: System.exit(0); 
		}
	}
	
	
	
	public int showMenu() {
		System.out.println("1. 회원 조회하기\n" + 
				"2. 강사 조회하기\n" + 
				"3. 모든 회원 조회하기\n" + 
				"4. 모든 강사 조회하기\n" + 
				"5. 회원 정보 업데이트\n"+
				"6. 강사 추가\n"+
				"7. 강사 삭제\n"
				+ "0. 종료\n");
	
		return this.scan.nextInt();
		
	}
	
}
