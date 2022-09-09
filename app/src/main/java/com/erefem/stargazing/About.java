package com.erefem.stargazing;

import static android.text.Html.fromHtml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Properties;

public class About extends AppCompatActivity {

    private Context context;
    private Properties properties;
    private PropertiesReader propertiesReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.about_title);
        }

        TextView tvContact =(TextView) findViewById(R.id.tvcontact);

        propertiesReader = new PropertiesReader(this);
        properties = propertiesReader.getMyProperties("Sample.txt");

        tvContact.setText(fromHtml(properties.getProperty("contactHtml")));

        tvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
                email.putExtra(Intent.EXTRA_SUBJECT, "Hallo Developers");
                email.putExtra(Intent.EXTRA_TEXT, "you will send a message to developer stargazing\n" +
                        "please write your message:" );
                email.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                email.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"app.stargazing@gmail.com"});
                startActivity(Intent.createChooser(email, "Send Mail"));

            }
        });

    }

}