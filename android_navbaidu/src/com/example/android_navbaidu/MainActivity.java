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
			// ������ʼ�����첽�ģ���ҪһС��ʱ�䣬�������־��ʶ�������Ƿ��ʼ���ɹ���Ϊtrueʱ����ܷ��𵼺�
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
				Toast.makeText(MainActivity.this, "keyУ��ɹ�", Toast.LENGTH_LONG).show();
			}
			
			@Override
			public void onVerifyFailed(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "keyУ��ʧ��", Toast.LENGTH_LONG).show();
			}
		};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ��ʼ����������
		BaiduNaviManager.getInstance().initEngine(this, getSdcardDir(),
				mNaviEngineInitListener, "wbAAqx25wv1Rw6d4SzfRxKTL", mKeyVerifyListener);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ������
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
