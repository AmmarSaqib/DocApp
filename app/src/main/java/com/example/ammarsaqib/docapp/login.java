package com.example.ammarsaqib.docapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;


public class login extends Fragment {
    LoginButton loginButton;
    CallbackManager callbackManager;
    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginButton = (loginButton).findViewById(R.id.login_button);
        loginButton.setReadPermissions("email","public_profile");

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String userid = loginResult.getAccessToken().getUserId();
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        displayUserInfo(object, view);

                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "firstName, lastName, email, id");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void displayUserInfo(JSONObject object, View view){
        String firstName, lastName, email, id;
        firstName = "";
        lastName = "";
        email = "";
        id = "";

        try {
            firstName = object.getString("firstName");
            lastName= object.getString("lastName");
            email= object.getString("email");
            id= object.getString("id");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView tv_name, tv_email, tv_id;

        tv_name = view.findViewById(R.id.TV_name);
        tv_email = view.findViewById(R.id.TV_email);
        tv_id = view.findViewById(R.id.TV_id);

        tv_name.setText(firstName+ " "+lastName );
        tv_email.setText("Email: "+email);
        tv_id.setText("ID: "+id);
    }

//conflict with your onactivityresult, it asks me to make it public(check it out)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}

