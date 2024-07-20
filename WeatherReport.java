import java.net.URL;   // for generating the url
import java.net.HttpURLConnection; // for estabilish the connection
import java.util.Scanner;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;


public class  WeatherReport extends Geocoding {
    public static void main(String[] args) {

         Geocoding obj = new Geocoding();
         double ans[] = obj.fetchLocation();
         double lat = ans[0];
         double lon = ans[1];


        try
        {
                URL url2 = new URL("https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=186f51a95984bff648bbc7962afac041");
                HttpURLConnection con2 = (HttpURLConnection) url2.openConnection(); // Estabilish the connection between server and client

                con2.setRequestMethod("GET");       //  generating the response
                con2.connect();     // here con is the connection name

                int rc2= con2.getResponseCode();    // checking response
             //   System.out.println(rc2);

                if (rc2!=200)
                    {
                        System.out.println("error in connection Estabilishment");
                    }
                else
                    {
                        String data2 ="";
                        Scanner sc2 = new Scanner(url2.openStream());
                            while ( sc2.hasNext())
                                {
                                    data2 += sc2.nextLine();
                                }

                            sc2.close();
                                                                     // System.out.println(data2);

                        JSONParser par = new JSONParser();
                        JSONObject obj2 = (JSONObject) par.parse(data2);
                                                                    // System.out.println(obj2.get("main"));
                        JSONObject main =(JSONObject) obj2.get("main");
                        JSONObject wind = (JSONObject) obj2.get("wind");

                        System.out.println("current temp = >> " + main.get("temp"));
                        System.out.println("wind speed   = >> " + wind.get("speed"));
                        System.out.println("pressure     = >> " + main.get("pressure"));






            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

}

