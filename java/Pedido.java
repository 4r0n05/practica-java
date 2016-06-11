package hito2;

class Pedido implements OfertaPerecederos{
    
    Producto productos[];
    
    int pos = 0;
    
    public Pedido(int n_prod){
        
        productos = new Producto[n_prod];
        
    }
    
    public int add(Producto product){
        
        boolean ok = true;
        
        int ret = 0;
        
        if(!(productos.length > pos)){
            
            ret = 1;
            
        }
        
        for(int i = 0; i < pos; i++){

            if(product.getcode() == productos[i].getcode()){

                ret = 2;

            }

        }
        
        if(ret == 0){
        
            productos[pos] = product;
            
            pos ++;
            
            
        }
        
        if(!(productos.length > pos) && ret != 1 && ret != 2){
            
            ret = 3;
            
        }
        
        return ret;
            
            
        
    }
    
    public double subtotal(){
        
        double t = 0;
        
        for(int i = 0; i < pos; i++){
  

            t = t + productos[i].getprice()*productos[i].getcount();

        }
        
        return t;
        
    }
        
    public double total(){
        
        double t = 0;
        
        double prec;
        
        int dias;
        
        int ofid;
        
        Oferta oferta;
        
        for(int i = 0; i < pos; i++){
            
            if(productos[i].gettype().equals("Perecedero")){
                
                dias = productos[i].getdias();
                
                if(dias > 0 && dias < 4){
                
                    prec = per_part[dias-1] * (productos[i].getprice() * productos[i].getcount());
                    
                }else{
                    
                    prec = productos[i].getprice() * productos[i].getcount();
                    
                //    System.out.print(prec);
                    
              //      System.exit(0);
                    
                }
                
            }else{
                
                oferta = productos[i].getoferta();
                
                ofid = productos[i].getofertaid();
                
                double resp;
                
                if(ofid != 0){
                    
                    
                   // resp = oferta.proc(2,5.0);
                    
                    resp = oferta.proc(productos[i].getcount(), productos[i].getprice() * productos[i].getcount());
                    
                  //  System.out.print(resp+" "+oferta.getmax());
                    
                    prec = resp;
                    
                }else{
                    
                    prec = productos[i].getprice() * productos[i].getcount();
                    
                }
                
            }
            
            t = t + prec;
            
        }
                    
        return t;

    }
        
        
    
    
    public Producto[] getproductos(){
        
        return productos;
        
    }
    
    public int getpos(){
        
        return pos;
        
    }
    
}
