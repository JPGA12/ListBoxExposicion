package co.edu.uptc.presentacion;

import co.edu.uptc.logica.control.Control;
import co.edu.uptc.persistencia.utilidades.TextPrompt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListBox extends JFrame implements ActionListener {

    private Container contenedor;/*declaramos el contenedor*/
    private JButton agregar, eliminar, borrar;/*declaramos el objeto Boton*/
    private JLabel labelTitulo, mensaje, nombre, edad;/*declaramos el objeto Label*/
    private JTextField campoNombre, campoEdad;
    private JList listaNombres;/*declaramos La Lista*/
    private DefaultListModel modelo;/*declaramos el Modelo*/
    private JScrollPane scrollLista;




    Control control = new Control();

    public ListBox() {
        /*permite iniciar las propiedades de los componentes*/
        iniciarComponentes();
        /*Asigna un titulo a la barra de titulo*/
        setTitle("JList-ListBox");
        /*tamaño de la ventana*/
        setSize(280, 480);
        /*pone la ventana en el Centro de la pantalla*/
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void iniciarComponentes() {
        contenedor = getContentPane();/*instanciamos el contenedor*/
        /*con esto definmos nosotros mismos los tamaños y posicion
         * de los componentes*/
        contenedor.setLayout(null);

        campoNombre = new JTextField();
        campoEdad = new JTextField();

       /* Inicializamos y agregamos un texto de guia a las cajas de texto*/
        campoNombre.setBounds(20, 80, 135, 23);
        nombre = new TextPrompt("Nombre",campoNombre);
        campoEdad.setBounds(20, 110, 135, 23);
        edad = new TextPrompt("Edad",campoEdad);


        /*Propiedades del boton, lo instanciamos, posicionamos y
         * activamos los eventos*/
        agregar = new JButton();
        agregar.setText("Agregar");
        agregar.setBounds(160, 95, 80, 23);
        agregar.addActionListener(this);

        eliminar = new JButton();
        eliminar.setText("Eliminar");
        eliminar.setBounds(10, 400, 100, 23);
        eliminar.addActionListener(this);

        borrar = new JButton();
        borrar.setText("Borrar Lista");
        borrar.setBounds(130, 400, 120, 23);
        borrar.addActionListener(this);

        /*Propiedades del Label, lo instanciamos, posicionamos y
         * activamos los eventos*/
        labelTitulo = new JLabel();
        labelTitulo.setFont(new java.awt.Font("Tahoma", 0, 25));
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("ListBox");
        labelTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        labelTitulo.setBounds(40, 20, 180, 43);

        mensaje = new JLabel();
        mensaje.setBounds(20, 370, 280, 23);

       /* instanciamos la lista*/
        listaNombres = new JList();
        listaNombres.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      /*  instanciamos el modelo*/
        modelo = new DefaultListModel();

       /* instanciamos el Scroll que tendra la lista*/
        scrollLista = new JScrollPane();
        scrollLista.setBounds(20, 140, 220, 220);
        scrollLista.setViewportView(listaNombres);

        /*Agregamos los componentes al Contenedor*/
        contenedor.add(labelTitulo);
        contenedor.add(campoNombre);
        contenedor.add(campoEdad);
        contenedor.add(agregar);
        contenedor.add(eliminar);
        contenedor.add(borrar);
        contenedor.add(mensaje);
        contenedor.add(scrollLista);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == agregar) {
            agregarNombreEdad();

        }
        if (evento.getSource() == eliminar) {
            eliminarPersona(listaNombres.getSelectedIndex());
        }
        if (evento.getSource() == borrar) {
            borrarLista();
            mensaje.setText("Se borró toda la lista");
        }
    }

   /* Agrega los datos de persona a la lista*/
    private void agregarNombreEdad() {
        String nombre = "";
        String edad ="";
        nombre = campoNombre.getText();
        edad = campoEdad.getText();

        if (nombre.isEmpty() || edad.isEmpty()){
            mensaje.setText("Por favor llene los campos");
        }else {

            String nombreEdad = nombre+" - "+edad;
            modelo.addElement(nombreEdad);
            listaNombres.setModel(modelo);
            campoNombre.setText("");
            campoEdad.setText("");
            mensaje.setText("Se agregó un nuevo elemento");
            control.agregarPersona(nombre,edad);
            System.out.println(control.mostrarPersonas());
        }


    }

    /* elimina a la persona seleccionada en la lista*/
    private void eliminarPersona(int indice) {
        if (indice >= 0) {
            modelo.removeElementAt(indice);
            mensaje.setText("Se eliminó un elemento en la posición " + (indice + 1));
            control.borrarPersona(indice);
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un indice"
                    , "Error", JOptionPane.ERROR_MESSAGE);

            mensaje.setText("NO se seleccionó ningún elemento");
        }

    }
   /* Elimina todos los datos existentes en la lista*/
    private void borrarLista() {
        modelo.clear();
        control.borrarDatos();
    }
}
