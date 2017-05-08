package com.br.exploreapisensors.utils.sensors;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.security.Permission;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import android.os.Handler;

/**
 * Created by r028367 on 26/01/2017.
 */

public class Device {

    public static final int REQUEST_PERMISSION = 0;
    private static Context context;
    private static String id;

    public static String genUUID() {
        UUID uuid = UUID.randomUUID();
        String res = uuid.toString();
        Log.i("UUID_", String.format("%d\n%s\n%d", uuid.version(), uuid.toString(), uuid.variant()));
        return res;
    }

    public static Calendar longToCalendar(long time) {
        Calendar calendar = Calendar.getInstance();
        // calendar.setTime(new Date(time));
        calendar.setTimeInMillis(time);
        return calendar;
    }

    private static void alertPermission(Context context, final String title, final String message, final boolean cancelable
            , final DialogInterface.OnClickListener posListener, final DialogInterface.OnClickListener negListener) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.setTitle(title)
                        .setMessage(message)
                        .setCancelable(cancelable)
                        .setPositiveButton("Sim", posListener)
                        .setNegativeButton("Não", negListener)
                        .create()
                        .show();
            }
        });
    }

    public static void getPermissions(final Context context, final String [] permissions, String title, String message, boolean cancelable) {
        DialogInterface.OnClickListener pos = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Activity activity = (Activity) context;
                ActivityCompat.requestPermissions(activity, permissions, REQUEST_PERMISSION);
                dialog.dismiss();
            }
        };

        DialogInterface.OnClickListener neg = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
        alertPermission(context, title, message, cancelable, pos, neg);
    }

    public static void getPermission(final Context context, final String permission, String title, String message, boolean cancelable) {
        DialogInterface.OnClickListener pos = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String [] perms = {permission};
                Activity activity = (Activity) context;
                ActivityCompat.requestPermissions(activity, perms, REQUEST_PERMISSION);
                dialog.dismiss();
            }
        };

        DialogInterface.OnClickListener neg = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
        alertPermission(context, title, message, cancelable, pos, neg);
    }


    public static String getId(Context context) {
        String id = "";
        TelephonyManager manager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        id = manager.getDeviceId();
        return id;
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = manager.getActiveNetworkInfo();

        if(netInfo != null) {
            String netExtraInfo = netInfo.getExtraInfo();
            if(netExtraInfo != null) {
                Log.i("NETWORK_EXTRA_INFO", netExtraInfo);
            }

            String reason = netInfo.getReason();
            if(reason != null) {
                Log.i("NETWORK_REASON", reason);
            }
        }

        ArrayList<Integer> connType = new ArrayList<>();
        connType.add(ConnectivityManager.TYPE_BLUETOOTH);
        connType.add(ConnectivityManager.TYPE_ETHERNET);
        connType.add(ConnectivityManager.TYPE_MOBILE);
        connType.add(ConnectivityManager.TYPE_WIFI);
        connType.add(ConnectivityManager.TYPE_VPN);

        for(Integer type : connType) {
            NetworkInfo networkInfo = manager.getNetworkInfo(type);
            if(netInfo != null) {
                String extraInfo = netInfo.getExtraInfo();
                if(extraInfo != null) {
                    Log.i("NETWORK_EXTRA_INFO", extraInfo);
                }
            }
        }

        NetworkInfo [] networkInfos = manager.getAllNetworkInfo();
        for(NetworkInfo net : networkInfos) {
            if(net != null) {
                String info = net.getExtraInfo();
                if(info != null)
                    Log.i("NETWORK_EXTRA_INFO", info);

                String reason = net.getReason();
                if(reason != null) {
                    Log.i("NETWORK_REASON", reason);
                }

                String typeName = net.getTypeName();
                if(typeName != null) {
                    Log.i("TYPE_NAME", typeName);
                }

                NetworkInfo.DetailedState detailedState = net.getDetailedState();
                if(detailedState != null) {
                    Log.i("DETAILS_STATE", detailedState.name());
                }
                /**
                 * Indicates whether network connectivity exists or is in the process
                 * of being established. This is good for applications that need to
                 * do anything related to the network other than read or write data.
                 * For the latter, call {@link #isConnected()} instead, which guarantees
                 * that the network is fully usable.
                 * @return {@code true} if network connectivity exists or is in the process
                 * of being established, {@code false} otherwise.
                 */
                boolean isConnectedOrConnecting = net.isConnectedOrConnecting();
                Log.i("isconnectedorconnecting", String.valueOf(isConnectedOrConnecting));

                /**
                 * Indicates whether network connectivity exists and it is possible to establish
                 * connections and pass data.
                 * <p>Always call this before attempting to perform data transactions.
                 * @return {@code true} if network connectivity exists, {@code false} otherwise.
                 */
                boolean isConnected = net.isConnected();
                Log.i("isConnected", String.valueOf(isConnected));
                if(isConnected /*&& i sConnectedOrConnecting*/) {
                    if(reason != null) {
                        //StateConnection stateConnection = new StateConnection(reason, isConnected);
                    }
                    return true;
                }
            }
        }
        return false;
    }


    public static WifiManager getWifiManager(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wifiManager;
    }

    public static boolean isWifiEnabled(Context context) {
        WifiManager wm = getWifiManager(context);
        if(wm != null)
            return wm.isWifiEnabled();
        return false;
    }
    
    private static void  requestPermissions(Context context) {
        List<String> permissionsDenied = new ArrayList<>();
        String permissions [] = {
                Manifest.permission.ACCESS_FINE_LOCATION
                ,Manifest.permission.ACCESS_COARSE_LOCATION
                ,Manifest.permission.ACCESS_WIFI_STATE
                ,Manifest.permission.CAMERA
                ,Manifest.permission.INTERNET
                ,Manifest.permission.ACCESS_WIFI_STATE
                ,Manifest.permission.ACCESS_NETWORK_STATE
                ,Manifest.permission.READ_PHONE_STATE
        };
        
        for (String permission : permissions) {
            boolean denied = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED;
            if(denied) {
                permissionsDenied.add(permission);
            }
            
            boolean shouldRequest = ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission);
            Log.i("SHOULD_REQUEST", String.format("%s %s", permission, String.valueOf(shouldRequest)));
        }
        
        String arrayPermissions [] = new String[permissionsDenied.size()];
        permissionsDenied.toArray(arrayPermissions);
        ActivityCompat.requestPermissions((Activity) context, arrayPermissions, 1);
    }
    
    public static void toggleStateWifi(Context context) {
        WifiManager wm = getWifiManager(context);
        if(wm != null) {
            
            if(ActivityCompat.checkSelfPermission(context, Manifest.permission.CHANGE_WIFI_STATE) == PackageManager.PERMISSION_GRANTED) {
                wm.setWifiEnabled( ! wm.isWifiEnabled() );
            }
            else {
                ActivityCompat.requestPermissions((Activity) context, new String[] {Manifest.permission.CHANGE_WIFI_STATE}, 1);
            }
        }
    }

    public static void setStateWifi(Context context, boolean state) {
        WifiManager wm = getWifiManager(context);
        if(wm != null) {
            wm.setWifiEnabled(state);
        }
    }

    public static int getStateWifi(Context context) {
        WifiManager wm = getWifiManager(context);
        if(wm != null) {
    
            if(ActivityCompat.checkSelfPermission(context, Manifest.permission.CHANGE_WIFI_STATE) == PackageManager.PERMISSION_GRANTED) {
                wm.getWifiState();
            }
            else {
                ActivityCompat.requestPermissions((Activity) context, new String[] {Manifest.permission.CHANGE_WIFI_STATE}, 1);
            }
           
        }
        return -1;
    }


    /**
     * https://developer.android.com/reference/android/provider/Settings.Secure.html#ANDROID_ID
     *
     * */

    public static void getSecurityID(Context context) {
        String id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.i("SECURITY_ID", id);
    }

    public static TelephonyManager get(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        SubscriptionManager sm= (SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);

        List<SubscriptionInfo> subsInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            subsInfo = sm.getActiveSubscriptionInfoList();
            if(subsInfo != null && subsInfo.size() > 0) {
                for(SubscriptionInfo si : subsInfo) {
                    String number = si.getNumber();
                }
            }
        }

        TelecomManager selecomManager = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);

        return tm;
    }

    /*
    * https://developer.android.com/guide/topics/sensors/sensors_overview.html
    * https://www.oficinadanet.com.br/post/14334-quais-os-sensores-presentes-no-seu-smartphone
    * */
    public static class Sensors {

        public static List<Sensor> get(Context context) {
            SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
            return sensors;
        }


        @TargetApi(Build.VERSION_CODES.N)
        public static List<Sensor> getDyn(Context context) {
            SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            List<Sensor> sensors = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                sensors = sensorManager.getDynamicSensorList(Sensor.TYPE_ALL);
            }
            return sensors;
        }
        public void print(Context context) {
            List<Sensor> sensors = get(context);
            if(sensors != null && sensors.size() > 0) {
                for(Sensor sensor : sensors) {
                    Log.i("SENSOR", String.format("%s", sensor.getName()));
                }
            }
        }
    
        
    }

    static class StateConnection {
        String type;
        Boolean status;

        StateConnection(String type, Boolean status) {
            this.type = type;
            this.status = status;
        }
    }
}
