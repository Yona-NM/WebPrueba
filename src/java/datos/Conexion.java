package datos;

import java.sql.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Conexion {
//    private static final String JDBC_URL = "jdbc:mysql://localhost/test?useSSL=false&serverTimezone=UTC";
//    private static final String JDBC_URL = "jdbc:mysql://localhost/pruebadb";
//    private static final String JDBC_USER = "root";
//    private static final String JDBC_PASS = "";

//    private static DataSource dataSource;
//    static Connection conn = null;

    public static Connection getConnection() throws Exception {
//        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

//    Context ctx = new InitialContext();
//            dataSource = (DataSource) ctx.lookup("java:/DevTestDS");
//            String DATASOURCE_CONTEXT = "java:/DevTestDS";
        Connection conn = null;
        String driver = "com.mysql.jdbc.Driver";
        try {
            

//        Properties props = new Properties();
//        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
//Context initialContext = new InitialContext(props);

            Context initialContext = new InitialContext();

            //cast is necessary
            DataSource datasource = (DataSource) initialContext.lookup("java:/TestDS");
            if (datasource == null) {
                throw new SQLException("Error de Conexion DataSource");
            }

            conn = datasource.getConnection();
            Class.forName(driver);

        } catch (NamingException ex) {
//      log("Cannot get connection: " + ex);
            System.out.println("NamingException" + ex.getMessage());
        } catch (SQLException ex) {
//      log("Cannot get connection: " + ex);
            System.out.println("SQLException" + ex.getMessage());
        }
        return conn;

    }

    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

}
