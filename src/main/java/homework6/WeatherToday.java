package homework6;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class WeatherToday {
    public static void main(String[] args) throws IOException {

        try {
            URL url = new URL("https://yandex.ru/pogoda/?lat=59.93909836&lon=30.31587601");
            // api key fcbc6008-3d0e-48b6-bdff-ab240e1a7539
            

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod( "GET" );
            conn.connect();

            int responseCode = conn.getResponseCode();
            StringBuilder informationString;
            if (responseCode != 200) {
                throw new RuntimeException( "HttpResponseCode: " + responseCode );
            } else {
                informationString = new StringBuilder();
                Scanner scanner = new Scanner( url.openStream() );
                while (scanner.hasNext()) {
                    informationString.append( scanner.nextLine() );
                }
                scanner.close();

                System.out.println( informationString );
            }
            JSONParser parse = new JSONParser();
            JSONArray dataObject = (JSONArray) parse.parse( String.valueOf( informationString ));
            System.out.println( dataObject.get( 0 ) );
            JSONObject countryData = (JSONObject) dataObject.get( 0 );
            System.out.println( countryData.get( "Location_type" ) );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}