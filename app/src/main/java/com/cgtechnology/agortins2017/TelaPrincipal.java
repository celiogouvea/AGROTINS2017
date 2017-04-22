package com.cgtechnology.agortins2017;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TelaPrincipal extends AppCompatActivity {

    //componente de imagemView
    private ImageView imagem = null;
    //componete de button
    ImageButton selecionar = null;
    ImageButton foto = null;
    ImageButton simNao = null;
    RelativeLayout telaPrincipal = null;

    //int usado
    private final  int GALERIA_IMAGEM = 1;
    private final int PERMISSAO_REQUEST = 2;
    private final int TIRA_FOTO = 3;
    private final int CAMERA = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        telaPrincipal = (RelativeLayout) findViewById(R.id.rlayoutPrincipal);
        imagem = (ImageView) findViewById(R.id.ivImagem);
        selecionar = (ImageButton) findViewById(R.id.btSelecionar);
        foto = (ImageButton) findViewById(R.id.btCapturar);

        //chamar metodo de preenchimento de espaçoes em branco com a cor predominante da imagem
        espacosBrancos();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSAO_REQUEST);
            }
        }

    }

    public void espacosBrancos()
    {
        BitmapDrawable drawable = (BitmapDrawable) imagem.getDrawable();
        final Bitmap bitmap = drawable.getBitmap();

        Palette.Builder builder = new Palette.Builder(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getLightMutedSwatch();
                if (vibrant != null)
                {
                    telaPrincipal.setBackgroundColor(vibrant.getRgb());
                }
            }
        });

    }


    public void selecionarImagem(View view)
    {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void editarImagem(View view){
        //Intent intent = new Intent(this, TelaTest.class);
       // startActivity(intent);



        final AlertDialog.Builder editar = new AlertDialog.Builder(TelaPrincipal.this);
        final View mView = getLayoutInflater().inflate(R.layout.activity_caixa_selecao, null);
        final Button salvar = (Button) mView.findViewById(R.id.btSalvar);

        final RadioGroup radio = (RadioGroup)mView.findViewById(R.id.radioGroup);
        final RadioButton cor01 = (RadioButton)mView.findViewById(R.id.rd01);
        final RadioButton cor02 = (RadioButton)mView.findViewById(R.id.rd02);
        final RadioButton cor03 = (RadioButton)mView.findViewById(R.id.rd03);
        final RadioButton cor04 = (RadioButton)mView.findViewById(R.id.rd04);
        final RadioButton cor05 = (RadioButton)mView.findViewById(R.id.rd05);
        final RadioButton cor06 = (RadioButton)mView.findViewById(R.id.rd06);

        //adicionar cor aos campos
        BitmapDrawable drawable = (BitmapDrawable) imagem.getDrawable();
        final Bitmap bitmap = drawable.getBitmap();

        Palette.Builder builder = new Palette.Builder(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch c01 = palette.getLightMutedSwatch();
                Palette.Swatch c02 = palette.getDarkMutedSwatch();
                Palette.Swatch c03 = palette.getDarkVibrantSwatch();
                Palette.Swatch c04 = palette.getVibrantSwatch();
                Palette.Swatch c05 = palette.getLightVibrantSwatch();
                Palette.Swatch c06 = palette.getMutedSwatch();
                if (c01 != null)
                {
                    cor01.setBackgroundColor(c01.getRgb());
                }
                if(c02 != null)
                {
                    cor02.setBackgroundColor(c02.getRgb());
                }
                if(c03 != null)
                {
                    cor03.setBackgroundColor(c03.getRgb());
                }
                if(c04 != null)
                {
                    cor04.setBackgroundColor(c04.getRgb());
                }
                if(c05 != null)
                {
                    cor05.setBackgroundColor(c05.getRgb());
                }
                if(c06 != null)
                {
                    cor06.setBackgroundColor(c06.getRgb());
                }
            }
        });










        salvar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                final int id = radio.getCheckedRadioButtonId();
                if(id == R.id.rd01 )
                {
                    Toast.makeText(TelaPrincipal.this, "Selecionado cor 01", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (id == R.id.rd02)
                {
                    Toast.makeText(TelaPrincipal.this, "Selecionado cor 02", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (id == R.id.rd03)
                {
                    Toast.makeText(TelaPrincipal.this, "Selecionado cor 03", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (id == R.id.rd04)
                {
                    Toast.makeText(TelaPrincipal.this, "Selecionado cor 04", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (id == R.id.rd05)
                {
                    Toast.makeText(TelaPrincipal.this, "Selecionado cor 05", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    Toast.makeText(TelaPrincipal.this, "Selecionado cor 06", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        editar.setView(mView);
        AlertDialog alert = editar.create();
        alert.show();
    }









    public void tirarFoto(View view)
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            try {
                arquivoFoto = criarArquivo();
            } catch (IOException ex) {
                // Manipulação em caso de falha de criação do arquivo
            }
            if (arquivoFoto != null) {
                Uri photoURI = FileProvider.getUriForFile(getBaseContext(),
                        getBaseContext().getApplicationContext().getPackageName() +
                                ".provider", arquivoFoto);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA);
            }
        }
    }




    private File arquivoFoto = null;
    private File criarArquivo() throws IOException {
        String timeStamp = new
                SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File pasta = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File imagem = new File(pasta.getPath() + File.separator
                + "JPG_" + timeStamp + ".jpg");
        return imagem;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == GALERIA_IMAGEM) {
            Uri selectedImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
            imagem.setImageBitmap(thumbnail);
            espacosBrancos();

        }
        if (requestCode == TIRA_FOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagem.setImageBitmap(imageBitmap);
        }
        if (requestCode == CAMERA && resultCode == RESULT_OK) {
            sendBroadcast(new Intent(
                    Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.fromFile(arquivoFoto))
            );
            exibirImagem();
        }
    }
    public void onStart(){
        super.onStart();
    }
    public void onStop(){
        super.onStop();
    }
    public void onResume(){
        super.onResume();
    }
    public void onPause(){
        super.onPause();
    }
    public void onDestroy(){
        super.onDestroy();
    }




    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        try {
            if (requestCode == PERMISSAO_REQUEST) {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // A permissão foi concedida. Pode continuar
                } else {// A permissão foi negada. Precisa ver o que deve ser desabilitado
                }
                return;
            }
        }catch (Exception e)
        {
            Log.i("INFO", "Ecessão = "+e);
        }
    }



    private void exibirImagem() {
        int targetW = imagem.getWidth();
        int targetH = imagem.getHeight();
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(arquivoFoto.getAbsolutePath(), bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        Bitmap bitmap =
                BitmapFactory.decodeFile(arquivoFoto.getAbsolutePath(), bmOptions);
        imagem.setImageBitmap(bitmap);
    }



}
