package com.azure.cloudapp.westeurope.chrisserver2.takenplanner;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Chris van der Werf on 11/4/2017.
 */

public class LinearPicker extends LinearLayout{
    private View minusButton;
    private TextView textView;
    private View plusButton;
    private int number;
    private onChangeListener changelistener;

    public LinearPicker(Context context) {
        super(context);
        init();
    }

    public LinearPicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinearPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        number = 0;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.linearpicker, this);
        this.minusButton = findViewById(R.id.MinusButton);
        this.textView = findViewById(R.id.PickerText);
        this.plusButton = findViewById(R.id.PlusButton);

        final View.OnClickListener listener = new OnClickListener() {

            @Override
            public void onClick(View view) {
                switch(view.getId()) {
                    case R.id.MinusButton:
                        number -= 1;
                        break;
                    case R.id.PlusButton:
                        number += 1;
                        break;
                }

                if (changelistener != null) {
                    changelistener.onChange(number);
                }
                setContent();
            }
        };
        this.minusButton.setOnClickListener(listener);
        this.plusButton.setOnClickListener(listener);
        setContent();
    }

    private void setContent() {
        this.textView.setText(String.valueOf(this.number));
        this.minusButton.setEnabled(this.number > 0);
        this.plusButton.setEnabled(this.number < 10);
    }

    public void setOnChangeListener(onChangeListener listener) {
        this.changelistener = listener;
    }

    public interface onChangeListener {
        public void onChange(int number);
    }
}
