package com.cnm.birdview.ui.view

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import com.cnm.birdview.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class RoundedBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.isHideable = false
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        return dialog

    }


}