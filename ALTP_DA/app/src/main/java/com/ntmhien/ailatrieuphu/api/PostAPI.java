package com.ntmhien.ailatrieuphu.api;

import android.content.Context;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;


public class PostAPI {
    public static void PostAPI(final Context context, final Map<String,String> mMap, String duongdan) {
        //Khởi tạo stringrequest với method = post, đường dẫn
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override//Kết quả trả về
            public void onResponse(String response) {
//                try {
//                    JSONArray jr = new JSONArray(response);
//                    JSONObject jb = jr.getJSONObject(5);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

                Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() { //Kết quả trả về nếu lỗi
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() { //Định dạng chuỗi trả về UTF-8
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            //Giá trị truyên vồ là 1 map <String,String>
            // String 1: là tên request.
            // String 2 là giá trị....
            // ví dụ localhost:8000/api?concunhonho=8.35cm
            //String 1: concunhonho - String 2= 8.35cm
            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params=mMap;
//                for(String key:mMap.keySet()){
//                    params.put( key,mMap.get(key));
//                }

                return mMap;

            }
        };
        //Khởi tạo 1 request chuẩn bị thực thi
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        //Thực thi request
        requestQueue.add(stringRequest);// Rồi còn thắc mắc nào ko
        //Map<String,String> map = new Hashmap<>();
        //String 1 là key, String 2 là value (có thể thay = int,float,double,...)
    }
}
