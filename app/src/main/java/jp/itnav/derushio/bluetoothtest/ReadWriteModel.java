package jp.itnav.derushio.bluetoothtest;

import android.bluetooth.BluetoothSocket;
import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by derushio on 14/11/08.
 */
public class ReadWriteModel extends Thread {
	private Context context;
	private String sendMessage;
	private static InputStream inputStream;
	private static OutputStream outputStream;

	public ReadWriteModel(Context context, String sendMessage, BluetoothSocket bluetoothSocket) {
		this.context = context;
		this.sendMessage = sendMessage;

		try {
			inputStream = bluetoothSocket.getInputStream();
			outputStream = bluetoothSocket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Write(byte[] buffer) {
		try {
			outputStream.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
