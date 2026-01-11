
package com.example.lib_communication_context.Core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import com.example.lib_domain_context.ICaller;
import com.example.lib_domain_context.ServiceData;
import com.example.lib_utilities.Utilities.Convert;
import com.example.lib_utilities.Utilities.JsonHelper;

public class CallerServices implements ICaller
{
    private static String token = null;
    private static Date expires = null;
    
    public HashMap<String, Object> Execute(HashMap<String, Object> data)
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            response = Authenticate(data);
            if (response == null || response.containsKey("Error"))
                return response;
            response.clear();

            if (data.containsKey("Architecture"))
                data.remove("Architecture");
            String _url = (String)data.get("Url");
            data.remove("Url");
            data.remove("UrlToken");
            data.put("Bearer", token);
            String stringData = JsonHelper.ConvertToString(data);
            
            URL url = new URL(_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.getOutputStream().write(stringData.getBytes("utf-8"));
            
            if (conn.getResponseCode() != 200)
            {
                conn.disconnect();
                response.put("Error", conn.getResponseMessage());
                return response;
            }           

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String resp = "";
            String output = null;
            while ((output = br.readLine()) != null) {
                resp += output;
            }
            conn.disconnect();

            if (resp == null || resp.equals(""))
            {
                response.put("Error", "lbErrorAuthentication");
                return response;
            }
            resp = resp.replace("\"", "").replace("\\", "'").replace("'", "\"");
            
            response = JsonHelper.ConvertToObject(resp);
            return response;
        }
        catch (Exception ex)
        {
            response.put("Error", ex.toString());
            return response;
        }
    }

    public HashMap<String, Object> Authenticate(HashMap<String, Object> data) 
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            if (expires != null && 
                new Date().getTime() - expires.getTime() <= new Date(0,0,0,0,1,0).getTime())
            {
                response.put("Response", "Ok");
                return response;
            }
            
            HashMap<String, Object> temp = new HashMap<String, Object>();
            String _url = (String)data.get("UrlToken");
            temp.put("User", ServiceData.UserData);
            String stringData = JsonHelper.ConvertToString(temp);
            
            URL url = new URL(_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.getOutputStream().write(stringData.getBytes("utf-8"));

            if (conn.getResponseCode() != 200)
            {
                response.put("Error", conn.getResponseMessage());
                return response;
            }
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String resp = "";
            String output = null;
            while ((output = br.readLine()) != null) {
                resp += output;
            }
            conn.disconnect();

            if (resp == null || resp.equals(""))
            {
                response.put("Error", "lbErrorAuthentication");
                return response;
            }
            resp = resp.replace("\"", "").replace("\\", "'").replace("'", "\"");
            
            response = JsonHelper.ConvertToObject(resp);
            token = (String)response.get("Token");
            expires = Convert.StringToDate((String)response.get("Expires"));
            return response;
        }
        catch (Exception ex)
        {
            response.put("Error", ex.toString());
            return response;
        }
    }
}