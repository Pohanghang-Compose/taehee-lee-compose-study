package com.haeti.sopose.home

sealed class FriendType {
    data class Friend(
        val psa: Int,
        val name: String,
    ) : FriendType()

    data class MusicianFriend(
        val psa: Int,
        val name: String,
        val music: String,
    ) : FriendType()

    data class BirthdayFriend(
        val psa: Int,
        val name: String,
        val birthday: String,
    ) : FriendType()
}
