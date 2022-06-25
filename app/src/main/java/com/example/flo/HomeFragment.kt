package com.example.flo

import android.graphics.Insets
import android.graphics.Insets.add
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.flo.databinding.FragmentHomeBinding
import com.google.gson.Gson


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private var albumDatas = ArrayList<Album>();

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

//        binding.homeTodayLaunch01Iv.setOnClickListener {
//            (context as MainActivity).supportFragmentManager.beginTransaction()
//                .replace(R.id.main_frm, AlbumFragment())
//                .commitAllowingStateLoss()
//        }

        //데이터 리스트 생성 더미데이터
        albumDatas.apply{
            add(Album("butter", "방탄소년단(BTS)", R.drawable.img_album_exp))
            add(Album("Lilac", "아이유(IU)", R.drawable.img_album_exp))
            add(Album("Next Level", "에스파(Aespa)", R.drawable.img_album_exp))
            add(Album("Boy with Luv", "방탄소년단(BTS)", R.drawable.img_album_exp))
            add(Album("Bboom Bboom", "모모랜드", R.drawable.img_album_exp))
            add(Album("Weekend", "태연", R.drawable.img_album_exp))
        }



        //더미데이터랑 adpater 연결
        val albumRVAdapter = AlbumRVAdapter(albumDatas)

        //리사이클러뷰에 어뎁터를 연결
        binding.homeTodayMusicAlbumRecyclerView.adapter = albumRVAdapter

        albumRVAdapter.setMyItemClickListener(object : AlbumRVAdapter.MyItemClickListener{

            override fun onItemclick(album: Album) {
                changeAlbumFragment(album)
            }

            override fun onRemoveAlbum(position: Int) {
                albumRVAdapter.removeItem(position)
            }
        })

        //레이아웃 매니저 설정
        binding.homeTodayMusicAlbumRecyclerView.layoutManager = rLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        val bannerAdapter = BannerViewpagerAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))

        binding.homeBannerVp.adapter = bannerAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL



//        binding.homeTodayLaunch01Iv.background = getResources().getDrawable(R.drawable.radius, null)
//        binding.homeTodayLaunch01Iv.setClipToOutline(true)
//
//        binding.homeTodayLaunch02Iv.background = getResources().getDrawable(R.drawable.radius, null)
//        binding.homeTodayLaunch02Iv.setClipToOutline(true)
//
//        binding.homeTodayLaunch03Iv.background = getResources().getDrawable(R.drawable.radius, null)
//        binding.homeTodayLaunch03Iv.setClipToOutline(true)

//        binding.homePotcast01Iv.background = getResources().getDrawable(R.drawable.radius, null)
//        binding.homePotcast01Iv.setClipToOutline(true)
//
//        binding.homePotcast02Iv.background = getResources().getDrawable(R.drawable.radius, null)
//        binding.homePotcast02Iv.setClipToOutline(true)
//
//        binding.homePotcast03Iv.background = getResources().getDrawable(R.drawable.radius, null)
//        binding.homePotcast01Tv.setClipToOutline(true)
//
//        binding.homeVideoCollection01Iv.background = getResources().getDrawable(R.drawable.radius, null)
//        binding.homeVideoCollection01Iv.setClipToOutline(true)
//
//        binding.homeVideoCollection02Iv.background = getResources().getDrawable(R.drawable.radius, null)
//        binding.homeVideoCollection02Iv.setClipToOutline(true)
//
//        binding.homeVideoCollection03Iv.background = getResources().getDrawable(R.drawable.radius, null)
//        binding.homeVideoCollection03Iv.setClipToOutline(true)

        return binding.root
    }

    private fun changeAlbumFragment(album: Album) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, AlbumFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val albumJson = gson.toJson(album)
                    putString("album", albumJson)
                }
            })
            .commitAllowingStateLoss()
    }


}
