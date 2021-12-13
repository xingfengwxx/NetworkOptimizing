package com.wangxingxing.networkoptimizing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.wangxingxing.networkoptimizing.databinding.ActivityMainBinding
import com.wangxingxing.networkoptimizing.model.User
import com.wangxingxing.networkoptimizing.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private val TAG = "wxx"

    private val mBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        init()
    }

    private fun init() {
        mBinding.apply {
            btnRequest.setOnClickListener {
                lifecycleScope.launchWhenCreated {
                    mViewModel.list().collectLatest {
                        tvInfo.text = it.toString()
                    }
                }
            }
        }
    }
}