package course.examples.Services.KeyClient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import course.examples.Services.KeyCommon.KeyGenerator;

public class KeyServiceUser extends Activity {

	private KeyGenerator mMusicService;
	private boolean isBound = false;
	private boolean isPlaying = false;
	private boolean isPaused = false;

	private Button bindBtn, unbindBtn, listBtn;
	private Button playBtn, pauseBtn, resumeBtn, stopBtn;
	private Spinner clipSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initViews();
		setButtonListeners();
		updateUIState();
	}

	private void initViews() {
		bindBtn = findViewById(R.id.bind_button);
		unbindBtn = findViewById(R.id.unbind_button);
		listBtn = findViewById(R.id.list_button);
		playBtn = findViewById(R.id.play_button);
		pauseBtn = findViewById(R.id.pause_button);
		resumeBtn = findViewById(R.id.resume_button);
		stopBtn = findViewById(R.id.stop_button);
		clipSpinner = findViewById(R.id.clip_spinner);
	}

	private void setButtonListeners() {
		bindBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bindToService();
			}
		});

		unbindBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				unbindFromService();
			}
		});

		listBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					String[] clips = mMusicService.listClips();
					ArrayAdapter<String> adapter = new ArrayAdapter<>(KeyServiceUser.this,
							android.R.layout.simple_spinner_item, clips);
					adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					clipSpinner.setAdapter(adapter);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});

		playBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					int position = clipSpinner.getSelectedItemPosition() + 1;
					mMusicService.playClip(position);
					isPlaying = true;
					isPaused = false;
					updateUIState();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});

		pauseBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					mMusicService.pauseClip();
					isPaused = true;
					updateUIState();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});

		resumeBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					mMusicService.resumeClip();
					isPaused = false;
					updateUIState();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});

		stopBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					mMusicService.stopClip();
					isPlaying = false;
					isPaused = false;
					updateUIState();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void bindToService() {
		if (!isBound) {
			Intent intent = new Intent(KeyGenerator.class.getName());
			ResolveInfo info = getPackageManager().resolveService(intent, 0);
			if (info != null && info.serviceInfo != null) {
				intent.setComponent(new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name));
				bindService(intent, connection, Context.BIND_AUTO_CREATE);
			} else {
				Toast.makeText(this, "Service not found!", Toast.LENGTH_SHORT).show();
			}
		}
	}

	private void unbindFromService() {
		if (isBound && !isPlaying) {
			unbindService(connection);
			isBound = false;
			updateUIState();
			Toast.makeText(this, "Service Unbound!", Toast.LENGTH_SHORT).show();
		}
	}

	private final ServiceConnection connection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mMusicService = KeyGenerator.Stub.asInterface(service);
			isBound = true;
			updateUIState();
			Toast.makeText(KeyServiceUser.this, "Service Connected!", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mMusicService = null;
			isBound = false;
			updateUIState();
			Toast.makeText(KeyServiceUser.this, "Service Disconnected!", Toast.LENGTH_SHORT).show();
		}
	};

	private void updateUIState() {
		bindBtn.setEnabled(false);
		listBtn.setEnabled(false);
		playBtn.setEnabled(false);
		pauseBtn.setEnabled(false);
		resumeBtn.setEnabled(false);
		stopBtn.setEnabled(false);
		unbindBtn.setEnabled(false);

		if (!isBound) {
			bindBtn.setEnabled(true);
		} else {
			listBtn.setEnabled(true);
			playBtn.setEnabled(true);

			if (isPlaying) {
				stopBtn.setEnabled(true);

				if (isPaused) {
					resumeBtn.setEnabled(true);
					pauseBtn.setEnabled(false);
				} else {
					pauseBtn.setEnabled(true);
					resumeBtn.setEnabled(false);
				}

				unbindBtn.setEnabled(false);
			} else {
				unbindBtn.setEnabled(true);
			}
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (isBound && !isPlaying) {
			unbindService(connection);
			isBound = false;
			updateUIState();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (isBound) {
			try {
				mMusicService.stopClip();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			unbindService(connection);
			isBound = false;
		}
	}
}