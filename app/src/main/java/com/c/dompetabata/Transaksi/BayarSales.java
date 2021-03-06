package com.c.dompetabata.Transaksi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.c.dompetabata.R;
import com.c.dompetabata.sharePreference.Preference;

public class BayarSales extends AppCompatActivity {
    TextView saldokusales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar_sales);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#4AB84E'><b>Pembayaran Saldoku <b></font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        saldokusales = findViewById(R.id.saldokusales);
        saldokusales.setText(Preference.getSaldoku(getApplicationContext()));

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}