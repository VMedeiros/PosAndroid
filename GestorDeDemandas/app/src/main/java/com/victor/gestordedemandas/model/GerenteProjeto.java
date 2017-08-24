package com.victor.gestordedemandas.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GerenteProjeto implements Parcelable {

    @JsonProperty("nome")
    private String nome;
    @JsonProperty("id")
    private int id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("projeto")
    private String projeto;
    @JsonProperty("demandas")
    private List<Demanda> demandas;

    protected GerenteProjeto(Parcel in) {
        nome = in.readString();
        id = in.readInt();
        email = in.readString();
        projeto = in.readString();
        demandas = in.createTypedArrayList(Demanda.CREATOR);
    }

    public static final Creator<GerenteProjeto> CREATOR = new Creator<GerenteProjeto>() {
        @Override
        public GerenteProjeto createFromParcel(Parcel in) {
            return new GerenteProjeto(in);
        }

        @Override
        public GerenteProjeto[] newArray(int size) {
            return new GerenteProjeto[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProjeto() {
        return projeto;
    }

    public void setProjeto(String projeto) {
        this.projeto = projeto;
    }

    public List<Demanda> getDemandas() {
        return demandas;
    }

    public void setDemandas(List<Demanda> demandas) {
        this.demandas = demandas;
    }

    public GerenteProjeto() {
    }

    public GerenteProjeto(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public String getmNomeGerente() {
        return nome;
    }

    public void setmNomeGerente(String mNomeGerente) {
        this.nome = mNomeGerente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GerenteProjeto{" +
                "mNomeGerente='" + nome + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeInt(id);
        dest.writeString(email);
        dest.writeString(projeto);
        dest.writeTypedList(demandas);
    }
}
