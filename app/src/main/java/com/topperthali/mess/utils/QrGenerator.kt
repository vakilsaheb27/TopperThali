package com.topperthali.mess.utils

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException

object QrGenerator {

    /**
     * Takes a string (like our random UUID) and converts it into a QR Code Bitmap image.
     */
    fun generateQrCode(textToEncode: String, width: Int = 512, height: Int = 512): Bitmap? {
        return try {
            val bitMatrix = MultiFormatWriter().encode(
                textToEncode,
                BarcodeFormat.QR_CODE,
                width,
                height
            )
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            
            // Loop through the matrix to draw the black and white squares
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            bitmap
        } catch (e: WriterException) {
            e.printStackTrace()
            null
        }
    }
}
