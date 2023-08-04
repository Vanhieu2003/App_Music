package edu.huflit.app_music.APIServer;

public class APIService {
    private static final String base_url = "https://vanhieu260303.000webhostapp.com/Server/";
    public static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
