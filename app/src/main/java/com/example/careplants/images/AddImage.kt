package com.example.careplants.images

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.careplants.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.hdodenhof.circleimageview.CircleImageView
import java.io.FileNotFoundException


class AddImage : Activity() {

    private var imageView: ImageView? = null
    private val Pick_image = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_add_info)
        setContentView(R.layout.fragment_edit_info)


        imageView = findViewById<View>(R.id.plantImage) as CircleImageView


        val PickImage = findViewById<View>(R.id.button_add_image) as FloatingActionButton


        PickImage.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)

            photoPickerIntent.type = "image/*"

            startActivityForResult(photoPickerIntent, Pick_image)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        when (requestCode) {
            Pick_image -> if (resultCode == RESULT_OK) {
                try {

                    val imageUri = imageReturnedIntent.data
                    val imageStream = contentResolver.openInputStream(
                        imageUri!!
                    )
                    val selectedImage = BitmapFactory.decodeStream(imageStream)
                    imageView!!.setImageBitmap(selectedImage)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
        }
    }
}