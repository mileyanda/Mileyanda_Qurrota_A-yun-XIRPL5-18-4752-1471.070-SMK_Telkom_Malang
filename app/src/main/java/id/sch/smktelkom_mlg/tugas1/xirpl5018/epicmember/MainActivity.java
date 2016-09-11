package id.sch.smktelkom_mlg.tugas1.xirpl5018.epicmember;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    EditText etNama, etTahun;
    Spinner spProvinsi, spKota;
    RadioGroup rgJK;
    CheckBox cbDesain, cbCoding, cbWirausaha;
    Button bDaftar;
    TextView tvHasil1, tvHasil2, tvHasil3, tvHasil4, tvHobi;
    int nHobi;

    String[][] arKota =
            {
                    {"Bandung", "Bekasi", "Bogor", "Cirebon", "Garut", "Indramayu", "Karawang", "Purwakarta", "Sumedang", "Tasikmalaya"},
                    {"Jakarta Pusat", "Jakarta Utara", "Jakarta Timur", "Jakarta Barat", "Jakarta Selatan"},
                    {"Banyumas", "Cilacap", "Demak", "Jepara", "Kudus", "Magelang", "Pekalongan", "Rembang", "Semarang", "Tegal"},
                    {"Bantul", "Gunung Kidul", "Kulon Progo", "Sleman", "Yogyakarta"},
                    {"Banyuwangi", "Gresik", "Jember", "Kediri", "Lamongan", "Malang", "Ngawi", "Pasuruan", "Surabaya", "Tuban"}
            };
    ArrayList<String> listKota = new ArrayList<>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etNama = (EditText) findViewById(R.id.Nama);
        etTahun = (EditText) findViewById(R.id.Tahun);
        spProvinsi = (Spinner) findViewById(R.id.spinnerProvinsi);
        spKota = (Spinner) findViewById(R.id.spinnerKota);
        rgJK = (RadioGroup) findViewById(R.id.radioGroupJK);
        cbDesain = (CheckBox) findViewById(R.id.checkBoxDesain);
        cbCoding = (CheckBox) findViewById(R.id.checkBoxCoding);
        cbWirausaha = (CheckBox) findViewById(R.id.checkBoxWirausaha);
        bDaftar = (Button) findViewById(R.id.buttonDaftar);

        cbDesain.setOnCheckedChangeListener(this);
        cbCoding.setOnCheckedChangeListener(this);
        cbWirausaha.setOnCheckedChangeListener(this);

        tvHasil1 = (TextView) findViewById(R.id.textViewHasil1);
        tvHasil2 = (TextView) findViewById(R.id.textViewHasil2);
        tvHasil3 = (TextView) findViewById(R.id.textViewHasil3);
        tvHasil4 = (TextView) findViewById(R.id.textViewHasil4);
        tvHobi = (TextView) findViewById(R.id.textViewHobi);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listKota);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKota.setAdapter(adapter);

        spProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                listKota.clear();
                listKota.addAll(Arrays.asList(arKota[pos]));
                adapter.notifyDataSetChanged();
                spKota.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        findViewById(R.id.buttonDaftar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doClick();
            }
        });

    }

    private void doClick() {
        String nama = etNama.getText().toString();
        String tahun = etTahun.getText().toString();
        String hasil2 = "Hobi anda          : ";
        String hasil3 = null;
        int startlen = hasil2.length();

        if (nama.isEmpty()) {
            etNama.setError("Nama belum diisi");
        } else if (nama.length() <= 3) {
            etNama.setError("Nama minimal 3 karakter");
        } else {
            etNama.setError(null);
        }

        if (tahun.isEmpty()) {
            etTahun.setError("Tanggal Lahir belum diiisi");
        } else if (tahun.length() != 10) {
            etTahun.setError("Format Tanggal Lahir Salah");
        } else {
            etTahun.setError(null);
        }
        tvHasil1.setText("Nama                      : " + nama + "\nTanggal Lahir         : " + tahun);
        tvHasil2.setText("Asal                         : " + "Kota " + spKota.getSelectedItem().toString() + ", "
                + spProvinsi.getSelectedItem().toString());
        if (rgJK.getCheckedRadioButtonId() != -1) {
            RadioButton rb = (RadioButton)
                    findViewById(rgJK.getCheckedRadioButtonId());
            hasil3 = rb.getText().toString();
        }
        if (hasil3 == null) {
            tvHasil3.setText("Jenis Kelamin        : -");
        } else {
            tvHasil3.setText("Jenis Kelamin        : " + hasil3);
        }

        if (cbDesain.isChecked()) hasil2 += cbDesain.getText() + ", ";
        if (cbCoding.isChecked()) hasil2 += cbCoding.getText() + ", ";
        if (cbWirausaha.isChecked()) hasil2 += cbWirausaha.getText() + ". ";

        if (hasil2.length() == startlen) hasil2 += "Tidak ada pada Pilihan";
        tvHasil4.setText(hasil2);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) nHobi += 1;
        else nHobi -= 1;

        tvHobi.setText("Hobi");
    }
}