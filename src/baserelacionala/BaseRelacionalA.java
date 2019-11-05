

package baserelacionala;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseRelacionalA {
//    Que debemos facer : dende aplicacion java 
//
//
//	- inserir varios rexistros na taboa mediante o metodo insireProduto creado anteriormente usando sentencias sql standard   
//	os rexistros a  inserir son 
// 		 p1 , parafusos, 3
//		 p2 , cravos , 4
//		 p3, tachas, 6
//
// 	- Comprobar dende sqlplus que os rexistros foron creados .
//
//
//	- actualizar o prezo dun produto mediante o metodo actualizarPre  creado anteriormente . 	
//	- comprobar dende sqlplus que dita actualizacion tivo lugar correctamente
//	- listar mediante o metodo listaProdutos todos os datos das filas que se atopan na taboa produtos.
//	- borrar mediante o metodo borrarFila un producto.
//	- amosar mediante o metodo amosarFila os datos dun produto

Connection conn;

public Connection Conexion() throws SQLException{
    String driver = "jdbc:oracle:thin:";
    String host = "localhost.localdomain";
    String porto = "1521";
    String sid = "orcl";
    String usuario = "hr";
    String password = "hr";
    String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;
    
    Connection conn = DriverManager.getConnection(url);
    return conn;
}
public void Close() throws SQLException{
        conn.close();
    }
public void InserirDatos(String codigo, String descricion, int prezo) throws SQLException{
    try{
       Connection conn=Conexion();
       Statement st = conn.createStatement();
       //Esta part del codigo permite la insercion en la tabla
       st.executeUpdate("insert into produtos values('" + codigo + "','" + descricion + "','" + prezo + "')");
       
       System.out.println("Insertado");
    }
    
    catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalA.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en la insercción");

}
  
    
    
}
public void lista() throws SQLException{
        String sql="Select produtos.* from produtos";
        Connection conn=Conexion();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3));
        }
        
        
        
}
public void actualizar(String codigo, int precioNuevo) throws SQLException{
    Connection conn = Conexion();
    try {
       
            Statement st = conn.createStatement();

            st.executeUpdate("update produtos set precio='" + precioNuevo + "' where codigo='" + codigo + "'");

System.out.println("Cambio relaizado");
  
}catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalA.class.getName()).log(Level.SEVERE, null, ex);
}
}
public void borrarFila(String codigo) throws SQLException{
  Connection conn = Conexion();
    try{
       
        Statement st = conn.createStatement();
        st.executeUpdate("delete from produtos where codigo = '" + codigo + "'");
        System.out.println("Produto borrado");    
    }catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalA.class.getName()).log(Level.SEVERE, null, ex);
}
}
public void mostrarProducto(String codigo) throws SQLException{
    String sql = "Select * from produtos where codigo ='" + codigo + "'";
    Connection conn = Conexion();
     Statement st = conn.createStatement();
     ResultSet rs = st.executeQuery(sql);
   while(rs.next()){
            System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3));
    
}
}
    public static void main(String[] args) throws SQLException {
       BaseRelacionalA obj = new BaseRelacionalA();
       
//       obj.InserirDatos("p5","cravos",2);
//      obj.lista();
//      obj.actualizar("p5", 5);
//      obj.borrarFila();
//       obj.mostrarProducto("5");
       
       
       
    }
  
    
}
