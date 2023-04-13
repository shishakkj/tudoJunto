package com.example.tudojunto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class calculadoraHoras extends Fragment {
    Button somar, subtrair;
    EditText horaInicial, minutoInicial, horaFinal, minutoFinal;
    TextView resultadoHora, resultadoMinuto;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public calculadoraHoras() {

    }

    public static calculadoraHoras newInstance(String param1, String param2) {
        calculadoraHoras fragment = new calculadoraHoras();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_calculadora_horas, container, false);
        somar = v.findViewById(R.id.botaoSoma);
        subtrair = v.findViewById(R.id.botaoSub);



        return v;
    }
    public void soma(){
            int hi = Integer.parseInt(horaInicial.getText().toString());
            int mi = Integer.parseInt(minutoInicial.getText().toString());
            int hf = Integer.parseInt(horaFinal.getText().toString());
            int mf = Integer.parseInt(minutoFinal.getText().toString());
            int rh = hi + hf;
            int rm = mi + mf;
            while (rm > 59) {
                rm -= 60;
                rh++;
            }
            resultadoHora.setText(rh);
            resultadoMinuto.setText(rm);
        }
    }
