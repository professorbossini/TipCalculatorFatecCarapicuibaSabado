package br.com.bossini.tipcalculatorfateccarapicuibasabado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;
public class MainActivity extends AppCompatActivity {

    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private TextView amountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    private String zeroFormatado = currencyFormat.format(0);
    private double billAmount = 0;
    private double tipPercentage = 0.15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountTextView = (TextView) findViewById(R.id.amountTextView);
        percentTextView = findViewById(R.id.percentTextView);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);
        amountTextView.setText(zeroFormatado);
        tipTextView.setText(zeroFormatado);
        totalTextView.setText(zeroFormatado);
        SeekBar seekBar = findViewById(R.id.percentSeekBar);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener(){
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            tipPercentage = progress / 100.0;
                            String valorFormatado = percentFormat.format(tipPercentage);
                            percentTextView.setText(valorFormatado);
                            double tip = tipPercentage * billAmount;
                            double total = tip + billAmount;
                            tipTextView.setText(currencyFormat.format(tip));
                            totalTextView.setText(currencyFormat.format(total));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
        EditText amountEditText = findViewById(R.id.amountEditText);
        ObservadorDoEditText observadorDoEditText =
                new ObservadorDoEditText();
        amountEditText.addTextChangedListener(observadorDoEditText);
    }


    private class ObservadorDoEditText implements TextWatcher{


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try{
               billAmount = Double.parseDouble(s.toString()) / 100;
               amountTextView.setText(currencyFormat.format(billAmount));
               double tip = tipPercentage * billAmount;
               double total = billAmount + tip;
               tipTextView.setText(currencyFormat.format(tip));
               totalTextView.setText(currencyFormat.format(total));
            }
            catch (NumberFormatException x){
                amountTextView.setText(zeroFormatado);
                tipTextView.setText(zeroFormatado);
                totalTextView.setText(zeroFormatado);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
