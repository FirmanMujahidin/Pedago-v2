package com.pedago2;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
public class MyApps extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.pedago2", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}

/*
http://p.web.umkc.edu/pv6xc/Tutorials.html
Setting up java on ubuntu


    1.Download the required tarball from here

    2.unzip this tarball using "tar -zxvf tarball_name

    3.create a folder name java in /usr/lib, you need root permission

    4.mv the extracted folder to /usr/lib/java/

    5.next run these below scripts in terminal
    sudo update-alternatives --install "/usr/bin/java" "java" "/usr/lib/java/jdk1.7.0_65/bin/java" 1
    sudo update-alternatives --install "/usr/bin/javac" "javac" "/usr/lib/java/jdk1.7.0_65/bin/javac" 1
    sudo update-alternatives --install "/usr/bin/javaws" "javaws" "/usr/lib/java/jdk1.7.0_65/bin/javaws" 1

    6.update the JAVA_HOME in your ~/.bashrc
    export JAVA_HOME=/usr/lib/java/jdk1.7.0_65
    set PATH="$PATH:$JAVA_HOME/bin"
    export PATH
    Your bashrc should look like bashrc

    p.web.umkc.edu/pv6xc/bashrc.txt
    #JAVA HOME directory setup
    export JAVA_HOME=/usr/lib/java/jdk1.7.0_65
    export PATH="$PATH:$JAVA_HOME/bin"
*/