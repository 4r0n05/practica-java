package hito2;

class Empleado implements Productividad{

    int code;
    
    int level;

    String pass = new String();

    String name = new String();

    public Empleado(int code_i, String pass_i, String name_i, int level_i) {

        code = code_i;
        name = name_i;
        pass = pass_i;
        level = level_i;

    }
    
    public double getretencion(){
        
        return 0;
        
    }
    
    public double getplus(){
        
        return 0;
        
    }
    
    public String getturno(){
        
        return "";
        
    }
    
    public int getlevel(){
        
        return level;
        
    }
    
    public void gratificacion(double p_venta){
        
    }
    
    public int getcode(){
        
        return code;
        
    }
    
    public String getpass(){
        
        return pass;
        
    }
    
    public String getname(){
        
        return name;
        
    }
    
    public void setpass(String n_pass){
        
        pass = n_pass;
        
    }
    
    
    public double getgratificacion(){
        
        return 0;
        
    }

}

class EmpleadoDiurno extends Empleado{
    
    double gratificacion;
    
    double Retencion;
    
    public EmpleadoDiurno(int code_i, String pass_i, String name_i, int level_i,  double Ret_i) {

        super(code_i,pass_i,name_i, level_i);
        
        this.Retencion = Ret_i;

    }
    
    public void gratificacion(double p_venta){
        
    double total = 0, fijo = venta[this.getlevel()-1];
        
    switch(this.getlevel()){
                
            case 1:
                total = fijo;
                break;
                
            case 2: 
                if(p_venta > l2ret){
                    total = fijo - (fijo*this.getretencion());
                }
                break;
                   
            case 3:
                total = fijo - (fijo*this.getretencion());
                break;

        }
        
        gratificacion = gratificacion + total;
        
    }
    
    public double getretencion(){
        
        return Retencion;
        
    }
    
    public String getturno(){
        
        return "diurno";
        
    }
    
    
    public double getgratificacion(){
        
        return gratificacion;
        
    }
    
    
}

class EmpleadoNocturno extends Empleado{
    
    double Plus;
    
    double gratificacion;
    
    public EmpleadoNocturno(int code_i, String pass_i, String name_i, int level_i, double Plus_i) {

        super(code_i,pass_i,name_i, level_i);
        
        this.Plus = Plus_i;

    }
    
    public void gratificacion(double p_venta){
        
        double fijo = venta[this.getlevel()-1];
        
        double total = fijo + p_venta*(this.getplus()/100);
        
        gratificacion = gratificacion + total;
        
    }
    
    public double getplus(){
        
        return Plus;
        
    }
    
    public String getturno(){
        
        return "nocturno";
        
    }
    
    public double getgratificacion(){
        
        return gratificacion;
        
    }
    
}
