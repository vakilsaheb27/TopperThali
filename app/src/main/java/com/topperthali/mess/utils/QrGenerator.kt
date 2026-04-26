package com.topperthali.mess.utils

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

object QrGenerator {

    /**
     * Takes a string (like our random UUID) and converts it into a QR Code Bitmap image efficiently.
     */
    fun generateQrCode(textToEncode: String, width: Int = 512, height: Int = 512): Bitmap? {
        return try {
            // The BarcodeEncoder handles the optimized IntArray and setPixels natively
            val barcodeEncoder = BarcodeEncoder()
            barcodeEncoder.encodeBitmap(textToEncode, BarcodeFormat.QR_CODE, width, height)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
