package dev.autism;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OSHelper {

    public static String operatingSystem;
    public static String appdata;
    public static String ip;
    public static String whoami;

    public OSHelper() {
        this.operatingSystem = System.getProperty("os.name");
        this.appdata = System.getenv("APPDATA");
        this.ip = getPublicIp();
        this.whoami = getUser();
    }

    private String getPublicIp() {
        try {
            URL url = new URL("https://ipapi.co/json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject json = new JSONObject(response.toString());
            return json.getString("ip");
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    private String getUser() {
        return System.getProperty("user.name");
    }

}
