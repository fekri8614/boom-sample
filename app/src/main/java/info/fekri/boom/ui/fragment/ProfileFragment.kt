package info.fekri.boom.ui.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import info.fekri.boom.R
import info.fekri.boom.databinding.FragmentProfileBinding
import info.fekri.boom.databinding.ItemDialogChangeNameBinding
import info.fekri.boom.extra.KEY_PUT_NAME_SHARED

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val REQUEST_CODE = 100
    private lateinit var mContext: Context
    private lateinit var mSharedPreferences : SharedPreferences

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
        mContext = view.context
        mSharedPreferences = mContext.getSharedPreferences("data", Context.MODE_PRIVATE)

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

        val name = mSharedPreferences.getString(KEY_PUT_NAME_SHARED, "Your name is not set!")
        binding.txtUserName.text = name
    }
    private fun setUserName(dialogBinding: ItemDialogChangeNameBinding) {

        val userName = dialogBinding.edtNameChange.text.toString()
        mSharedPreferences.edit().putString(KEY_PUT_NAME_SHARED, userName).apply()

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