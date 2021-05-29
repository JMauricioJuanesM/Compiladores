import java.io.FileReader;
import java.io.IOException;

public class LeerCadena {
    public String Cadena(){
        StringBuilder cadenita=new StringBuilder();
        try{
        FileReader fr=new FileReader("/Users/ivanpalacios/Documents/Proyectos java/Compiladores/archivo.txt");
        int caracter=fr.read();
        if(caracter>=65 && caracter<=122){
            cadenita.append((char)caracter);
            caracter=fr.read();
        while(caracter>=65 && caracter<=122){
            cadenita.append((char)caracter);
            caracter=fr.read();
        }
    }
    else{
        if(caracter>=48 && caracter<=57){
            cadenita.append((char)caracter);
            caracter=fr.read();
            while(caracter>=48 && caracter<=57){
                cadenita.append((char)caracter);
                caracter=fr.read(); 
            }
        }
    }
        }
    catch(IOException e){
    e.printStackTrace();
}
    return cadenita.toString();
}
}
