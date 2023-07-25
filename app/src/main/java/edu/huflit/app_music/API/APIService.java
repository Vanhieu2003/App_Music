package edu.huflit.app_music.API;

public class APIService {
    private static final String base_url = "https://vanhieu2603.000webhostapp.com/Server/";
    public static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
