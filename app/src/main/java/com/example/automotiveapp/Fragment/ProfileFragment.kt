package com.example.automotiveapp.Fragment

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.alhamdanautomotive.Utills.Constants
import com.example.automotiveapp.Activities.Main.ContactUsActivity
import com.example.automotiveapp.Activities.Main.SettingsActivity
import com.example.automotiveapp.Activities.StartUp.LoginActivity
import com.example.automotiveapp.Activities.StartUp.MainActivity
import com.example.automotiveapp.Model.Users
import com.example.automotiveapp.R
import com.example.automotiveapp.Untills.SharedPref
import com.example.automotiveapp.databinding.FragmentProfileBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var sp: SharedPref
    private lateinit var databaseReference: DatabaseReference
    private lateinit var currentUser: Users
    private lateinit var language_change: LinearLayout
    private lateinit var tvContacUs: TextView
    private lateinit var tvSettings: TextView
    private lateinit var tvChangeLanguage: TextView
    private lateinit var mContext: Context

    private lateinit var btnEnglish: Button
    private lateinit var btnUrdu: Button
    private lateinit var btnArabic: Button
    private lateinit var btnClose: Button
    private val PREFERENCE_NAME = "MyPreferences"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = getSharedPreferences(requireContext(), Context.MODE_PRIVATE)

        sp = SharedPref(requireContext())
        currentUser = sp.getUsers() ?: Users()


        val tvChangeLanguage: TextView = view.findViewById(R.id.tv_change_language)
        val language_change: LinearLayout = view.findViewById(R.id.language_change)

        tvChangeLanguage.setOnClickListener {
            if (language_change.visibility == View.VISIBLE) {
                language_change.visibility = View.GONE
            } else {
                language_change.visibility = View.VISIBLE
            }
        }
        val buttonClose = view.findViewById<AppCompatButton>(R.id.buttonClose)
        val languageChangeLayout = view.findViewById<LinearLayout>(R.id.language_change)

        buttonClose.setOnClickListener {
            languageChangeLayout.visibility = View.GONE
        }

        btnEnglish = view.findViewById(R.id.buttonEnglish)
        btnUrdu = view.findViewById(R.id.buttonUrdu)
        btnArabic = view.findViewById(R.id.buttonArabic)

        btnEnglish.setOnClickListener {
            setLanguage("en")
        }

        btnUrdu.setOnClickListener {
            setLanguage("ur")
        }

        btnArabic.setOnClickListener {
            setLanguage("ar")
        }


        databaseReference = FirebaseDatabase.getInstance().reference.child("users").child(currentUser.uid)

        // Setup logout button click listener
        binding.btnLogout.setOnClickListener {
            openLogoutDialog()
        }


        binding.tvContactUs.setOnClickListener {
            val intent = Intent(requireContext(), ContactUsActivity::class.java)
            startActivity(intent)
        }


        binding.tvSettings.setOnClickListener {

            val intent = Intent(requireContext(), SettingsActivity::class.java)
            startActivity(intent)
        }



    }

    private fun setLanguage(languageCode: String) {

        saveLanguagePreference(languageCode)

        updateAppLanguage(languageCode)

        restartApp()
    }

    private fun saveLanguagePreference(languageCode: String) {

        val sharedPreferences: SharedPreferences = getSharedPreferences(
            requireContext(), // Pass the context here
            Context.MODE_PRIVATE // Use Context.MODE_PRIVATE for mode
        )

        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("language", languageCode)
        editor.apply()
    }

    private fun getSharedPreferences(context: Context, modePrivate: Int): SharedPreferences {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }


    private fun updateAppLanguage(languageCode: String) {
        // Update the app's configuration with the new language
        val configuration = Configuration(resources.configuration)
        configuration.setLocale(java.util.Locale(languageCode))
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }


    private fun restartApp() {
        // Restart the app to apply the language change
        val intent = Intent(requireActivity(), MainActivity::class.java) // Replace MainActivity with your app's main activity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        requireActivity().finishAffinity()
    }

    override fun onResume() {
        super.onResume()
        // Retrieve and display user data
        retrieveUserData()
    }

    private fun retrieveUserData() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val user = snapshot.getValue(Users::class.java)
                    user?.let {
                        // Update UI with user data
                        binding.tvUserName.text = "${user.firstName} ${user.lastName}"
                        binding.tvEmail.text = user.email
                        Glide.with(requireContext()).load(user.dpUrl).placeholder(R.drawable.profile).into(binding.ivUserImg)

                        // Save user data to Shared Preferences
                        sp.saveUsers(user)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
            }
        })
    }
    private fun openLogoutDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Logout?")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes") { dialog: DialogInterface, _: Int ->
                signOut()
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }
            .show()
    }

    private fun signOut() {

        FirebaseAuth.getInstance().signOut()

        sp.saveUsers(Users())

        sp.putBoolean(Constants.isLoggedIn,false)

        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        requireActivity().finish()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}
