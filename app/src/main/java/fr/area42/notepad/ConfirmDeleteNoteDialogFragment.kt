package fr.area42.notepad

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment

class ConfirmDeleteNoteDialogFragment() : DialogFragment() {
    var noteTitle: String? = ""

    companion object {
        val EXTRA_NOTE_TITLE = "noteTitle"

        fun newInstance(noteTitle: String): ConfirmDeleteNoteDialogFragment {
            val fragment = ConfirmDeleteNoteDialogFragment()
            fragment.arguments = Bundle().apply {
                putString(EXTRA_NOTE_TITLE, noteTitle)
            }
            return fragment
        }
    }

    interface ConfirmDeleteDialogListener {
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()
    }

    var listener: ConfirmDeleteDialogListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteTitle = arguments!!.getString(EXTRA_NOTE_TITLE)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)

        builder.setMessage("Do you want delete note \"${noteTitle}\" ?")
            .setPositiveButton("Delete",
                DialogInterface.OnClickListener { dialog, id -> listener?.onDialogPositiveClick() })
            .setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialog, id -> listener?.onDialogNegativeClick() })

        return builder.create()
    }
}