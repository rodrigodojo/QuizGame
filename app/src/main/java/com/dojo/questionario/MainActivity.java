package com.dojo.questionario;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public RadioGroup radioGroup;
    public RadioButton radioButton1;
    public RadioButton radioButton2;
    public RadioButton radioButton3;
    public String minhaUrl = "https://www.json-generator.com/api/json/get/cgxrCqHdpe?indent=2";
    ArrayList<Questao> questoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        questoes= new ArrayList<Questao>();

        Questao questao = new Questao("xxx", "aaa", "bbb", "ccc", 1);
        questoes.add(questao);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton1) {
                    Log.i("meuLog:", "Botao A");
                }
                if (checkedId == R.id.radioButton2) {
                    Log.i("meuLog:", "Botao B");
                }
                if (checkedId == R.id.radioButton3) {
                    Log.i("meuLog:", "Botao C");
                }
            }
        });

        new JsonTask().execute(minhaUrl);
    }

}