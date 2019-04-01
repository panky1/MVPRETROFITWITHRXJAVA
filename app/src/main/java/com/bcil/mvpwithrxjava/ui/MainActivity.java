package com.bcil.mvpwithrxjava.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bcil.mvpwithrxjava.R;
import com.bcil.mvpwithrxjava.app.AppController;
import com.bcil.mvpwithrxjava.data.DataManager;
import com.bcil.mvpwithrxjava.ui.login.LoginActivity;
import com.bcil.mvpwithrxjava.utils.AppConstants;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tvUser)
    TextView tvUser;
    private DataManager dataManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dataManager = ((AppController) getApplication()).getDataManager();
        dataManager.setLoggedIn();
        String getUserCode = getIntent().getStringExtra(AppConstants.USERCODE);
        String getUserName = getIntent().getStringExtra(AppConstants.USERNAME);
        dataManager.setUserCode(getUserCode);
        dataManager.setUserName(getUserName);
        tvUser.setText("Welcome "+getUserName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            dataManager.clear();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
