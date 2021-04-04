package com.erick.agendamiento_bicicleta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import com.erick.agendamiento_bicicleta.controllers.AgendaController;
import com.erick.agendamiento_bicicleta.modelos.Citas;
import com.erick.agendamiento_bicicleta.modelos.User;

public class AgendarCitasActivity extends AppCompatActivity {

    private EditText etProblema, etMarca;
    private User usuario;
    private AgendaController mascotasController;
    private Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_citas);
        Bundle extras = getIntent().getExtras();
        // Si no hay datos (cosa rara) salimos
        if (extras == null) {
            finish();
            return;
        }
        long idUserTmp = extras.getLong("idUserTmp");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etProblema = findViewById(R.id.etProblema);
        etMarca = findViewById(R.id.etMarca);
        btnAdd = findViewById(R.id.btnAdd);

        mascotasController = new AgendaController(AgendarCitasActivity.this);

        List<User> listaDeUsers;
        listaDeUsers = mascotasController.obtenerUsuarios();

        for (User usuarioTmp: listaDeUsers) {

            if(usuarioTmp.getId()==idUserTmp){
                usuario = usuarioTmp;
                break;
            }
        }

        // Agregar listener del botón de guardar
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Resetear errores a ambos

                etProblema.setError(null);
                etMarca.setError(null);
                String problema = etProblema.getText().toString(),
                        marca = etMarca.getText().toString();

                if ("".equals(problema)) {
                    etProblema.setError("Escribe el problema");
                    etProblema.requestFocus();
                    return;
                }
                if ("".equals(marca)) {
                    etMarca.setError("Escriba la marca");
                    etMarca.requestFocus();
                    return;
                }



                Citas cita = new Citas(problema, marca, usuario);
                long id =mascotasController.agendarCita(cita);

                if (id == -1) {
                    // De alguna manera ocurrió un error
                    Toast.makeText(AgendarCitasActivity.this, "Error al agendar. Intenta de nuevo", Toast.LENGTH_SHORT).show();
                } else {
                    // Terminar
                    Toast.makeText(AgendarCitasActivity.this, "Cita agendada con exito", Toast.LENGTH_SHORT).show();
                    List<Citas> listaCitas;
                    listaCitas = mascotasController.getCitas();
                    for (Citas citaTmp: listaCitas){
                        Toast.makeText(AgendarCitasActivity.this, citaTmp.getProblema() + " " +citaTmp.getUsuario().getId(), Toast.LENGTH_SHORT).show();
                    }

                }





            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item1:
                Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(),"Item 2 Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item3:
                Toast.makeText(getApplicationContext(),"Item 3 Selected",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}