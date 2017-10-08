package myhobbyproj.sensortokey;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private  BluetoothSocket mmSocket;
    private  BluetoothDevice mmDevice;

    Button left,up,right,down,space,esc,enter,map,sync;
    ToggleButton sensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        left = (Button) findViewById(R.id.but_left);
        up = (Button) findViewById(R.id.but_up);
        right = (Button) findViewById(R.id.but_right);
        down = (Button) findViewById(R.id.but_down);
        space = (Button) findViewById(R.id.but_space);
        esc = (Button) findViewById(R.id.but_esc);
        enter = (Button) findViewById(R.id.but_enter);
        map = (Button) findViewById(R.id.but_map);
        sync = (Button) findViewById(R.id.but_sync);
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("No Bluetooth Found! App will exit now.")
                    .setTitle("No Bluetooth");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    finish();
                    return;
                }
            });
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            //builder.show();
            AlertDialog dialog = builder.create();

        }
        /*if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }*/
        mBluetoothAdapter.cancelDiscovery();
        BluetoothSocket tmp = null;
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice("08:ED:B9:ED:32:ED");
        mmDevice = device;

        try {
            // Get a BluetoothSocket to connect with the given BluetoothDevice.
            // MY_UUID is the app's UUID string, also used in the server code.
            tmp = device.createInsecureRfcommSocketToServiceRecord(UUID.fromString("94f39d29-7d6d-437d-973b-fba39e49d4ee"));
        } catch (IOException e) {
            //Log.e(TAG, "Socket's create() method failed", e);
        }
        mmSocket = tmp;
        run();
        //while (true) {
            left.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            try {
                                manageMyConnectedSocket(mmSocket, "left");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
            right.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            try {
                                manageMyConnectedSocket(mmSocket, "right");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
            up.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            try {
                                manageMyConnectedSocket(mmSocket, "up");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
            down.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            try {
                                manageMyConnectedSocket(mmSocket, "down");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
            space.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            try {
                                manageMyConnectedSocket(mmSocket, "space");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
            esc.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            try {
                                manageMyConnectedSocket(mmSocket, "esc");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
            enter.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View v) {
                            try {
                                manageMyConnectedSocket(mmSocket, "enter");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
      //  }

    }

    public void run() {
        // Cancel discovery because it otherwise slows down the connection.

        try {
            // Connect to the remote device through the socket. This call blocks
            // until it succeeds or throws an exception.
            mmSocket.connect();
        } catch (IOException connectException) {
            // Unable to connect; close the socket and return.
            try {
                mmSocket.close();
            } catch (IOException closeException) {
                //Log.e(TAG, "Could not close the client socket", closeException);
            }
            return;
        }

        // The connection attempt succeeded. Perform work associated with
        // the connection in a separate thread.

        //manageMyConnectedSocket(mmSocket);
    }

    // Closes the client socket and causes the thread to finish.
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) {
           // Log.e(TAG, "Could not close the client socket", e);
        }
    }
    public void manageMyConnectedSocket(BluetoothSocket datasocket, String data) throws IOException {
        datasocket.getOutputStream().flush();
        datasocket.getOutputStream().write(data.getBytes());
        return;



    }

}
