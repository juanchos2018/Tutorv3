package com.example.tutorv3.ClasesAdmin;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class CaptureToken {

  String token;

    public   String getToken(){

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {

                if (!task.isSuccessful()){
                    Log.v("main","getintance",task.getException());
                    return;
                }

                token=task.getResult().getToken();

            }
        });
        return token;
    }
}
