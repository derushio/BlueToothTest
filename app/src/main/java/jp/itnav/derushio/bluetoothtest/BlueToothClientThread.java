package jp.itnav.derushio.bluetoothtest;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by derushio on 14/11/08.
 */
public class BlueToothClientThread extends Thread {
	public static final UUID SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	private Context context;
	private BluetoothAdapter bluetoothAdapter;
	private BluetoothDevice blueToothDevice;
	private BluetoothSocket bluetoothSocket;

	public BlueToothClientThread(Context context, BluetoothAdapter bluetoothAdapter, BluetoothDevice blueToothDevice) {
		this.context = context;
		this.bluetoothAdapter = bluetoothAdapter;
		this.blueToothDevice = blueToothDevice;

		try {
			bluetoothSocket = blueToothDevice.createRfcommSocketToServiceRecord(SPP_UUID);
		} catch (IOException e) {

		}
	}

	@Override
	public void run() {
		if (bluetoothAdapter.isDiscovering()) {
			bluetoothAdapter.cancelDiscovery();
		}

		try {
			bluetoothSocket.connect();
		} catch (IOException e1) {
			try {
				bluetoothSocket.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			return;
		}
	}
}
