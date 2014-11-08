package jp.itnav.derushio.bluetoothtest;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by derushio on 14/11/08.
 */
public class BlueToothClientThreadManager {
	public static final UUID SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

	private Context context;
	private String sendMessage = "";
	private BluetoothAdapter bluetoothAdapter;
	private BluetoothDevice blueToothDevice;
	private BluetoothSocket bluetoothSocket;

	public BlueToothClientThreadManager(Context context, BluetoothAdapter bluetoothAdapter, BluetoothDevice blueToothDevice) {
		this.context = context;
		this.bluetoothAdapter = bluetoothAdapter;
		this.blueToothDevice = blueToothDevice;

		try {
			bluetoothSocket = blueToothDevice.createRfcommSocketToServiceRecord(SPP_UUID);
		} catch (IOException e) {

		}

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

	public void setMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}

	public void run() {

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				OutputStream outputStream = null;
				try {
					outputStream = bluetoothSocket.getOutputStream();
				} catch (IOException e) {
					e.printStackTrace();
				}

				try {
					outputStream.write(sendMessage.getBytes("UTF-8"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		thread.start();
	}

	public void disconnect(){
		try {
			bluetoothSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
