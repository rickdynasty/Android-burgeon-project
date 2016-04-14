/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tencent.tws.core.apidemos.view;

import android.app.TwsActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tencent.tws.core.widget.NumberPicker;
import com.tencent.tws.core.widget.NumberPicker.OnValueChangeListener;
import com.tencent.tws.burgeon.R;

public class NumberPickerView extends TwsActivity {

    // where we display the selected date and time
    private TextView mNumberDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.numberpicker_number_example);

        mNumberDisplay = (TextView) findViewById(R.id.numberDisplay);

        NumberPicker picker = (NumberPicker) findViewById(R.id.numberPicker);
        picker.setMinValue(0);
        picker.setMaxValue(20);
        picker.setValue(8);
        picker.setWrapSelectorWheel(false);
        picker.setOnValueChangedListener(new OnValueChangeListener() {
            
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                updateDisplay(newValue);
            }
        });
    }

    private void updateDisplay(int number) {
        mNumberDisplay.setText("Number=" + number);
    }

}
