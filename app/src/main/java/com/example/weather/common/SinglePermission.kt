package com.example.weather.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.weather.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@ExperimentalPermissionsApi
@Composable
fun RequestPermission(
    permission: String,
    initialContent: @Composable (() -> Unit) -> Unit,
    grantedContent: @Composable () -> Unit,
    onPermissionGranted: () -> Unit,
    rationaleMessage: String = stringResource(R.string.to_use_this_app_functionalities_you_need_to_give_us_the_permission)
) {
    val permissionState = rememberPermissionState(permission)

    HandleRequest(
        permissionState = permissionState,
        grantedContent = grantedContent,
        onPermissionGranted = onPermissionGranted,
        deniedContent = { shouldShowRationale ->
            PermissionDeniedContent(
                shouldShowRationale = shouldShowRationale,
                rationaleMessage = rationaleMessage,
                initialContent = initialContent,
                onRequestPermission = {
                    permissionState.launchPermissionRequest()
                },
            )
        },
    )
}

@ExperimentalPermissionsApi
@Composable
private fun HandleRequest(
    permissionState: PermissionState,
    grantedContent: @Composable () -> Unit,
    onPermissionGranted: () -> Unit,
    deniedContent: @Composable (Boolean) -> Unit
) {
    when (permissionState.status) {
        is PermissionStatus.Granted -> {
            grantedContent()
            onPermissionGranted()
        }

        is PermissionStatus.Denied -> {
            deniedContent(permissionState.status.shouldShowRationale)
        }
    }
}

@ExperimentalPermissionsApi
@Composable
fun PermissionDeniedContent(
    shouldShowRationale: Boolean,
    rationaleMessage: String,
    initialContent: @Composable (() -> Unit) -> Unit,
    onRequestPermission: () -> Unit
) {
    val shouldShowDialog = remember { mutableStateOf(true) }
    if (shouldShowRationale && shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(
                    text = stringResource(R.string.permission_request),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            text = {
                Text(rationaleMessage)
            },
            confirmButton = {
                Button(onClick = onRequestPermission) {
                    Text(stringResource(R.string.give_permission))
                }
            },
            dismissButton = {
                Button(onClick = { shouldShowDialog.value = false }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    } else {
        initialContent(onRequestPermission)
    }
}
