package hito2;

class Oferta{
    
    int id;
    
    public Oferta(int id_i){
        
        id = id_i;
        
    }
    
    public int getid(){
        
        return id;
        
    }
    
    public double proc(int in, double prec){
        
        double out = 0;
        
        return out;
        
    }
    
    public int getmax(){
        
        return 0;
        
    }
        
    
}

class OfertaCantidad extends Oferta{
    
    int c_in, c_out;
    
    public OfertaCantidad(int id_i, String x){
        
        super(id_i);
                
        String[] parts = x.split("x");
        
        this.c_in = Integer.parseInt(parts[0]);
        this.c_out = Integer.parseInt(parts[1]);
    
    }
    
    public double proc(int in, double prec){
        
        double[] out = new double[2];
        
        out[0] = ((in / c_in * c_out) + (in % c_in));
        
        out[1] = (prec / in * out[0]);
        
        return out[1];
        
    }
    
}

class OfertaPorcentaje extends Oferta{
    
    double perc;
    
    int max;
    
    public OfertaPorcentaje(int id_i, double x, int max_i){
        
        super(id_i);
        
        this.perc = x;
        
        this.max = max_i;

    }
    
    public int getmax(){
        
        return max;
        
    }
    
    public double proc(int in, double prec){
        
        double[] out = new double[2];
        
        out[0] = in;
        
        if(in > max){
            
            double prec_ap = prec / in * max;
            
            double prec_nap = prec / in * (in-max);
            
            out[1] = (prec_ap / 100 * (100-perc)) + prec_nap;
            
        }else{
        
            out[1] = (prec / 100 * (100-perc));
            
        }
        
        return out[1];
        
        
    }
    
}

interface OfertaPerecederos{
    
    double[] per_part = {0.25,0.33,0.5}; 

}
