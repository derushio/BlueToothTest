package jp.itnav.derushio.bluetoothtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import jp.itnav.derushio.bluetoothmanager.BluetoothManagedActivity;


public class MainActivity extends BluetoothManagedActivity {

	private LinearLayout btDevicesHolder;
	private ArrayList<TextView> paredDevices;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btDevicesHolder = (LinearLayout) findViewById(R.id.btDevicesHolder);
		paredDevices = new ArrayList<TextView>();

		ArrayList<String> paredDeviceNames = getParedDeviceNames();
		if (paredDeviceNames.size() > 0) {
			for (String paredDeviceName : paredDeviceNames) {
				final TextView textView = new TextView(this);
				textView.setText(paredDeviceName);
				textView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(MainActivity.this, SendOnlyActivity.class);
						intent.putExtra(SendOnlyActivity.BLUETOOTH_DEVICE_NAME, textView.getText().toString());
						startActivity(intent);
					}
				});
				btDevicesHolder.addView(textView);
				paredDevices.add(textView);
			}
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
