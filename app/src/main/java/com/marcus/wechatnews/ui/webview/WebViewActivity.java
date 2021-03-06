package com.marcus.wechatnews.ui.webview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.marcus.wechatnews.NewsApplication;
import com.marcus.wechatnews.R;
import com.marcus.wechatnews.utils.WeChatUtil;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by marcus on 16/8/14.
 */
public class WebViewActivity extends AppCompatActivity implements WebContract.View {
    private static final String TAG = "WebViewActivity";
    @BindView(R.id.web_toolbar)
    Toolbar toolBar;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.load_progress)
    ProgressBar progressBar;
    @Inject
    WebPresenter webPresenter;
    private String[] infos;
    private IWXAPI api;

    public static void startActivity(Context context, ArrayList<String> value) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putStringArrayListExtra("result", value);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webactivity_view);
        initInjector();
        initView();
    }

    private void initInjector() {
        DaggerWebComponent.builder()
                .webModule(new WebModule())
                .build()
                .inject(this);
    }

    private void initView() {
        //该网页的各项信息
        ArrayList<String> result = getIntent().getStringArrayListExtra("result");
        int size = result.size();
        infos = new String[size];
        for (int i = 0; i < size; i++) {
            infos[i] = result.get(i);
        }
        //绑定 View
        ButterKnife.bind(this);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progressBar.setVisibility(View.VISIBLE);
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                Log.d(TAG, "onProgressChanged: " + newProgress);
                if (newProgress >= 100) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                        }
                    }, 1200);
                }
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(infos[0]);
        //ToolBar
        toolBar.setTitle(infos[2]);
        setSupportActionBar(toolBar);
        toolBar.setNavigationIcon(R.drawable.back);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //WebPresenter
        webPresenter.attachView(this);
        //api
        api = ((NewsApplication) getApplicationContext()).getApi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //朋友圈
            case R.id.share_zone:
                webPresenter.shareToWX(SendMessageToWX.Req.WXSceneTimeline);
                return true;
            //朋友
            case R.id.share_friends:
                webPresenter.shareToWX(SendMessageToWX.Req.WXSceneSession);
                return true;
            //收藏
            case R.id.collect:
                webPresenter.shareToWX(SendMessageToWX.Req.WXSceneFavorite);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    @Override
    public void setShare(int type) {
        //初始化一个 WXWebpageObject 对象，填写 url
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = infos[0];
        //用 WXWebpageObjcect 对象初始化一个 WXMediaMessage 对象，填写标题、描述
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = infos[1];
        msg.description = infos[2];
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        msg.thumbData = WeChatUtil.bmpToByteArray(bitmap, true);
        //构造一个 Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = type;
        api.sendReq(req);
    }
}