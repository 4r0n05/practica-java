package hito2;

class Producto{

    int code;

    String name = new String();

    double price;
    
    int count;
    
    Oferta oferta;

    public Producto(int code_i, double price_i, String name_i, int count_i){

        code = code_i;
        name = name_i;
        price = price_i;
        count = count_i;

    }
    
    public int getcount(){
        
        return count;
        
    }
    
    public int getcode(){
        
        return code;
        
    }
    
    public String getname(){
        
        return name;
        
    }
    
    public double getprice(){
        
        return price;
        
    }
    
    public String gettype(){
        
       return "";
        
    }
    
    public int getofertaid(){
        
        return 0;        
        
    }
    
    public int getdias(){
        
        return -1;
        
    }
    
    public Oferta getoferta(){
        
        return oferta;
        
    }
    
    public void setoferta(Oferta oferta_i){
        
        oferta = oferta_i;
        
    }        
    
    public void setcount(int n_count){
        
        count = n_count;
        
    }
    
    public void pop(int kant) throws NoStockException{
        
        if(kant > count){
            
            throw new NoStockException();
            
        }else{
            
            count = count - kant;
            
        }
        
    }
    
    public void setcode(int n_code){
        
        code = n_code;
        
    }
    
    public void setname(String n_name){
        
        name = n_name;
        
    }
    
    public void setprice(double n_price){
        
        price = n_price;
        
    }

}

class ProductoPerecedero extends Producto{
    
    int dias;
    
    public ProductoPerecedero(int code_i, double price_i, String name_i, int count_i, int dias_i){
    
        super(code_i,price_i,name_i, count_i);
        
        this.dias = dias_i;
        
    }
    
    public int getdias(){
        
        return dias;
        
    }
    
    
    public String gettype(){
        
        return "Perecedero";
        
    }
    
}

class ProductoNoPerecedero extends Producto{
    
    int ofertaid;
    
    public ProductoNoPerecedero(int code_i, double price_i, String name_i, int count_i, int oferta_i){
    
        super(code_i,price_i,name_i, count_i);
        
        this.ofertaid = oferta_i;
        
    }
    
    public String gettype(){
        
        return "No perecedero";
        
    }
    
    public int getofertaid(){
        
        return ofertaid;
        
    }
    
    
}
