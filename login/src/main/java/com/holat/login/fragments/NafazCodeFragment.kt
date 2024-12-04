package com.holat.login.fragments

import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.holat.login.R
import com.holat.login.databinding.FragmentNafazCodeBinding
import com.holat.login.base.BaseFragment
import com.holat.login.viewmodels.LoginViewModel
import com.holat.login.models.nafath.Nafath
import com.holat.login.models.nafath.NafathStatus
import com.holat.login.network.NetworkResult
import com.holat.login.utils.FinishListener
import com.holat.login.utils.Timer
import com.holat.login.utils.gotToMainActivity
import com.holat.login.utils.gotToSpecificActivity
import com.holat.login.utils.safeNavigate
import kotlinx.coroutines.launch
import javax.inject.Inject

class NafazCodeFragment : BaseFragment() {
    private lateinit var binding: FragmentNafazCodeBinding
    private val loginViewModel by activityViewModels<LoginViewModel>()

    @Inject
    lateinit var timer: Timer
    override fun getBinding(): ViewBinding {
        binding = FragmentNafazCodeBinding.inflate(layoutInflater)
        return binding
    }

    override fun onClick() {
        binding.tvResend.setOnClickListener {
            binding.tvResend.isEnabled = false
            binding.tvResend.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))

            val nafath = Nafath(
                id = loginViewModel.nationalId.toString()
            )
            resendCode(nafath)
        }
    }

    override fun getData() {
        binding.tvCode.text = loginViewModel.random ?: ""
    }

    override fun onResume() {
        super.onResume()
        setTimer()
    }

    private fun setTimer() {
        timer = Timer(object : FinishListener {
            override fun finish() {
                if (isAdded) {
                    val nafathStatus = NafathStatus(
                        id = loginViewModel.nationalId.toString(), random = loginViewModel.random,
                        transId = loginViewModel.transId
                    )
                    nafathStatus(nafathStatus)
                }
                timer.stopCountdownTimer()
            }

        })
        timer.startCountdownTimer()
    }

    private fun nafathStatus(
        nafathStatus: NafathStatus
    ) {
        loginViewModel.nafathStatus(nafathStatus)
        viewLifecycleOwner.lifecycleScope.launch {
            loginViewModel.nafathStatus.collect {
                when (it) {
                    is NetworkResult.Loading -> {
                       // showProgressBar()
                    }

                    is NetworkResult.Success -> {
                        hideProgressBar()
                        when (it.data.status) {
                            com.holat.login.models.NafathStatus.WAITING.type -> {
                                binding.loginBtn.text = getString(R.string.order_waiting)
                                timer.startCountdownTimer()
                            }

                            com.holat.login.models.NafathStatus.COMPLETED.type -> {
                                binding.loginBtn.text = getString(R.string.order_completed)
//                             //   if (it.data.client.verifiedByNafath == 1) {
                                    if (it.data.client.dataCompleted == 1) {
                                    // save token
                                    dataStoreViewModel.saveToken(it.data.token)
                                        requireActivity().gotToMainActivity("MainActivity")
                                } else {
                                    // If the mobile does not verified
                                    val action = NafazCodeFragmentDirections.actionNafazCodeFragmentToNafathSendVerifyCodeFragment()
                                    findNavController().safeNavigate(action)
                                }
                            }

                            com.holat.login.models.NafathStatus.EXPIRED.type -> {
                                binding.loginBtn.text = getString(R.string.order_expired)
                                binding.tvResend.isEnabled = true
                                binding.tvResend.setTextColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.red
                                    )
                                )
                            }
                        }
                    }

                    is NetworkResult.Error -> {
                        showError(it.code, it.responseBody)
                       // hideProgressBar()
                    }

                    is NetworkResult.ErrorEX -> {
                      //  hideProgressBar()
                        // showError(it)
                        //reloadCaptcha()
                    }

                    is NetworkResult.Exception -> {
                     //   hideProgressBar()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.stopCountdownTimer()
    }
    private fun resendCode(
        nafath: Nafath
    ) {
        loginViewModel.nafath(nafath)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                loginViewModel.nafath.collect {
                    when (it) {
                        is NetworkResult.Loading -> {
                            showProgressBar()
                        }

                        is NetworkResult.Success -> {
                            loginViewModel.random = it.data.random
                            loginViewModel.transId = it.data.transId
                            binding.tvCode.text = loginViewModel.random
                            hideProgressBar()
                            setTimer()
                        }

                        is NetworkResult.Error -> {
                            showError(it.code,it.responseBody)
                            hideProgressBar()
                        }
                        is NetworkResult.ErrorEX -> {
                            hideProgressBar()
                        }
                        is NetworkResult.Exception -> {
                            hideProgressBar()
                        }
                    }
                }
            }
        }
    }
}