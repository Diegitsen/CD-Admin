package com.example.cdadmin.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cdadmin.Entities.Alumno;
import com.example.cdadmin.Fragments.CursoFragment;
import com.example.cdadmin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener {

    //layout
    private EditText etUsuario, etContrasenia;
    Button bLogin;
    ProgressDialog progressDialog;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    private CursoFragment.OnFragmentInteractionListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = findViewById(R.id.etUsuario);
        etContrasenia = findViewById(R.id.etContrasenia);
        bLogin = findViewById(R.id.bLogin);

        requestQueue = Volley.newRequestQueue(this);
    }

    public void cargarWebService(View v)
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        String url = "http://192.168.0.14/CapacitacionDestino/wsJSONLoginAlumno.php?username=" + etUsuario.getText().toString() +
                "&contrasenia=" + etContrasenia.getText().toString();

        url = url.replace(" ", "%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        //jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

        progressDialog.hide();
        //Toast.makeText(getContext(),"Mensaje: " + response, Toast.LENGTH_SHORT).show();

        Alumno alumno = new Alumno();

        JSONArray json = response.optJSONArray("Alumno");
        JSONObject jsonObject=null;

        try {
            jsonObject=json.getJSONObject(0);
            alumno.setIdAlumno(jsonObject.optInt("idAlumno"));
            alumno.setNombre(jsonObject.optString("nombre"));
            alumno.setApellido(jsonObject.optString("apellido"));
            alumno.setMinisterio(jsonObject.optString("ministerio"));
            alumno.setNivel(jsonObject.optInt("nivel"));
            alumno.setUsername(jsonObject.optString("username"));
            alumno.setContrasenia(jsonObject.optString("contrasenia"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(!(alumno.getUsername().isEmpty()) && !(alumno.getContrasenia().isEmpty()))
        {
            Toast.makeText(this, "Se logeo ", Toast.LENGTH_SHORT).show();
            goToActivity();
        }
        else
            Toast.makeText(this, "no Se logeo ", Toast.LENGTH_SHORT).show();
    }

    public void goToActivity()
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
