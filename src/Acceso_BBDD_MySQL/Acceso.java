package Acceso_BBDD_MySQL;

import java.sql.*;

public class Acceso {
    private Connection conexion;
    private Statement statement;
    private ResultSet resultSet;
    ResultSetMetaData metaDatos;

    public Acceso() {
        this.conexion = null;
        this.statement = null;
        this.resultSet = null;
    }

    public void conectar() {
        try {
            // Se carga el driver mysql de la siguiente forma:
            Class.forName("com.mysql.jdbc.Driver");
            // Datos para la conexión:
            String url = "jdbc:mysql://localhost:3306/aad";
            String usuario = "root";
            String contrasena = "";
            // Crear un objeto conexión:
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            // Se crea un objeto de tipo Statement, para realizar la consulta:
            statement = conexion.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void desconectar() {
        if (statement!=null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
       
        if (conexion != null)
            try {
                conexion.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

    }

    public void realizarConsulta(String consulta) {
        try {
            // Se realiza la consulta. Los resultados se guardan en el ResultSet rs
            resultSet = statement.executeQuery(consulta);
            // se recuperan los datos recorriendo el ResultSet, mostrando por pantalla los
            // resultados:
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " +
                        resultSet.getString(2) + " " +
                        resultSet.getString(3) + " " +
                        resultSet.getInt(4));
            }
            // Se debe cerrar la conexión con la base de datos (aquí o en otra función
            // aparte, al finalizar todas las operaciones)
             
             resultSet.close();
            // conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void realizarConsultaFormateada(String consulta){
        try {
            resultSet = statement.executeQuery(consulta);
             metaDatos = resultSet.getMetaData();
             System.out.println("Numero de Columnas: " + metaDatos.getColumnCount());
             System.out.println("Nombre de la tabla "+ metaDatos.getTableName(1));
             System.out.println("Nombre del esquema o catalogo: "+ metaDatos.getCatalogName(1));
             System.out.println();
             System.out.println("Datos de la tabla " +metaDatos.getTableName(1)+ " con columnas");
             System.out.println("-------------------------");

             for (int i = 1; i < metaDatos.getColumnCount()+1; i++) {
                System.out.print(metaDatos.getColumnName(i)+" ");
                
             }
             System.out.println();

             realizarConsulta(consulta);
            
             
             
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    
    }
}
