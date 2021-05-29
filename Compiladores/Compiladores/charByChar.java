import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class charByChar {

    public static int lecturaIndex = 0;

    public static void main(String[] args) throws IOException {
        char x;
         ReadFileToCharArray();

    }

    public static char[] ReadFileToCharArray() throws IOException {
        String nombreFichero = "archivo.txt";
        char[] salida = new char[1024];
        char o = 00;

            StringBuilder fileData = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(nombreFichero));
    
            char[] buf = new char[1000];
            int numRead = 0;
            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
                buf = new char[1024];
            }
            reader.close();
            salida = fileData.toString().toCharArray();

        if(lecturaIndex<salida.length){
            o = salida[lecturaIndex];
        }else{
            o = 0;
        }
        lecturaIndex++;
		return salida;	
	}
}