package hito2;

import java.util.*;
import java.io.*;

class UserInterface{
 
    SubVentas sv = new SubVentas();
    
    SubEmpleados se = new SubEmpleados();
    
    Empleado emp;
    
    Formateo fr = new Formateo();
    
    Scanner TC = new Scanner(System.in);
    
    public void login(){
          
        while(true){ //Bucle principal del programa, mientras no se agoten los intentos de login, todo lo demás serán situaciones derivadas de haberse identificado correctamente
        
            boolean loged = false;
            
            for(int i = 0; i < 3 && loged == false; i++){
                
                fr.clr();
        
                fr.color(" ## Control de acceso de empleados ## \n\n", 0, 2);
                                
                System.out.print("Introduzca su código de empleado: ");
                
                int in_code = TC.nextInt();
                
                System.out.print("Introduzca su contraseña: ");
        
                String in_pass = TC.next();
                    
                try{
                    
                    emp = se.check(in_code, in_pass);
                    
               //     System.out.print("Checkpoint"); System.exit(0);
                    
                    loged = true;
                
                }
                
                catch(LoginErrorException e){
                    
                    fr.color(e.toString(),1,0);
                    
                    try { System.in.read(); } catch (IOException er) {er.printStackTrace();}
                    
                }


                catch(PasswordErrorException e){
                
                    fr.color(e.toString(),1,0);
                    
                    try { System.in.read(); } catch (IOException er) {er.printStackTrace();}
                    
                }
                
                
                
            }
            
            if(loged == false){
        
                System.out.println("Número de intentos de identificación agotados.");
                
                System.exit(0);
                
            }else{
                
                this.menuempleados();
                
                
            }
            
        }
        
    }
    
    private void menuempleados(){
        
        fr.clr();
        
        fr.color(" ## Menú de empleado ", 0, 3);
        fr.color("["+emp.getname()+"]", 6,3);
        fr.color(" ## \n\n", 0, 3);
        
        System.out.println("1. Hacer pedido");
        System.out.println("2. Modificar producto");
        System.out.println("3. Cambiar contraseña empleado");
        System.out.println("4. Salir");
        
        Scanner TC = new Scanner(System.in);
        
        int opt = TC.nextInt();
        
        if(opt < 1 || opt > 4){
            
            this.menuempleados();
            
        }
        
        switch(opt){
            
            case 1: 
            
                fr.clr();
            
                fr.color(" ## 1. Hacer pedido ## \n\n", 0, 2);
            
                System.out.print("Introduzca el número de productos distintos del pedido: ");
                
                this.menuventa(TC.nextInt());
                
                this.menuempleados();
            
                break;
            
            case 2: this.menuproductos_mod(); this.menuempleados(); break;
            
            case 3: 
            
                fr.clr();
            
                fr.color(" ## 3. Cambiar contraseña empleado ## \n\n", 0, 2);
            
                System.out.print("Introduzca la nueva contraseña: ");
                
                emp.setpass(TC.next());
                
                this.menuempleados();
                
                break;
                
            case 4: 
            
                try{
                    
                    fr.clr();
                    
                    fr.color(" ## Productividad del empleado ## \n\n", 0, 2);
            
                    System.out.print("Productividad: "+emp.getgratificacion()+"€");
                    
                    try { System.in.read(); } catch (IOException e) {e.printStackTrace();}
                    
                    
                    se.save();
                    sv.save();
                    
                }catch(FileNotFoundException | UnsupportedEncodingException e){
                    
                    System.out.println("No se ha podido guardar en disco!");
                    
                }
                    
                this.login(); break;
            
        }

    }

    private Producto menuproductos_list(){
        
        Producto[] productos = sv.getproductos();
        
        fr.clr();
        
        fr.color(" ## 2.1 Seleccione un producto ## \n\n", 0, 2);
        
        System.out.print("   "+fr.ret("Nombre",4,0,15)+fr.ret("Código",5,0,15)+fr.ret("Precio",6,0,15)+fr.ret("Cantidad",4,0,15)+fr.ret("Tipo",5,0,15)+fr.ret("Oferta",6,0,15)+fr.ret("Días restantes",4,0,15)+"\n");

        
        for(int p = 1; p <= productos.length; p++){
            
            String oferta;
            
            String dias;
            
            
            if(productos[p-1].gettype().equals("Perecedero")){
                
                oferta = "n/a";
                
                dias = String.format("%d",productos[p-1].getdias());
                
            }else{
                
                oferta = String.format("%d",productos[p-1].getofertaid());
                
                dias = "n/a";
                
            }
                

            System.out.print(p+". "
            +fr.ret(productos[p-1].getname(),4,0,15)
            +fr.ret(String.format("%d",productos[p-1].getcode()),5,0,15)
            +fr.ret(String.format("%.2f€",productos[p-1].getprice()),6,0,15)
            +fr.ret(String.format("%d",productos[p-1].getcount()),4,0,15)
            +fr.ret(String.format("%s",productos[p-1].gettype()),5,0,15)
            +fr.ret(oferta,6,0,15)
            +fr.ret(dias,4,0,15)+"\n");
            
        }
        
        Scanner TC = new Scanner(System.in);
        
        int opt = TC.nextInt();
        
        if(opt > 0 && opt <= productos.length){
        
            return productos[opt-1];
            
        }else{
            
            return this.menuproductos_list();
            
        }
        
    }

    public void menuproductos_mod(){
        
        Producto producto = this.menuproductos_list();
        
        this.mod(producto);
        
    }

    private void mod(Producto producto){
        
        Producto[] productos = sv.getproductos();
        
        fr.clr();
        
        fr.color(" ## 2.2 Seleccione el atributo a modificar ## \n\n", 0, 2);
        
        System.out.println("1. Modificar nombre");
        System.out.println("2. Modificar precio");
        System.out.println("3. Modificar código");
        System.out.println("4. Modificar número de unidades");
        
        
        Scanner TC = new Scanner(System.in);
        
        int opt = TC.nextInt();
        
        if(opt < 1 || opt > 4){
            
            this.mod(producto);
            
        }else{
            
            switch(opt){
                
                case 1:
                
                    fr.clr();
        
                    fr.color(" ## 2.2.1 Modificar nombre ## \n\n", 0, 2);
                    
                    boolean ok1;
                    
                    String nam;
                    
                    do{
                        
                        ok1 = true;
                        
                        System.out.print("Introduzca el nuevo nombre: ");
                      
                        nam = TC.next();
                        
                        for(int i = 0; i < productos.length; i++){
                        
                            if(nam.equals(productos[i].getname()) &&  !producto.getname().equals(productos[i].getname())){
                                
                                ok1 = false;
                                
                            }
                            
                        }
                        
                        if(ok1 == false){
                            
                            fr.color("El nombre introducido está en uso, introduzca uno alternativo!\n\n",1,0);
                            
                        }
                        
                    }while(ok1 == false);
                
                    producto.setname(nam);
                    
                    break;
                    
                case 2:
                
                    fr.clr();
        
                    fr.color(" ## 2.2.2 Modificar precio ## \n\n", 0, 2);
                    
                    System.out.print("Introduzca el nuevo precio: ");
                
                    producto.setprice(TC.nextDouble());
                    
                    break;
                    
                case 3:
                
                    fr.clr();
        
                    fr.color(" ## 2.2.3 Modificar código ## \n\n", 0, 2);
                    
                    boolean ok2;
                    
                    int cod;
                    
                    do{
                        
                        ok2 = true;
                        
                        System.out.print("Introduzca el nuevo código: ");
                      
                        cod = TC.nextInt();
                        
                        for(int i = 0; i < productos.length; i++){
                        
                            if(productos[i].getcode() == cod && productos[i].getcode() != producto.getcode()){
                                
                                ok2 = false;
                                
                            }
                            
                        }
                        
                        if(ok2 == false){
                            
                            fr.color("El código introducido está en uso, introduzca uno alternativo!\n\n",1,0);
                            
                        }
                        
                    }while(ok2 == false);
                
                    producto.setcode(cod);
                    
                    break;
                    
                case 4:
                
                    fr.clr();
        
                    fr.color(" ## 2.2.2 Modificar número de unidades ## \n\n", 0, 2);
                    
                    int cant;
                    
                    boolean ok3;
                    
                    do{
                        
                        ok3 = false;
                    
                        System.out.print("Introduzca la cantidad de unidades a añadir: ");
                        
                        cant = TC.nextInt();
                        
                        if(cant > 0){
                            
                            ok3 = true;
                            
                        }else{
                            
                            fr.color("Debes introducir 1 o más productos a añadir!\n\n",1,0);
                            
                        }
                    }while(ok3 == false);
                
                    producto.setcount(cant+producto.getcount());
                    
                    break;
                
            }
            
            try{
                    
                sv.save();
                    
            }catch(FileNotFoundException | UnsupportedEncodingException e){
                    
                System.out.println("No se ha podido guardar en disco!");
                    
            }
        }
        
    }

    public void menuventa(int n_prod){
        
        Pedido pedido = new Pedido(n_prod);
        
        this.m_venta(pedido);
        
    }
    
    private void m_venta(Pedido pedido){
        
        fr.clr();
        
        fr.color(" ## 1. Hacer pedido ## \n\n", 0, 3);
        
        System.out.println("1. Añadir productos");
        System.out.println("2. Visualizar precio total");
        System.out.println("3. Imprimir factura");
        System.out.println("4. Terminar pedido");
        
        Scanner TC = new Scanner(System.in);
        
        int opt = TC.nextInt();
        
        if(opt < 1 || opt > 4){
            
            this.m_venta(pedido);
            
        }
        
        switch(opt){
            
            case 1: 
            
            
            
                Producto orig = this.menuproductos_list();
                
                System.out.print("Introduzca la cantidad de productos de esta clase a añadir: ");
                
                int kant = TC.nextInt();
                                
                try{
                    
                    orig.pop(kant);
                    
                    int st = pedido.add(sv.clone(orig, kant));
                    
                    if(st != 0){
                        
                        fr.clr();
            
                        fr.color(" ## 1.1. Añadir producto ## \n\n", 0, 1);
                                                
                    }                    
                
                    switch(st){
                    
                        case 1: 
                        
                            fr.color("La lista de productos está completa, no se pueden añadir más productos!", 1, 0);
                            
                            try { System.in.read(); } catch (IOException e) {e.printStackTrace();}
                            
                            orig.setcount(orig.getcount()+kant);

                            
                            break;
                        
                        case 2:
                        
                            fr.color("Este producto ya se encuentra en la lista del pedido!", 1, 0);
                            
                            try { System.in.read(); } catch (IOException e) {e.printStackTrace();}
                            
                            orig.setcount(orig.getcount()+kant);

                            break;
                            
                        case 3:
                        
                            fr.color("Se ha completado la lista de productos!", 1, 0);
                            
                            try { System.in.read(); } catch (IOException e) {e.printStackTrace();}
                            
                            break;
                            
                    }
                
                 }catch(NoStockException e){
                    
                    fr.color(e.toString(), 1, 0);
                    
                    try { System.in.read(); } catch (IOException er) {er.printStackTrace();}
                    
                }
                
                this.m_venta(pedido); break;
            
            case 2: 
            
                fr.clr();
        
                fr.color(" ## 1.2. Visualizar precio total ## \n\n", 0, 3);
            
                fr.color("> "+String.format("%.2f",pedido.subtotal())+"€\n", 0,2);
                
                try { System.in.read(); } catch (IOException e) {e.printStackTrace();}
                
                this.m_venta(pedido); break;
                
            case 3:
            
                this.menuventa_factura(pedido);
                
                try { System.in.read(); } catch (IOException e) {e.printStackTrace();}
                
                this.m_venta(pedido); break;
                
            case 4: 
            
                emp.gratificacion(pedido.total());
                
            
        }
        
    }
    
    public void menuventa_factura(Pedido pedido){
        
        Producto[] productos = pedido.getproductos();
        
        int pos = pedido.getpos();
        
        fr.clr();
        
        fr.color(" ## 1.3. Imprimir factura ## \n\n", 0, 3);
                
        System.out.print("   "+fr.ret("Nombre",4,0,15)+fr.ret("Código",5,0,15)+fr.ret("Precio",6,0,15)+fr.ret("Cantidad",4,0,15)+fr.ret("Tipo",5,0,15)+fr.ret("Oferta",6,0,15)+fr.ret("Días restantes",4,0,15)+"\n");

        
        for(int p = 1; p <= pos; p++){
            
            String oferta;
            
            String dias;
            
            
            if(productos[p-1].gettype().equals("Perecedero")){
                
                oferta = "n/a";
                
                dias = String.format("%d",productos[p-1].getdias());
                
            }else{
                
                oferta = String.format("%d",productos[p-1].getofertaid());
                
                dias = "n/a";
                
            }
                

            System.out.print(p+". "
            +fr.ret(productos[p-1].getname(),4,0,15)
            +fr.ret(String.format("%d",productos[p-1].getcode()),5,0,15)
            +fr.ret(String.format("%.2f€",productos[p-1].getprice()),6,0,15)
            +fr.ret(String.format("%d",productos[p-1].getcount()),4,0,15)
            +fr.ret(String.format("%s",productos[p-1].gettype()),5,0,15)
            +fr.ret(oferta,6,0,15)
            +fr.ret(dias,4,0,15)+"\n");
            
        }
        
        fr.color("\n\nSubtotal: "+String.format("%.2f",pedido.subtotal())+"€",0,2);
        
        fr.color("\n\nOfertas: "+String.format("%.2f",pedido.total()-pedido.subtotal())+"€",0,2);
        
        fr.color("\n\nTotal: "+String.format("%.2f",pedido.total())+"€",0,2);
        
        fr.color("\n\nLe atendió: "+emp.getname()+"\n\n",0,3);
        
    }

    public UserInterface(String empleados, String productos, String ofertas){
        
        boolean ld = false;
        for(int i = 0; i < 2 && ld == false; i++){
        
            try{
            
                 ld = sv.loadprod(productos);
                
            }catch(FileNotFoundException e){
                
                System.out.println("No se ha encontrado el fichero de productos!\nIntroduzca la ruta: ");
                
                productos = TC.next();
                
                }
            
        }
        
        if(ld != true){ System.out.print("Error en la lectura de ficheros!"); System.exit(0); }
        
        ld = false;
        for(int i = 0; i < 2 && ld == false; i++){
        
            try{
            
                 ld = sv.loadofert(ofertas);
                
            }catch(FileNotFoundException e){
                
                System.out.println("No se ha encontrado el fichero de ofertas!\nIntroduzca la ruta: ");
                
                ofertas = TC.next();
                
                }
            
        }
        
        if(ld != true){ System.out.print("Error en la lectura de ficheros!"); System.exit(0); }
        
        ld = false;
        for(int i = 0; (i < 2 && ld == false); i++){
        
            try{
            
                 ld = se.load(empleados);
                
            }catch(FileNotFoundException e){
                
                System.out.println("No se ha encontrado el fichero de empleados!\nIntroduzca la ruta: ");
                
                empleados = TC.next();
                
                }
            
        }
        
        se.link(sv);
        
        if(ld != true){ System.out.print("Error en la lectura de ficheros!"); System.exit(0); }
        
    }
    
}
