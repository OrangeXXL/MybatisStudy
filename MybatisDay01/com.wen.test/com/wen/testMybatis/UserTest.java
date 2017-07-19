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
		//通过流将核心配置文件读取进来配置
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//通过核心配置文件输入流来创建会话工厂
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过工厂创建会话
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
		List<User> list = sqlSession.selectList("test.findUserByName", "王");
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
		user.setUsername("张三");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setAddress("陕西西安");
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
		
		//需要提交
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
		user.setUsername("哈哈哈");
		sqlSession.update("test.updateUser", user);
		
		sqlSession.commit();
	}
}
