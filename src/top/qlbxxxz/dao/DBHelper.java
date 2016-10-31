package top.qlbxxxz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 		��    ��:	�޺�ܿ
 * 		ժ    Ҫ:	����һ���������ݿ�����Ĺ����࣬ʹ��ʱ��Ҫ����JDBC��������
 * 		��    ��:	0.1
 * 		��������:	2016-10-26
 * 
 * 		��������:	
 *					1.����ϸ�Ĵ�����ʾ
 *					2.����ʹ�÷���
 *
 *		ʹ�÷���:
 *					1.ʵ����һ���ù������ұ����ڹ��캯���д���ָ�������ӱ�Ҫ����(����)
 *					2.����startMysql,���ӵ����ݿ�(����)
 *					3.ʹ�ø��ֲ����������в���
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
                 System.out.println("���ݿ�����ʧ��01!!!");
             }
             try
             {
                 conn = DriverManager.getConnection(url,user,password);
                 
                 System.out.println("���ݿ����ӳɹ�!");
                 i = 1;
             }
             catch (SQLException e)
             {
                 e.printStackTrace();
                 System.out.println("���ݿ�����ʧ��02!!!");
             }
    	}else{
    		System.out.println("�Ѿ����ӳɹ�!����Ҫ�����ӻ�������ʹ��closeMysql()�����رպ�������!");
    	}
    	return i;
    }
    
    public int delete(String sql) //����һ��ɾ����� ,ɾ���ɹ�����1
    {  
    	int i=0;
        System.out.println("Log:ɾ������!");
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
	        	System.out.println("ɾ���ɹ�");
	        }else{
	        	System.out.println("ɾ��ʧ��");
	        }
    	}else{
    		System.out.println("���ݿ�����δ��");
    	}
    	return i;
    }
    public int delete(String ����, String ���� , String ֵ) //ͨ��ָ��IDɾ������  
    {  
    	int i=0; 
    	System.out.println("Log:ɾ������!");
    	if(!isclosed()){
	        String sql = "delete from "+����+" where "+���� +"='"+ֵ+"'";  
	         
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
	        	System.out.println("ɾ���ɹ�");
	        }else{
	        	System.out.println("ɾ��ʧ��");
	        }
    	}else{
    		System.out.println("���ݿ�����δ��");
    	}
        return i;//������ص���1����ִ�гɹ�;  
    }  
    
    public int insert(String uID , String name , String password , String email, boolean status, String activactionCode)
    		throws SQLException   //���user��Ϣ 
    {  
    	int i=0;  
    	System.out.println("Log:��Ӳ���!");
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
	            System.out.println("��ӳɹ�!");
	            
	            preStmt.close();
	        }
	        catch (SQLException e)  
	        {
	            System.out.println("���ʧ��!");
	        }
    	}else{
    		System.out.println("���ݿ�����δ��");
    	}
        return i;//����Ӱ���������1Ϊִ�гɹ�  
    } 
    public int insert(String SQL) throws SQLException   //�����Ϣ 
    {  
    	System.out.println("Log:��Ӳ���!");
        int i=0;
        if(!isclosed()){
	        String sql=SQL;   
	        try{
	        	Statement preStmt= conn.createStatement();
	            preStmt.execute(sql); 
	            System.out.println("��ӳɹ�!");
	            preStmt.close();
	        }
	        catch (SQLException e)
	        {
	            System.out.println("���ʧ��!");
	        }  
        }else{
    		System.out.println("���ݿ�����δ��");
    	}
        return i;//����Ӱ���������1Ϊִ�гɹ�  
    } 
    
    public String select(String ���� , String ���� , String value , String infotype){  
    	System.out.println("Log:��ѯ����!");
    	String userinfo = null;
    	if(!isclosed()){
	        String sql = "select * from "+����+" where "+����+"='"+value+"'";
	        try
	        {  
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(sql);  
	  
	            if(rs.next())  
	            {
	                String Name = rs.getString(infotype);
	                userinfo = Name;
	                System.out.println("��ѯ�ɹ�!");
	            }
	            stmt.close();
	            //���Խ����ҵ���ֵд���࣬Ȼ�󷵻���Ӧ�Ķ��� 
	        }
	        catch (SQLException e)
	        {
	            System.out.println("��ѯʧ��!");
	        }
    	}else{
    		System.out.println("���ݿ�����δ��");
    	}
        return userinfo;
    }
    
    public int update(String ����, String ����, String �޸�ֵ, String ��������, String ����ֵ){  
        int i=0;  
        System.out.println("Log:�޸Ĳ���!");
        if(!isclosed()){
	        String sql="update "+����+" set  "+����+"=? where "+��������+"='"+ ����ֵ +"'";//ע��Ҫ��where����  
	        Connection cnn=getConn();
	        try{  
	            PreparedStatement preStmt =cnn.prepareStatement(sql);  
	            preStmt.setString(1,�޸�ֵ);
	            i=preStmt.executeUpdate();
	            preStmt.close();
	            System.out.println("�޸ĳɹ�");
	        }  
	        catch (SQLException e)  
	        {  
	        	System.out.println("�޸�ʧ��");
	        }  
        }else{
    		System.out.println("���ݿ�����δ��");
    	}
        return i;//����Ӱ���������1Ϊִ�гɹ�  
    }
    
    public int update(String SQL){
    	System.out.println("Log:�޸Ĳ���!");
        int i=0;  
        if(!isclosed()){
	        String sql=SQL;
	        try{
	        	Statement preStmt= conn.createStatement();
	            preStmt.execute(sql); 
	            System.out.println("�޸ĳɹ�!");
	            preStmt.close();
	        }
	        catch (SQLException e)
	        {
	            System.out.println("�޸�ʧ��!");
	        }  
	     }
	     else{
	    		System.out.println("���ݿ�����δ��");
	    	}
        return i;//����Ӱ���������1Ϊִ�гɹ�  
    }
    
    public Connection getConn(){
    	boolean isclose = true;
    	try {
			isclose = conn.isClosed();
		} catch (SQLException e) {
			System.out.println("δ�������ݿ⣬getConn����null");
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
				System.out.println("�ر����ݿ�����!");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
    
	public boolean isclosed(){  //�ж�conn�Ƿ�ر� 
		boolean closed = true;
		if(conn != null){
			try {
				closed =  conn.isClosed();
			} catch (SQLException e) {
				System.out.println("δ֪����01!");
			}
		}
		return closed;
    }
}
