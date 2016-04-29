package io.xr.colorx;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by dhruvil on 29-04-2016.
 */
public class MaterialColorPicker extends Dialog {
    LinearLayout hsv;
    LinearLayout hsvColors;
    Context context;
    MaterialColor mColorPallete;

    public MaterialColorPicker(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public MaterialColorPicker(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        init();
    }

    protected MaterialColorPicker(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
        init();
    }

    private void init() {
        setContentView(R.layout.activity_material_chooser_screen);
        hsv = (LinearLayout) this.findViewById(R.id.hsl_categories);
        hsvColors = (LinearLayout) this.findViewById(R.id.hsl_colors);
        displayCategories();
    }

    private void displayCategories() {

        new GenerateMaterialColors(context) {
            @Override
            public void response(MaterialColor materialColor) {
                mColorPallete = materialColor;
                fillScrollView(materialColor);
            }
        }.start();


    }

    private void fillScrollView(MaterialColor materialColor) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(96, 96);
        params.setMargins(4,4,4,4);

        for (MColor color : materialColor.materialcolors) {
            View view = new View(context);
            view.setBackgroundColor(Color.parseColor(color.variations.get(6).hex));
            view.setOnClickListener(categoryClick);
            hsv.addView(view, params);
        }

        processSubCategoryDisplay(0);
    }

    private View.OnClickListener categoryClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            processSubCategoryDisplay(hsv.indexOfChild(v));
        }
    };

    private void processSubCategoryDisplay(int position) {

        MColor color = mColorPallete.materialcolors.get(position);
        displayVariations(color);

    }

    private void displayVariations(MColor color) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 96);
        hsvColors.removeAllViews();

        for (MVariation variation : color.variations) {
            View view = new View(context);
            view.setBackgroundColor(Color.parseColor(variation.hex));
            hsvColors.addView(view, params);
        }

    }

}
