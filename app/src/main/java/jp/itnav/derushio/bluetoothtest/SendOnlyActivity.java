package jp.itnav.derushio.bluetoothtest;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import jp.itnav.derushio.bluetoothmanager.BluetoothManagedActivity;


public class SendOnlyActivity extends BluetoothManagedActivity {

	public static final String BLUETOOTH_DEVICE_NAME = "deviceName";

	private static String deviceName;
	private static BluetoothDevice bluetoothDevice;
	private static BluetoothAdapter bluetoothAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_only);

		deviceName = getIntent().getStringExtra(BLUETOOTH_DEVICE_NAME);
		Log.d("btAddress", deviceName);
	}

	@Override
	protected void onResume() {
		super.onResume();
		connectDevice(deviceName);
	}

	@Override
	protected void onStop() {
		super.onStop();
		disConnectDevices();
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
		sendMessage("x0y-500s0m0e");
	}

	public void mouseMoveLeft(View v) {
		sendMessage("x-500y0s0m0e");
	}

	public void mouseMoveRight(View v) {
		sendMessage("x500y0s0m0e");
	}

	public void mouseMoveDown(View v) {
		sendMessage("x0y500s0m0e");
	}

	public void mouseActionClick(View v) {
		sendMessage("x0y0s0m1e");
	}

}
