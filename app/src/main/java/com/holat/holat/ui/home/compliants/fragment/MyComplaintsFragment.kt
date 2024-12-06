package com.holat.holat.ui.home.compliants.fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.holat.holat.R
import com.holat.holat.data.models.tickets.DataTicket
import com.holat.holat.databinding.FragmentMyCompliantsBinding
import com.holat.holat.ui.home.activity.MainActivity
import com.holat.holat.ui.home.compliants.adapter.AllComplaintsAdapter
import com.holat.holat.ui.home.compliants.viewmodel.CompliantViewModel
import com.holat.login.base.BaseFragment
import com.holat.login.network.NetworkResult
import com.holat.login.utils.DatePickerDialogUtil.selectDateOfBirthGregorian
import com.holat.login.utils.listener.ClickListener
import com.holat.login.utils.listener.NotificationListener
import com.holat.login.utils.onDebouncedListener
import com.holat.login.utils.safeNavigate
import com.holat.login.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyComplaintsFragment : BaseFragment() {
    private lateinit var binding: FragmentMyCompliantsBinding
    private val compliantViewModel by viewModels<CompliantViewModel>()
    override fun getBinding(): ViewBinding {
        binding = FragmentMyCompliantsBinding.inflate(layoutInflater)
        return binding
    }

    override fun getData() {
        toolbar.changeTextTitle(getString(R.string.title_orders))
        toolbar.showSearchText(false)
        toolbar.showProfileImage(false)
        toolbar.showNotificationIcon(true)
        allTickets()
    }

    override fun onClick() {
        (requireActivity() as MainActivity).setNotificationListener(object : NotificationListener {
            override fun openNotificationPage() {
                val action =
                    MyComplaintsFragmentDirections.actionOrdersFragmentToNotificationsFragment()
                findNavController().safeNavigate(action)
            }

        })
        binding.etDate.onDebouncedListener {
            binding.root.selectDateOfBirthGregorian(
                binding.etDate,
                getString(R.string.date),
                enableFutureDate = false
            )
        }
        binding.searchBtn.onDebouncedListener {
            val text = binding.etSearch.text.toString().trim()
            if (text == "") {
                binding.root.showSnackBar(getString(R.string.error_ticket))
                return@onDebouncedListener
            }
            ticketSearch(text)
            hideKeyBoardForActivity()
        }

    }

    private fun allTickets() {

        compliantViewModel.oldTickets(1)
        compliantViewModel.oldTickets.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    showProgressBar()
                }

                is NetworkResult.Success -> {
                    setAdapter(it.data.data)
                }

                is NetworkResult.Error -> {
                    showError(it.code, it.responseBody)
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

    private fun setAdapter(data: List<DataTicket>) {
        if (data.isNotEmpty()) {
            val adapter = AllComplaintsAdapter(object : ClickListener<DataTicket> {
                override fun onClick(view: View, t: DataTicket) {
                    val action =
                        MyComplaintsFragmentDirections.actionOrdersFragmentToTicketDetailsFragment(t)
                    findNavController().safeNavigate(action)
                }
            })
            binding.recyclerView.visibility = View.VISIBLE
            binding.recyclerView.adapter = adapter
            adapter.submitList(data)
        } else {
            binding.recyclerView.visibility = View.GONE
        }
        hideProgressBar()
    }

    private fun ticketSearch(ticketId: String) {
        compliantViewModel.ticketSearch(ticketId)
        viewLifecycleOwner.lifecycleScope.launch {
            compliantViewModel.ticketSearch.collect {
                when (it) {
                    is NetworkResult.Loading -> {
                        showProgressBar()
                    }

                    is NetworkResult.Success -> {
                        hideProgressBar()
                        //setAdapter(it.data.data)
                        if (it.data.status?.title_en == "success") {
                            val action =
                                MyComplaintsFragmentDirections.actionOrdersFragmentToTicketDetailsFragment(
                                    it.data
                                )
                            findNavController().safeNavigate(action)
                        }
                    }

                    is NetworkResult.Error -> {
                        showError(it.code, it.responseBody)
                        hideProgressBar()
                    }

                    is NetworkResult.ErrorEX -> {
                        hideProgressBar()
                        binding.root.showSnackBar(it.exception?.message.toString())
                    }

                    is NetworkResult.Exception -> {
                        hideProgressBar()
                    }
                }
            }
        }
    }

}