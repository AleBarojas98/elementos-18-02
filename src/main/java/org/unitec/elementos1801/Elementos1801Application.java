package org.unitec.elementos1801;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Elementos1801Application implements CommandLineRunner{
@Autowired RepositorioMensajito repoMensa;

	public static void main(String[] args) {
		SpringApplication.run(Elementos1801Application.class, args);
	}

    @Override
    public void run(String... strings) throws Exception {
        //repoMensa.save(new Mensajito("17 de octubre no temblo", "no temblo"));
       // System.out.println("si se guardo");
        //System.out.println("Vamos a buscar todos");
        
       /* for( Mensajito mensa : repoMensa.findAll()){
            System.out.println(mensa);
        }*/
        
        /*System.out.println("Vamos a buscar por id");
        System.out.println(repoMensa.findOne(2));*/
        
        //Actualizar
       /* repoMensa.save(new Mensajito(2,"Nuevo titulo","Nuevo cuerpo"));
        System.out.println(repoMensa.findOne(2));*/
       for( Mensajito men : repoMensa.findByTitulo("17 de octubre no temblo")){
           System.out.println(men);
       }
    
    }
}
