package com.littlelemon.littlelemon

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlin.Int

@Serializable
data class MenuNetwork (
    @SerialName("menu") val menu: List<MenuItemNetwork>
) {
    @Serializable
    data class MenuItemNetwork(
        @SerialName("id") val id: Int,
        @SerialName("title") val title: String,
        @SerialName("description") val description: String,
        @SerialName("price") val price: String,
        @SerialName("image") val image: String,
    ) {
        fun toMenuItem(): MenuItem {
            return MenuItem(
                id = id,
                title = title,
                description = description,
                price = price,
                image = image
            )
        }
    }
}

