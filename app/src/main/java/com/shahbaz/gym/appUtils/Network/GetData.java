package com.shahbaz.gym.appUtils.Network;

import com.shahbaz.gym.appUtils.l;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class GetData {

    public String downloadedData(String address)  {

        StringBuilder stringBuilder = new StringBuilder();
        try {


            URL url = new URL(address);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            Scanner scanner = new Scanner(url.openConnection().getInputStream());
            while (scanner.hasNextLine()){
             stringBuilder.append(scanner.nextLine());
            }

        }catch (Exception e){
            l.l(e.getMessage());
        }
        return stringBuilder.toString();
    }


}
