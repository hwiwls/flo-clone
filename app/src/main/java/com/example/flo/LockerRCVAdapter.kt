package com.example.flo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flo.databinding.ItemLockerSongBinding

class LockerRCVAdapter(private val lockerList: ArrayList<Locker>): RecyclerView.Adapter<LockerRCVAdapter.ViewHolder>(){
    //클릭 인터페이스 정의
    interface MyItemClickListener{
        fun onItemclick(locker : Locker)
        fun onRemoveLocker(position : Int)
    }
    private lateinit var mItemClickListener: LockerRCVAdapter.MyItemClickListener

    fun setMyItemClickListener(itemClickListener: LockerRCVAdapter.MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LockerRCVAdapter.ViewHolder {
        val binding: ItemLockerSongBinding = ItemLockerSongBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }
    fun addItem(locker: Locker){
        lockerList.add(locker)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        lockerList.removeAt(position)
        notifyDataSetChanged()
    }

    //뷰홀더에 데이터를 바인딩해줘야 할 때마다 호출되는 함수
    override fun onBindViewHolder(holder: LockerRCVAdapter.ViewHolder, position: Int) {
        holder.bind(lockerList[position])
        holder.binding.itemLockerSongTitleTv.setOnClickListener{mItemClickListener.onRemoveLocker(position)}
//        holder.itemView.setOnClickListener{ mItemClickListener.onItemclick(albumList[position])}
    }

    //데이터 세트 크기를 알려주는 함수
    override fun getItemCount(): Int =lockerList.size


    //뷰홀더
    inner class ViewHolder(val binding: ItemLockerSongBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(locker: Locker){
            binding.itemLockerSongTitleTv.text = locker.title
            binding.itemLockerSongSingerTv.text = locker.singer
            binding.itemLockerSongImgIv.setImageResource(locker.coverImg!!)
        }
    }
}
