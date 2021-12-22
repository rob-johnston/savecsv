package com.iotabalanceticker.savecsv

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResult
import com.getcapacitor.JSObject
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.ActivityCallback
import com.getcapacitor.annotation.CapacitorPlugin
import com.getcapacitor.annotation.Permission
import java.io.IOException
import java.io.OutputStream

@CapacitorPlugin(
        name = "SaveCSV",
        permissions = [
            Permission(
                    alias = "storage",
                    strings = [
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    ],
            )],
)
class SaveCSVPlugin : Plugin() {
    @PluginMethod
    fun writeCSVToStorage(call: PluginCall) {
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_CREATE_DOCUMENT
            type = "text/csv"
        }

        startActivityForResult(call, intent, "writeCSVToStorageResult")
    }

    @ActivityCallback
    fun writeCSVToStorageResult(call: PluginCall, result: ActivityResult) {
        try {
            val csvData = call.getString("value", "default") as String
            val bytes = csvData.toByteArray()
            val intent: Intent = result.data as Intent
            val uri = intent.data as Uri
            val outputStream: OutputStream = getContext().getContentResolver().openOutputStream(uri) as OutputStream
            outputStream.write(bytes)
            outputStream.close()

            val ret = JSObject()
            ret.put("value", csvData)
            call.resolve(ret)
        } catch (e: IOException) {
            call.reject("Failed to write file")
        }

    }
}