package com.erick.agendamiento_bicicleta.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.erick.agendamiento_bicicleta.AyudanteBaseDeDatos;
import com.erick.agendamiento_bicicleta.modelos.Citas;
import com.erick.agendamiento_bicicleta.modelos.User;


public class AgendaController {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;

    private String usuarios = "users";
    private String citas = "citas";

    public AgendaController(Context contexto) {
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }



    public long registrarUsuario(User usuario) {
        // writable porque vamos a insertar
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("cedula", usuario.getCedula());
        valoresParaInsertar.put("nombre", usuario.getNombre());
        valoresParaInsertar.put("apellido", usuario.getApellido());
        valoresParaInsertar.put("telefono", usuario.getTelefono());
        valoresParaInsertar.put("correo", usuario.getCorreo());
        valoresParaInsertar.put("password", usuario.getPasswd());

        return baseDeDatos.insert(usuarios, null, valoresParaInsertar);
    }

    public long agendarCita(Citas cita) {
        // writable porque vamos a insertar
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("problema", cita.getProblema());
        valoresParaInsertar.put("marca", cita.getMarcaBicicleta());
        valoresParaInsertar.put("user", cita.getUsuario().getId());

        return baseDeDatos.insert(citas, null, valoresParaInsertar);
    }

    public boolean getCorreo(String mailTmp){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();

        String[] columnasAConsultar = {"id"};
        String where = "correo = ?";
        String[] args = new String[]{
                mailTmp
        };
        Cursor cursor = baseDeDatos.query(
                usuarios,//from mascotas
                columnasAConsultar,
                where,
                args,
                null,
                null,
                null
        );
        if (cursor == null) {
            /*
                Salimos aquí porque hubo un error, regresar
                lista vacía
             */
            return true;

        }
        if (!cursor.moveToFirst()) return true;

        // Fin del ciclo. Cerramos cursor y regresamos la lista de mascotas :)
        cursor.close();
        return false;

    }

    public boolean validarCedula(String cedula) {
        boolean cedulaCorrecta = false;

        try {

            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
// Coeficientes de validación cédula
// El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
                    int verificador = Integer.parseInt(cedula.substring(9,10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1))* coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    }
                    else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            System.out.println("Una excepcion ocurrio en el proceso de validadcion");
            cedulaCorrecta = false;
        }

        if (!cedulaCorrecta) {
            System.out.println("La Cédula ingresada es Incorrecta");
        }
        return cedulaCorrecta;
    }

    public List<Citas> getCitas() {
        List<Citas> citaList = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();
        // SELECT nombre, edad, id
        String[] columnasAConsultar = {"problema", "marca","id", "user"};
        Cursor cursor = baseDeDatos.query(
                citas,//from mascotas
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null) {
            /*
                Salimos aquí porque hubo un error, regresar
                lista vacía
             */
            return citaList;

        }
        // Si no hay datos, igualmente regresamos la lista vacía
        if (!cursor.moveToFirst()) return citaList;

        // En caso de que sí haya, iteramos y vamos agregando los
        // datos a la lista de mascotas
        do {
            // El 0 es el número de la columna, como seleccionamos
            // nombre, edad,id entonces el nombre es 0, edad 1 e id es 2
            String problema = cursor.getString(0);
            String marca = cursor.getString(1);
            long idCita = cursor.getLong(2);
            int idUser = cursor.getInt(3);
            User userTmp = new User();
            userTmp.setId(idUser);
            Citas cita = new Citas(problema,marca,userTmp,idCita);
            citaList.add(cita);
        } while (cursor.moveToNext());

        // Fin del ciclo. Cerramos cursor y regresamos la lista de mascotas :)
        cursor.close();
        return citaList;
    }

    public List<User> obtenerUsuarios() {
        List<User> usuariosList = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();
        // SELECT nombre, edad, id
        String[] columnasAConsultar = {"password", "correo", "id"};
        Cursor cursor = baseDeDatos.query(
                usuarios,//from mascotas
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null) {
            /*
                Salimos aquí porque hubo un error, regresar
                lista vacía
             */
            return usuariosList;

        }
        // Si no hay datos, igualmente regresamos la lista vacía
        if (!cursor.moveToFirst()) return usuariosList;

        // En caso de que sí haya, iteramos y vamos agregando los
        // datos a la lista de mascotas
        do {
            // El 0 es el número de la columna, como seleccionamos
            // nombre, edad,id entonces el nombre es 0, edad 1 e id es 2
            String passwordBd = cursor.getString(0);
            String correoBd = cursor.getString(1);
            long idUser = cursor.getLong(2);
            User usuarioBd = new User(correoBd, passwordBd, idUser);
            usuariosList.add(usuarioBd);
        } while (cursor.moveToNext());

        // Fin del ciclo. Cerramos cursor y regresamos la lista de mascotas :)
        cursor.close();
        return usuariosList;
    }


}