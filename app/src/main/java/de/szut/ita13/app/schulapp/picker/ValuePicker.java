package de.szut.ita13.app.schulapp.picker;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import de.szut.ita13.app.schulapp.R;

/**
 * Created by Rene on 19.06.2015.
 */
public class ValuePicker extends RelativeLayout implements View.OnClickListener, View.OnFocusChangeListener {

    private Button valueIncrementButton, valueDecrementButton;
    private int valueIncrementStep;
    private int valueDecrementStep;
    private int currentValue;
    private int maxValue;
    private int minValue;
    private EditText valueEditText;

    public ValuePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.value_picker_layout, this);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ValuePicker, 0, 0);
        valueIncrementStep = attributes.getInt(R.styleable.ValuePicker_value_steps_increment, 1);
        valueDecrementStep = attributes.getInt(R.styleable.ValuePicker_value_steps_decrement, -1);
        maxValue = attributes.getInt(R.styleable.ValuePicker_value_maximum, 180);
        minValue = attributes.getInt(R.styleable.ValuePicker_value_minimum, 0);
        attributes.recycle();

        currentValue = 0;

        valueEditText = (EditText)findViewById(R.id.value);
        valueEditText.setOnFocusChangeListener(this);
        valueEditText.setText(String.valueOf(currentValue));
        valueIncrementButton = (Button)findViewById(R.id.value_increment);
        valueIncrementButton.setOnClickListener(this);
        valueDecrementButton = (Button)findViewById(R.id.value_decrement);
        valueDecrementButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        currentValue = Integer.parseInt(valueEditText.getText().toString());
        switch(view.getId()) {
            case R.id.value_decrement:
                currentValue = (currentValue + valueDecrementStep >= minValue) ?
                        currentValue + valueDecrementStep : minValue;
                valueEditText.setText(String.valueOf(currentValue));
                break;
            case R.id.value_increment:
                currentValue = (currentValue + valueIncrementStep <= maxValue) ?
                        currentValue + valueIncrementStep : maxValue;
                valueEditText.setText(String.valueOf(currentValue));
                break;
        }
    }

    public int getCurrentValue() {
        return currentValue;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus) {
            currentValue = Integer.parseInt(valueEditText.getText().toString());
            currentValue = (currentValue > maxValue) ? maxValue : currentValue;
            currentValue = (currentValue < minValue) ? minValue : currentValue;
            currentValue = (currentValue % valueIncrementStep == 0) ?
                    currentValue : currentValue - (currentValue % valueIncrementStep);
            valueEditText.setText(String.valueOf(currentValue));
        }
    }
}
