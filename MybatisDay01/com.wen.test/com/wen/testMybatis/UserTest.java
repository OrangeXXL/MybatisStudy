package com.wen.testMybatis;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.wen.pojo.User;


public class UserTest {
	
	@Test
	public void TestFindUserById()throws Exception{
		String resource = "SqlMapConfig.xml";
		//ͨ���������������ļ���ȡ��������
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//ͨ�����������ļ��������������Ự����
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		//ͨ�����������Ự
		SqlSession openSession = factory.openSession();
		
		User user = openSession.selectOne("test.findUserById", 1);
		System.out.println(user);
		openSession.close();
	}
	
	@Test
	public void TestfindUserByName()throws Exception{
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession sqlSession =  factory.openSession();
		List<User> list = sqlSession.selectList("test.findUserByName", "��");
		System.out.println(list);
		sqlSession.close();
	}
	
	@Test
	public void TestInsertUser()throws Exception{
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = factory.openSession();
		
		
		User user = new User();
		System.out.println("----"+user.getId()+"------");
		user.setUsername("����");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setAddress("��������");
		sqlSession.insert("test.insertUser", user);
		sqlSession.commit();
		
		System.out.println("===="+user.getId()+"=======");
	}
	
	@Test
	public void deleteUserById()throws Exception{
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession sqlSession  = factory.openSession();
		sqlSession.delete("test.deleteUser", 29);
		
		//��Ҫ�ύ
		sqlSession.commit();
	}
	
	@Test
	public void updateUserById()throws Exception{
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession sqlSession  = factory.openSession();
		User user = new User();
		
		user.setId(27);
		user.setUsername("������");
		sqlSession.update("test.updateUser", user);
		
		sqlSession.commit();
	}
}
