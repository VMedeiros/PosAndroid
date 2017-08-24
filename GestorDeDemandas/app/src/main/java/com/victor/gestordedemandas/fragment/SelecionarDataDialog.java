package com.victor.gestordedemandas.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

public class SelecionarDataDialog extends DialogFragment {

    static Calendar sCalendar;
    int mDia;
    int mMes;
    int mAno;
    Context mContext;
    DatePickerDialog.OnDateSetListener mCallback;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        sCalendar = Calendar.getInstance();
        mDia = sCalendar.get(Calendar.DAY_OF_MONTH);
        mMes = sCalendar.get(Calendar.MONTH);
        mAno = sCalendar.get(Calendar.YEAR);
        mContext = getActivity();
        mCallback = (DatePickerDialog.OnDateSetListener) getActivity();

        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, mCallback, mAno, mMes, mDia);
        datePickerDialog.getDatePicker().setMinDate(sCalendar.getTimeInMillis());

        return datePickerDialog;
    }
}
