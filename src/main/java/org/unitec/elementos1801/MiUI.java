/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.elementos1801;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author T-107
 */

@SpringUI
@Theme("valo")
public class MiUI extends UI {
    @Autowired RepositorioMensajito repoMensa;
    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        
        //Generamos una etiqueta
        Label etiqueta = new Label("Aplicacion con mensajes");
        etiqueta.addStyleName(ValoTheme.LABEL_H1);
        
        Label etiquetaGuardar = new Label("Guardar");
        etiquetaGuardar.addStyleName(ValoTheme.LABEL_H2);
        TextField textoTitulo = new TextField();
        textoTitulo.setPlaceholder("Escribe el titulo");
        TextArea textoCuerpo = new TextArea();
        textoCuerpo.setPlaceholder("Escribe el cuerpo del mensaje");
        Button boton = new Button("Guardar mensaje");
        boton.addStyleName(ValoTheme.BUTTON_DANGER);
        Grid<Mensajito> grid = new Grid<>();
        //Manejamos el evento del boton
        boton.addClickListener(evento->{
            
            if( textoCuerpo.getValue().equals("") && textoTitulo.getValue().equals("")){
                Notification.show("Se requieren los campos",Notification.Type.ERROR_MESSAGE);
            }else{
                repoMensa.save( new Mensajito(textoTitulo.getValue(),textoCuerpo.getValue()));
            Notification.show("Se guardo el mensaje!!",Notification.Type.ERROR_MESSAGE);
            
            // Create a grid bound to the list
            
            grid.setItems((List)repoMensa.findAll());
            grid.addColumn(Mensajito::getId).setCaption("id del mensaje");
            grid.addColumn(Mensajito::getTitulo).setCaption("Titulo del mensaje");
            grid.addColumn(Mensajito::getCuerpo).setCaption("Cuerpo del mensaje");
            }
            
        });
        
        
       
        layout.addComponent(etiqueta);
        layout.addComponent(etiquetaGuardar);
        layout.addComponent(textoTitulo);
        layout.addComponent(textoCuerpo);
        layout.addComponent(boton);
        layout.addComponent(grid);
        
        //Primero creamos un horizontal layout
        HorizontalLayout layout1 = new HorizontalLayout();
        TextField textoId = new TextField();
        textoId.setPlaceholder("Introduce el id");
        Button botonBuscar = new Button("Buscar");
        botonBuscar.addStyleName(ValoTheme.BUTTON_PRIMARY);
        
   
        layout1.addComponent(textoId);
        layout1.addComponent(botonBuscar);
        layout.addComponent(layout1);
        
        //Creamos otro layout para los campos de texto
        HorizontalLayout layout2 = new HorizontalLayout();
        TextField textoBuscarId = new TextField();
         TextField textoBuscarTitulo = new TextField();
          TextArea textoBuscarCuerpo = new TextArea();
        
          layout2.addComponent(textoBuscarId);
         layout2.addComponent(textoBuscarTitulo);
         layout2.addComponent(textoBuscarCuerpo);
          layout.addComponent(layout2);
          
          Button botonActualizar = new Button("Actualizar");
          botonActualizar.addStyleName(ValoTheme.BUTTON_FRIENDLY);
          layout.addComponent(botonActualizar);
          
    
        setContent(layout);
        //Vamos a buscar por id
        botonBuscar.addClickListener(evento->{
            Mensajito mensa = repoMensa.findOne(Integer.parseInt(textoId.getValue()));
            //Ajustamos los 3 campos con los datos
            //Primero el id
            textoBuscarId.setValue(""+ mensa.getId());
            textoBuscarTitulo.setValue(mensa.getTitulo());
            textoBuscarCuerpo.setValue(mensa.getCuerpo());
            
            textoBuscarId.setEnabled(false);
            
        });
        
    }
    
}
