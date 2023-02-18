package info.fekri.boom.ui.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import info.fekri.boom.R
import info.fekri.boom.databinding.FragmentProfileBinding
import info.fekri.boom.databinding.ItemDialogChangeNameBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val REQUEST_CODE = 100

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userImageUi()
        userNameUi()
    }

    private fun userNameUi() {
        binding.cardLayoutName.setOnLongClickListener {
            val dialog = AlertDialog.Builder(context).create()
            val dialogBinding = ItemDialogChangeNameBinding.inflate(layoutInflater)
            dialog.setView(dialogBinding.root)
            dialog.setCancelable(true)
            dialog.show()

            dialogBinding.btnCancelChange.setOnClickListener {
                dialog.dismiss()
            }
            dialogBinding.btnAcceptChange.setOnClickListener {
                if (dialogBinding.edtNameChange.text!!.isNotEmpty()) setUserName(dialogBinding)
                else dialog.dismiss()
                dialog.dismiss()
            }

            true
        }
    }
    private fun setUserName(dialogBinding: ItemDialogChangeNameBinding) {
        binding.txtUserName.text = dialogBinding.edtNameChange.text.toString()
    }

    private fun userImageUi() {
        binding.imgCoverUser.setOnLongClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_CODE)

            true
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri: Uri = data.data ?: return
            loadImage(imageUri)
        }
    }
    private fun loadImage(imageUri: Uri) {
        Glide
            .with(requireContext())
            .load(imageUri)
            .error(R.drawable.broken_img)
            .into(binding.imgCoverUser)
    }

}