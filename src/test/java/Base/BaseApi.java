package Base;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import Objects.objectApi;



public class BaseApi {
	@Test
    public void obtenerYMostrarTodosLosComics() throws NoSuchAlgorithmException {
		System.out.println("...INCIO...");
		objectApi api = new objectApi();
    	api.obtieneTodosLosComics();
    	System.out.println("...Termino el primer test Existoso...");
    }
    @Test
    public void buscarYMostrarPersonajePorNombre() {
    	System.out.println("...INICIO DEL SEGUNDO TEST...");
    	objectApi api = new objectApi(); 
    	List<String> nombresAleatorios = Arrays.asList("Iron Man", "Hulk", "SpiderMan", "Thor", "Captain America");
    	String resultado = api.buscarPersonajePorNombre(nombresAleatorios);
    	
    	System.out.println(resultado);
    	System.out.println("...Termino el segundo test Existoso...");
    }
    @Test
    public void obtenerYMostrarSeriesEnCurso() {
    	System.out.println("...INICIO DEL TERCERO TEST...");
    	 
        objectApi api = new objectApi();

        
        List<String> seriesEnCurso = api.obtenerSeriesEnCurso();

        
        System.out.println("Series en curso:");
        for (String serie : seriesEnCurso) {
            System.out.println(serie);
            System.out.println("...Termino el tercero test Existoso...");
    
    }
        
    }
    @Test
    public void obtenerComicsPorID() {
    	System.out.println("...INICIO DEL CUARTO TEST...");
    	 objectApi api = new objectApi();
    	    List<Integer> comicIds1 = Arrays.asList(22, 2, 3); // Aquí debes proporcionar los IDs de los cómics que deseas obtener
    	    List<String> respuestas = api.LlamadaDeComicsPorID(comicIds1);

    	   
    	    for (String respuesta : respuestas) {
    	        
    	        System.out.println(respuesta);
    	        System.out.println("...Termino el cuarto test Existoso...");
    }
}}
