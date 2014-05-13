package com.example.android_navbaidu;
import com.baidu.navisdk.BNaviEngineManager.NaviEngineInitListener;
import com.baidu.navisdk.util.verify.BNKeyVerifyListener;
import com.baidu.navisdk.BaiduNaviManager;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends Activity {
	private boolean mIsEngineInitSuccess = false;
	private NaviEngineInitListener mNaviEngineInitListener = new NaviEngineInitListener() {
		public void engineInitSuccess() {
			// 导航初始化是异步的，需要一小段时间，以这个标志来识别引擎是否初始化成功，为true时候才能发起导航
			mIsEngineInitSuccess = true;
		}

		public void engineInitStart() {
		}

		public void engineInitFail() {
		}
	};

	private String getSdcardDir() {
		if (Environment.getExternalStorageState().equalsIgnoreCase(
				Environment.MEDIA_MOUNTED)) {
			return Environment.getExternalStorageDirectory().toString();
		}
		return null;
	}
	 private BNKeyVerifyListener mKeyVerifyListener = new BNKeyVerifyListener() {
			
			@Override
			public void onVerifySucc() {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "key校验成功", Toast.LENGTH_LONG).show();
			}
			
			@Override
			public void onVerifyFailed(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "key校验失败", Toast.LENGTH_LONG).show();
			}
		};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化导航引擎
		BaiduNaviManager.getInstance().initEngine(this, getSdcardDir(),
				mNaviEngineInitListener, "wbAAqx25wv1Rw6d4SzfRxKTL", mKeyVerifyListener);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题
		setContentView(R.layout.activity_main);
		
	}
	public void onNav(View view){
		Intent intent=new Intent(this, RouteGuideDemo.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
