//Fila 2, Silla 7 
package lab7p1_miaelvir;

import java.util.Scanner; 
import java.util.Random; 

public class Lab7P1_MiaElvir {

    static Scanner papoy = new Scanner(System.in); 
    static Random ran = new Random(); 
    
    public static void main(String[] args) {
        
        System.out.println("---- MENU ----");
        System.out.println("1. Tres en Raya");
        System.out.println("2. Puntos de silla");
        System.out.println("3. Salir del Menu");
        System.out.println("Ingrese Opcion: ");
        int opcion = papoy.nextInt(); 
        
        while (opcion > 0 && opcion < 3){
            switch (opcion){
                case 1: 
                    System.out.println("--- Tres en Raya ---");
                    System.out.println(" ");
                    System.out.println("¡Bienvenido al Tres en Raya!");
                    System.out.println(" ");
                    char respuesta = 's'; 
                    int turno = 0, fila = 0, columna = 0; 
                    char [][] tablero = new char [3][3]; 
                   
                    boolean continua = false;
                    
                    while (respuesta == 's' || respuesta == 'S'){
                        tablero = generarTablero(tablero);
                        turno = 0;
                        while (continua == false){
                            System.out.println("Tablero Actual");
                            imprimir_matriz(tablero);
                            System.out.println(" ");
                            //0 = X y 1 = O
                            if (turno == 0){
                                char caracter = 'X'; 
                                System.out.println("Es el turno de X");
                                System.out.println("Ingrese la fila (0,1,2): ");
                                fila = papoy.nextInt(); 
                                System.out.println("Ingrese la columna (0,1,2): ");
                                columna = papoy.nextInt(); 


                                if(VerificarPosicionValida(tablero, fila, columna) == true){
                                    System.out.println("El usuario ha elegido la posicion ("+fila+","+columna+")");
                                    tablero = jugada(tablero, fila, columna, turno); 
                                    continua = VerificarVictoria(tablero, caracter); 
                                }else{
                                    System.out.println("La posicion no es valida, vuelva a ingresarlo: ");
                                    System.out.println("Ingrese la fila (0,1,2): ");
                                    fila = papoy.nextInt(); 
                                    System.out.println("Ingrese la columna (0,1,2): ");
                                    columna = papoy.nextInt(); 

                                }

                                turno = 1; 
                                
                            }else if (turno == 1){
                                
                                char caracter = 'O'; 
                                System.out.println("Es el turno de O");
                                boolean pp = false; 
                                
                                while (pp == false){
                                    fila = ran.nextInt(0,3); 
                                    columna = ran.nextInt(0,3); 
                                    pp = VerificarPosicionValida(tablero, fila, columna); 
                                }

                                if(VerificarPosicionValida(tablero, fila, columna) == true){
                                    System.out.println("La maquina ha elegido la posicion ("+fila+","+columna+")");
                                    tablero = jugada(tablero, fila, columna, turno); 
                                    continua=VerificarVictoria(tablero, caracter); 
                                    
                                }

                                turno = 0; 
                            }
                            

                        }//while continuar
                        imprimir_matriz(tablero); 
                        if (turno == 1){
                            System.out.println("El jugador X gano");
                        }else if (turno == 0){
                            System.out.println("La maquina gano");
                        }else{
                            System.out.println("Es un empate");
                        }
                        
                        continua = false; 
                        System.out.println(" ");
                        System.out.println("Desea seguir jugando (s/n): ");
                        respuesta = papoy.next().charAt(0); 
                    }
                    
                    break; 
                    
                case 2: 
                    System.out.println("-- Puntos de Sillas --");
                    System.out.println(" ");
                    System.out.println("Ingrese el numero de filas de su matriz: ");
                    int filas = papoy.nextInt(); 
                    System.out.println("Ingrese el numero de columnas de su matriz: ");
                    int columnas = papoy.nextInt(); 
                    while(filas <= 0 || columnas <= 0){
                        System.out.println("Los valores no pueden ser menores o iguales a 0: ");
                        System.out.println("Ingrese el numero de filas de su matriz: ");
                        filas = papoy.nextInt(); 
                        System.out.println("Ingrese el numero de columnas de su matriz: ");
                        columnas = papoy.nextInt(); 
                    }
                    
                    System.out.println("Matriz Generada");
                    int [][] matriz = new int [filas][columnas]; 
                    matriz = generarIntMatriz(filas, columnas); 
                    imprimir_matrizInt(matriz); 
                    encontrarpuntossilla(matriz); 
                    
                    
                    break; 
            
            }//fin switch
            
            System.out.println(" ");
            System.out.println("---- MENU ----");
            System.out.println("1. Tres en Raya");
            System.out.println("2. Puntos de silla");
            System.out.println("3. Salir del Menu");
            System.out.println("Ingrese Opcion: ");
            opcion = papoy.nextInt(); 
        
        }//fin while principal
          
    }//fin main
    
    public static void imprimir_matriz(char x[][]){
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length; j++) {
                if (j == 0){
                    System.out.print("["+x[i][j]+",");
                }else if(j == x.length-1){
                    System.out.print(x[i][j]+"]");
                }else{
                    System.out.print(x[i][j]+",");
                }
            }
            System.out.println(" ");
        }
    }
    
    public static void imprimir_matrizInt(int x[][]){
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                System.out.print("["+x[i][j]+"] ");
            }
            System.out.println(" ");
        }
    }
    
    public static char [][] generarTablero(char x[][]){
        char espacio = ' '; 
        char [][] temporal = new char [3][3]; 
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length; j++) {
                temporal [i][j] = ' '; 
            }
        }
        return temporal; 
    }
    
    public static int [][] generarIntMatriz(int x, int y){
        int [][] matriz = new int [x][y];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j]=ran.nextInt(0,99); 
            }
        }
        return matriz; 
    }
    
    public static char [][] jugada(char x[][], int y, int z, int a){
        if (a == 0){
            x[y][z]='X'; 
        }else if(a == 1){
            x[y][z]='O'; 
        }
        return x; 
    }
    
    public static boolean VerificarPosicionValida(char x[][], int y, int z){
        boolean prueba = false; 
        if (y < 0 || y > 3 || z < 0 || z > 3 ){
            prueba = false; 
        }
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                if (x[y][z] != ' '){
                    prueba = false; 
                }else{
                    prueba = true; 
                }
            }
        }
        return prueba; 
    }
    
    public static boolean VerificarVictoria(char x[][], char y){
        boolean victoria = false; 
        if (x[0][0] == y && x[0][1] == y && x[0][2] == y){
            victoria = true; 
        }else if(x[1][0] == y && x[1][1] == y && x[1][2] == y){
            victoria = true; 
        }else if(x[2][0] == y && x[2][1] == y && x[2][2] == y){
            victoria = true; 
        }else if(x[0][0] == y && x[1][0] == y && x[2][0] == y){
            victoria = true; 
        }else if(x[0][1] == y && x[1][1] == y && x[2][1] == y){
            victoria = true; 
        }else if(x[0][2] == y && x[1][2] == y && x[2][2] == y){
            victoria = true; 
        }else if(x[0][0] == y && x[1][1] == y && x[2][2] == y){
            victoria = true; 
        }else if(x[0][2] == y && x[1][1] == y && x[2][0] == y){
            victoria = true; 
        }
        return victoria;
        
        /*int cont = 0; 
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length; j++) {
                if (x[i][j]==y){
                    cont++; 
                }
            }
        }
        if (cont == 3){
            victoria = true; 
            return victoria;  
        }
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length; j++) {
                if (x[j][i]==y){
                    cont++; 
                }
            }
        }
        if (cont == 3){
            victoria = true; 
            return victoria;  
        }
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length; j++) {
                if (i == j && x[i][j] == y){
                    
                }
            }
        }*/
        
    }
    
    public static int filas_menor(int x[][]){
        int menor = 100; 
        for (int i = 0; i < x[0].length; i++) {
            if (x[i][x.length] < menor){
                menor = x[x.length][i]; 
            }
        }
        return menor; 
    }
    
    public static int columnas_mayor(int x[][]){
        int mayor = -100; 
        for (int i = 0; i < x.length; i++) {
            if (x[i][x[i].length] > mayor){
                mayor = x[i][x[i].length]; 
            }
        }
        return mayor; 
    }
    
    public static void encontrarpuntossilla(int x [][]){
        
        /*if (columnas_mayor(x) == filas_menor(x)){
            System.out.println("El punto de silla es "+columnas_mayor(x));
        
        }*/
        int cont = 0; 
        boolean mayor = false; 
        boolean menor = false; 
        int z = 0, c = 0; 
        int temporal = 0; 
        
        for (int i = 0; i < x.length; i++) {
            temporal = x[i][cont];
             
            for (int j = 0; j < x[i].length; j++) {
                 
                if (temporal > x[i][j]){
                    c = j; 
                    temporal=x[i][j];
                }
            }
            for (int j = 0; j < x.length; j++) {
                if (temporal < x[j][c]){
                    temporal = x[j][c]; 
                    z=j; 
                }
            }
                 
        }
          System.out.println("El punto de silla es: "+temporal+" en las coordenadas ["+z+"]["+c+"]");  
            
            
        
    }
    
    
    
    
    
   
}// fin clase
