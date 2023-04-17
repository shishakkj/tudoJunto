package com.example.tudojunto;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class camera extends Fragment {
    ImageView foto;
    Button fotografar, armazenamento;

   private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public camera() {

    }

    public static camera newInstance(String param1, String param2) {
        camera fragment = new camera();
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
        View v = inflater.inflate(R.layout.fragment_camera, container, false);
        foto = v.findViewById(R.id.camera);
        fotografar = v.findViewById(R.id.tiraFoto);
        armazenamento = v.findViewById(R.id.armazenamento);
        fotografar.setOnClickListener(sla -> tiraFoto());
        armazenamento.setOnClickListener(sla -> pegaFoto());
        return v;

    }
    public void tiraFoto(){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        abrirCamera.launch(i);
    }
    public void pegaFoto(){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        abrirGaleria.launch(i);
    }
    ActivityResultLauncher<Intent> abrirCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{
        if(result.getResultCode() == Activity.RESULT_OK){
            Intent data = result.getData();
            Bundle dado = data.getExtras();
            Bitmap imagem = (Bitmap) dado.get("data");
            foto.setImageBitmap(imagem);
        }
    });

    ActivityResultLauncher<Intent> abrirGaleria = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{
        if(result.getResultCode() == Activity.RESULT_OK){
            Intent data = result.getData();
            Uri ImagemSelecionada = data.getData();
            String[] caminho = {MediaStore.Images.Media.DATA};
            Cursor c = getActivity().getContentResolver().query(ImagemSelecionada, caminho, null, null, null);
            c.moveToFirst();
            int coluna = c.getColumnIndex(caminho[0]);
            String caminhoFisico = c.getString(coluna);
            c.close();
            Bitmap imagem = (BitmapFactory.decodeFile(caminhoFisico));
            foto.setImageBitmap(imagem);
        }
    });

}