package com.example.jhstudy.data

import android.net.Uri

sealed class ChatData{
    interface BaseItem {
        val isMe:Boolean
    }

    data class TextItem(
        val massage : String,
        override val isMe:Boolean = true
    ): ChatData(), BaseItem

    data class ImageItem(
        val image : String,
        override val isMe:Boolean = true
    ): ChatData(), BaseItem

    data class CameraItem(
        val camera : Uri,
        override val isMe: Boolean = true
    ): ChatData(), BaseItem
}


