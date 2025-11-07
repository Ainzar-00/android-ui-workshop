package com.example.androidtest1

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.androidtest1.R

class ContactActivity : AppCompatActivity() {
    private lateinit var etFullName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var etAddress1: EditText
    private lateinit var etAddress2: EditText
    private lateinit var rgCategory: RadioGroup
    private lateinit var btnAdd: Button
    private lateinit var btnReset: Button
    private lateinit var tvContactDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        etFullName = findViewById(R.id.etFullName)
        etPhone = findViewById(R.id.etPhone)
        etEmail = findViewById(R.id.etEmail)
        etAddress1 = findViewById(R.id.etAddress1)
        etAddress2 = findViewById(R.id.etAddress2)
        rgCategory = findViewById(R.id.rgCategory)
        btnAdd = findViewById(R.id.btnAdd)
        btnReset = findViewById(R.id.btnReset)
        tvContactDisplay = findViewById(R.id.tvContactDisplay)

        btnAdd.setOnClickListener {
            ajouterContact()
        }
        btnReset.setOnClickListener {
            reinitialiser()
        }
    }

    private fun ajouterContact() {
        // Récupération des valeurs
        val nom = etFullName.text.toString().trim()
        val telephone = etPhone.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val adresse1 = etAddress1.text.toString().trim()
        val adresse2 = etAddress2.text.toString().trim()

        // Validation des champs obligatoires
        if (nom.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer le nom complet", Toast.LENGTH_SHORT).show()
            return
        }
        if (telephone.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer le numéro de téléphone", Toast.LENGTH_SHORT).show()
            return
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer l'email", Toast.LENGTH_SHORT).show()
            return
        }
        if (adresse1.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer au moins la ligne 1 de l'adresse", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedId = rgCategory.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, "Veuillez sélectionner une catégorie", Toast.LENGTH_SHORT).show()
            return
        }
        val radioButton = findViewById<RadioButton>(selectedId)
        val category = radioButton.text.toString()

        val contactInfo = """
            Contact ajouté:
            Nom: $nom
            Tél: $telephone
            Email: $email
            Adresse: $adresse1, $adresse2
            Catégorie: $category
        """.trimIndent()

        tvContactDisplay.text = contactInfo

        Toast.makeText(this, "Contact ajoute avec succes !", Toast.LENGTH_SHORT).show()
    }

    private fun reinitialiser() {
        etFullName.setText("")
        etPhone.setText("")
        etEmail.setText("")
        etAddress1.setText("")
        etAddress2.setText("")

        rgCategory.clearCheck()

        tvContactDisplay.text = "Aucun contact ajouté"

        Toast.makeText(this, "Formulaire réinitialisé", Toast.LENGTH_SHORT).show()
    }
}