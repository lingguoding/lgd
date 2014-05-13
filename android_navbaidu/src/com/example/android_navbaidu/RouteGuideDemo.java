package com.example.android_navbaidu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.baidu.navisdk.BaiduNaviManager;
import com.baidu.navisdk.BaiduNaviManager.OnStartNavigationListener;
import com.baidu.navisdk.comapi.routeplan.RoutePlanParams.NE_RoutePlan_Mode;
import com.baidu.nplatform.comapi.basestruct.GeoPoint;

/**
 * GPS导航Demo
 */
public class RouteGuideDemo extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_routeguide);
		Button btnStartNavigation = (Button) findViewById(R.id.button_navigation);
		btnStartNavigation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				launchNavigator();

			}
		});
	}

	/**
	 * 启动GPS导航. 前置条件：导航引擎初始化成功
	 */
	private void launchNavigator(){
		//这里给出一个起终点示例，实际应用中可以通过POI检索、外部POI来源等方式获取起终点坐标
		BaiduNaviManager.getInstance().launchNavigator(this, 
				40.05087, 116.30142,"百度大厦", 
		        39.90882, 116.39750,"北京天安门",
				NE_RoutePlan_Mode.ROUTE_PLAN_MOD_MIN_TIME, 		 //算路方式
				true, 									   		 //真实导航
				BaiduNaviManager.STRATEGY_FORCE_ONLINE_PRIORITY, //在离线策略
				new OnStartNavigationListener() {				 //跳转监听
					
					@Override
					public void onJumpToNavigator(Bundle configParams) {
						Intent intent = new Intent(RouteGuideDemo.this, BNavigatorActivity.class);
						intent.putExtras(configParams);
				        startActivity(intent);
					}
					@Override
					public void onJumpToDownloader() {
					}
				});
	}
}
