/*
 * Copyright (C) 2015 Andriy Druk
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
package com.druk.bonjourbrowser.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.druk.bonjourbrowser.R;
import com.druk.bonjourbrowser.dnssd.RxDNSSD;
import com.druk.bonjourbrowser.ui.fragment.ServiceBrowserFragment;

public class RegTypeActivity extends AppCompatActivity {

    private static final String KEY_REG_TYPE = "com.druk.bonjourbrowser.ui.RegTypeActivity.KEY_DOMAIN";
    private static final String KEY_DOMAIN = "com.druk.bonjourbrowser.ui.RegTypeActivity.KEY_REG_TYPE";

    public static void startActivity(Context context, String regType, String domain){
        context.startActivity(new Intent(context, RegTypeActivity.class).
                putExtra(RegTypeActivity.KEY_DOMAIN, domain).
                putExtra(RegTypeActivity.KEY_REG_TYPE, regType));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_type);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent() != null && getIntent().hasExtra(KEY_DOMAIN) && getIntent().hasExtra(KEY_REG_TYPE)){
            String regType = getIntent().getStringExtra(KEY_REG_TYPE);
            String domain = getIntent().getStringExtra(KEY_DOMAIN);
            String description = RxDNSSD.getRegTypeDescription(regType);
            if (description != null){
                setTitle(description);
            }
            else {
                setTitle(regType);
            }
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.content, ServiceBrowserFragment.newInstance(domain, regType)).commit();
            }
        }
    }
}
