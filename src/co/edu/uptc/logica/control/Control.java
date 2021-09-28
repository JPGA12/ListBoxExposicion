package co.edu.uptc.logica.control;

import co.edu.uptc.logica.modelo.Persona;

import java.util.ArrayList;

public class Control {

    private ArrayList<Persona> listadoPersonas;

    public Control(){
        listadoPersonas = new ArrayList<Persona>();
    }

    public ArrayList<Persona> getListadoPersonas() {
        return listadoPersonas;
    }

    public void setListadoPersonas(ArrayList<Persona> listadoPersonas) {
        this.listadoPersonas = listadoPersonas;
    }

    public void agregarPersona(String nombre, String edad){
        Persona persona = new Persona();
        persona.setNombre(nombre);
        persona.setEdad(edad);

        listadoPersonas.add(persona);
    }
    public String mostrarPersonas(){
        String mensaje = null;
        for (int i = 0; i < listadoPersonas.size(); i++) {

            mensaje = "-----------------------------"+"\n"+
           "NOMBRE: "+listadoPersonas.get(i).getNombre()+"\n"
            +"Edad: "+listadoPersonas.get(i).getEdad()+"\n"
            +"-----------------------------";

        }
        return mensaje;
    }
    public void borrarDatos(){
        listadoPersonas.clear();
        System.err.println("\033[32m---------------------------------------------------");
        System.out.println("\033[32m Personas en el ArrayList: "+listadoPersonas.size());
        System.out.println("\033[32m Se borraron todos los datos");
        System.out.println("\033[32m---------------------------------------------------");

    }

    public void borrarPersona(int indice){
        System.err.println("---------------------------------------------------");
        System.err.println("Personas en el ArrayList: "+listadoPersonas.size());
        System.err.println( "-----------------------------"+"\n"+
                "SE BORRO A LA PERSONA: \n"+
                "NOMBRE: "+listadoPersonas.get(indice).getNombre()+"\n"
                +"Edad: "+listadoPersonas.get(indice).getEdad()+"\n"
                +"-----------------------------");
        listadoPersonas.remove(indice);
        System.err.println("-----------------------------");
        System.err.println("Se borro un elemento en la posicion: "+(indice+1));
        System.err.println("-----------------------------");
        System.err.println("");
        System.err.println("Personas en el ArrayList: "+listadoPersonas.size());
        System.err.println("---------------------------------------------------");
    }


}
