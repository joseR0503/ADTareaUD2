import Acceso_BBDD_MySQL.Acceso;

public class App {
    public static void main(String[] args) {
        Acceso miAcceso = new Acceso();
        miAcceso.conectar();
        miAcceso.realizarConsulta("SELECT * FROM ALUMNOS");
        System.out.println("-----------");
        miAcceso.realizarConsultaFormateada("SELECT * FROM ALUMNOS");







        miAcceso.desconectar();
    }
}
