package com.innotek.demotestproject.activity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.innotek.demotestproject.R;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import util.LogUtil;

/**
 * Created by admin on 2017/12/12.
 */

public class NongShangBankActivity extends BaseActivity {

    private WebView webView;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nongshangbank);
        webView = (WebView) findViewById(R.id.webview);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.addJavascriptInterface(this, "$$");
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        settings.setSupportZoom(false);

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtil.e("url:...."+url);
                if("http://158.222.68.191:8080/merchant/gb/index.jsp".equals(url)){
                       finish();
                }
                return super.shouldOverrideUrlLoading(view, url);//super.shouldOverrideUrlLoading(view, url)
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });

        loadUrl();
    }

    public void loadUrl() {
        String url = "http://60.190.244.46:8006/paygate/main";
        HashMap<String,String> strMap = new HashMap<>();
        strMap.put("Plain","<Finance><Message><TransId>IPEM</TransId><MerchantId>20171205103200</MerchantId><MerSeqNo>INK2017121213475952785</MerSeqNo><OrderId>P083301221513057679095</OrderId><TransAmt>1</TransAmt><MerDateTime>20171212134759</MerDateTime><MerURL>http://158.222.25.107:8080/merchant/*.do</MerURL><MerURL1>http://158.222.68.191:8080/merchant/gb/index.jsp</MerURL1><Subject>中国好停车停车付费</Subject><MerTransList><MerTransDetail><SubMerchantId>2W2017120510320000</SubMerchantId><SubMerDateTime>20171212134759</SubMerDateTime><SubMerSeqNo>SUB2017121213475952785</SubMerSeqNo><ProductInfo>车牌号浙A133AQ于20170621182700在九曲营路（大红鹰）停车41分钟</ProductInfo><SubTransAmt>1</SubTransAmt><SubMerImport>K0</SubMerImport></MerTransDetail></MerTransList></Message></Finance>");
        strMap.put("Signature","kpTgGvAlht9yuEWSCBzMF7fl1ayS4KqF/Q8876ZwvjpfU3KeYT8eL9PA40Kl68Gsd/gOT9Ks7ewUdnPBDRuAUAuutbauPLFXWFzm29pLlX+frcNBQD/CLiS2h4/io+D+A1KgFhF/QOkrgW4pExCmyQd+8wdgjd8dHfdQC/JMyq0=");
        Collection<Map.Entry<String, String>> postData = strMap.entrySet();
        webview_ClientPost(webView,url,postData);

    }
    public   void webview_ClientPost(WebView webView, String url, Collection< Map.Entry<String, String>> postData){
        StringBuilder sb = new StringBuilder();

        sb.append("<html><head></head>");
        sb.append("<body onload='form1.submit()'>");
        sb.append(String.format("<form id='form1' action='%s' method='%s'>", url, "post"));
        for (Map.Entry<String, String> item : postData) {
            sb.append(String.format("<input name='%s' type='hidden' value='%s' />", item.getKey(), item.getValue()));
        }
        sb.append("</form></body></html>");
        webView.loadData(sb.toString(), "text/html", "UTF-8");
    }

    boolean isGoBack  = true;
    @Override
    public void onBackPressed() {
        if (isGoBack) {
            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                finish();
            }
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 防止js代码在后台一直运行，销毁webview
        if (webView != null) {
            //个别手机WebSettings对象为空，屏蔽空指针
            if (webView.getSettings() != null) {
                webView.getSettings().setJavaScriptEnabled(false);
            }
            webView.setWebChromeClient(null);
            webView.setWebViewClient(null);
            webView.clearCache(true);
            webView.destroy();
        }
    }
}
