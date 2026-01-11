
package com.example.android;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import androidx.core.content.ContextCompat;
import com.example.android.Core.MessageAN;
import com.example.android.Screens.person_typesForm;
import com.example.lib_communication_context.Core.FactoryCallerContext;
import com.example.lib_domain_context.FactoryCaller;
import com.example.lib_domain_context.MessagesHelper;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import com.example.lib_utilities.Utilities.Callback;

public class MainActivity extends Activity
{
    private boolean isPhone = true;
    public static Activity activity = null;
    public static Callback<byte[]> callback = null;
    private static FragmentManager fragmentManager = null;
    private static Fragment oldFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        activity = this;
        MainActivity.fragmentManager = this.getFragmentManager();
        Context context = this.getApplicationContext();
        MessagesHelper.Set(new MessageAN());
        FactoryCaller.IFactoryCaller = new FactoryCallerContext();
        com.example.lib_language.Bundle.Load("Es");

        TelephonyManager manager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if (manager.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE)
            isPhone = false;

        Permissions();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.ShowFragment(new person_typesForm());
    }

    private void Permissions()
    {
        Context context = this.getApplicationContext();
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) ==  PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==  PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET) ==  PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.WAKE_LOCK) ==  PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_NETWORK_STATE) ==  PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_WIFI_STATE) ==  PackageManager.PERMISSION_GRANTED)
            return;

    }

    public static void ShowFragment(Fragment fragment)
    {
        if (oldFragment != null)
        {
            fragmentManager.beginTransaction()
                .remove(oldFragment)
                .commit();
        }

        fragmentManager.beginTransaction()
            .add(R.id.container, fragment)
            .commit();
        oldFragment = fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData)
    {
        super.onActivityResult(requestCode, resultCode, resultData);
        try
        {
            if (resultCode != Activity.RESULT_OK)
                return;
            if (resultData == null)
                return;
            Uri uri = resultData.getData();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            InputStream inputStream = getContentResolver().openInputStream(uri);
            byte[] buf = new byte[1024];
            int n;
            while ((n = inputStream.read(buf)) != -1)
                byteArrayOutputStream.write(buf, 0, n);
            byte[] bytes = byteArrayOutputStream.toByteArray();

            if (callback == null)
                return;
            callback.Execute(bytes);
            callback = null;
        }
        catch (Exception ex)
        {
            String error = ex.toString();
            callback = null;
        }
    }
}