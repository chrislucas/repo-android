package com.project.studywebview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends AppCompatActivity {

    private  WebView webView;

    private static final String URL_EXAMPLE = "https://www.google.com.br/?gws_rd=ssl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.my_web_view);
        webView.loadUrl(URL_EXAMPLE);
        SslCertificate sslCertificate = webView.getCertificate();
        if(sslCertificate != null) {
            Date date = sslCertificate.getValidNotAfterDate();
            if(date != null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                String formatDate = simpleDateFormat.format(date);
                Log.i("SSL_VALID_DATE", formatDate);
            }
        }
        toggleJavacriptWebView();
        addJsInterface();
        setWebViewClient(new MyWebViewClient(this));
    }


    private void toggleJavacriptWebView() {
        WebSettings webSettings = webView.getSettings();
        boolean toggle = !webSettings.getJavaScriptEnabled();
        webSettings.setJavaScriptEnabled(toggle);
        Log.i("JAVASCRIPT_WEBVIEW", toggle ? "Ativado" : "Desatiavdo");
    }


    @SuppressLint("JavascriptInterface")
    private void addJsInterface() {
        webView.addJavascriptInterface(new WebViewInterface(this), "AndroidWebView");
    }

    private void setWebViewClient(WebViewClient webViewClient) {
        webView.setWebViewClient(webViewClient);
    }

    public static class WebViewInterface {
        private Context context;

        public WebViewInterface(Context context) {
            this.context = context;
        }


        public void showToastMessage(String message) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }


    public static class MyWebViewClient extends WebViewClient {

        private Context context;

        public MyWebViewClient(Context context) {
            this.context = context;
        }

        /**
         *
         * Permite que codigo javascript de uma aplicacao web controle o host, ou seja
         * permite que um JS controle uma funcionalidade no App
         * */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String url  = request.toString();
            String host = Uri.parse(url).getHost();
            if(URL_EXAMPLE.equals(host)) {
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
            return true;
        }

        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return true;
        }

        /**
         * Notify the host application that a page has started loading. This method
         * is called once for each main frame load so a page with iframes or
         * framesets will call onPageStarted one time for the main frame. This also
         * means that onPageStarted will not be called when the contents of an
         * embedded frame changes, i.e. clicking a link whose target is an iframe,
         * it will also not be called for fragment navigations (navigations to
         * #fragment_id).
         *
         * @param view    The WebView that is initiating the callback.
         * @param url     The url to be loaded.
         * @param favicon The favicon for this page if it already exists in the
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        /**
         * Notify the host application that a page has finished loading. This method
         * is called only for main frame. When onPageFinished() is called, the
         * rendering picture may not be updated yet. To get the notification for the
         * new Picture, use {@link WebView.PictureListener#onNewPicture}.
         *
         * @param view The WebView that is initiating the callback.
         * @param url  The url of the page.
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        /**
         * Notify the host application that the WebView will load the resource
         * specified by the given url.
         *
         * @param view The WebView that is initiating the callback.
         * @param url  The url of the resource the WebView will load.
         */
        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        /**
         * Notify the host application of a resource request and allow the
         * application to return the data.  If the return value is null, the WebView
         * will continue to load the resource as usual.  Otherwise, the return
         * response and data will be used.  NOTE: This method is called on a thread
         * other than the UI thread so clients should exercise caution
         * when accessing private data or the view system.
         *
         * @param view    The {@link WebView} that is requesting the
         *                resource.
         * @param request Object containing the details of the request.
         * @return A {@link WebResourceResponse} containing the
         * response information or null if the WebView should load the
         * resource itself.
         */
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            return super.shouldInterceptRequest(view, request);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if(webView.canGoBack()) {
                    webView.goBack();
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
