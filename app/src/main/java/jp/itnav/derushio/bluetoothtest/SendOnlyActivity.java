package jp.itnav.derushio.bluetoothtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import jp.itnav.derushio.bluetoothmanager.BluetoothManagedActivity;


public class SendOnlyActivity extends BluetoothManagedActivity {

	public static final String BLUETOOTH_DEVICE_NAME = "deviceName";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_only);

		setTargetDeviceName(getIntent().getStringExtra(BLUETOOTH_DEVICE_NAME));
		Log.d("btAddress", getTargetDeviceName());
	}

	@Override
	protected void onResume() {
		super.onResume();
		TimerHandler timerHandler = new TimerHandler();
		timerHandler.sleep(3000);
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
		writeMessage("x0y-500s0m0e");
	}

	public void mouseMoveLeft(View v) {
		writeMessage("x-500y0s0m0e");
	}

	public void mouseMoveRight(View v) {
		writeMessage("x500y0s0m0e");
	}

	public void mouseMoveDown(View v) {
		writeMessage("x0y500s0m0e");
	}

	public void mouseActionClick(View v) {
		writeMessage("x0y0s0m1e");
	}

	@Override
	protected void onReadMessageFinished(String s) {
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
	}

	public class TimerHandler extends Handler {
		private long delayMillis;

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			readMessageStart();

			if (this != null) {
				sleep(delayMillis);
			}
		}

		public void sleep(long delayMillis) {
			this.delayMillis = delayMillis;
			removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	}
}
