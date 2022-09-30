package com.ravi.myapplication.ui.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.android.example.cameraxbasic.utils.padWithDisplayCutout
import com.ravi.myapplication.R
import com.ravi.myapplication.ui.activity.PhotoFragment
import kotlinx.android.synthetic.main.fragment_gallery.*
import java.io.File
import java.util.*

val EXTENSION_WHITELIST = arrayOf("JPG")
class GalleryFragment internal constructor(): Fragment() {

    private lateinit var mediaList: MutableList<File>
   private val args: GalleryFragmentArgs by navArgs()


    /** Adapter class used to present a fragment containing one photo or video as a page */
    inner class MediaPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount(): Int = mediaList.size
        override fun getItem(position: Int): Fragment = PhotoFragment.create(mediaList[position])
        override fun getItemPosition(obj: Any): Int = POSITION_NONE
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true
        val rootDirectory = File(args.rootDirectory)
        mediaList = rootDirectory.listFiles { file ->
            EXTENSION_WHITELIST.contains(file.extension.toUpperCase(Locale.ROOT))
        }?.sortedDescending()?.toMutableList() ?: mutableListOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photo_view_pager.apply {
            offscreenPageLimit = 2
            adapter = MediaPagerAdapter(childFragmentManager)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

          cutout_safe_area.padWithDisplayCutout()
        }

       back_button.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigateUp()
        }
    }
}