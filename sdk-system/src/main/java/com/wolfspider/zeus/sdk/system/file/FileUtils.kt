package com.wolfspider.zeus.sdk.system.file

import android.os.Environment
import java.io.File

class FileUtils {

    companion object {

        @Suppress("DEPRECATION")
        @JvmStatic
        fun getExternalStorageRootPath(): String? {
            if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) {
                return null
            }

            return Environment.getExternalStorageDirectory().absolutePath
        }

        @JvmStatic
        fun getExternalStorageDirPath(fileName: String): String? {
            val rootPath = getExternalStorageRootPath()
            if (rootPath.isNullOrBlank()) {
                return null
            }

            val file = File(rootPath, fileName)
            if (!file.exists()) {
                file.mkdirs()
            }

            return file.absolutePath
        }

        @JvmStatic
        fun getExternalStorageDirPath(parentName: String, childName: String): String? {
            val parentPath = getExternalStorageDirPath(parentName)
            if (parentPath.isNullOrBlank()) {
                return null
            }

            val parentFile = File(parentName)
            if (!parentFile.exists()) {
                parentFile.mkdirs()
            }

            val childFile = File(parentName, childName)
            if (!childFile.exists()) {
                childFile.mkdirs()
            }

            return childFile.absolutePath
        }

        @JvmStatic
        fun createExternalStorageFile(parentName: String, fileName: String): String {
            val file = File(parentName, fileName)
            return file.absolutePath
        }

    }
}