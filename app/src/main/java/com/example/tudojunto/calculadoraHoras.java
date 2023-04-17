package com.example.tudojunto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        View v = inflater.inflate(R.layout.fragment_calculadora_horas, container, false);
        horaInicial = v.findViewById(R.id.horaInicial);
        minutoInicial = v.findViewById(R.id.minutoInicial);
        horaFinal = v.findViewById(R.id.horaFinal);
        minutoFinal = v.findViewById(R.id.minutoFinal);
        resultadoHora = v.findViewById(R.id.resultadoHora);
        resultadoMinuto = v.findViewById(R.id.resultadoMinuto);
        somar = v.findViewById(R.id.botaoSoma);
        subtrair = v.findViewById(R.id.botaoSub);
        somar.setOnClickListener(sla -> soma());
        subtrair.setOnClickListener(sla -> subtracao());
        return v;
    }

    public void soma() {
        try {

            int hi = Integer.parseInt(horaInicial.getText().toString());
            int mi = Integer.parseInt(minutoInicial.getText().toString());
            int hf = Integer.parseInt(horaFinal.getText().toString());
            int mf = Integer.parseInt(minutoFinal.getText().toString());
            int rhi = hi + hf;
            int rmi = mi + mf;
            while (rmi > 59) {
                rmi -= 60;
                rhi++;
            }
            String resultadoH = String.valueOf(rhi);
            String resultadoM = String.valueOf(rmi);

            resultadoHora.setText(resultadoH);
            resultadoMinuto.setText(resultadoM);
        }
        catch (Exception e){
            Toast.makeText(getContext(), "Algo deu errado", Toast.LENGTH_SHORT).show();
        }
    }

    public void subtracao() {
            try {

                int horaInicialInt = Integer.parseInt(horaInicial.getText().toString());
                int horaFinalInt = Integer.parseInt(horaFinal.getText().toString());
                int minutoInicialInt = Integer.parseInt(minutoInicial.getText().toString());
                int minutoFinalInt = Integer.parseInt(minutoFinal.getText().toString());

                while (horaInicialInt >= 1 && minutoInicialInt == 0) {
                    if (minutoFinalInt >= 1) {
                        minutoFinalInt = Math.abs(minutoFinalInt - 60);
                        horaInicialInt--;
                    }
                }

                while (horaFinalInt >= 1 && minutoFinalInt == 0) {
                    if (minutoInicialInt >= 1) {
                        minutoInicialInt = Math.abs(minutoInicialInt - 60);
                        horaFinalInt--;
                    }
                }

                int resultadoMinInt = Math.abs(minutoInicialInt - minutoFinalInt);
                int resultadoHoraInt = Math.abs(horaInicialInt - horaFinalInt);

                while (resultadoMinInt >= 60) {
                    resultadoMinInt -= 60;
                    resultadoHoraInt--;
                }
                if (resultadoHoraInt < 0) {
                    resultadoHoraInt = resultadoHoraInt * -1;
                }
                String resultadoH = String.valueOf(resultadoHoraInt);
                String resultadoMin = String.valueOf(resultadoMinInt);

                resultadoMinuto.setText(resultadoMin);
                resultadoHora.setText(resultadoH);

            } catch (Exception e) {
                Toast.makeText(getContext(), "Algo deu errado", Toast.LENGTH_SHORT).show();
            }
        }
    }




