//package com.shahbaz.gym.appUtils.Network;
//
//import org.apache.http.params.HttpConnectionParams;
//import org.apache.http.params.HttpParams;
//
//public class SendData {
//    public boolean send(String address , String[] params){
//        try {
//
//            HttpParams myHttpParams = new BasicHttpParams();
//
//            HttpConnectionParams.setConnectionTimeout(myHttpParams, 10000);
//
//            HttpConnectionParams.setSoTimeout(myHttpParams, 10000);
//
//            HttpClient client = new DefaultHttpClient( myHttpParams );
//
//            HttpPost post = new HttpPost( url_ );
//
//            try {
//
//                post.setEntity( new UrlEncodedFormEntity( dataToSend ));
//
//                client.execute( post );
//
//                return true;
//
//            } catch ( Exception e ) {
//                /*
//                 * Log.i( "MatiMessage" , "error in posting data -> " + e.toString() );
//                 */
//                return false;
//            }
//
//        }catch (Exception e){
//
//        }
//    }
//}
