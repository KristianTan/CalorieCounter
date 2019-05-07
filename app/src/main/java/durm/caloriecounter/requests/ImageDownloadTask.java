package durm.caloriecounter.requests;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import durm.caloriecounter.R;
import durm.caloriecounter.activities.MainActivity;

public class ImageDownloadTask extends AsyncTask<String, Integer, Bitmap> {
    public ImageView imageView;
    public AppCompatActivity activity;

    @Override
    protected Bitmap doInBackground(String... url) {
        String input = url[0];

        try {
            URL someUrl = new URL(input);
            URLConnection conn = someUrl.openConnection();
            InputStream is = conn.getInputStream();
            BufferedInputStream stream = new BufferedInputStream(is);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int intInput = stream.read();
            int ticker = 0;

            while(intInput != -1) {
                ticker ++;
                if(ticker % 100 == 0) {
                    publishProgress(baos.size());
                }

                baos.write(intInput);
                intInput = stream.read();
            }

            byte[] imageBytes = baos.toByteArray();
            Bitmap bmp = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            return bmp;


        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onProgressUpdate(Integer... progress){
        //
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
        Animation anim2 = AnimationUtils.loadAnimation(activity, R.anim.fade_in);
        imageView.startAnimation(anim2);

    }
}
