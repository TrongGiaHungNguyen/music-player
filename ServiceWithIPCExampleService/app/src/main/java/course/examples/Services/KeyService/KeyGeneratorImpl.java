package course.examples.Services.KeyService;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;

import course.examples.Services.KeyCommon.KeyGenerator;

public class KeyGeneratorImpl extends Service {

	private MediaPlayer mediaPlayer = null;

	private final int[] clipResources = {
			R.raw.bad_disco,
			R.raw.jazz_montage,
			R.raw.no_remember,
			R.raw.we_let_just_slay,
			R.raw.rain
	};

	private final String[] clipNames = {
			"Bad Disco",
			"Jazz Montage",
			"No Remember",
			"We Let Just Slay",
			"Rain"
	};

	private final KeyGenerator.Stub binder = new KeyGenerator.Stub() {

		@Override
		public void playClip(int clipNumber) throws RemoteException {
			stopCurrentClip();

			if (clipNumber > 0 && clipNumber <= clipResources.length) {
				int resId = clipResources[clipNumber - 1];
				mediaPlayer = MediaPlayer.create(KeyGeneratorImpl.this, resId);
				if (mediaPlayer != null) {
					mediaPlayer.setOnCompletionListener(mp -> stopCurrentClip());
					mediaPlayer.start();
				}
			}
		}

		@Override
		public void pauseClip() throws RemoteException {
			if (mediaPlayer != null && mediaPlayer.isPlaying()) {
				mediaPlayer.pause();
			}
		}

		@Override
		public void resumeClip() throws RemoteException {
			if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
				mediaPlayer.start();
			}
		}

		@Override
		public void stopClip() throws RemoteException {
			stopCurrentClip();
		}

		@Override
		public String[] listClips() throws RemoteException {
			return clipNames;
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	@Override
	public void onDestroy() {
		stopCurrentClip();
		super.onDestroy();
	}

	private void stopCurrentClip() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}
}