package com.holat.holat.ui.home.sama

import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.holat.holat.R
import com.holat.holat.databinding.FragmentContactUsBinding
import com.holat.login.base.BaseFragment
import com.holat.login.utils.onDebouncedListener
import com.holat.login.utils.openLink
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactUsFragment : BaseFragment() {
    private lateinit var binding: FragmentContactUsBinding
    override fun getBinding(): ViewBinding {
        binding = FragmentContactUsBinding.inflate(layoutInflater)
        return binding
    }

    override fun onClick() {
        getServiceConfigData()
    }

    override fun getData() {
        toolbar.changeTextTitle(getString(R.string.contact_us))
        toolbar.showSearchText(false)
        toolbar.showProfileImage(false)
        toolbar.showNotificationIcon(false)
    }

    private fun getServiceConfigData() {
        selfServiceConfigDatabaseViewModel.getServiceConfig()
        //Get data from local
        viewLifecycleOwner.lifecycleScope.launch {
            selfServiceConfigDatabaseViewModel.getServiceConfig.collect {
                if (it != null) {
                    val socialMedia = it
                    binding.tvGetPhone.text = socialMedia.unifiedNumber
                    if (socialMedia.facebookUrl != "") {
                        binding.imFacebook.visibility = View.VISIBLE
                        binding.imFacebook.onDebouncedListener {
                            requireActivity().openLink(socialMedia.facebookUrl)
                        }
                    } else {
                        binding.imFacebook.visibility = View.INVISIBLE
                    }
                    if (socialMedia.twitterUrl != "") {
                        binding.imTwitter.visibility = View.VISIBLE
                        binding.imTwitter.onDebouncedListener {
                            requireActivity().openLink(socialMedia.twitterUrl)
                        }
                    } else {
                        binding.imTwitter.visibility = View.INVISIBLE
                    }
                    if (socialMedia.linkedinUrl != "") {
                        binding.imLinkedIn.visibility = View.VISIBLE
                        binding.imLinkedIn.onDebouncedListener {
                            requireActivity().openLink(socialMedia.linkedinUrl)
                        }
                    } else {
                        binding.imLinkedIn.visibility = View.INVISIBLE
                    }
                    if (socialMedia.youtubeURl != "") {
                        binding.imYouTube.visibility = View.VISIBLE
                        binding.imYouTube.onDebouncedListener {
                            requireActivity().openLink(socialMedia.youtubeURl)
                        }
                    } else {
                        binding.imYouTube.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }
}