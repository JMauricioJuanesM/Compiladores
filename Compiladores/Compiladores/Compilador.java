public class Compilador {
    Concatenador c = new Concatenador();
    String anterior = "";
    Boolean antes = false;

    //<PROGRAMA>::=BLOQUE
    public void Programa(){
        String X;
        if(antes){
            X = anterior;
            antes = false;
        }else{
            X = c.nextToken();
        }
        
        Bloque();
        if(antes){
            X = anterior;
            antes = false;
        }else{
            X = c.nextToken();
        }
        if( "." == X ){
            System.out.println("El programa compilo exitosamente");
        }
        else{
            Error(1);
        }
    }
    
    //<BLOQUE>::=<AUXCON<AUXVAR><AUXPRO><PROPOSICION>
    public void Bloque(){
        Auxcon();
        Auxvar();
        Auxpro();
        Proposicion();
    }
    //<PROPOSICION>:=<IDENT> = <EXPRE>
    public void Proposicion(){
        String X;
       if(antes){
           X = anterior;
           antes = false;
       }else{
           X = c.nextToken();
       }
        if(Ident(X)){
            c.nextToken();
            if(X == "="){
                anterior = X;
                Expre();
            }
            else{
                Error(9);
            }
        }
        //<PROPOSICION>:= RUN <IDENT>
      

        else if(X == "RUN"){
            anterior = X;
            if(Ident(X)){
               
            }
            else{

            }
            }
        //<PROPOSICION>:= RD <IDENT>
        else if(X == "RD"){
            anterior = X;
            if(Ident(X)){

            }
            }
        //<PROPOSICION>::= WR<AUX3>
        else if(X == "WR"){
            anterior = X;
            Aux3();
            }
        
        else if(X == "STR"){
            anterior = X;
            Proposicion();
            Aux4();
            
            if(antes){
                X = anterior;
                antes = false;
            }else{
                X = c.nextToken();
            }
            if(X == "END"){
                anterior = X;
            }
            else{

            }
        }

        // <PROPOSICION>: WHEN<CONDICION> THEN <PROPOSICION><AUX5>
        else if(X == "WHEN"){
            anterior = X;
            Condicion();
            if(antes){
                X = anterior;
                antes = false;
            }else{
                X = c.nextToken();
            }
            if(X == "THEN"){
                anterior = X;
                Proposicion();
                Aux5();
            }
        }
        // <PROPOSICION>::=WHL <CONDICION> RUN <PROPOSICION>
        else if(X == "WHL"){
            anterior = X;
            if(antes){
                X = anterior;
                antes = false;
            }else{
                X = c.nextToken();
            }
            Condicion();
            if(antes){
                X = anterior;
                antes = false;
            }else{
                X = c.nextToken();
            }
            if(X == "RUN"){
                anterior = X;
                Proposicion();
            }
            else{

            }
        }
        }
//<CONDICION>::=<EXPRE><SIMREL><EXPRE>
    public void Condicion(){
        Expre();
        Simrel();
        Expre();
    }
    public boolean Simrel(){
        String X;
        if(antes){
            X = anterior;
            antes = false;
        }else{
            X = c.nextToken();
        }
        boolean resultado = false;
        switch(X){
            case "=?":
                anterior = X;
                resultado = true;
            break;
            case "=/":
            anterior = X;
            resultado = true;
            break;
            case ">>":
            anterior = X;
            resultado = true;
            break;
            case "=>":
            anterior = X;
            resultado = true;
            break;
            case "<<":
            anterior = X;
            resultado = true;
            break;
            case "=<":
            anterior = X;
            resultado = true;
            break;
            default:
            Error(10);
        }
            return resultado;
        }
    /*
    <AUX5>::= ELSE <PROPOSICION> <AUX5>
    <AUX5>::=  ε
    */
    public void Aux5(){
        String X;
        if(antes){
            X = anterior;
            antes = false;
        }else{
            X = c.nextToken();
        }
        //Aquí va un if para validar follows
        //TODO: Checar porque Hay ELSE en en los follows y en el first
        if(!X.equals(";") && !X.equals(".") && !X.equals("ELSE") && !X.equals("END")){
            if(X == "ELSE"){
                anterior = X;
                Proposicion();
                Aux5();
            }
        }else{
            //TODO: Hacer else
            antes = true;
        }
    }
   /*
   <AUX4>::= ; <PROPOSICION> <AUX4>
    <AUX4>::= ε
   */
    public void Aux4(){
        String X;
        if(antes){
            X = anterior;
            antes = false;
        }else{
            X = c.nextToken();
        }
        if(!X.equals("END")){
            if(X == ";"){
                anterior = X;
                Proposicion();
                Aux4();
            }
            else{
                
            }
        }else{
            //TODO: Hacer 
            antes = true;
        }
    }
    /*
    <AUX3>::=<IDENT>
    <AUX3>::=<NUM>

    */
    public void Aux3(){
        String X;
        if(antes){
            X = anterior;
            antes = false;
        }else{
            X = c.nextToken();
        }

       if (Ident(X)){

       }
        else if (Num(X)){

        }
        else{

        }    
    }
    //<EXPRE>::=<FACTOR><AUX7>
    public void Expre(){
        Factor();
        Aux7();
    } 
    // <AUX7>::=<SIMART><FACTOR><AUX7>
    //TODO: ¿Dónde se pondría la validación de follows en esta función? 
    public void Aux7(){
        Simart();
        Factor();
        Aux7();
    }    
    public void Simart(){
        String X;
        if(antes){
            X = anterior;
            antes = false;
        }else{
            X = c.nextToken();
        }
        boolean resultado;
        switch(X){
            case "*":
                anterior = X;
                resultado = true;
            break;
            case "/":
            anterior = X;
                resultado = true;
            break;
            case "+":
            anterior = X;
                resultado = true;
            break;
            case "-":
            anterior = X;
                resultado = true;
            break;

        }
    }
    /*
    <FACTOR>::= <IDENT>
    <FACTOR>::= <NUM>
    <FACTOR>::= (<EXPRE>)

    */
    public void Factor(){
        String X;
        if(antes){
            X = anterior;
            antes = false;
        }else{
            X = c.nextToken();
        }
        Ident(X);
        Num(X);
        if(antes){
            X = anterior;
            antes = false;
        }else{
            X = c.nextToken();
        }
        if(X.equals("(")){
            anterior = X;
            Expre();
        if(X.equals(")")){
            anterior = X;
            
        }
        else{
            
        }
        }
        else{

        }
    }
    //<AUXVAR>:== VAR <TIPOS> <IDENT><AUX2>
    //<AUXVAR>::=  ε
    public void Auxvar(){
        String X;
        if(antes){
            X = anterior;
            antes = false;
        }else{
            X = c.nextToken();
        }
        if(!X.equals("PRO") && !X.equals("IDENT") && !X.equals("RUN") && !X.equals("RD") && !X.equals("WR")  && !X.equals("STR") && !X.equals("WHEN") && !X.equals("WHL")){
            if(X == "VAR"){
                anterior = X;
                if(Tipos(X)){
                    if(Ident(X)){
                        Aux2();
                    }
                    else{
    
                    }
                }else{
    
                }
            }
            else{
                Error(6);
            }
        }else{
            //TODO: Hacer 
            antes = true;
        }
    }
    
    /*
    <AUX2>:== , <IDENT> <AUX2>
    <AUX2>:== ;
    <AUX2>:== CON <IDENT> = <NUM> <AUX2>
    <AUX2>:==Epsilon
*/  
    //TO/DO: Checar follows de esta regla
    public void Aux2(){
       String X;
        if(antes){
            X = anterior;
            antes = false;
        }else{
            X = c.nextToken();
        }
        if(!X.equals("PRO") && !X.equals("IDENT") && !X.equals("RUN") && !X.equals("RD") && !X.equals("WR")  && !X.equals("STR") && !X.equals("WHEN") && !X.equals("WHL")){
            if(X == ","){
                anterior = X;
                Ident(X);
                Aux2();
            }
            else if(X == "CON"){
                anterior = X;
                    Ident(X);
                    if(antes){
                        X = anterior;
                        antes = false;
                    }else{
                        X = c.nextToken();
                    }
                    if(X == "="){
                        anterior = X;
                        Num(X);
                        Aux2();
                    }
            }else if(X == ";"){
                anterior = X;
            }
        }else{
            //TODO: Hacer 
            antes = true;
        }
    }
    
    //<AUXPRO>:== PRO <IDENT> => <BLOQUE>; <AUXPRO>
    //TODO: Checar porque Hay PRO en en los follows y en el first
    public void Auxpro(){
        String X;
        if(antes){
            X = anterior;
            antes = false;
        }else{
            X = c.nextToken();
        }
        if(!X.equals("PRO") && !X.equals("IDENT") && !X.equals("RUN") && !X.equals("RD")  && !X.equals("WR") && !X.equals("STR") && !X.equals("WHL") && !X.equals("WHEN")){ 
            if(X == "PRO"){
                anterior = X;
                if(Ident(X)){
                    
                }else{
                    Error(8);
                }
                

            }else{
                Error(7);
            }
        }
    }
    public boolean Tipos( String X ){
        boolean validacion = false;
        String[] tipos = new String[]{"ENT","PUN","STR"};
        for(int i=0;i<tipos.length;i++){
            if(X == tipos[i]){
                anterior = X;
                validacion = true;
            }

        }
        return validacion;
    }
    /*<AUXCON>::=CON <IDENT> = <NUM><AUX1>
    FOLLOWS(AUXCON) = {FIRST(AUXVAR)} = {“VAR” + FIRST(AUXPRO)}} = 
    {“VAR + “PRO” + FIRST(PROPOSICION) = 
    { “VAR” + “PRO” +  “IDENT” + “RUN” + “RD” + “WR” + “STR” + “WHEN” + “WHL”}*/

    public void Auxcon(){
        String X;
        if(antes){
            X = anterior;
            antes = false;
        }else{
            X = c.nextToken();
        }
        String identificador;
        String valor;
        if(!X.equals("VAR") && !X.equals("PRO") && !X.equals("IDENT") && !X.equals("RUN") && !X.equals("RD")  && !X.equals("WR") && !X.equals("STR") && !X.equals("WHL") && !X.equals("WHEN")){
            if(X == "CON"){
                anterior = X;
                if(Ident(X)){
                    identificador = X;
                    if(X == "="){
                        anterior = X;
                        if(Num(X)){
                            Aux1();
                        /* try{
                            valor = Integer.parseInt(X);
                            }catch(NumberFormatException e){
                                Error(5);
                            }*/

                        }
                    }
                    else{
                        Error(4);
                    }
                }
                else{
                    Error(3);
                }
            }
            else{
                Error(2);
            }
        }else{
            //TODO: Hacer else
            antes = true;
        }
    }
    /* <AUX1>:== ;
    <AUX1>:==, CON <IDENT> = <NUM> <AUX1>
    */
    public void Aux1(){
        String X;
        if(antes){
            X = anterior;
            antes = false;
        }else{
            X = c.nextToken();
        }
        if(X == "CON"){
            anterior = X;
            Ident(X);
            if(X == "="){
                Num(X);
                Aux1();
            }
        else if(X == ";"){

        }
        else{

        }
        }
    }
//<NUM>
public boolean Num( String X ){
    boolean es_Valido = true;
    char caracter;
    caracter = X.charAt(0);
    for(int i = 1; i<X.length(); i++){
    if( caracter>=48 && caracter<=57 ){
        caracter = X.charAt(i);   
    }
    else{
        es_Valido = false;
        break;
    }
}
    return es_Valido;
}
//<IDENT>
public boolean Ident( String X ){
        if(antes){
            X = anterior;
            antes = false;
        }else{
            X = c.nextToken();
        }
    boolean es_valido = true;
    String[] palabras_invalidas=new String[]{"VAR","PRO","ENT","PUN","STR",
    "CHR","RUN","RD","WR",">STR","END<","WHEN","THEN","WHL","RUN","=","=?","=/",
    "<<","=<",">>","=>","+","-","*","/",".",",",";","(",")","<NUM>","<IDENT>"};
    
    for(int i = 0; i < palabras_invalidas.length; i++){
        if(X == palabras_invalidas[i]){
            es_valido = false;
        }
    }
   return es_valido;
}








    
    public void Error(int i){
        switch(i){
            case 1:
                System.err.println("Error en PROGRAMA, se esperaba un punto.");
            case 2: 
                System.err.println("Error en AUXCON, se esperaba la palabra const");
            case 3:
                System.err.println("Error en AUXCON, se esperaba un identificador");
            case 4:
                System.err.println("Error en AUXCON, se esperaba un igual");
            case 5:
                System.err.println("Error en AUXCON, error en la asignacion del numero");
            case 6:
                System.err.println("Error en AUXVAR, se esperaba la palabra VAR");
            case 7:
                System.err.println("Error en AUXPRO, se esperaba la palabra PRO");
            case 8:
                System.err.println("Error en AUXPRO, se esperaba  un identificador");
            case 9:
                System.err.println("Error en PROPOSICION, identificador requerido");
        }

    }

    public static void main(String[]as){
        
    }
}
