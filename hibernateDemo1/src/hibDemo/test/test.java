package hibDemo.test;

import hibDemo1.dao.StudentService;
import hibDemo1.entity.Student;

import java.util.List;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {

		// 添加学生信息
		// AddStudent();

		// 显示所有学生信息
		ShowAll();

		// 显示单个学生信息
		// ShowOne();

		//更新学生信息
		//Update();
		
		//删除学生信息
		//Delete();
		
		//ShowAll();
	}

	public static void ShowAll() {
		StudentService service = new StudentService();
		List<Student> list = service.GetAllStudent();
		for (Student student : list) {
			System.out.println(student.getStuNo() + "  " + student.getStuName());
		}
	}

	public static void ShowOne() {
		String no = "A004";
		StudentService service = new StudentService();
		Student student = service.GetStudentBystuNo(no);
		if (student != null) {
			System.out.println(student.getStuNo() + "  " + student.getStuName());
		} else {
			System.out.println("no data");
		}
	}

	public static void AddStudent() {
		Scanner input = new Scanner(System.in);
		Student stu = new Student();
		System.out.print("请输入学生编号：(A001)");
		stu.setStuNo(input.next());
		System.out.print("请输入学生姓名：(A001)");
		stu.setStuName(input.next());
		stu.setStuPass("888888");
		System.out.print("请输入学生年龄：(0-100)");
		stu.setStuAge(input.nextInt());
		System.out.print("请输入学生手机号：(A001)");
		stu.setMobile(input.next());
		System.out.print("请输入学生邮箱：(A001)");
		stu.setEmail(input.next());
		System.out.print("请输入学生地址：(A001)");
		stu.setAddress(input.next());

		StudentService service = new StudentService();
		service.AddStudent(stu);
	}

	public static void Update() {
		Scanner input = new Scanner(System.in);
		System.out.print("输入要修改的学号：");
		String stuNo = input.next();
		System.out.print("输入要修改的姓名：");
		String newName = input.next();

		StudentService service = new StudentService();
		service.UpdateStudent(stuNo, newName);
	}

	public static void Delete() {
		Scanner input = new Scanner(System.in);
		System.out.print("输入要修改的学号：");
		String stuNo = input.next();

		StudentService service = new StudentService();
		service.DeleteStudent(stuNo);
	}
}
