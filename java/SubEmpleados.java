package hito2;

import java.util.*;
import java.io.*;

class SubEmpleados{

    Empleado empleados[];
    
    Formateo fr = new Formateo();
    
    SubVentas sv;
    
    String fichero;

    
    public Empleado check (int in_code, String in_pass) throws PasswordErrorException,LoginErrorException{

        int ni = -1;
        
        for(int i = 0; i < empleados.length; i++){
            
            if(empleados[i].getcode() == in_code){
                
                ni = i;
                
            }
            
        }
        
        if(ni == -1){ throw new LoginErrorException(); }
        
        if(empleados[ni].getpass().equals(in_pass)){
                
            return empleados[ni];
                
        }else{

            throw new PasswordErrorException();
            
        }
        

    }
    
    public SubEmpleados(){
        
    }
    
    public void link(SubVentas sv_i){
        
        sv = sv_i;
        
    }

    public boolean load(String fichero_i) throws FileNotFoundException{
        
        fichero = fichero_i;
        
        String nombre="nombre:";
        String codigo="codigo:";
        String password="password:";
        String numero="empleados:";
        String nivel="nivel:";
        String turno="turno:";
    
        String Num_Empleado = "";
        String Nom_Empleado = "";
        String Cod_Empleado = "";
        String Pas_Empleado = "";
        String Tur_Empleado = "";
        int Niv_Empleado = 0;
        double Ext = 0.0;
        int Codigo = 0;
    
        File Datos=new File(fichero);
        Scanner datos=new Scanner(Datos);
        
        int e = 0;
        while (datos.hasNext()){
            
            String Variable=datos.next();
        
            if(Variable.equals(numero)){
                Num_Empleado = datos.next();
                empleados = new Empleado[Integer.parseInt(Num_Empleado)];
            }
            
            else if (Variable.equals(nombre)){
                Nom_Empleado = datos.next();
            }
            
            else if(Variable.equals(codigo)){
                Cod_Empleado = datos.next();
                Codigo = Integer.parseInt(Cod_Empleado);
            }
            
            else if(Variable.equals(password)){
                Pas_Empleado = datos.next(); 
            }
            
            else if(Variable.equals(nivel)){
                Niv_Empleado = Integer.parseInt(datos.next());
                
            }
            
            else if(Variable.equals(turno)){
                Tur_Empleado = datos.next();
            }
            
            else if(Tur_Empleado.equals("nocturno")){
                                
                Ext = Double.parseDouble(datos.next());
                
                empleados[e] = new EmpleadoNocturno(Codigo,Pas_Empleado,Nom_Empleado,Niv_Empleado,Ext);
                                
                e++;
                
            }else{
                
                Ext = Double.parseDouble(datos.next());
                
                empleados[e] = new EmpleadoDiurno(Codigo,Pas_Empleado,Nom_Empleado,Niv_Empleado,Ext);

                e++;
    
            }
                      
        }
        
        datos.close();
                
        return true;

    }

    public void save() throws FileNotFoundException,UnsupportedEncodingException{
        
        PrintWriter writer = new PrintWriter(fichero, "UTF-8");
        
        writer.println("empleados:");
        writer.println(empleados.length);
        
        for(int i=0; i < empleados.length; i++){
            
            writer.println("nombre:");
            writer.println(empleados[i].getname());
            writer.println("codigo:");
            writer.println(empleados[i].getcode());
            writer.println("password:");
            writer.println(empleados[i].getpass());
            writer.println("nivel:");
            writer.println(empleados[i].getlevel());
            writer.println("turno:");
            writer.println(empleados[i].getturno());
            
            if(empleados[i].getturno().equals("diurno")){
                
                writer.println("retencion:");
                writer.println(empleados[i].getretencion());
                
            }else{
                
                writer.println("plusNocturnidad:");
                writer.println(empleados[i].getplus());
                
            }
            
        }     
            
            
        writer.close();
        
    }

} 
