package com.example.capstoneproject.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.capstoneproject.Desain
import com.example.capstoneproject.ViewModel.DesainAddUpdateViewModel
import com.example.capstoneproject.R
import com.example.capstoneproject.ViewModel.ViewModelFactory
import com.example.capstoneproject.databinding.ActivityDesainAddUpdateBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DesainAddUpdateActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DESAIN = "extra_desain"
        const val EXTRA_POSITION = "extra_position"
        const val REQUEST_UPDATE = 200
        const val RESULT_DELETE = 301
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

    private var isEdit = false
    private var desain: Desain?  = null
    private var position = 0

    private lateinit var desainAddUpdateViewModel: DesainAddUpdateViewModel
    private var _activityDesainAddUpdateBinding: ActivityDesainAddUpdateBinding? = null
    private val binding get() = _activityDesainAddUpdateBinding


    private val pickImage1 = 101
    private var imageUri: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityDesainAddUpdateBinding = ActivityDesainAddUpdateBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.ivChoosenimg1?.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage1)
        }

        desainAddUpdateViewModel = obtainViewModel(this@DesainAddUpdateActivity)

        desain = intent.getParcelableExtra(EXTRA_DESAIN)
        if (desain != null) {
            position = intent.getIntExtra(EXTRA_POSITION, 0)
            isEdit = true
        } else {
            desain = Desain()
        }
        val actionBarTitle: String
        val btnTitle: String
        if (isEdit) {
            actionBarTitle = getString(R.string.edit)
            btnTitle = getString(R.string.update)
            if (desain != null) {
                desain?.let { desain ->
                    binding?.etNamadesain?.setText(desain!!.judul)

                }
            }
        } else {
            actionBarTitle = getString(R.string.add)
            btnTitle = getString(R.string.save)
        }
        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding?.butSimpan?.text = btnTitle


        binding?.butSimpan?.setOnClickListener {
            val title = binding?.etNamadesain?.text.toString().trim()
                if (title.isEmpty()) {
                    binding?.etNamadesain?.error = getString(R.string.empty)
                } else {
                    insertToDb()
                    desain.let { desain ->
                        desain?.judul = title
                    }
                }
                if (isEdit) {
                    desainAddUpdateViewModel.update(desain as Desain)
                    finish()
                } else {
                    desainAddUpdateViewModel.insert(desain as Desain)
                    finish()
                }
            }
        }

    private fun insertToDb() {
        val title = binding?.etNamadesain?.text.toString()
        val image = imageUri
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val desain = Desain(judul = title,gambar = image)
                desainAddUpdateViewModel.insert(desain)
            }
            Toast.makeText(this@DesainAddUpdateActivity, "sukses", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (isEdit) {
            menuInflater.inflate(R.menu.menu_form, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE)
    }


    override fun onDestroy() {
        super.onDestroy()
        _activityDesainAddUpdateBinding = null
    }

    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String
        if (isDialogClose) {
            dialogTitle = getString(R.string.cancel)
            dialogMessage = getString(R.string.message_cancel)
        } else {
            dialogMessage = getString(R.string.message_delete)
            dialogTitle = getString(R.string.delete)
        }
        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                if (!isDialogClose) {
                    desainAddUpdateViewModel.delete(desain as Desain)
                    val intent = Intent()
                    intent.putExtra(EXTRA_POSITION, position)
                    setResult(RESULT_DELETE, intent)
                }
                finish()
            }
            setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun obtainViewModel(activity: AppCompatActivity): DesainAddUpdateViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(DesainAddUpdateViewModel::class.java)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val uri = data?.data as Uri
            imageUri = uri.toString()
            binding?.ivChoosenimg1?.setImageURI(uri)
            }
        }
    }

