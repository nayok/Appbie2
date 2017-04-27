package cph.nayok.max.appbie;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class ServiceActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView;
    private ListView listView;
    private String[] loginStrings, nameStrings,
            dateStrings, detailStrings, qrCodeStrings;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        initiaView();

        getValueFromIntent();

        createListView();



    } //Main Class

    private void createListView() {
        String tag = "27AprilV2";
        String urlPHP = "http://swiftcodingthai.com/cph/getProduct.php";

        try {

            GetData getData = new GetData(ServiceActivity.this);
            getData.execute(urlPHP);
            String strJSON = getData.get();
            Log.d(tag, "JSON ==>" + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            nameStrings = new String[jsonArray.length()];
            dateStrings = new String[jsonArray.length()];
            detailStrings = new String[jsonArray.length()];
            qrCodeStrings = new String[jsonArray.length()];


            for (int i=0;i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStrings[i] = jsonObject.getString("Name");
                dateStrings[i] = jsonObject.getString("Date");
                detailStrings[i] = jsonObject.getString("Detail");
                qrCodeStrings[i] = jsonObject.getString("QR_code");
            }

            MyAdapter myAdapter = new MyAdapter(ServiceActivity.this, nameStrings,
                    dateStrings, detailStrings);
            listView.setAdapter(myAdapter);






        } catch (Exception e) {
            Log.d(tag, "e createListView ==>" + e.toString());
        }
    }

    private void getValueFromIntent() {
        loginStrings = getIntent().getStringArrayExtra("Login");
        textView.setText(loginStrings[1]);
    }

    private void initiaView() {
        textView = (TextView) findViewById(R.id.txtName);
        imageView = (ImageView) findViewById(R.id.imvQR);
        listView = (ListView) findViewById(R.id.livProduct);

    }
}  //Main Class
