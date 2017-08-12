package main;

import static spark.Spark.*;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Server {
	
	private static final String BASE_URL = "http://api.tvmaze.com/shows";
	private Scanner scanner;
	private StringBuilder stringBuilder;

	public void runServer() {
		
		stringBuilder = new StringBuilder();
		
		// Route to retrieve a single serie by its id
		get("/single-serie/:id", (request, response) -> {
			String id = request.params("id");
			response.status(200);
			return getData(BASE_URL + "/" + id);
		});
		
		// Route to retrieve a page of series (250 items) by page number
		get("/series-page/:pgn", (request, response) -> {
			String pgn = request.params("pgn");
			response.status(200);
			return getData(BASE_URL + "?page=" + pgn);
		});

		// Route to retrieve all series
        get("/all-series", (request, response) -> {
        	String retorno = getData(BASE_URL);
            response.status(200);
            return retorno;
        });

    }
	
	/**
	 * GETS DATA
	 */
	public String getData(String urlStr) {
		URL url;
		try {
            url = new URL(urlStr);
            scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) stringBuilder.append(" ").append(scanner.next());
        } catch (IOException e) {
            e.printStackTrace();
        }
    	assert stringBuilder != null;
    	String str = stringBuilder.toString();
    	stringBuilder.delete(0, stringBuilder.length());
        return str;
	}

}
