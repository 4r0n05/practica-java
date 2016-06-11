package hito2;

class LoginErrorException extends Exception{
    
    public String toString(){
        
        return "Excepción #111: Error, login incorrecto";
        
    }
    
}

class PasswordErrorException extends Exception{
    
    public String toString(){
        
        return "Excepción #222: Error, contraseña incorrecta";
        
    }
    
}

class NoStockException extends Exception{
    
    public String toString(){
        
        return "Excepción #333: Error, número insuficiente de productos disponibles";
        
    }
    
    
}
