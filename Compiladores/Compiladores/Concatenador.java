import java.io.IOException;

public class Concatenador {
    charByChar cbc=new charByChar();
    char caracter;
    String[] palabras_validas=new String[]{"CON","VAR","PRO","ENT","PUN","STR",
    "CHR","RUN","RD","WR",">STR","END<","WHEN","THEN","WHL","RUN","=","=?","=/",
    "<<","=<",">>","=>","+","-","*","/",".",",",";","(",")","<NUM>","<IDENT>"};
    char[] caracteres;
    char aux;
    int contador=0;
    int entro=0;
    StringBuilder[] tokens;
public String nextToken(){
    StringBuilder sb= new StringBuilder();

    try{
        //-------BLOQUE DE CODIGO PARA LAS CADENAS QUE SE SEPARAN POR ESPACIO Y NUMEROS----------//

        caracteres=charByChar.ReadFileToCharArray();
        
        if(entro>=1){
            if(caracteres[contador-1]==32){
            }else{
                contador++;//Contador para las multiples cadenas que se analizan
            }
        }
        else{
            
        }
        caracter=caracteres[contador];
    entro++;
    

    if((caracter>=65 && caracter<=90) || (caracter>=97 && caracter<=122)){
        sb.append(caracter);
        contador++;
        caracter=caracteres[contador];
    while(((caracter>=65 && caracter<=90) || (caracter>=97 && caracter<=122) || (caracter>=49 && caracter<=57)) && caracteres.length>contador && caracter!=32){
        caracter=caracteres[contador];                  
        sb.append(caracter);            
        contador++;
    }
    return sb.toString();
    }
    else if((caracter>=49 && caracter<=57) ){
        do{
            caracter=caracteres[contador];
            contador++;
            
            sb.append(caracter);
            
        }while(((caracter>=48 && caracter<=57)|| caracter=='.') && contador<caracteres.length);
        return sb.toString();
    }
    //-----------------------------PARES DE CARACTERES A DEVOLVER---------------------------------//
    else if(caracter=='='){
        aux=caracter;
        contador++;
        caracter=caracteres[contador];
        sb.append(aux);
        if(caracter=='?'||caracter=='/'||caracter=='<'||caracter=='>'){
            
            sb.append(caracter);
            
        }
        return sb.toString();
    }
    else if(caracter=='<'){
        aux=caracter;
        contador++;
        caracter=caracteres[contador];
        if(caracter=='<'){
            sb.append(aux);
            sb.append(caracter);
            return sb.toString();
        }
        
    }
    else if(caracter=='>'){
        aux=caracter;
        contador++;
        caracter=caracteres[contador];
        if(caracter=='>'){
            sb.append(aux);
            sb.append(caracter);
            return sb.toString();
        }
    }
}
    catch(IOException e){
        e.printStackTrace();
    }
    return sb.toString();
}
public boolean validar(String palabra){
    boolean esvalida=false;
    for(int i=0;i<palabras_validas.length;i++){
    if(palabras_validas[i].equals(palabra)){
        esvalida=true;
    }
}
    return esvalida;
}

public void hacer_tokens(String cadena){
    int c=0;
    tokens = new StringBuilder[1000];
    for(int i = 0; i<cadena.length(); i++){
        if(cadena.charAt(i) != 32){
            tokens[c].append(cadena.charAt(i));
        }
    }
}
public void siguiente_token(){
    contador++;
}
public static void main(String []as){
    Concatenador c = new Concatenador();
    String prueba = c.nextToken();
    String prueba1 = c.nextToken();
    String prueba2 = c.nextToken();
    System.out.println(prueba);
    System.out.println(prueba1);
    System.out.println(prueba2);
}
}
