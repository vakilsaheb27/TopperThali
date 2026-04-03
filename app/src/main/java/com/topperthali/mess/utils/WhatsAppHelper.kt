package com.topperthali.mess.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

object WhatsAppHelper {

    fun sendReminder(context: Context, phone: String, studentName: String, daysLeft: Int) {
        // Format the phone number (assuming India +91 if no country code provided)
        val formattedPhone = if (phone.startsWith("+")) phone else "+91$phone"
        
        val message = "Hello $studentName,\n\nThis is a reminder from Topper Thali Mess. You have $daysLeft days remaining on your subscription. Please renew soon to ensure uninterrupted meals. Thank you!"

        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://api.whatsapp.com/send?phone=$formattedPhone&text=${Uri.encode(message)}")
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "WhatsApp not installed or error opening.", Toast.LENGTH_SHORT).show()
        }
    }
}
