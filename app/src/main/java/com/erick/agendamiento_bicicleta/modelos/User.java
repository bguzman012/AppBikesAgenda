package com.erick.agendamiento_bicicleta.modelos;

import java.util.List;

public class User {

    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direecionPrincipal;
    private String direccionSecundaria;
    private String direccionReferencia;
    private String latitud;
    private String longitud;
    private String correo;
    private String passwd;
    private long id;
    private List<Citas> citas;

    public User(){

    }

    public User(String correo, String passwd, long id) {
        this.correo = correo;
        this.passwd = passwd;
        this.id = id;
    }

    public User(String cedula, String nombre, String apellido, String telefono, String direecionPrincipal, String direccionSecundaria, String direccionReferencia, String latitud, String longitud, String correo, String passwd, long id) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direecionPrincipal = direecionPrincipal;
        this.direccionSecundaria = direccionSecundaria;
        this.direccionReferencia = direccionReferencia;
        this.latitud = latitud;
        this.longitud = longitud;
        this.correo = correo;
        this.passwd = passwd;
        this.id = id;
    }

    public User(String cedula, String nombre, String apellido, String telefono, String correo, String passwd, long id) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.passwd = passwd;
        this.id = id;
    }

    public User(String cedula, String nombre, String apellido, String telefono, String correo, String passwd) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.passwd = passwd;
    }

    public User(String direecionPrincipal, String direccionSecundaria, String direccionReferencia, String latitud, String longitud, long id) {
        this.direecionPrincipal = direecionPrincipal;
        this.direccionSecundaria = direccionSecundaria;
        this.direccionReferencia = direccionReferencia;
        this.latitud = latitud;
        this.longitud = longitud;
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireecionPrincipal() {
        return direecionPrincipal;
    }

    public String getDireccionSecundaria() {
        return direccionSecundaria;
    }

    public String getDireccionReferencia() {
        return direccionReferencia;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public long getId() {
        return id;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireecionPrincipal(String direecionPrincipal) {
        this.direecionPrincipal = direecionPrincipal;
    }

    public void setDireccionSecundaria(String direccionSecundaria) {
        this.direccionSecundaria = direccionSecundaria;
    }

    public void setDireccionReferencia(String direccionReferencia) {
        this.direccionReferencia = direccionReferencia;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Citas> getCitas() {
        return citas;
    }

    public void setCitas(List<Citas> citas) {
        this.citas = citas;
    }

    @Override
    public String toString() {
        return "User{" +
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direecionPrincipal='" + direecionPrincipal + '\'' +
                ", direccionSecundaria='" + direccionSecundaria + '\'' +
                ", direccionReferencia='" + direccionReferencia + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", correo='" + correo + '\'' +
                ", passwd='" + passwd + '\'' +
                ", id=" + id +
                '}';
    }
}
