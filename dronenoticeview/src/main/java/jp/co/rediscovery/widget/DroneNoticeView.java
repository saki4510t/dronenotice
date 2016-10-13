package jp.co.rediscovery.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import jp.co.rediscovery.dronenoticeview.R;

/**
 * Created by saki on 2016/10/13.
 *
 */
public class DroneNoticeView extends LinearLayout {
	private static final long SWITCH_DURATION = 3000;

	private static final int[] IMAGES = {
		R.drawable.notice01densely_populated,
		R.drawable.notice02height_limit,
		R.drawable.notice03night_flyght,
		R.drawable.notice04fly_me_to_the_moon,
		R.drawable.notice05visual,
		R.drawable.notice06osaka_castle,
		R.drawable.notice07warrior_castle,
		R.drawable.notice08clash,
		R.drawable.notice09parliament,
		R.drawable.notice10fighter,
		R.drawable.notice11helicopter,
		R.drawable.notice12crazy,
		R.drawable.notice13animal_abuse,
		R.drawable.notice14counterattack,
		R.drawable.notice15privacy,
		R.drawable.notice16privacy2,
		R.drawable.notice17spy,
		R.drawable.notice18concentration,
		R.drawable.notice19flight_condition,
		R.drawable.notice20against_heat,
		R.drawable.notice21restriction,
		R.drawable.notice22restriction,
		R.drawable.notice23autopilot,
		R.drawable.notice24easy_control,
		R.drawable.notice25proper_judgment,
		R.drawable.notice26case_of_need,
		R.drawable.notice27battle,
		R.drawable.notice28battle,
		R.drawable.notice29beside_oneself,
		R.drawable.notice30battery_check,
		R.drawable.notice31battery_dead,
		R.drawable.notice32charging_condition,
		R.drawable.notice33fun_flight,
	};

	private static final int[] NOTICES = {
		R.string.n000,
		R.string.n001,
		R.string.n002,
		R.string.n003,
		R.string.n004,
		R.string.n005,
		R.string.n006,
		R.string.n007,
		R.string.n008,
		R.string.n009,
		R.string.n010,
		R.string.n011,
		R.string.n012,
		R.string.n013,
		R.string.n014,
		R.string.n015,
		R.string.n016,
		R.string.n017,
		R.string.n018,
		R.string.n019,
		R.string.n020,
		R.string.n021,
		R.string.n022,
		R.string.n023,
		R.string.n024,
		R.string.n025,
		R.string.n026,
		R.string.n027,
		R.string.n028,
		R.string.n029,
		R.string.n030,
		R.string.n031,
		R.string.n032,
	};

	private static final int IMAGE_NUM = IMAGES.length;
	private static final Random sRandom = new Random(System.nanoTime());

	private ImageView mNoticeIv;
	private TextView mNoticeTv;
	private long mSwitchDuration = SWITCH_DURATION;

	public DroneNoticeView(final Context context) {
		this(context, null);
	}

	public DroneNoticeView(final Context context, final AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public DroneNoticeView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context);
	}

	private void initView(final Context context) {
		setOrientation(LinearLayout.VERTICAL);
		final LayoutInflater inflater = LayoutInflater.from(context);
		try {
			final View rootView = inflater.inflate(R.layout.view_notice, this, true);
			mNoticeTv = (TextView)findViewById(R.id.notice);
			mNoticeIv = (ImageView)findViewById(R.id.imageView);
			setImage();
		} catch (final Exception e) {
			//
		}
	}

	public void onResume() {
		if (mSwitchDuration > 0L) {
			mNoticeIv.postDelayed(mSwitchNoticeTask, mSwitchDuration);
		}
	}

	public void onPause() {
		mNoticeIv.removeCallbacks(mSwitchNoticeTask);
	}

	public void next() {
		setImage();
	}

	/**
	 * 自動切り替えのインターバルをセット
	 * @param durationMs ミリ秒
	 */
	public void setAutoNextDuration(final long durationMs) {
		mNoticeIv.removeCallbacks(mSwitchNoticeTask);
		mSwitchDuration = durationMs;
		if (mSwitchDuration > 0L) {
			mNoticeIv.postDelayed(mSwitchNoticeTask, mSwitchDuration);
		}
	}

	private void setImage() {
		mNoticeIv.removeCallbacks(mSwitchNoticeTask);
		final int ix = sRandom.nextInt(IMAGE_NUM);
		mNoticeIv.setImageResource(IMAGES[ix]);
		mNoticeTv.setText(NOTICES[ix]);
		if (mSwitchDuration > 0L) {
			mNoticeIv.postDelayed(mSwitchNoticeTask, mSwitchDuration);
		}
	}

	private final Runnable mSwitchNoticeTask
		= new Runnable() {
		@Override
		public void run() {
			setImage();
		}
	};
}
