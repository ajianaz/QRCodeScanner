package com.viluvasa.qrscanner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    private lateinit var mScannerView: ZXingScannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mScannerView = ZXingScannerView(this)
        setContentView(mScannerView)
    }

    public override fun onResume() {
        super.onResume()
        mScannerView.setResultHandler(this)
        mScannerView.startCamera()
    }

    public override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()
    }
    override fun handleResult(rawResult: Result?) {
        Log.v("TAG", rawResult?.text) // Prints scan results
        Log.v("TAG", rawResult?.barcodeFormat.toString())

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Scan Result")
        builder.setMessage(rawResult?.text)
        val alert1 = builder.create()
        alert1.show()

        mScannerView.resumeCameraPreview(this)
    }
}