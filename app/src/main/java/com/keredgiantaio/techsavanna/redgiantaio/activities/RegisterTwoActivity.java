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
import com.keredgiantaio.techsavanna.redgiantaio.helpers.ApiRegisterTwoClient;
import com.keredgiantaio.techsavanna.redgiantaio.helpers.ApiRegisterTwoService;
import com.keredgiantaio.techsavanna.redgiantaio.methods.RegisterTwoResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class RegisterTwoActivity extends AppCompatActivity {

    EditText Idnumber;
    String questiongender,questionage,questionheight,questioneducation;
    List<String> listgender,listage,listheight,listeducation;
    Spinner gender,age,height,education;
    Button btnlogin;
    String  genderString,ageString,heightString,educationString,nameofperson,telephone;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_two);

        Idnumber=findViewById(R.id.input_number);
        gender=findViewById(R.id.gender);
        age=findViewById(R.id.age);
        height=findViewById(R.id.height);
        education=findViewById(R.id.education);

        btnlogin=findViewById(R.id.btn_login);

        nameofperson=getIntent().getExtras().getString("nameofperson");
        telephone=getIntent().getExtras().getString("telephone");
        getGender();
        getAge();
        getHeight();
        getEducation();

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genderString=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ageString=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        height.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                heightString=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        education.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                educationString=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

           btnlogin.setOnClickListener(new View.OnClickListener() {
             @Override
                public void onClick(View v) {
sendData();
                         }});

    }



    private void getGender() {
        StringRequest stringRequest = new StringRequest(AppConfig.DATA_URL+"readgender.php",
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
                            listgender = new ArrayList<String>();
                            for(int i=0;i<jr.length();i++) {
                                JSONObject jb1 = jr.getJSONObject(i);

                                questiongender= jb1.getString("gendername");


                                // spinner.setAdapter(new ArrayAdapter<String>(StockTakeClass.this, android.R.layout.simple_spinner_dropdown_item, Collections.singletonList(question)));
                                //getProducts(question);

                                listgender.add(questiongender);


                            }
                            System.out.println("Data Response: "+  listgender);
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(RegisterTwoActivity.this,
                                    android.R.layout.simple_spinner_item, listgender);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            gender.setAdapter(dataAdapter);

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

    private void getAge() {


        StringRequest stringRequest = new StringRequest(AppConfig.DATA_URL+"readage.php",
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
                            listage = new ArrayList<String>();
                            for(int i=0;i<jr.length();i++) {
                                JSONObject jb1 = jr.getJSONObject(i);

                                questionage= jb1.getString("agename");


                                // spinner.setAdapter(new ArrayAdapter<String>(StockTakeClass.this, android.R.layout.simple_spinner_dropdown_item, Collections.singletonList(question)));
                                //getProducts(question);

                                listage.add(questionage);


                            }
                            System.out.println("Data Response: "+  listage);
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(RegisterTwoActivity.this,
                                    android.R.layout.simple_spinner_item, listage);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            age.setAdapter(dataAdapter);

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

    private void getHeight() {


        StringRequest stringRequest = new StringRequest(AppConfig.DATA_URL+"readheight.php",
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
                            listheight = new ArrayList<String>();
                            for(int i=0;i<jr.length();i++) {
                                JSONObject jb1 = jr.getJSONObject(i);

                                questionheight= jb1.getString("heightname");


                                // spinner.setAdapter(new ArrayAdapter<String>(StockTakeClass.this, android.R.layout.simple_spinner_dropdown_item, Collections.singletonList(question)));
                                //getProducts(question);

                                listheight.add(questionheight);


                            }
                            System.out.println("Data Response: "+  listheight);
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(RegisterTwoActivity.this,
                                    android.R.layout.simple_spinner_item, listheight);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            height.setAdapter(dataAdapter);

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

    private void getEducation() {


        StringRequest stringRequest = new StringRequest(AppConfig.DATA_URL+"readeducation.php",
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
                            listeducation = new ArrayList<String>();
                            for(int i=0;i<jr.length();i++) {
                                JSONObject jb1 = jr.getJSONObject(i);

                                questioneducation= jb1.getString("educationname");


                                // spinner.setAdapter(new ArrayAdapter<String>(StockTakeClass.this, android.R.layout.simple_spinner_dropdown_item, Collections.singletonList(question)));
                                //getProducts(question);

                                listeducation.add(questioneducation);


                            }
                            System.out.println("Data Response: "+  listeducation);
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(RegisterTwoActivity.this,
                                    android.R.layout.simple_spinner_item, listeducation);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            education.setAdapter(dataAdapter);

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

        pDialog = new ProgressDialog(RegisterTwoActivity.this,
                R.style.Theme_AppCompat_DayNight_DarkActionBar);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Sending data...");
        pDialog.setCancelable(false);

        showpDialog();

        String inputnumber =  Idnumber.getText().toString();
        String genderStrings = genderString;
        String ageStrings =  ageString;
        String heightStrings = heightString;
        String educationStrings =  educationString;

        ApiRegisterTwoService service = ApiRegisterTwoClient.getClient().create(ApiRegisterTwoService.class);

        Call<RegisterTwoResponse> userCall = service.sendRegisterTwo(nameofperson, telephone,inputnumber,genderStrings,ageStrings,heightStrings,educationStrings);
        System.out.println("data shop"+ nameofperson+" "+telephone+" "+inputnumber+" "+genderStrings+" "+ageStrings+" "+heightStrings+" "+educationStrings);

        userCall.enqueue(new Callback<RegisterTwoResponse>() {
            @Override
            public void onResponse(Call<RegisterTwoResponse> call, retrofit2.Response<RegisterTwoResponse> response) {
                hidepDialog();
                //onSignupSuccess();
                System.out.println("data out"+ call);
                Log.d("onResponse", "" + response.body().getMessage());

                String res=response.body().getMessage();



                if(response.body().getSuccess() == 1) {
                    Toast.makeText(RegisterTwoActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterTwoActivity.this, R.style.MyDialogTheme);

                    builder.setTitle("Confirmation");
                    builder.setIcon(R.drawable.resized);
                    builder.setMessage("Saved successfully");
                    builder.setPositiveButton(Html.fromHtml("<font color='orange'>Ok</font>"),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(RegisterTwoActivity.this, RegisterPictureActivity.class);
                                    intent.putExtra("nameperson",nameofperson);
                                    intent.putExtra("phone",telephone);
                                    startActivity(intent);

                                }
                            });
                    builder.create().show();

                }else {
                    Toast.makeText(RegisterTwoActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterTwoResponse> call, Throwable t) {
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
