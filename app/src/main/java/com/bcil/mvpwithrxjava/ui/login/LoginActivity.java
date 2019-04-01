package com.bcil.mvpwithrxjava.ui.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bcil.mvpwithrxjava.R;
import com.bcil.mvpwithrxjava.app.AppController;
import com.bcil.mvpwithrxjava.data.DataManager;
import com.bcil.mvpwithrxjava.data.model.UserInfo;
import com.bcil.mvpwithrxjava.ui.MainActivity;
import com.bcil.mvpwithrxjava.utils.AppConstants;
import com.bcil.mvpwithrxjava.utils.CommonUtils;
import com.bcil.mvpwithrxjava.utils.NetworkUtils;
import com.bcil.mvpwithrxjava.utils.Validation;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView{

    @Bind(R.id.etUserName)
    EditText etUserName;
    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    private DataManager dataManager;
    private String getUserCode;
    private String getUserName;
    private LoginPresenterImpl presenter;
    private CompositeDisposable disposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        dataManager = ((AppController) getApplication()).getDataManager();
        if(dataManager.getLoggedInMode()){
            getUserCode = dataManager.getUserCode();
            getUserName = dataManager.getUserName();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra(AppConstants.USERCODE,getUserCode);
            intent.putExtra(AppConstants.USERNAME,getUserName);
            startActivity(intent);
            finish();
        }else {
            presenter = new LoginPresenterImpl(this);
        }
    }

    @OnClick(R.id.btLogin)
    public void onViewClicked() {
        if (checkValidation()) {
            if (NetworkUtils.isNetworkAvailable(LoginActivity.this)) {
                presenter = new LoginPresenterImpl(this,new GetLoginIntractorImpl(etUserName.getText().toString().trim(),etPassword.getText().toString().trim(),disposable));
                presenter.validateLoginFromServer();
            } else {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "No internet connection!", Snackbar.LENGTH_LONG);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackbar.show();
            }
        } else {
            Toast.makeText(this, "Please enter the required fields", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(etUserName)) ret = false;
        if (!Validation.hasText(etPassword)) ret = false;
        return ret;
    }

    @Override
    public void showProgress() {
        CommonUtils.startProgressBarDialog(LoginActivity.this,"Validating please wait...");
    }

    @Override
    public void hideProgress() {
        CommonUtils.stopProgressBarDialog();
    }

    @Override
    public void setLoginData(UserInfo userInfo) {
        if(userInfo!=null){
            if(userInfo.getMessage().equals("success")){
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra(AppConstants.USERCODE,userInfo.getUserDto().getUserCode());
                intent.putExtra(AppConstants.USERNAME,userInfo.getUserDto().getUserName());
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "Something went wrong please try again", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Something went wrong please try again", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(LoginActivity.this,
                "Something went wrong please try again" , Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null)
            presenter.onDestroy();
        disposable.dispose();
    }
}
