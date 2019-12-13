package com.sterlitepower.vendorcom;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class login extends AppCompatActivity {

    EditText phone,Password;
    Button signin;
    TextView forgot_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone=findViewById(R.id.in_phone);
        Password=findViewById(R.id.in_password);
        signin=findViewById(R.id.signin);
        forgot_password =findViewById(R.id.forgot_password);
        Gson gson=new Gson();
        final ProgressDialog progressDialog=new ProgressDialog(this);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://teststerlitepower.senpiper.com/api/core/auth/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        final com.sterlitepower.vendorcom.retrologin login_in=retrofit.create(com.sterlitepower.vendorcom.retrologin.class);
        ProgressBar progress= new ProgressBar()
        {
                final progress.setTitle("Please Wait");
                final progress.setMessage("Logging in...");
               final progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
               final progress.show();
            String mobile_num="+91";
            String mobile_num_1=phone.getText().toString();
            mobile_num=mobile_num.concat(mobile_num_1);
            String pass=Password.getText().toString();
                if(!(mobile_num.isEmpty())&&!(pass.isEmpty())){
            login_deatils details=new login_deatils(mobile_num,pass);
                Gson gson_con=new Gson();
                String finalString=gson_con.toJson(details);
                Call<Object> makeCall=login_in.sign(finalString);
                Log.d("123",finalString);
                makeCall.enqueue(new Callback<Object>() {
        }}
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    if (response.isSuccessful()) {
                        LinkedTreeMap responseTreeMap = (LinkedTreeMap) response.body();
                        if (response.code()==200) {
                            String restoken=((LinkedTreeMap)responseTreeMap.get("response")).get("token").toString();
                            Log.d("1234",restoken);
                            SharedPreferences sharedPreferences=getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                            sharedPreferences.edit().putString("token",restoken).apply();
                            Toast.makeText(login.this, "Login Success", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            Intent intent = new Intent(login.this, vendorcom.class);
                            startActivity(intent);
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call call, Throwable t) {
                    Toast.makeText(login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
                else
        {
            Toast.makeText(login.this,"Please enter the details",Toast.LENGTH_SHORT).show();
        }
    }
};
        }
public class login_deatils{
    private String phone;
    private String Password;
    public login_deatils(String phone, String password){
        this.phone=phone;
        this.Password=password;
    }
}
}

