package com.crexative.jetpackcompose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun MyAlertDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (!show) return
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Titulo")
        },
        text = {
            Text(text = "Descripción del diálogo")
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = "Confirm Button")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Dismiss Button")
            }
        }
    )
}

@Composable
fun MySimpleCustomDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (!show) return

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = true
        )
    ) {
        Column(
            Modifier
                .background(Color.White)
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Esto es un ejemplo")
        }
    }

}

@Composable
fun MyCustomDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (!show) return

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = true
        )
    ) {
        Column(
            Modifier
                .background(Color.White)
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            MyTitleDialog(text = "Set backup account")

            AccountItem("correo@correo.com", R.drawable.ic_launcher_foreground)
            AccountItem("andy@gmail.com", R.drawable.ic_launcher_foreground)
            AccountItem("Añadir nueva cuenta", R.drawable.ic_launcher_foreground)
        }
    }
}

@Composable
fun MyTitleDialog(text: String, modifier: Modifier =  Modifier.padding(bottom = 12.dp)) {
    Text(
        text = text, fontWeight = FontWeight.SemiBold, fontSize = 18.sp,
        modifier = modifier
    )
}

@Composable
fun AccountItem(email: String, @DrawableRes drawable: Int) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = email,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(40.dp)
                .clip(CircleShape)
        )
        Text(text = email, fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun MyConfirmationDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (!show) return

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = true
        )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            MyTitleDialog(text = "Phone ringtone", modifier = Modifier.padding(24.dp))
            Divider(Modifier.fillMaxWidth(), color = Color.LightGray)

            var status by remember { mutableStateOf("") }
            MyRadioButtonList(name = status, onItemSelected = { status = it} )

            Divider(Modifier.fillMaxWidth(), color = Color.LightGray)
            Row(modifier = Modifier.align(Alignment.End).padding(8.dp)) {
                TextButton(onClick = onDismiss) {
                    Text(text = "Cancel")
                }
                TextButton(onClick = onConfirm) {
                    Text(text = "Ok")
                }
            }
        }
    }
}