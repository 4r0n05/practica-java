package hito2;

class Formateo{
    
    // Formateo ANSI
    
    String NG = "\u001B[30m";
    String RJ = "\u001B[31m";
    String VD = "\u001B[32m";
    String AM = "\u001B[33m";
    String AZ = "\u001B[34m";
    String MG = "\u001B[35m";
    String CI = "\u001B[36m";
    
    String NG_B = "\u001B[40m";
    String RJ_B = "\u001B[41m";
    String VD_B = "\u001B[42m";
    String AM_B = "\u001B[43m";
    String AZ_B = "\u001B[44m";
    String MG_B = "\u001B[45m";
    String CI_B = "\u001B[46m";
    
    String RST = "\u001B[0m";
    String CLR = "\033[2J\033[1;1H";
    
    public Formateo(){
        
    }
    
    public void clr(){
        
        System.out.print(CLR);
        
    }
    
    public void color(String in, int fg, int bg){
        
        System.out.print(this.ret(in,fg,bg,in.length()));
        
    }
    
    public String ret(String in, int fg, int bg, int pad){
        
        String COL1 = "";
        String COL2 = "";
        
        switch(fg){
            case 0: COL1 = NG; break;
            case 1: COL1 = RJ; break;
            case 2: COL1 = VD; break;
            case 3: COL1 = AM; break;
            case 4: COL1 = AZ; break;
            case 5: COL1 = MG; break;
            case 6: COL1 = CI; break;
        }
        
        switch(bg){
            case 0: COL2 = NG_B; break;
            case 1: COL2 = RJ_B; break;
            case 2: COL2 = VD_B; break;
            case 3: COL2 = AM_B; break;
            case 4: COL2 = AZ_B; break;
            case 5: COL2 = MG_B; break;
            case 6: COL2 = CI_B; break;
        }
        
        return COL1+COL2+String.format("%-"+pad+"s",in)+RST;
        
    }
    
}
