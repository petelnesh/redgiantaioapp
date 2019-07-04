package com.keredgiantaio.techsavanna.redgiantaio.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.keredgiantaio.techsavanna.redgiantaio.R;
import com.keredgiantaio.techsavanna.redgiantaio.app.AppConfig;
import com.keredgiantaio.techsavanna.redgiantaio.helpers.ApiRegisterClient;
import com.keredgiantaio.techsavanna.redgiantaio.helpers.ApiRegisterService;
import com.keredgiantaio.techsavanna.redgiantaio.methods.RegisterResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class RegisterActivity extends AppCompatActivity {
    private Spinner spinner;
    EditText nameofperson,password,confirmpassword,email,telephoneedit;
    String  cardStatusString;
    List<String> list;
    String question;
    Button btnlogin;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        spinner = (Spinner) findViewById(R.id.weight);
        nameofperson=findViewById(R.id.input_name);
        password=findViewById(R.id.password);
        confirmpassword=findViewById(R.id.confirm);
        email=findViewById(R.id.email);
        telephoneedit=findViewById(R.id.telephone);
        btnlogin=findViewById(R.id.btn_login);

        nameofperson.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                nameofperson.setHint("");
            }
        });
        getData();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cardStatusString= parent.getItemAtPosition(position).toString();

                System.out.println("Data selected"+cardStatusString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals(confirmpassword.getText().toString())) {
                    sendData();
                }else{
                    Toast.makeText(RegisterActivity.this,"Password Not matching",Toast.LENGTH_SHORT).show();
                }

            }


        });

    }



    private void getData() {

        StringRequest stringRequest = new StringRequest(AppConfig.DATA_URL+"readweight.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("print sasa");

                        try {
                            //Parsing the fetched Json String to JSON Object

                            JSONObject jObj = new JSONObject(response);
                            //  JSONObject responses = jObj.getJSONObject("");
System.out.println("print sasa"+response);
                            JSONArray jr =jObj.getJSONArray("records");
                            list = new ArrayList<String>();
                            for(int i=0;i<jr.length();i++) {
                                JSONObject jb1 = jr.getJSONObject(i);

                                question= jb1.getString("weightname");


                                // spinner.setAdapter(new ArrayAdapter<String>(StockTakeClass.this, android.R.layout.simple_spinner_dropdown_item, Collections.singletonList(question)));
                                //getProducts(question);

                                list.add(question);


                            }
                            System.out.println("Data Response: "+  list);
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                                    android.R.layout.simple_spinner_item, list);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setPrompt("Select weight...");
                            spinner.setAdapter(dataAdapter);


//                            String text = spinner.getSelectedItem().toString();
//
//                            System.out.println("selected item:"+text);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("error sasa"+error);
                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
    private void sendData() {
        pDialog = new ProgressDialog(RegisterActivity.this,
                R.style.Theme_AppCompat_DayNight_DarkActionBar);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Sending data...");
        pDialog.setCancelable(false);

        showpDialog();

        final String nameofpersonn =  nameofperson.getText().toString();
        final String telephonee = telephoneedit.getText().toString();
        String passwordd =  password.getText().toString();
        String confirmpasswordd = confirmpassword.getText().toString();
        String emaill =  email.getText().toString();
        String weight = cardStatusString;


        ApiRegisterService service = ApiRegisterClient.getClient().create(ApiRegisterService.class);
        //User user = new User(name, email, password);


        Call<RegisterResponse> userCall = service.sendRegister(nameofpersonn, telephonee,emaill,passwordd,confirmpasswordd,weight);
        System.out.println("data shop"+ nameofpersonn+" "+telephonee+" "+emaill+" "+passwordd+" "+weight);

        userCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, retrofit2.Response<RegisterResponse> response) {
                hidepDialog();
                //onSignupSuccess();
                System.out.println("data out"+ call);
                Log.d("onResponse", "" + response.body().getMessage());

                String res=response.body().getMessage();



                if(response.body().getSuccess() == 1) {
                    Toast.makeText(RegisterActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this, R.style.MyDialogTheme);

                    builder.setTitle("Confirmation");
                    builder.setIcon(R.drawable.resized);
                    builder.setMessage("Saved successfully");
                    builder.setPositiveButton(Html.fromHtml("<font color='orange'>Ok</font>"),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(RegisterActivity.this, RegisterTwoActivity.class);
                                    intent.putExtra("nameofperson",nameofpersonn);
                                    intent.putExtra("telephone",telephonee);
                                    startActivity(intent);

                                }
                            });
                    builder.create().show();

                }else {
                    Toast.makeText(RegisterActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                hidepDialog();
                Log.d("onFailure", t.toString());
            }
        });

    }
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
