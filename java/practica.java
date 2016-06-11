package hito2;

class practica{

    public static void main(String args[]){
        
        UserInterface interfaz = new UserInterface("./data/empleados.txt", "./data/productos.txt", "./data/ofertas.txt");
        
        interfaz.login();
        
    }

}
