package com.erick.agendamiento_bicicleta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.erick.agendamiento_bicicleta.controllers.AgendaController;
import com.erick.agendamiento_bicicleta.modelos.User;

public class MainActivity extends AppCompatActivity {
    private Button btnAgregarMascota;
    private EditText etCorreo, etPasswd;
    private AgendaController mascotasController;
    private TextView lblGotoRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Ojo: este código es generado automáticamente, pone la vista y ya, pero
        // no tiene nada que ver con el código que vamos a escribir
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCorreo = findViewById(R.id.etCorreo);
        etPasswd = findViewById(R.id.etPassw);

        btnAgregarMascota = findViewById(R.id.btnAgregarMascota);

        // Lo siguiente sí es nuestro ;)
        // Definir nuestro controlador
        mascotasController = new AgendaController(MainActivity.this);

        // Agregar listener del botón de guardar
        btnAgregarMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Resetear errores a ambos

                etCorreo.setError(null);
                etPasswd.setError(null);
                String correo = etCorreo.getText().toString(),
                        passwd = etPasswd.getText().toString();

                if ("".equals(correo)) {
                    etCorreo.setError("Escribe el correo");
                    etCorreo.requestFocus();
                    return;
                }
                if ("".equals(passwd)) {
                    etPasswd.setError("Escriba la contraseña");
                    etPasswd.requestFocus();
                    return;
                }



                List<User> listaDeUsers;
                listaDeUsers = mascotasController.obtenerUsuarios();
                boolean bandera = false;
                boolean passMissMatch = false;
                long idParam = 0;



                for (User usuarioTmp: listaDeUsers) {
                    if(usuarioTmp.getCorreo().equals(correo) && !usuarioTmp.getPasswd().equals(passwd)){
                        bandera = false;
                        passMissMatch = true;
                        break;
                    }
                    if(usuarioTmp.getCorreo().equals(correo) && usuarioTmp.getPasswd().equals(passwd)){
                        idParam = usuarioTmp.getId();
                        bandera = true;
                        break;
                    }
                }

                if(passMissMatch){
                    etPasswd.setError("La contraseña no coincide con el usuario, intentelo nuevamente");
                    etPasswd.requestFocus();
                    return;

                }


                if(bandera){
                    Toast.makeText(MainActivity.this, "Correcto Inicio de sesion", Toast.LENGTH_SHORT).show();
                    Intent item12 = new Intent(MainActivity.this, AgendarCitasActivity.class);
                    item12.putExtra("idUserTmp", idParam);
                    MainActivity.this.startActivity(item12);


                }else{
                    Toast.makeText(MainActivity.this, "Incorrecto inicio de sesion, intentelo nuevamente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lblGotoRegister = findViewById(R.id.link_to_register);
        lblGotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itemintent = new Intent(MainActivity.this, Register.class);
                MainActivity.this.startActivity(itemintent);}
        });



    }


}
