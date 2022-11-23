package com.crexative.jetpackcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.crexative.jetpackcompose.ReactionType.*

@Composable
fun TwitterCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Image",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                    alignment = Alignment.Center
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f)
            ) {
                TwitterCardHeader(Modifier.fillMaxWidth())
                TwitterCardContent(Modifier.fillMaxWidth())
                TwitterCardFooter(Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
fun TwitterCardFooter(modifier: Modifier) {
    var commentsActive by remember { mutableStateOf(false) }
    var numberComments by remember { mutableStateOf(2) }

    var rtActive by remember { mutableStateOf(false) }
    var numberRt by remember { mutableStateOf(10) }

    var likesActive by remember { mutableStateOf(false) }
    var numberLikes by remember { mutableStateOf(19) }

    Row(modifier = modifier.padding(top = 12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        ReactionItem(numberComments, commentsActive, type = COMMENT) {
            commentsActive = it
            if (it) numberComments++ else numberComments--
        }

        ReactionItem(numberRt, rtActive, type = REPOST) {
            rtActive = it
            if (it) numberRt++ else numberRt--
        }

        ReactionItem(numberLikes, likesActive, type = LIKES) {
            likesActive = it
            if (it) numberLikes++ else numberLikes--
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = "share",
            tint = Color.Gray
        )

    }
}

@Composable
fun ReactionItem(
    number: Int,
    isActive: Boolean,
    type: ReactionType = COMMENT,
    onClick: (Boolean) -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onClick(!isActive)
        }) {

        val icon = when (type) {
            COMMENT -> if (isActive) R.drawable.ic_chat_filled else R.drawable.ic_chat
            REPOST -> if (isActive) R.drawable.ic_rt else R.drawable.ic_rt
            LIKES -> if (isActive) R.drawable.ic_like_filled else R.drawable.ic_like
        }

        val tint = when (type) {
            COMMENT -> if (isActive) Color.Gray else Color.Gray
            REPOST -> if (isActive) Color.Green else Color.Gray
            LIKES -> if (isActive) Color.Red else Color.Gray
        }

        Icon(
            painter = painterResource(id = icon),
            contentDescription = "message",
            modifier = Modifier.padding(end = 3.dp),
            tint = tint
        )
        Text(text = "$number")
    }
}

enum class ReactionType {
    COMMENT,
    REPOST,
    LIKES
}

@Composable
fun TwitterCardContent(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "lorem ipsum generator the text for content in the twitter card example",
            fontSize = 16.sp,
            color = Color.Black
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Image",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(180.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun TwitterCardHeader(modifier: Modifier) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(modifier = modifier.padding(end = 24.dp)) {
            Text(text = "El tiempo", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(
                text = "@ElTiempo * 11h es mas grande",
                fontSize = 20.sp,
                color = Color.LightGray,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "options",
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }

}

@Preview
@Composable
fun previewTwitterCard() {
    TwitterCard()
}