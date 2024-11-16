package com.example.cosmosmasterapp

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.apply {
            // Включаем JavaScript
            javaScriptEnabled = true

            // Разрешаем загрузку из любых источников
            domStorageEnabled = true

            // Разрешаем смешанный контент (http и https)
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

            // Разрешаем cross-origin запросы
            allowUniversalAccessFromFileURLs = true
            allowFileAccessFromFileURLs = true

            // Разрешаем загрузку ресурсов
            blockNetworkLoads = false
            blockNetworkImage = false
        }

        // Настраиваем WebViewClient
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url ?: "")
                return true
            }
        }

        // Настраиваем WebChromeClient для поддержки JavaScript alerts
        webView.webChromeClient = WebChromeClient()

        webView.loadUrl("file:///android_asset/index.html")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}