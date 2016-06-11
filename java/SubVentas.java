package hito2;

import java.util.*;
import java.io.*;

class SubVentas{

    Producto productos[];
    
    Oferta ofertas[];

    Formateo fr = new Formateo();
    
    Empleado emp;
    
    String fichero_prod;
    
    String fichero_ofert;
    
    public SubVentas(){
        
        
    }
    
    public Producto[] getproductos(){
        
        return productos;
        
    }

    public boolean loadprod(String fichero) throws FileNotFoundException{
        
        fichero_prod = fichero;
        
        String nombre=("nombre:");
        String codigo=("codigo:");
        String precio=("precio:");
        String cantidad = "unidades:";
        String perecedero = "perecedero:";
        String dias = "dias:";
        String oferta = "oferta:";
        String numero=("productos:");

        String Num_Producto = "";
        
        String Nom_Producto = "";
        String Cod_Producto = "";
        String Pre_Producto = "";
        String Cant_Productos = "";
        String Tip_Productos = "";
        String Ext_Productos = "";
        
        
        int Codigo = 0;
        double Precio = 0.0;
        int Cantidad = 0;
        int Ext = 0;

        File Datos=new File(fichero);
        Scanner datos=new Scanner(Datos);

        int e = 0;

        while (datos.hasNext()){
    
            String Variable=datos.next();

            if(Variable.equals(numero)){
                Num_Producto=datos.next();
                productos = new Producto[Integer.parseInt(Num_Producto)];
            }
            else if (Variable.equals(nombre)){
                Nom_Producto = datos.next();
            }
            else if(Variable.equals(codigo)){
                Cod_Producto = datos.next();
                Codigo = Integer.parseInt(Cod_Producto);
            }
            else if(Variable.equals(precio)){
                Pre_Producto = datos.next();
                Precio = Double.parseDouble(Pre_Producto);
                
            }
            
            else if(Variable.equals(cantidad)){
                Cant_Productos = datos.next();
                Cantidad = Integer.parseInt(Cant_Productos);
                
            }
            
            else if(Variable.equals(perecedero)){
                if(datos.next().equals("si")){
                    
                    datos.next();
                    
                    Ext = Integer.parseInt(datos.next());
                    
                    productos[e] = new ProductoPerecedero(Codigo,Precio,Nom_Producto, Cantidad, Ext);
                    
                }else{
                    
                    datos.next();
                    
                    Ext = Integer.parseInt(datos.next());
                    
                    productos[e] = new ProductoNoPerecedero(Codigo,Precio,Nom_Producto, Cantidad, Ext);
                    
                }
                
                e++;
                
            }
            
    
        }
        
        datos.close();
        
        return true;
    
        
    }
    
    public boolean loadofert(String fichero) throws FileNotFoundException{
        
        fichero_ofert = fichero;
        
        String id=("idOferta:");
        String tipo=("tipo:");
        String txc=("tantoPorciento:");
        String max = "maximo:";
        String numero=("ofertas:");

        String Num_Ofertas = "";
        
        String id_oferta = "";
        String tipo_oferta = "";
        String txc_oferta = "";
        String max_oferta = "";
        
        int idd = 0;
        double txcc = 0.0;
        int maxx = 0;

        File Datos=new File(fichero);
        Scanner datos=new Scanner(Datos);

        int e = 0;

        while (datos.hasNext()){
    
            String Variable=datos.next();

            if(Variable.equals(numero)){
                Num_Ofertas=datos.next();
                ofertas = new Oferta[Integer.parseInt(Num_Ofertas)];
            }
            else if (Variable.equals(id)){
                idd = Integer.parseInt(datos.next());
            }                
            else if(Variable.equals(tipo)){
                tipo_oferta = datos.next();
                
                if(tipo_oferta.equals("porcentaje")){
                    
                    datos.next();
                    
                    txcc = Double.parseDouble(datos.next());
                    
                    datos.next();
                    
                    maxx = Integer.parseInt(datos.next());
                    
                    ofertas[e] = new OfertaPorcentaje(idd,txcc,maxx);
                    
                }else{
                    
                    ofertas[e] = new OfertaCantidad(idd,tipo_oferta);
                    
                }
                
                e++;
                
            }
            
    
        }
        
        datos.close();
        
        linkofprod();
        
        return true;
    
        
    }
    
    private void linkofprod(){
        
        int ofid;
        
        for(int i=0; i < productos.length; i++){
            
            ofid = productos[i].getofertaid();
            
            if(ofid != 0){
                
                for(int j=0; j < ofertas.length; j++){
                  //                          System.out.print(ofertas[j].getid());

                    if(ofertas[j].getid() == ofid){
                        
                        
                        productos[i].setoferta(ofertas[j]);
                        
                    }
                    
                }
                
            
                
            }
            
            
            
        }//System.exit(0);
        
    }

    public void save() throws FileNotFoundException,UnsupportedEncodingException{
        
        PrintWriter writer = new PrintWriter(fichero_prod, "UTF-8");
        
        writer.println("productos:");
        writer.println(productos.length);
        
        for(int i=0; i < productos.length; i++){
            
            writer.println("codigo:");
            writer.println(productos[i].getcode());
            writer.println("nombre:");
            writer.println(productos[i].getname());
            writer.println("precio:");
            writer.println(productos[i].getprice());
            writer.println("unidades:");
            writer.println(productos[i].getcount());
            writer.println("perecedero:");
            
            if(productos[i].gettype().equals("Perecedero")){
                
                writer.println("si");
                writer.println("dias:");
                writer.println(productos[i].getdias());
                
            }else{
                
                writer.println("no");
                writer.println("oferta:");
                writer.println(productos[i].getofertaid());
                
            }
            
        }     
            
            
        writer.close();
        
    }

    public Producto clone(Producto in, int cant){
        
        Producto out;
        
        if(in.gettype().equals("Perecedero")){
    
            out = new ProductoPerecedero(in.getcode(), in.getprice(), in.getname(), cant, in.getdias());
            
        }else{
            
            out = new ProductoNoPerecedero(in.getcode(), in.getprice(), in.getname(), cant, in.getofertaid());
            
            out.setoferta(in.getoferta());
            
        }
        
        return out;
        
    }
        
}

