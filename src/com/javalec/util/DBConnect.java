package com.javalec.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
		// Database 환경 정의 //파이날 쓰는이유 못바꾸게 //데이터베이스를 가져온다.// 형식을 알아야 쓴다
		// 연결프로그램 jdbc: 내가사용할프로그램 mysql ://데이터베이스(서버)번호//3306포트 // 데이터베이스이름 //옵션자는 ?을씀 //캐릭터 uft8이다 
		//데이터베이스 타입 utf8 내 자바 utf파일로 연결도 utf로 그래야 안꺠지고 잘 왔다갔다거림 //서버타임존 시간 // 인코딩 유티에프8 도안 //
		public static final String url_mysql = "jdbc:mysql://127.0.0.1:3306/coffeeshop?severTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=FALSE";
		public static final String id_mysql = "root";
		public static final String pw_mysql = "qwer1234";
		
		//정보를 클라스에 두면 서버가 망가져도 가능하다. static 인스턴스를 만들지않고 바로쓴다.
		// 127.0.0.1 < 전세계에서 쓰는 내pc이다.
		// 남에꺼 불러오기. 
	
}
