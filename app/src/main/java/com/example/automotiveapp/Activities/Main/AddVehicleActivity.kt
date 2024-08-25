package com.example.automotiveapp.Activities.Main

import com.example.automotiveapp.Model.Vehicle
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.automotiveapp.Activities.StartUp.MainActivity
import com.example.automotiveapp.R
import com.example.automotiveapp.Untills.Const
import com.example.automotiveapp.Untills.Functions
import com.example.automotiveapp.Untills.LoadingDialog
import com.example.automotiveapp.databinding.ActivityAddVehicleBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

class AddVehicleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddVehicleBinding

    private lateinit var loadingDialog: LoadingDialog

    private var databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("vehicles")

    private var mContext = this
    private lateinit var btnUploadProperty: Button

    private lateinit var spPropertyPurpose: Spinner
    private lateinit var spPropertyType: Spinner
    private lateinit var spPropertyCity: Spinner
    private lateinit var spPropertyAreaSize: Spinner

    private lateinit var etTitle: EditText
    private lateinit var etPrice: EditText
    private lateinit var etDescription: EditText
    private lateinit var etMake: EditText
    private lateinit var etModel: EditText
    private lateinit var etVersion: EditText
    private lateinit var etRegion: EditText
    private lateinit var etExterior: EditText
    private lateinit var etMileage: EditText
    private lateinit var etEngineCapacity: EditText
    private lateinit var etEmailAddress: EditText
    private lateinit var etContactNumber: EditText
    private lateinit var etContactNumber1: EditText
    private lateinit var tvAddImg: TextView

    private var propertyId = ""
    private var imgUrl = ""

    private var propertyPurpose = ""
    private var propertyType = ""
    private var city = ""
    private var areaSize = ""

    private var id = ""
    private var title = ""
    private var price = ""
    private var make = ""
    private var model = ""
    private var modelYear = ""
    private var version = ""
    private var exterior = ""
    private var mileage = ""
    private var region = ""
    private var engineCapacity = ""
    private var vehicleDescription = ""
    private var emailAddress = ""
    private var contactNumber = ""
    private var contactNumber1 = ""


    private val requestPickImage = 2
    private val cameraPermissionCode = 101
    private val requestImageCapture = 1
    private val galleryRequestCode = 2

    private var storageRef = FirebaseStorage.getInstance().reference.child("Vehicle Pictures")

    private var selectedFileURI: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddVehicleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        clickListeners()


        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    private fun initUI() {


        loadingDialog = LoadingDialog(this)



        spPropertyPurpose = findViewById(R.id.sp_property_purpose)
        spPropertyType = findViewById(R.id.sp_property_type)
        spPropertyCity = findViewById(R.id.sp_property_city)
        spPropertyAreaSize = findViewById(R.id.sp_area_size)

        etTitle = findViewById(R.id.et_title)
        etPrice = findViewById(R.id.et_price)
        etDescription = findViewById(R.id.et_description)
        etMake = findViewById(R.id.et_Make)
        etModel = findViewById(R.id.et_model)
        etVersion = findViewById(R.id.et_version)
        etRegion = findViewById(R.id.et_region)
        etExterior = findViewById(R.id.et_exterior)
        etMileage = findViewById(R.id.et_mileage)
        etEngineCapacity = findViewById(R.id.et_engine_capacity)
        etEmailAddress = findViewById(R.id.et_email)
        etContactNumber = findViewById(R.id.et_contact_number)
        etContactNumber1 = findViewById(R.id.et_contact_number_1)

        btnUploadProperty = findViewById(R.id.btnUploadProperty)

        tvAddImg = findViewById(R.id.tv_add_img)

    }

    private fun clickListeners() {

        val propertyTitleAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.spinner_property_title_items,
            android.R.layout.simple_spinner_item
        )


        tvAddImg.setOnClickListener {

            val options = arrayOf("Camera", "Gallery")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Choose Option")
            builder.setItems(options) { dialog, which ->

                when (which) {
                    1 -> openGallery()    // Open gallery for images

                    0 -> {
                        if (checkCameraPermission()) {

                            openCamera()

                        } else {

                            requestCameraPermission()

                        }
                    }

                    1 -> {
                        val pickPhotoIntent =
                            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        startActivityForResult(pickPhotoIntent, requestPickImage)
                    }
                }
            }
            builder.show()
        }

        btnUploadProperty.setOnClickListener {

            validateInputs()

        }

    }

    private fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, requestImageCapture)
        }
    }

    private fun requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                cameraPermissionCode
            )
        } else {

            openCamera()
        }
    }

    private val pickImage =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                binding.ivImg.visibility = View.VISIBLE
                binding.llImg.visibility = View.GONE
                Glide.with(mContext).load(uri).into(binding.ivImg)
            }
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == Const.PICK_FILE_REQUEST_CODE) {
            data?.data?.let { uri ->
                if (!uri.path.isNullOrEmpty()) {
                    selectedFileURI = uri
                    binding.ivImg.visibility = View.VISIBLE
                    binding.llImg.visibility = View.GONE
                    Glide.with(mContext).load(uri).into(binding.ivImg)
                }
            }
        }
    }


    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, galleryRequestCode)
    }

    private fun validateInputs() {

        propertyPurpose = spPropertyPurpose.selectedItem.toString()
        propertyType = spPropertyType.selectedItem.toString()
        city = spPropertyCity.selectedItem.toString()
        areaSize = spPropertyAreaSize.selectedItem.toString()

        title = etTitle.text.toString().trim()
        price = etPrice.text.toString().trim()
        mileage = etMileage.text.toString().trim()
        version = etVersion.text.toString().trim()
        engineCapacity = etEngineCapacity.text.toString().trim()
        exterior = etExterior.text.toString().trim()
        make = etMake.text.toString().trim()
        model = etModel.text.toString().trim()
        region = etRegion.text.toString().trim()
        vehicleDescription = etDescription.text.toString().trim()
        emailAddress = etEmailAddress.text.toString().trim()
        contactNumber = etContactNumber.text.toString().trim()
        contactNumber1 = etContactNumber1.text.toString().trim()


        // If all fields are filled, proceed to upload property image
        // Add Validations here when all field will be validated then call uploadPropertyImage() function

        // Check if any of the required fields are empty
        if (title.isEmpty() || price.isEmpty() || mileage.isEmpty() || version.isEmpty() ||
            engineCapacity.isEmpty() || exterior.isEmpty() || make.isEmpty() || model.isEmpty() ||
            region.isEmpty() || vehicleDescription.isEmpty() || emailAddress.isEmpty() ||
            contactNumber.isEmpty() || contactNumber1.isEmpty()
        ) {
            // If any field is empty, display a toast message
            Toast.makeText(
                this,
                "Please fill in all fields",
                Toast.LENGTH_SHORT
            ).show()
        }

        uploadPropertyImage()

    }

    private fun uploadPropertyImage() {

        propertyId = databaseReference.push().key.toString()

        val filePath: StorageReference = storageRef.child("$propertyId.jpg")

        loadingDialog.show()

        filePath.putFile(selectedFileURI!!)
            .addOnSuccessListener { taskSnapshot: UploadTask.TaskSnapshot ->
                val firebaseUri =
                    taskSnapshot.storage.downloadUrl
                firebaseUri.addOnSuccessListener { uri: Uri ->

                    selectedFileURI = null
                    imgUrl = uri.toString()

                    uploadVehicle()

                }.addOnFailureListener { e: Exception ->

                    loadingDialog.dismiss()
                    Toast.makeText(this, "" + e.message, Toast.LENGTH_SHORT).show()

                }
            }.addOnFailureListener { e: Exception ->

                loadingDialog.dismiss()
                Toast.makeText(this, "" + e.message, Toast.LENGTH_SHORT).show()

            }

    }

    private fun uploadVehicle() {
        // Get user input values

        // Create property object
        var vehicle = Vehicle(
            id,
            modelYear,
            propertyPurpose,
            propertyType,
            city,
            areaSize,
            model,
            make,
            region,
            engineCapacity,
            price,
            exterior,
            version,
            vehicleDescription,
            emailAddress,
            contactNumber,
            contactNumber1,
            imgUrl,

            )

        databaseReference.child(propertyId).setValue(vehicle)
            .addOnSuccessListener {

                loadingDialog.dismiss()

                Toast.makeText(this, "Vehicle uploaded successfully", Toast.LENGTH_SHORT)
                    .show()

                refreshBuyACarFragment()
                finish() // Close activity after uploading

            }
            .addOnFailureListener {

                loadingDialog.dismiss()
                Toast.makeText(this, "Failed to upload vehicle", Toast.LENGTH_SHORT).show()

            }

    }

    private fun refreshBuyACarFragment() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("refreshBuyACarFragment", true)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}
