package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentSavedSongBinding
import com.example.flo.databinding.ItemAlbumBinding
import com.example.flo.databinding.SongfileFragmentBinding

class SongFileFragment : Fragment(){
    lateinit var binding: SongfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SongfileFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }
}
