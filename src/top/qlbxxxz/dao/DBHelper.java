package top.qlbxxxz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 		作    者:	罗浩芸
 * 		摘    要:	这是一个连接数据库操作的工具类，使用时需要导入JDBC的驱动包
 * 		版    本:	0.1
 * 		更新日期:	2016-10-26
 * 
 * 		更新内容:	
 *					1.更详细的错误提示
 *					2.增加使用方法
 *
 *		使用方法:
 *					1.实例化一个该工具类且必须在构造函数中传参指定的连接必要数据(必须)
 *					2.启动startMysql,连接到数据库(必须)
 *					3.使用各种操作方法进行操作
 */

public class DBHelper {
    public  String url = "jdbc:mysql://115.28.61.70/simplesteam";  
    public  String name = "com.mysql.jdbc.Driver";  
    public  String user = "root";  
    public  String password = "root";  
    private Connection conn = null;
    private PreparedStatement pst = null;
    
    public int startMysql(){
    	int i = 0;
    	if(isclosed()){
    		 try
             {
                 Class.forName(name);
             }
             catch (ClassNotFoundException e)
             {  
                 e.printStackTrace();  
                 System.out.println("数据库连接失败01!!!");
             }
             try
             {
                 conn = DriverManager.getConnection(url,user,password);
                 
                 System.out.println("数据库连接成功!");
                 i = 1;
             }
             catch (SQLException e)
             {
                 e.printStackTrace();
                 System.out.println("数据库连接失败02!!!");
             }
    	}else{
    		System.out.println("已经连接成功!不需要再连接或者请先使用closeMysql()方法关闭后再连接!");
    	}
    	return i;
    }
    
    public int delete(String sql) //传入一条删除语句 ,删除成功返回1
    {  
    	int i=0;
        System.out.println("Log:删除操作!");
    	if(!isclosed()){
	        try  
	        {
	            Statement stmt = conn.createStatement();  
	            i = stmt.executeUpdate(sql);  
	            stmt.close();
	        }
	        catch (SQLException e)  
	        {  
	            e.printStackTrace();  
	        }
	        if(i == 1){
	        	System.out.println("删除成功");
	        }else{
	        	System.out.println("删除失败");
	        }
    	}else{
    		System.out.println("数据库连接未开");
    	}
    	return i;
    }
    public int delete(String 表名, String 列名 , String 值) //通过指定ID删除数据  
    {  
    	int i=0; 
    	System.out.println("Log:删除操作!");
    	if(!isclosed()){
	        String sql = "delete from "+表名+" where "+列名 +"='"+值+"'";  
	         
	        try  
	        {  
	            Statement stmt = conn.createStatement();  
	            i = stmt.executeUpdate(sql); 
	            stmt.close();
	        }  
	        catch (SQLException e)  
	        {  
	            e.printStackTrace();  
	        } 
	        
	        if(i == 1){
	        	System.out.println("删除成功");
	        }else{
	        	System.out.println("删除失败");
	        }
    	}else{
    		System.out.println("数据库连接未开");
    	}
        return i;//如果返回的是1，则执行成功;  
    }  
    
    public int insert(String uID , String name , String password , String email, boolean status, String activactionCode)
    		throws SQLException   //添加user信息 
    {  
    	int i=0;  
    	System.out.println("Log:添加操作!");
    	if(!isclosed()){
	        String sql="insert into t_user values(?,?,?,?,?,?)";   
	        try{
	        	PreparedStatement preStmt= conn.prepareStatement(sql);  
	            preStmt.setString(1, uID);
	            preStmt.setString(2, name);
	            preStmt.setString(3, password);
	            preStmt.setString(4, email);
	            preStmt.setBoolean(5, status);
	            preStmt.setString(6, activactionCode);
	        	preStmt.executeUpdate(); 
	            System.out.println("添加成功!");
	            
	            preStmt.close();
	        }
	        catch (SQLException e)  
	        {
	            System.out.println("添加失败!");
	        }
    	}else{
    		System.out.println("数据库连接未开");
    	}
        return i;//返回影响的行数，1为执行成功  
    } 
    public int insert(String SQL) throws SQLException   //添加信息 
    {  
    	System.out.println("Log:添加操作!");
        int i=0;
        if(!isclosed()){
	        String sql=SQL;   
	        try{
	        	Statement preStmt= conn.createStatement();
	            preStmt.execute(sql); 
	            System.out.println("添加成功!");
	            preStmt.close();
	        }
	        catch (SQLException e)
	        {
	            System.out.println("添加失败!");
	        }  
        }else{
    		System.out.println("数据库连接未开");
    	}
        return i;//返回影响的行数，1为执行成功  
    } 
    
    public String select(String 表名 , String 列名 , String value , String infotype){  
    	System.out.println("Log:查询操作!");
    	String userinfo = null;
    	if(!isclosed()){
	        String sql = "select * from "+表名+" where "+列名+"='"+value+"'";
	        try
	        {  
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(sql);  
	  
	            if(rs.next())  
	            {
	                String Name = rs.getString(infotype);
	                userinfo = Name;
	                System.out.println("查询成功!");
	            }
	            stmt.close();
	            //可以将查找到的值写入类，然后返回相应的对象 
	        }
	        catch (SQLException e)
	        {
	            System.out.println("查询失败!");
	        }
    	}else{
    		System.out.println("数据库连接未开");
    	}
        return userinfo;
    }
    
    public int update(String 表名, String 列名, String 修改值, String 条件列名, String 条件值){  
        int i=0;  
        System.out.println("Log:修改操作!");
        if(!isclosed()){
	        String sql="update "+表名+" set  "+列名+"=? where "+条件列名+"='"+ 条件值 +"'";//注意要有where条件  
	        Connection cnn=getConn();
	        try{  
	            PreparedStatement preStmt =cnn.prepareStatement(sql);  
	            preStmt.setString(1,修改值);
	            i=preStmt.executeUpdate();
	            preStmt.close();
	            System.out.println("修改成功");
	        }  
	        catch (SQLException e)  
	        {  
	        	System.out.println("修改失败");
	        }  
        }else{
    		System.out.println("数据库连接未开");
    	}
        return i;//返回影响的行数，1为执行成功  
    }
    
    public int update(String SQL){
    	System.out.println("Log:修改操作!");
        int i=0;  
        if(!isclosed()){
	        String sql=SQL;
	        try{
	        	Statement preStmt= conn.createStatement();
	            preStmt.execute(sql); 
	            System.out.println("修改成功!");
	            preStmt.close();
	        }
	        catch (SQLException e)
	        {
	            System.out.println("修改失败!");
	        }  
	     }
	     else{
	    		System.out.println("数据库连接未开");
	    	}
        return i;//返回影响的行数，1为执行成功  
    }
    
    public Connection getConn(){
    	boolean isclose = true;
    	try {
			isclose = conn.isClosed();
		} catch (SQLException e) {
			System.out.println("未连接数据库，getConn返回null");
		}
    	if(!isclose){
    		return conn;
    	}else{
    		return null;
    	}
    }
    
    public void closeMysql(){
    	if(!isclosed()){
    		try {
				conn.close();
				if(pst != null)
					pst.close();
				System.out.println("关闭数据库连接!");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
    
	public boolean isclosed(){  //判断conn是否关闭 
		boolean closed = true;
		if(conn != null){
			try {
				closed =  conn.isClosed();
			} catch (SQLException e) {
				System.out.println("未知错误01!");
			}
		}
		return closed;
    }
}
