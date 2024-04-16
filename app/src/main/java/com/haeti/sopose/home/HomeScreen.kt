package com.haeti.sopose.home

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.haeti.sopose.R
import com.haeti.sopose.common.extensions.VerticalSpacer
import com.haeti.sopose.common.profile.BirthdayProfile
import com.haeti.sopose.common.profile.FriendProfile
import com.haeti.sopose.common.profile.MusicProfile
import com.haeti.sopose.common.profile.MyProfile
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

val friendTypeSaver: Saver<List<FriendType>, *> = Saver(
    save = { list ->
        list.map { friend ->
            when (friend) {
                is FriendType.BirthdayFriend -> "BirthdayFriend,${friend.psa},${friend.name},${friend.birthday}"
                is FriendType.Friend -> "Friend,${friend.psa},${friend.name}"
                is FriendType.MusicianFriend -> "MusicianFriend,${friend.psa},${friend.name},${friend.music}"
            }
        }
    },
    restore = { savedList ->
        savedList.map { savedString ->
            val parts = savedString.split(',')
            when (parts[0]) {
                "BirthdayFriend" -> FriendType.BirthdayFriend(
                    parts[1].toInt(),
                    parts[2],
                    parts.getOrNull(3) ?: ""
                )

                "Friend" -> FriendType.Friend(parts[1].toInt(), parts[2])

                "MusicianFriend" -> FriendType.MusicianFriend(
                    parts[1].toInt(),
                    parts[2],
                    parts.getOrNull(3) ?: ""
                )

                else -> throw IllegalArgumentException("Unknown FriendType")
            }
        }
    }
)


@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    val context = LocalContext.current
    val homeState by homeViewModel.collectAsState()
    val friendList = rememberSaveable(stateSaver = friendTypeSaver) {
        mutableStateOf(
            listOf(
                FriendType.BirthdayFriend(
                    psa = R.drawable.img_fox,
                    name = "박호연",
                    birthday = "3월 20일"
                ),
                FriendType.Friend(psa = R.drawable.img_fox, name = "강유리"),
                FriendType.Friend(psa = R.drawable.img_fox, name = "박강희"),
                FriendType.MusicianFriend(
                    psa = R.drawable.img_fox,
                    name = "최다민",
                    music = "밤양갱 - 비비"
                ),
                FriendType.Friend(psa = R.drawable.img_fox, name = "조관희"),
                FriendType.Friend(psa = R.drawable.img_fox, name = "박동민"),
                FriendType.MusicianFriend(
                    psa = R.drawable.img_fox,
                    name = "차기안팟장",
                    music = "Time Travel - 빈지노"
                ),
                FriendType.Friend(psa = R.drawable.img_fox, name = "김지영"),
                FriendType.MusicianFriend(
                    psa = R.drawable.img_fox,
                    name = "전채연",
                    music = "No Pain - 실리카겔"
                ),
                FriendType.Friend(psa = R.drawable.img_fox, name = "이다은"),
                FriendType.Friend(psa = R.drawable.img_fox, name = "김준서"),
                FriendType.Friend(psa = R.drawable.img_fox, name = "누누"),
            )
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            MyProfile(
                currentName = homeState.name,
                onNameChange = homeViewModel::updateName
            )
            VerticalSpacer(height = 8.dp)
        }

        items(friendList.value.size) { index ->
            when (val friend = friendList.value[index]) {
                is FriendType.BirthdayFriend -> BirthdayProfile(
                    birthday = friend.birthday,
                    image = friend.psa,
                    name = friend.name
                )

                is FriendType.Friend -> FriendProfile(
                    image = friend.psa,
                    name = friend.name
                )

                is FriendType.MusicianFriend -> MusicProfile(
                    image = friend.psa,
                    name = friend.name,
                    music = friend.music
                )
            }
        }
    }

    homeViewModel.collectSideEffect {
        when (it) {
            is HomeSideEffect.NameChangeToast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview() {
    HomeScreen(homeViewModel = hiltViewModel())
}