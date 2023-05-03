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
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
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
import com.example.cocktails.application.CocktailApplication
import com.example.cocktails.databinding.ActivityAddUpdateCocktailBinding
import com.example.cocktails.databinding.DialogCustomImageSelectionBinding
import com.example.cocktails.databinding.DialogCustomListBinding
import com.example.cocktails.model.entities.Cocktail
import com.example.cocktails.utils.Constants
import com.example.cocktails.view.adapters.CustomListItemAdapter
import com.example.cocktails.viewmodel.AllCocktailsViewModel
import com.example.cocktails.viewmodel.AllCocktailsViewModelFactory
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
    private lateinit var customListDialog: Dialog

    private val cocktailViewModel: AllCocktailsViewModel by viewModels {
        AllCocktailsViewModelFactory((application as CocktailApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddUpdateCocktailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()

        binding.ivAddCocktailImage.setOnClickListener(this@AddUpdateCocktailActivity)
        binding.etCategory.setOnClickListener(this@AddUpdateCocktailActivity)
        binding.etGlass.setOnClickListener(this@AddUpdateCocktailActivity)
        binding.etAlcoholic.setOnClickListener(this@AddUpdateCocktailActivity)
        binding.btnAddCocktail.setOnClickListener(this@AddUpdateCocktailActivity)
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
                    customItemsListDialog(
                        resources.getString(R.string.title_select_category),
                        Constants.categories(),
                        Constants.CATEGORY
                    )
                    return
                }
                R.id.et_glass -> {
                    customItemsListDialog(
                        resources.getString(R.string.title_select_glass),
                        Constants.glasses(),
                        Constants.GLASS
                    )
                    return
                }
                R.id.et_alcoholic -> {
                    customItemsListDialog(
                        resources.getString(R.string.title_select_alcoholic),
                        Constants.alcoholic(),
                        Constants.ALCOHOLIC
                    )
                    return
                }
                R.id.btn_add_cocktail -> {
                    val title = binding.etTitle.text.toString().trim { it <= ' ' }
                    val category = binding.etCategory.text.toString().trim { it <= ' ' }
                    val alcoholic = binding.etAlcoholic.text.toString().trim { it <= ' ' }
                    val glass = binding.etGlass.text.toString().trim { it <= ' ' }
                    val instructions = binding.etGlass.text.toString().trim { it <= ' ' }
                    val ingredients = binding.etIngredients.text.toString().trim { it <= ' ' }
                    val measures = binding.etMeasures.text.toString().trim { it <= ' ' }

                    when {
                        // Geef toast-melding als er waarden ontbreken
                        TextUtils.isEmpty(imagePath) -> {
                            Toast.makeText(
                                this@AddUpdateCocktailActivity,
                                resources.getString(R.string.err_msg_select_cocktail_image),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        TextUtils.isEmpty(title) -> {
                            Toast.makeText(
                                this@AddUpdateCocktailActivity,
                                resources.getString(R.string.err_msg_select_cocktail_title),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        TextUtils.isEmpty(category) -> {
                            Toast.makeText(
                                this@AddUpdateCocktailActivity,
                                resources.getString(R.string.err_msg_select_cocktail_category),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        TextUtils.isEmpty(alcoholic) -> {
                            Toast.makeText(
                                this@AddUpdateCocktailActivity,
                                resources.getString(R.string.err_msg_select_cocktail_alcoholic),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        TextUtils.isEmpty(glass) -> {
                            Toast.makeText(
                                this@AddUpdateCocktailActivity,
                                resources.getString(R.string.err_msg_select_cocktail_glass),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        TextUtils.isEmpty(instructions) -> {
                            Toast.makeText(
                                this@AddUpdateCocktailActivity,
                                resources.getString(R.string.err_msg_select_cocktail_instructions),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        TextUtils.isEmpty(ingredients) -> {
                            Toast.makeText(
                                this@AddUpdateCocktailActivity,
                                resources.getString(R.string.err_msg_select_cocktail_ingredients),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        TextUtils.isEmpty(measures) -> {
                            Toast.makeText(
                                this@AddUpdateCocktailActivity,
                                resources.getString(R.string.err_msg_select_cocktail_measures),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {
                            val cocktail: Cocktail = Cocktail(
                                imagePath, Constants.COCKTAIL_IMAGE_SOURCE_LOCAL, title, category,
                                alcoholic, glass, instructions, ingredients, measures,
                                dateModified = "date", false
                            )
                            cocktailViewModel.insert(cocktail)
                            Toast.makeText(
                                this@AddUpdateCocktailActivity,
                                "You successfully added your favorite cocktail details.",
                                Toast.LENGTH_SHORT
                            ).show()
                            Log.e("Insertion", "Success")
                            finish()
                        }

                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Als bij het openen van de camera alles ok is verlopen moet resultcode OK zijn
        if (resultCode == Activity.RESULT_OK) {

            // Requestcode 1 of CAMERA
            if (requestCode == CAMERA) {
                data?.extras?.let {

                    // Haal genomen foto op via de data parameter
                    val thumbnail: Bitmap = data.extras!!.get("data") as Bitmap

                    // Zet image-bitmap om in ImageView
                    Glide.with(this).load(thumbnail).centerCrop().into(binding.ivCocktailImage)

                    // Sla image op in internal storage
                    imagePath = saveImageToInternalStorage(thumbnail)
                    Log.i("ImagePath", imagePath)

                    // Eens foto 1 keer geupdate is moet camera-thumbnail vervangen worden met edit-thumbnail
                    binding.ivAddCocktailImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.ic_vector_edit
                        )
                    )
                }
            }

            // Requestcode 2 of GALLERY
            else if (requestCode == GALLERY) {
                data?.let {
                    val thumbnailUri = data.data

                    // Zet imageURI om in ImageView, en plaats in cache
                    Glide.with(this).load(thumbnailUri)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                Log.e("TAG", "Error loading image", e)

                                // return false zodat de error placeholder kan geplaatst worden
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                resource?.let {
                                    val bitmap: Bitmap = resource.toBitmap()

                                    imagePath = saveImageToInternalStorage(bitmap)

                                    Log.i("ImagePath", imagePath)
                                }
                                return false
                            }

                        })
                        .into(binding.ivCocktailImage)

                    binding.ivAddCocktailImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.ic_vector_edit
                        )
                    )
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.e("Cancelled", "The selection of the image got cancelled by user.")
            }
        }
    }

    /* Functie om custom image dialoog te openen & foto kiezen via camera/gallery*/
    private fun customImageSelectionDialog() {
        val dialog = Dialog(this)

        val binding: DialogCustomImageSelectionBinding =
            DialogCustomImageSelectionBinding.inflate(layoutInflater)

        dialog.setContentView(binding.root)

        // Eventlistener optie camera
        binding.tvCamera.setOnClickListener {
            Dexter.withContext(this).withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ).withListener(
                object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        /* Als alle toestemmingen gegeven zijn in de emulator
                        zal de camera geopend worden om een foto te nemen */
                        report?.let {
                            if (report.areAllPermissionsGranted()) {
                                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                                startActivityForResult(intent, CAMERA)
                            }
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: MutableList<PermissionRequest>?,
                        token: PermissionToken?
                    ) {
                        showRationalDialogForPermissions()
                    }
                }).onSameThread().check()

            dialog.dismiss()
        }

        // Eventlistener optie gallery
        binding.tvGallery.setOnClickListener {
            Dexter.withContext(this).withPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE,
            ).withListener(object : PermissionListener {
                /* Als alle toestemmingen gegeven zijn zal de gallery
                geopend worden om een foto te kiezen */
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    val galleryIntent = Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                    startActivityForResult(galleryIntent, GALLERY)
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
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

    /* Functie om alert-messages te tonen als er geen toestemmingen zijn en moeten
    toegelaten worden via app settings */
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

        // file returnt directory in internal storage
        // MODE_PRIVATE = waar de file enkel toegankelijk is via de aanroepende applicatie
        var file = wrapper.getDir(IMAGE_DIRECTORY, Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.jpg")

        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return file.absolutePath
    }

    private fun customItemsListDialog(title: String, itemsList: List<String>, selection: String) {
        customListDialog = Dialog(this@AddUpdateCocktailActivity)

        val binding: DialogCustomListBinding = DialogCustomListBinding.inflate(layoutInflater)

        customListDialog.setContentView(binding.root)

        binding.tvTitle.text = title

        binding.rvList.layoutManager = LinearLayoutManager(this@AddUpdateCocktailActivity)

        val adapter = CustomListItemAdapter(this@AddUpdateCocktailActivity, itemsList, selection)
        binding.rvList.adapter = adapter

        customListDialog.show()
    }

    fun selectedListItem(item: String, selection: String) {
        when (selection) {
            Constants.CATEGORY -> {
                customListDialog.dismiss()
                binding.etCategory.setText(item)
            }

            Constants.GLASS -> {
                customListDialog.dismiss()
                binding.etGlass.setText(item)
            }

            Constants.ALCOHOLIC -> {
                customListDialog.dismiss()
                binding.etAlcoholic.setText(item)
            }
        }
    }

    companion object {
        private const val CAMERA = 1
        private const val GALLERY = 2
        private const val IMAGE_DIRECTORY = "FavDishImages"
    }
}