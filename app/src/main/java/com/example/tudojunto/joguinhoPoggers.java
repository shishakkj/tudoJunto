package com.example.tudojunto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class joguinhoPoggers extends Fragment {
    int numeroGerado = 0;
    int tentativas = 0;
    Random gerador = new Random();
    EditText entrada;
    TextView saida, saidaT;
    Button gerar, confirmar;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public joguinhoPoggers() {
    }

    public static joguinhoPoggers newInstance(String param1, String param2) {
        joguinhoPoggers fragment = new joguinhoPoggers();
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
        View v = inflater.inflate(R.layout.fragment_joguinho_poggers, container, false);
        entrada = v.findViewById(R.id.adivinhe);
        saida = v.findViewById(R.id.resultado);
        saidaT = v.findViewById(R.id.saidaTentativas);
        confirmar = v.findViewById(R.id.confirma);
        gerar = v.findViewById(R.id.gera);
        gerar.setOnClickListener(sla -> gerarNumero());
        confirmar.setOnClickListener(sla -> confirmar());
        return v;
    }
    public void gerarNumero (){
        tentativas = 0;
        numeroGerado = gerador.nextInt(100)+1;
        saida.setText("Novo número gerado");
        saidaT.setText("");
    }
    public void confirmar (){
        try{
            tentativas++;
            int digito = Integer.parseInt(entrada.getText().toString());
            if (digito > numeroGerado){
                saida.setText("O número é menor!");
                saidaT.setText("Você tem " + (5 - tentativas)+ " tentativas.");
            }
            else if (digito < numeroGerado){
                saida.setText("O número é maior!");
                saidaT.setText("Você tem " +(5 - tentativas)+ " tentativas.");
            }
            else{
                saida.setText("Parabéns, você acertou!");
                saidaT.setText("Você acertou o número em " +tentativas+ " tentativas.");
            }
            if (tentativas >= 5){
                saida.setText("Você gastou todas as suas tentativas! O número gerado era " +numeroGerado+ ". Gere um novo número!");
                saidaT.setText("");
            }
            if (numeroGerado == 0 && entrada == null){
                saida.setText("Por favor gere um número!");
            }
        }
        catch (Exception e){
            saida.setText("Por favor insira um valor válido!");
        }

    }
}