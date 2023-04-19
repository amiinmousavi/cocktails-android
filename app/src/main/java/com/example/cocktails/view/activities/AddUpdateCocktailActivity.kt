package com.example.cocktails.view.activities

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.cocktails.R
import com.example.cocktails.databinding.ActivityAddUpdateCocktailBinding
import com.example.cocktails.databinding.DialogCustomImageSelectionBinding
import com.example.cocktails.databinding.DialogCustomListBinding
import com.example.cocktails.utils.Constants
import com.example.cocktails.view.adapters.CustomListItemAdapter
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

class AddUpdateCocktailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityAddUpdateCocktailBinding
    private var imagePath: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUpdateCocktailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()

        binding.ivAddCocktailImage.setOnClickListener(this)
        binding.tilCategory.setOnClickListener(this)
        binding.tilGlass.setOnClickListener(this)
        binding.tilAlcoholic.setOnClickListener(this)
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbarAddCocktailActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarAddCocktailActivity.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.iv_add_cocktail_image -> {
                    customImageSelectionDialog()
                    return
                }
                R.id.et_category -> {
                    customItemsListDialog(resources.getString(R.string.title_select_category),
                    Constants.categories(),
                    Constants.CATEGORY)
                    return
                }
                R.id.et_glass -> {
                    customItemsListDialog(resources.getString(R.string.title_select_glass),
                    Constants.glasses(),
                    Constants.GLASS)
                    return
                }
                R.id.et_alcoholic -> {
                    customItemsListDialog(resources.getString(R.string.title_select_alcoholic),
                    Constants.alcoholic(),
                    Constants.ALCOHOLIC)
                    return
                }
            }
        }
    }

    private fun customImageSelectionDialog() {
        val dialog = Dialog(this)
        val binding: DialogCustomImageSelectionBinding =
            DialogCustomImageSelectionBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        binding.tvCamera.setOnClickListener {
            Dexter.withContext(this).withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ).withListener(
                object : MultiplePermissionsListener {

                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        // Als alle toestemmingen gegeven zijn in de emulator
                        // zal de camera geopend worden om een foto te nemen
                        report?.let{
                            if (report.areAllPermissionsGranted()) {
                                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                                startActivityForResult(intent, CAMERA)
                            }
                        }
                    }
                    // Als er geen toestemming gegeven is:
                    override fun onPermissionRationaleShouldBeShown(
                        permissions: MutableList<PermissionRequest>?,
                        token: PermissionToken?
                    ) {
                        showRationalDialogForPermissions()
                    }

                }
            ).onSameThread().check()

            dialog.dismiss()
        }

        binding.tvGallery.setOnClickListener {
            Dexter.withContext(this).withPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE,
            ).withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    val galleryIntent = Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(galleryIntent, GALLERY)
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    Toast.makeText(
                        this@AddUpdateCocktailActivity,
                        "You have denied the storage permission",
                        Toast.LENGTH_SHORT
                    )
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                    showRationalDialogForPermissions()
                }
            }).onSameThread().check()

            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Als bij het openen van de camera alles ok is verlopen moet resultcode OK zijn
        if(resultCode == Activity.RESULT_OK) {
            // Als de request code CAMERA, dus het nummer 1, was bij het oproepen van de activity:
            if(requestCode == CAMERA){
                data?.extras?.let{
                    // genomen foto ophalen
                    val thumbnail : Bitmap = data.extras!!.get("data") as Bitmap

                    Glide.with(this).load(thumbnail).centerCrop().into(binding.ivCocktailImage)

                    imagePath = saveImageToInternalStorage(thumbnail)
                    Log.i("ImagePath", imagePath)

                    // eens foto 1 keer geupdate is moet camera-thumbnail vervangen worden met edit-thumbnail
                    binding.ivAddCocktailImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_vector_edit))
                }
            }
            if(requestCode == GALLERY){
                data?.let{
                    val thumbnailUri = data.data

                    Glide.with(this).load(thumbnailUri)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .listener(object: RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                TODO("Not yet implemented")
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                resource?.let{
                                    val bitmap: Bitmap = resource.toBitmap()
                                    imagePath = saveImageToInternalStorage(bitmap)
                                    Log.i("ImagePath", imagePath)
                                }
                                return false
                            }

                        })
                        .into(binding.ivCocktailImage)

                    binding.ivAddCocktailImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_vector_edit))
                }
            } else if(resultCode == Activity.RESULT_CANCELED){
                Log.e("Cancelled", "The selection of the image got cancelled by user.")
            }
        }
    }
    private fun showRationalDialogForPermissions() {
        AlertDialog.Builder(this)
            .setMessage(
                "It looks like you have turned off permissions required for this feature. " +
                        "Please check your Application settings."
            )
            .setPositiveButton("GO TO SETTINGS") { _, _ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun saveImageToInternalStorage(bitmap: Bitmap): String {
        val wrapper = ContextWrapper(applicationContext)

        var file = wrapper.getDir(IMAGE_DIRECTORY, Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.jpg")

        try{
            val stream : OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        }catch (e: IOException){
           e.printStackTrace()
        }

        return file.absolutePath
    }

    private fun customItemsListDialog(
        title: String,
        itemsList: List<String>,
        selection: String){

        val customListDialog = Dialog(this)
        val binding : DialogCustomListBinding = DialogCustomListBinding.inflate(layoutInflater)

        customListDialog.setContentView(binding.root)

        binding.tvTitle.text = title

        binding.rvList.layoutManager = LinearLayoutManager(this)

        val adapter = CustomListItemAdapter(this, itemsList, selection)
        binding.rvList.adapter = adapter
        customListDialog.show()
    }

    companion object {
        private const val CAMERA = 1
        private const val GALLERY = 2

        private const val IMAGE_DIRECTORY = "FavDishImages"
    }
}