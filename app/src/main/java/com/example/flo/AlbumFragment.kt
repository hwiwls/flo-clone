package com.example.flo

import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentAlbumBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class AlbumFragment : Fragment() {
    lateinit var binding: FragmentAlbumBinding
    private var gson: Gson = Gson()

    val information = arrayListOf("수록곡", "상세정보", "영상")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)

        //home에서 넘어온 데이터 받아오기
        val albumData = arguments?.getString("album")
        val album = gson.fromJson(albumData, Album::class.java)
        //home에서 넘어온 데이터를 반영
        binding.albumAlbumIv.setImageResource(album.coverimg!!)
        binding.albumAlbumTitleTv.text = album.title.toString()
        binding.albumAlbumIuTv.text= album.singer.toString()

        binding.albumBackIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }

//        binding.songLilacLayout.setOnClickListener {
//            Toast.makeText(activity, "라일락", Toast.LENGTH_SHORT). show()
//        }

        val albumAdapter = AlbumViewpagerAdapter(this)
        binding.albumContentVp.adapter = albumAdapter

        TabLayoutMediator(binding.albumContentTb, binding.albumContentVp){
                tab, position ->
            tab.text = information[position]
        }.attach()





        return binding.root
    }
}
