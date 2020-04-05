package com.example.core_presentation_api.view

import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.core_presentation_api.dialog.DialogFactory

abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {
    protected fun showErrorNetworkDialog(onClick: () -> Unit, onCancel: () -> Unit) {
        context?.let { context ->
            DialogFactory.showDialogWithOnButton(
                context,
                "test",
                "test",
                "test",
                { dialogInterface ->
                    dialogInterface.cancel()
                    onClick()
                },
                {
                    onCancel()
                }
            )
        }
    }

    protected fun notify(@StringRes message: Int) {
        Toast.makeText(context, getString(message), Toast.LENGTH_SHORT).show()
    }
}