package com.it.test;

import com.it.dao.IUserDao;
import com.it.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DemoTest {
        private InputStream inputStream;
        private SqlSession sqlSession;
        private IUserDao userDao;

        @Before
        public void init()throws IOException {
            inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = factory.openSession();
            userDao = sqlSession.getMapper(IUserDao.class);
        }
        @After
        public void destory() throws IOException {
            sqlSession.commit();
            //释放资源
            sqlSession.close();
            inputStream.close();
        }

    /*
     * 增加用户*/
    @Test
    public void testSaveUser(){
        try {
        User user = new User();
        user.setMobile("李四");
        user.setPassword("123456");
        user.setSex("男");

        Date birthday=new SimpleDateFormat("yyyy-MM-dd").parse("2000-11-11");
        user.setBirthdate(birthday);
        user.setAddress("黑龙江");
        user.setEmail("netyu@163.com");
        user.setState("1");

        Timestamp d = new Timestamp(System.currentTimeMillis());
        user.setCreate_time(d);
        user.setUpdate_time(d);

        userDao.saveUser(user);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * 根据登录名查询用户
    * */
    @Test
    public void testFindByMobile(){
        List<User> user = userDao.findByMobile("李四");
        for (User user1:user){
            System.out.println(user1);
        }

    }
    /*
    * 根据登录名或id  、 用户名和id 删除用户*/
    @Test
    public void testDeleteUser() {
        try {
            User user = new User();
            user.setId(3L);
            user.setMobile("王五");
            userDao.deleteUser(user);
            System.out.println("删除成功");
        }catch(Exception e){
            System.out.println("删除失败");
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setMobile("王五");
        user.setPassword("55555");
        user.setId(3L);
        userDao.updateUser(user);
    }

}
