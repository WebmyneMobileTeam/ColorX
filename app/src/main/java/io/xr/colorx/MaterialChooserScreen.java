package io.xr.colorx;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MaterialChooserScreen extends AppCompatActivity {

    LinearLayout hsv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_chooser_screen);

        hsv = (LinearLayout) findViewById(R.id.hsl_categories);
        displayCategories();
    }

    private void displayCategories() {

        new GenerateMaterialColors(MaterialChooserScreen.this) {
            @Override
            public void response(MaterialColor materialColor) {

                fillScrollView(materialColor);

            }
        }.start();


    }

    private void fillScrollView(MaterialColor materialColor) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(96,96);

        for (MColor color : materialColor.materialcolors) {

            View view = new View(MaterialChooserScreen.this);
            view.setBackgroundColor(Color.parseColor(color.variations.get(6).hex));
            hsv.addView(view, params);

        }
    }
}
