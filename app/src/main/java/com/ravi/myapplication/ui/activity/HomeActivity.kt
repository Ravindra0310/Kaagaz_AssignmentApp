package com.ravi.myapplication.ui.activity

import android.Manifest
import android.content.AbstractThreadedSyncAdapter
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ravi.myapplication.R
import com.ravi.myapplication.ui.adapter.AlbumAdapter
import com.ravi.myapplication.ui.adapter.OnClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import java.io.File

private const val PERMISSIONS_REQUEST_CODE = 10
private val PERMISSIONS_REQUIRED = arrayOf(Manifest.permission.CAMERA)

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() ,OnClickListener{

    lateinit var adapter: AlbumAdapter
    private var albumList = ArrayList<File>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        CameraPermission()
        setRecyclerView()
        setAlbumList()
        floatingActionCameraButton.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                startActivity(Intent(this@HomeActivity, MainActivity::class.java))
            }
        }
    }

    private fun setAlbumList() {
        val mediaDir = applicationContext.externalMediaDirs.firstOrNull()?.let {
            albumList.clear()
            it.listFiles()?.forEach {
                if (it.listFiles()?.size!! > 0) {
                        albumList.add(it)
                    }
            }
            adapter.notifyDataSetChanged()
        }
    }

    private fun setRecyclerView() {
        AlbumRecyclerView.layoutManager=LinearLayoutManager(this)
        adapter= AlbumAdapter(albumList,this)
        AlbumRecyclerView.adapter=adapter
    }

    private fun CameraPermission() {
        if (!hasPermissions(applicationContext)) {
            // Request camera-related permissions
            requestPermissions(PERMISSIONS_REQUIRED, PERMISSIONS_REQUEST_CODE)
        } else {

        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == com.ravi.myapplication.ui.activity.PERMISSIONS_REQUEST_CODE) {
            if (PackageManager.PERMISSION_GRANTED == grantResults.firstOrNull()) {
                // Take the user to the success fragment when permission is granted
                Toast.makeText(applicationContext, "Permission request granted", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(applicationContext, "Permission request denied", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }


    companion object {

        /** Convenience method used to check if all permissions required by this app are granted */
        fun hasPermissions(context: Context) = PERMISSIONS_REQUIRED.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    override fun ShowImage(file: File, position: Int) {
        var intent=Intent(this,AlbumImageActivity::class.java)
        intent.putExtra("file",file.toString())
        startActivity(intent)
    }
}