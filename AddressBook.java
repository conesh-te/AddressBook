/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tecmilenio.agenda_telefonica;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 *
 * @author conesh
 */
public class AddressBook {

    static HashMap<String, String> agenda = new HashMap<>();
    public static void main(String[] args) {
        load();
        Scanner decision = new Scanner(System.in);
        while (true) { 
        System.out.println("\n1. Mostrar los contactos de la agenda");
        System.out.println("2. Crear un nuevo contacto");
        System.out.println("3. Modificar un contacto");
        System.out.println("4. Borrar un contacto");
        System.out.println("\nPor favor seleccione lo que desea hacer: ");
        int capturadecision=decision.nextInt();
        if (capturadecision == 1) {
            System.out.println("\nContactos:");
            for (Map.Entry<String, String> registro : agenda.entrySet()) {
                System.out.println(registro.getKey()+" : "+registro.getValue());
            }
        }
        else if (capturadecision == 2) {
            decision.nextLine();
            System.out.println("\nPor favor escribe el número telefonico: ");
            String captura_numero_telefonico=decision.nextLine();
            System.out.println("\nPor favor escribe el nombre completo");
            String captura_nombre=decision.nextLine();
            agenda.put(captura_numero_telefonico,captura_nombre);
            System.out.println("\nEl contacto fue creado correctamente.");
        }
        else if (capturadecision == 3) {
            decision.nextLine();
            System.out.println("\nPor favor escribe el número telefonico que deseas modificar: ");
            String modifica_numero_telefonico=decision.nextLine();
            if (agenda.containsKey(modifica_numero_telefonico)) {
            System.out.println("\nPor favor escribe el nombre completo");
            String modifica_nombre=decision.nextLine();
            agenda.put(modifica_numero_telefonico,modifica_nombre);    
            System.out.println("\nContacto modificado con éxito.");
            
    } else {
        System.out.println("\nEl número ingresado no existe en la agenda.");
    }
        }
        else if (capturadecision == 4) {
            decision.nextLine();
            System.out.println("\nPor favor escribe el número telefonico que deseas eliminar: ");
            String captura_numero_telefonico=decision.nextLine();
            agenda.remove(captura_numero_telefonico);
            System.out.println("\nEl contacto fue eliminado correctamente.");
        }
        else {
            System.out.println("\nLa opcion selecionada no es parte del menu \n");
            System.out.println("Guardando informacion, por favor espere...");
            save();
            System.out.println("\nPrograma cerrado correctamente.");
            break;
        }
      }
    }
    public static void save() {
    String outputFilename = "/home/conesh/Documents/Java/Agenda_telefonica/src/main/java/tecmilenio/agenda_telefonica/agenda.txt";
    FileOutputStream fileOutputStream = null;
    try {
        fileOutputStream = new FileOutputStream(outputFilename);
        for (Map.Entry<String, String> registro : agenda.entrySet()) {
            String linea = registro.getKey() + " , " + registro.getValue() + "\n";
            fileOutputStream.write(linea.getBytes());
            }
            System.out.println("\nCambios guardados en el archivo correctamente.");
    } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }
    public static void load() {
    String inputFilename = "/home/conesh/Documents/Java/Agenda_telefonica/src/main/java/tecmilenio/agenda_telefonica/agenda.txt";
    FileInputStream fileInputStream = null;
    Scanner lectorarchivo = null;
    try {
        fileInputStream = new FileInputStream(inputFilename);
        lectorarchivo = new Scanner(fileInputStream);
        while (lectorarchivo.hasNextLine()) {
            String linea = lectorarchivo.nextLine();
            String[] partes = linea.split(",");
            if (partes.length == 2) {
               String numero = partes[0];
               String nombre = partes[1];
               agenda.put(numero, nombre);
            }
        }
        } catch(IOException e) {
            System.out.println("IOException catched while reading: Iniciando la agenda vacia, no se ha generado el archivo todavia " + e.getMessage());
        } finally {
                if (lectorarchivo != null) {
                lectorarchivo.close();
                }
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }
  }

