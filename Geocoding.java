import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Geocoding {
    double[] fetchLocation() {
        double ans[] = new double[2];
        Scanner val = new Scanner(System.in);
        System.out.println("Enter the city name");
        String cityName = val.next();
        System.out.println("Enter the State code in Capital letters");
        String stateCode = val.next();

        try
        {
            URL url = new URL("https://api.openweathermap.org/geo/1.0/direct?q="+cityName+","+stateCode+",IN&limit=5&appid=186f51a95984bff648bbc7962afac041");
            HttpURLConnection con = (HttpURLConnection) url.openConnection(); // Estabilish the connection between server and client

            con.setRequestMethod("GET");       //  generating the response

            con.connect();     // here con is the connection name

            int rc = con.getResponseCode();  // checking the response code
           // System.out.println(rc);

            if (rc != 200)
                {
                    System.out.println("error in connection Estabilishment");
                }
            else
                {
                    String data = "";
                    Scanner sc = new Scanner(url.openStream());

                    while ( sc.hasNext() )
                        {
                            data += sc.nextLine();
                        }

                sc.close();
                                  // System.out.println(data);

                JSONParser par = new JSONParser();
                JSONArray arr = (JSONArray) par.parse(data);

                JSONObject obj = null;

                                   // System.out.println(arr);
                for (int i = 0; i < arr.size(); i++)
                    {
                        obj = (JSONObject) arr.get(i);
                    }
                                  //  System.out.println(obj.get("lon"));
                                  //  System.out.println(obj.get("lat"));

                 ans[0] = (double) obj.get("lon");
                 ans[1]= (double) obj.get("lat");
            }
        }
        catch (Exception e)
            {
                System.out.println(e);
            }
        return ans;
    }
}
