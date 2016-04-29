package io.xr.colorx;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dhruvil on 29-04-2016.
 */
public abstract class GenerateMaterialColors implements IColors {

    public abstract void response(MaterialColor materialColor);

    private Context context;

    public GenerateMaterialColors(Context context) {
        this.context = context;
    }

    public synchronized final GenerateMaterialColors start() {

        new FetchColors().execute();

        return this;
    }


    private class FetchColors extends AsyncTask<Void, Void, Void> {
        private String response;

        @Override
        protected Void doInBackground(Void... params) {

            response = loadJSONFromAsset();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            MaterialColor colors = new Gson().fromJson(response, MaterialColor.class);
            response(colors);

        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("mc.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
