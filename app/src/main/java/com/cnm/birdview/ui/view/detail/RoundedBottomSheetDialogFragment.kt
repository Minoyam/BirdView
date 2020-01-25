package com.cnm.birdview.ui.view.detail

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import com.cnm.birdview.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class RoundedBottomSheetDialogFragment : BottomSheetDialogFragment() {


    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        Handler().postDelayed({
            dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }, 200)
        return dialog

    }

}