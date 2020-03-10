package com.example.meetup

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseServer (context: Context){
    private var databaseReference: DatabaseReference

    init {
        // naplnenie databaze
        // zavolanie inicializacie
        FirebaseApp.initializeApp(context)

        databaseReference = FirebaseDatabase
            .getInstance()
            .getReferenceFromUrl("https://meetup-269212.firebaseio.com/") // adresa z console firebase z verze 1

        databaseReference.keepSynced(true) // tato cast databaze ma byt automaticky syncovana, nestiahnu sa zmeny ale cely strom
    }

    fun getDatabaseReference(): DatabaseReference { // vrati celu databazu
        return databaseReference
    }

    fun getPlacesReference(): DatabaseReference{ // definovanie endpointu aby sme pracovali iba s nim
        return databaseReference.child("places")
    }

    fun sendDataToFirebase( // odoslanie dat na server
        reference: DatabaseReference, // miesto kam to chcem poslat
        key: String?, // ci chceme aby firebase generoval kluc alebo nie, ak nevlozime hodnotu chceme aby firebase vytvorit klic (idealna varianta)
        objectToSend: Any, // posle sa tam cokolvek, akykolvek objekt a on si ho serializuje a ulozi na firebase
        listener: ValueEventListener // posleme tam svoj eventListener aby sme vedeli ci sa to podarilo alebo nie
    ){
        // iniacializacia klucu
        var firebaseKey: String? = null
        if (key == null) {
            firebaseKey = reference.push().key // zavola priamo server, rekne si o kluc a vrati ho
        } else {
            firebaseKey = key
        }

        // poslem tam hodnotu
        reference.child(firebaseKey!!).setValue(objectToSend)
        // nasetujem si listener
        reference.addValueEventListener(listener)
    }
}