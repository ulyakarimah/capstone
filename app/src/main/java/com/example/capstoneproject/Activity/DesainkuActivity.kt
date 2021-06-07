package com.example.capstoneproject.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneproject.Desain
import com.example.capstoneproject.DesainAdapter
import com.example.capstoneproject.ViewModel.MainViewModel
import com.example.capstoneproject.ViewModel.ViewModelFactory
import com.example.capstoneproject.databinding.ActivityDesainkuBinding

//Android 3

class DesainkuActivity : AppCompatActivity(){

    private var _activityDesainkuBinding: ActivityDesainkuBinding? = null
    private val binding get() = _activityDesainkuBinding

    private lateinit var adapter: DesainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        _activityDesainkuBinding = ActivityDesainkuBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.butAddDesign?.setOnClickListener {
                val intent = Intent(this@DesainkuActivity, DesainAddUpdateActivity::class.java)
                startActivity(intent)
        }

        val mainViewModel = obtainViewModel(this@DesainkuActivity)
        mainViewModel.getAllDesigns().observe(this, desainObserver)

        adapter = DesainAdapter(this@DesainkuActivity)
        binding?.rvListdesain?.layoutManager = LinearLayoutManager(this)
        binding?.rvListdesain?.setHasFixedSize(true)
        binding?.rvListdesain?.adapter = adapter
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(MainViewModel::class.java)
    }
    private val desainObserver = Observer<List<Desain>> { desainList ->
        if (desainList != null) {
            adapter.setListDesain(desainList)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityDesainkuBinding = null
    }

}