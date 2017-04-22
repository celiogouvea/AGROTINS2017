package com.cgtechnology.agortins2017;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static android.content.pm.PackageManager.MATCH_DEFAULT_ONLY;

public class TelaTest extends AppCompatActivity {

    private final int PERMISSAO_REQUEST = 2;
    private ImageView imagem = null;
    LinearLayout test = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_test);

        test = (LinearLayout) findViewById(R.id.testCor);
        imagem = (ImageView) findViewById(R.id.ivTest);
        BitmapDrawable drawable = (BitmapDrawable) imagem.getDrawable();
        final Bitmap bitmap = drawable.getBitmap();

        Palette.Builder builder = new Palette.Builder(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                Intent intent = new Intent();
                isAvailable(intent);
                if (vibrant != null)
                {
                    test.setBackgroundColor(vibrant.getRgb());
                }
            }
        });
    }


    public boolean isAvailable(Intent intent) {
        boolean info = getPackageManager().queryIntentActivities(intent, MATCH_DEFAULT_ONLY).size() > 0;
        Log.i("INFO", " Variabel isAvailable -> "+info );
        return getPackageManager().queryIntentActivities(intent, MATCH_DEFAULT_ONLY).size() > 0;
    }
}
