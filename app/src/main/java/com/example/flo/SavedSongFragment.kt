package com.example.flo

import SongDatabase
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flo.databinding.FragmentSavedSongBinding

class SavedSongFragment : Fragment() {
    lateinit var binding: FragmentSavedSongBinding
    lateinit var songDB: SongDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentSavedSongBinding.inflate(inflater, container, false)

        songDB = SongDatabase.getInstance(requireContext())!!

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initRecyclerview()
    }

    private fun initRecyclerview(){
        binding.lockerMusicListRecyclerView
        binding.lockerMusicListRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val songRVAdapter = SongRVAdapter()
        //리스너 객체 생성 및 전달

        songRVAdapter.setMyItemClickListener(object : SongRVAdapter.MyItemClickListener{
            override fun onRemoveSong(songId: Int) {
                songDB.songDao().updateIsLikeById(false,songId)
            }
        })

        binding.lockerMusicListRecyclerView.adapter = songRVAdapter

        songRVAdapter.addSongs(songDB.songDao().getLikedSongs(true) as ArrayList)

    }
}

//package com.example.flo
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.flo.databinding.FragmentSavedSongBinding
//import com.example.flo.databinding.ItemAlbumBinding
//import com.google.android.material.tabs.TabLayoutMediator
//import com.google.gson.Gson
//
//class SavedSongFragment : Fragment() {
//    lateinit var binding: FragmentSavedSongBinding
//    private var lockerDatas = ArrayList<Locker>();
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentSavedSongBinding.inflate(inflater, container, false)
//
//        lockerDatas.apply{
//            add(Locker("butter", "방탄소년단(BTS)", R.drawable.img_album_exp))
//            add(Locker("Lilac", "아이유(IU)", R.drawable.img_album_exp))
//            add(Locker("Next Level", "에스파(Aespa)", R.drawable.img_album_exp))
//            add(Locker("Bboom Bboom", "모모랜드", R.drawable.img_album_exp))
//            add(Locker("Weekend", "태연", R.drawable.img_album_exp))
//            add(Locker("butter", "방탄소년단(BTS)", R.drawable.img_album_exp))
//            add(Locker("Lilac", "아이유(IU)", R.drawable.img_album_exp))
//            add(Locker("Next Level", "에스파(Aespa)", R.drawable.img_album_exp))
//            add(Locker("Bboom Bboom", "모모랜드", R.drawable.img_album_exp))
//            add(Locker("Weekend", "태연", R.drawable.img_album_exp))
//        }
//
//        val lockerRCVAdapter = LockerRCVAdapter(lockerDatas)
//
//        //리사이클러뷰에 어뎁터 연결
//        binding.lockerMusicListRecyclerView.adapter = lockerRCVAdapter
//
//
//        //리사이클러뷰에 어뎁터를 연결
//        binding.lockerMusicListRecyclerView.adapter = lockerRCVAdapter
//
//        lockerRCVAdapter.setMyItemClickListener(object : LockerRCVAdapter.MyItemClickListener{
//
//            override fun onItemclick(locker: Locker) {
//                changeLockerFragment(locker)
//            }
//
//            override fun onRemoveLocker(position: Int) {
//                lockerRCVAdapter.removeItem(position)
//            }
//        })
//
//        binding.lockerMusicListRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//
//
//
//        return binding.root
//    }
//
//    private fun changeLockerFragment(locker: Locker) {
//        (context as MainActivity).supportFragmentManager.beginTransaction()
//            .replace(R.id.main_frm, SavedSongFragment().apply {
//                arguments = Bundle().apply {
//                    val gson = Gson()
//                    val albumJson = gson.toJson(locker)
//                    putString("album", albumJson)
//                }
//            })
//            .commitAllowingStateLoss()
//    }
//
//
//}
