package jp.itnav.derushio.bluetoothtest;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Set;


public class SendOnlyActivity extends Activity {

	public static final String BLUETOOTH_MAC_ADDRESS = "btAddress";

	private static String btAddress;
	private static BluetoothDevice bluetoothDevice;
	private static BluetoothAdapter bluetoothAdapter;

	private BlueToothClientThreadManager blueToothClientThreadManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_only);

		btAddress = getIntent().getStringExtra(BLUETOOTH_MAC_ADDRESS);
		Log.d("btAddress", btAddress);

		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		Set<BluetoothDevice> paredDevices = bluetoothAdapter.getBondedDevices();

		if (paredDevices.size() > 0) {
			for (BluetoothDevice paredDevice : paredDevices) {
				if (paredDevice.getAddress().equals(btAddress)) {
					bluetoothDevice = paredDevice;
					break;
				}
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		blueToothClientThreadManager = new BlueToothClientThreadManager(this, bluetoothAdapter, bluetoothDevice);
	}

	@Override
	protected void onStop() {
		super.onStop();
		blueToothClientThreadManager.disconnect();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_send_only, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void mouseMoveUp(View v) {
		blueToothClientThreadManager.setMessage("x0y-500s0m0e");
		blueToothClientThreadManager.run();
	}

	public void mouseMoveLeft(View v) {
		blueToothClientThreadManager.setMessage("x-500y0s0m0e");
		blueToothClientThreadManager.run();
	}

	public void mouseMoveRight(View v) {
		blueToothClientThreadManager.setMessage("x500y0s0m0e");
		blueToothClientThreadManager.run();
	}

	public void mouseMoveDown(View v) {
		blueToothClientThreadManager.setMessage("x0y500s0m0e");
		blueToothClientThreadManager.run();
	}

	public void mouseActionClick(View v) {
		blueToothClientThreadManager.setMessage("x0y0s0m1e");
		blueToothClientThreadManager.run();
	}

}
