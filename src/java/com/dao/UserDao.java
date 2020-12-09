package com.dao;

import com.common.AES256;
import com.common.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDao {

    public boolean validate(String username, String password) {
        boolean flag = false;

        DatabaseConnection db = new DatabaseConnection();
        Connection con = db.dbConnection();

        String encpassword = AES256.encrypt(password);
        if (con != null) {

            try {
                String sql = "SELECT * FROM users where username=(?) and userpassword=(?);";
                PreparedStatement smt=con.prepareStatement(sql);
                smt.setString(1, username);
                smt.setString(2,encpassword);
                ResultSet rs = smt.executeQuery();
                if(rs.next()) {
                    flag=true;
                }
                rs.close();
                smt.close();
            } catch (Exception e) {
                    System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            }
        }
        return flag;
    }

}
