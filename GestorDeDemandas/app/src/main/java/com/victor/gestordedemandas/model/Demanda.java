package com.victor.gestordedemandas.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Demanda implements Parcelable {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("gerente")
    private String gerenteProjeto;

    @JsonProperty("numero")
    private Integer mNumero;
    @JsonProperty("titulo")
    private String mTitulo;
    @JsonProperty("status")
    private String mStatus;
    @JsonProperty("responsavel")
    private String mResponsavel;
    @JsonProperty("prazo")
    private String mPrazo;
    @JsonProperty("observacoes")
    private String mObservacoes;

    public Demanda() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGerenteProjeto() {
        return gerenteProjeto;
    }

    public void setGerenteProjeto(String gerenteProjeto) {
        this.gerenteProjeto = gerenteProjeto;
    }


    public Integer getmNumero() {
        return mNumero;
    }

    public void setmNumero(Integer mNumero) {
        this.mNumero = mNumero;
    }

    public String getmTitulo() {
        return mTitulo;
    }

    public void setmTitulo(String mTitulo) {
        this.mTitulo = mTitulo;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmResponsavel() {
        return mResponsavel;
    }

    public void setmResponsavel(String mResponsavel) {
        this.mResponsavel = mResponsavel;
    }

    public String getmPrazo() {
        return mPrazo;
    }

    public void setmPrazo(String mPrazo) {
        this.mPrazo = mPrazo;
    }

    public String getmObservacoes() {
        return mObservacoes;
    }

    public void setmObservacoes(String mObservacoes) {
        this.mObservacoes = mObservacoes;
    }

    protected Demanda(Parcel in) {
        gerenteProjeto = in.readString();
        mTitulo = in.readString();
        mStatus = in.readString();
        mResponsavel = in.readString();
        mPrazo = in.readString();
        mObservacoes = in.readString();
        mNumero = in.readInt();
        id = in.readInt();
    }

    public static final Creator<Demanda> CREATOR = new Creator<Demanda>() {
        @Override
        public Demanda createFromParcel(Parcel in) {
            return new Demanda(in);
        }

        @Override
        public Demanda[] newArray(int size) {
            return new Demanda[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(gerenteProjeto);
        dest.writeString(mTitulo);
        dest.writeString(mStatus);
        dest.writeString(mResponsavel);
        dest.writeString(mPrazo);
        dest.writeString(mObservacoes);
        dest.writeInt(mNumero);
        dest.writeInt(id);
    }

    @Override
    public String toString() {
        return "Demanda{" +
                "id=" + id +
                ", gerenteProjeto=" + gerenteProjeto +
                ", mNumero=" + mNumero +
                ", mTitulo='" + mTitulo + '\'' +
                ", mStatus='" + mStatus + '\'' +
                ", mResponsavel='" + mResponsavel + '\'' +
                ", mPrazo='" + mPrazo + '\'' +
                ", mObservacoes='" + mObservacoes + '\'' +
                '}';
    }
}
