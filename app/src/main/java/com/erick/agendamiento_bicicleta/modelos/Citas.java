package com.erick.agendamiento_bicicleta.modelos;

public class Citas {
    private String problema;
    private String marcaBicicleta;
    private User usuario;
    private long id;

    public Citas(String problema, String marcaBicicleta, User usuario, long id) {
        this.problema = problema;
        this.marcaBicicleta = marcaBicicleta;
        this.usuario = usuario;
        this.id = id;
    }

    public Citas(String problema, String marcaBicicleta, User usuario) {
        this.problema = problema;
        this.marcaBicicleta = marcaBicicleta;
        this.usuario = usuario;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getMarcaBicicleta() {
        return marcaBicicleta;
    }

    public void setMarcaBicicleta(String marcaBicicleta) {
        this.marcaBicicleta = marcaBicicleta;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Citas{" +
                "problema='" + problema + '\'' +
                ", marcaBicicleta='" + marcaBicicleta + '\'' +
                ", usuario=" + usuario +
                ", id=" + id +
                '}';
    }
}
