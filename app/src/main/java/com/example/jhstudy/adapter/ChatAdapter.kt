package com.example.jhstudy.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jhstudy.data.ChatData
import com.example.jhstudy.databinding.ItemMessageCameraMeBinding
import com.example.jhstudy.databinding.ItemMessageCameraYouBinding
import com.example.jhstudy.databinding.ItemMessageImageMeBinding
import com.example.jhstudy.databinding.ItemMessageImageYouBinding
import com.example.jhstudy.databinding.ItemMessageTextMeBinding
import com.example.jhstudy.databinding.ItemMessageTextYouBinding

enum class Type {
    TEXT_RIGHT, TEXT_LEFT, IMAGE_RIGHT, IMAGE_LEFT , CAMERA_RIGHT, CAMERA_LEFT
}

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var adapterList = mutableListOf<ChatData>()

    fun set(list: List<ChatData>) {
        adapterList.clear()
        adapterList.addAll(list)
        notifyDataSetChanged()
    }

    fun addMessage(message: ChatData) {
        adapterList.add(message)
        notifyItemInserted(adapterList.lastIndex)
        Log.d("add11",message.toString())
    }


    fun lastMassageIsMe() = if (adapterList.isEmpty()) true
    else !(adapterList.last() as ChatData.BaseItem).isMe

    inner class RightViewHolder(private val textMeBinding: ItemMessageTextMeBinding) :
        RecyclerView.ViewHolder(textMeBinding.root) {
        fun textMeBind(chatData: ChatData.TextItem) {
            textMeBinding.tvMessage.text = chatData.massage
        }
    }

    inner class LeftViewHolder(private val textYouBinding: ItemMessageTextYouBinding) :
        RecyclerView.ViewHolder(textYouBinding.root) {
        fun textYouBind(chatData: ChatData.TextItem) {
            textYouBinding.tvMessage.text = chatData.massage
        }
    }


    inner class ImageRightViewHolder(private val ImageMeBinding: ItemMessageImageMeBinding) :
        RecyclerView.ViewHolder(ImageMeBinding.root) {
        fun imageMeBind(chatData: ChatData.ImageItem) {
            Glide.with(ImageMeBinding.root)
                .load(chatData.image)
                .into(ImageMeBinding.imageMe)
        }
    }

    inner class ImageLeftViewHolder(private val ImageYouBinding: ItemMessageImageYouBinding) :
        RecyclerView.ViewHolder(ImageYouBinding.root) {
        fun imageYouBind(chatData: ChatData.ImageItem) {
            Glide.with(ImageYouBinding.root)
                .load(chatData.image)
                .into(ImageYouBinding.imageYou)
        }
    }

    inner class CameraRightViewHolder(private val CameraMeBinding: ItemMessageCameraMeBinding):
    RecyclerView.ViewHolder(CameraMeBinding.root){
        fun cameraMeBind(chatData: ChatData.CameraItem){
            Glide.with(CameraMeBinding.root)
                .load(chatData.camera)
                .into(CameraMeBinding.cameraMe)
        }
    }

    inner class CameraLeftViewHolder(private val CameraYouBinding: ItemMessageCameraYouBinding):
        RecyclerView.ViewHolder(CameraYouBinding.root){
        fun cameraYouBind(chatData: ChatData.CameraItem){
            Glide.with(CameraYouBinding.root)
                .load(chatData.camera)
                .into(CameraYouBinding.cameraYou)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (val chatItem = adapterList.getOrNull(position)) {
            is ChatData.TextItem -> {
                if (chatItem.isMe) Type.TEXT_RIGHT.ordinal else Type.TEXT_LEFT.ordinal
            }

            is ChatData.ImageItem -> {
                if (chatItem.isMe) Type.IMAGE_RIGHT.ordinal else Type.IMAGE_LEFT.ordinal
            }

            is ChatData.CameraItem ->{
                if(chatItem.isMe) Type.CAMERA_RIGHT.ordinal else Type.CAMERA_LEFT.ordinal
            }

            else -> {
                Type.TEXT_RIGHT.ordinal
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            Type.TEXT_RIGHT.ordinal ->{
                RightViewHolder(
                    ItemMessageTextMeBinding.inflate(layoutInflater, parent, false)
                )
            }
            Type.TEXT_LEFT.ordinal ->{
                LeftViewHolder(
                    ItemMessageTextYouBinding.inflate(layoutInflater,parent,false)
                )
            }
            Type.IMAGE_RIGHT.ordinal ->{
                ImageRightViewHolder(
                    ItemMessageImageMeBinding.inflate(layoutInflater,parent,false)
                )
            }
            Type.IMAGE_LEFT.ordinal ->{
                ImageLeftViewHolder(
                    ItemMessageImageYouBinding.inflate(layoutInflater,parent,false)
                )
            }
            Type.CAMERA_RIGHT.ordinal ->{
                CameraRightViewHolder(
                    ItemMessageCameraMeBinding.inflate(layoutInflater,parent,false)
                )
            }
            Type.CAMERA_LEFT.ordinal ->{
                CameraLeftViewHolder(
                    ItemMessageCameraYouBinding.inflate(layoutInflater,parent,false)
                )
            }

            else -> {
                RightViewHolder(
                    ItemMessageTextMeBinding.inflate(layoutInflater, parent, false)
                )
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when (holder) {
            is LeftViewHolder -> {
                holder.textYouBind(adapterList[position] as ChatData.TextItem)
            }

            is RightViewHolder -> {
                holder.textMeBind(adapterList[position] as ChatData.TextItem)
            }

            is ImageLeftViewHolder ->{
                holder.imageYouBind(adapterList[position] as ChatData.ImageItem)
            }

            is ImageRightViewHolder ->{
                holder.imageMeBind(adapterList[position] as ChatData.ImageItem)
            }

            is CameraLeftViewHolder ->{
                holder.cameraYouBind(adapterList[position] as ChatData.CameraItem)
            }

            is CameraRightViewHolder ->{
                holder.cameraMeBind(adapterList[position] as ChatData.CameraItem)
            }

            else -> Unit // 에러처리
        }
    }


    override fun getItemCount(): Int = adapterList.size


}