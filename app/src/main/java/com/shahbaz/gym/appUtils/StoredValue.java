//package com.shahbaz.gym.appUtils;
//
//import android.content.Context;
//import android.os.Build;
//import com.scottyab.aescrypt.AESCrypt;
//import com.softking.ir.phoneshop.data.SharedDB;
//
//
//public class SharedValues {
//
//
//    private static final String key = "Uekng3YxialIE42KjNtOaPoak5enAkeE";
//    private static final String main_key = (Build.DEVICE+key).substring( 0 , 16 );
//    SharedDB sharedDB;
//    Context context;
//    public SharedValues(Context context){
//        this.context = context;
//        sharedDB = new SharedDB(context);
//    }
//    public String getVal(String table , String key ,String defulat_if_not_exist)
//    {
//        try {
//
//            String _key = AESCrypt.encrypt(main_key ,key);
//            String _defulat_if_not_exist = AESCrypt.encrypt(main_key ,defulat_if_not_exist);
//            String res = AESCrypt.decrypt(main_key ,  sharedDB.getVal(table , _key , _defulat_if_not_exist));
//            return res;
//
//        }catch (Exception e){
//
//        }
//        return "";
//    }
//    public void editVal(String table , String key ,String newVal)
//    {
//        try {
//
//            String _key = AESCrypt.encrypt(main_key ,key);
//            String _newValue = AESCrypt.encrypt(main_key ,newVal);
//            sharedDB.editVal(table , _key , _newValue);
//
//        }catch (Exception e){
//            a.a(e.getMessage());
//        }
//
//    }
//
//
//
//
////    private static final String key = "Uekng3YxialIE42KjNtOaPoak5enAkeE";
////    private static final String main_key = (Build.DEVICE+key).substring( 0 , 16 );
////    static String result = "";
////    static float fontsize = 15f;
////    static boolean exist = false;
////
////
////
////    public static String getVal(final Context context ,final String table ,final String key)
////    {
////
////        AsyncTask.execute(new Runnable() {
////            @Override
////            public void run() {
////                //TODO your background code
////                    try {
////                        String _key = AESCrypt.encrypt(main_key , key);
////
////                        String _value = AESCrypt.decrypt(main_key ,context.getSharedPreferences(table, Context.MODE_PRIVATE)
////                                .getString(_key , ""));
////                        result = _value != null ? _value : "";
////                    }catch (Exception e){
////                     a.a(e.getMessage());
////                    }
////
////            }
////        });
////
////
////        return result;
////
////    }
////    public static void editVal(final Context context ,final String table ,final String key ,final String value)
////    {
////
////        AsyncTask.execute(new Runnable() {
////            @Override
////            public void run() {
////                //TODO your background code
////
////                try {
////                    String _value = AESCrypt.encrypt(main_key , value);
////                    String _key = AESCrypt.encrypt(main_key , key);
////                    a.a(AESCrypt.decrypt(main_key, _value));
////                    a.a(AESCrypt.decrypt(main_key, _key));
////                    a.a("my:"+AESCrypt.decrypt(main_key, "+0HsFovIut+1CcKr6MPmTA=="));
////                    a.a("my:"+AESCrypt.decrypt(main_key, "DILR2H3x+bhH9fjhcMqQKQ=="));
////                    SharedPreferences pref= context.getSharedPreferences(table, Context.MODE_PRIVATE);
////
//////                    if(pref.edit().putString(_key , _value).commit()){
//////                        a.a("Success Saved Prefrences."+key);
//////                    }else{
//////                        a.a("Not Save Prefrences."+key);
//////                    }
////                    pref.edit().putString(_key , _value).apply();
////                }catch (Exception e){
////                    a.a(e.getMessage());
////                }
////
////            }
////        });
////
////
////
////    }
////    public static void editFontSize(final Context context ,final float value)
////    {
////
////        AsyncTask.execute(new Runnable() {
////            @Override
////            public void run() {
////                //TODO your background code
////                try {
////                    String _key = AESCrypt.encrypt(main_key , Settings.fontsize);
////                    String _value = AESCrypt.encrypt(main_key , String.valueOf(value));
////                    SharedPreferences pref= context.getSharedPreferences(val.settings, Context.MODE_PRIVATE);
////
//////                    if(pref.edit().putString(_key , _value).commit()){
//////                        a.a("Success Saved Prefrences."+Settings.fontsize );
//////                    }else{
//////                        a.a("Not Save Prefrences."+Settings.fontsize );
//////                    }
////                    pref.edit().putString(_key  , _value).apply();
////                }catch (Exception e){
////                    a.a(e.getMessage());
////                }
////            }
////        });
////
////
////
////    }
////    public static float getValFontSize(final Context context)
////    {
////        AsyncTask.execute(new Runnable() {
////            @Override
////            public void run() {
////                //TODO your background code
////                    try {
////                        String _key = AESCrypt.encrypt(main_key , Settings.fontsize);
////                        String _value = AESCrypt.decrypt(main_key , context.getSharedPreferences(val.settings, Context.MODE_PRIVATE)
////                                .getString(_key, "15"));
////
////                        fontsize = Float.valueOf(_value);
////                    }catch (Exception e){
////                        a.a(e.getMessage());
////                    }
////
////            }
////        });
////
////        return fontsize;
////
////    }
////
////    public static boolean checkKeyExist(final Context context , final String table , final String key)
////    {
////        AsyncTask.execute(new Runnable() {
////            @Override
////            public void run() {
////                //TODO your background code
////                try {
////                    String _key = AESCrypt.encrypt(main_key , key);
////                    exist = context.getSharedPreferences(table , Context.MODE_PRIVATE).contains(_key);
////                }catch (Exception e){
////                    a.a(e.getMessage());
////                }
////            }
////        });
////
////        return exist;
////    }
//
//
//    public static final class Shop{
//        public static String manager = "manager";
//    }
//
//    public static final class Settings{
//        public static String city1 = "city1";
//        public static String city2 = "city2";
//        public static String fontsize = "fontsize";
//        public static String fonttype = "fonttype";
//        public static String logo = "logo";
//        public static String phondex_loaded = "phondex_loaded";
//
//
//    }
//
//
//    public static final class Your_shop{
//        public static String id = "id";
//        public static String email = "email";
//        public static String pass = "pass";
//        public static String name = "name";
//        public static String address = "address";
//        public static String about = "about";
//        public static String img = "img";
//        public static String city = "city";
//        public static String lat = "lat";
//        public static String lng = "lng";
//        public static String token = "token";
//        public static String open_close = "open_close";
//    }
//    public static final class User{
//        public static String name = "name";
//        public static String family = "family";
//        public static String email = "email";
//        public static String pass = "pass";
//        public static String city = "city";
//        public static String fav_shop = "fav_shop";
//    }
//
//
//    public static final String your_shop = "your_shop";
//    public static final String settings = "settings";
//    public static final String user = "user";
//    public static final String shop = "shop";
//
//
//
//}
