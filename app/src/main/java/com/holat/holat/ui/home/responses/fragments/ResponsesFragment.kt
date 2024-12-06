package com.holat.holat.ui.home.responses.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.holat.holat.R
import com.holat.holat.data.models.tickets.DataTicket
import com.holat.holat.databinding.FragmentResponsesBinding
import com.holat.holat.ui.home.responses.adapter.ResponsesAdapter
import com.holat.holat.ui.home.responses.viewmodel.ResponsesViewModel
import com.holat.login.base.BaseFragment
import com.holat.login.network.NetworkResult
import com.holat.login.utils.onDebouncedListener
import com.holat.login.utils.safeNavigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResponsesFragment : BaseFragment() {
    private lateinit var binding: FragmentResponsesBinding
    private val responsesViewModel by viewModels<ResponsesViewModel>()
    private val args: ResponsesFragmentArgs by navArgs()

    override fun getBinding(): ViewBinding {
        binding = FragmentResponsesBinding.inflate(layoutInflater)
        return binding
    }

    override fun getData() {
        toolbar.changeTextTitle(getString(R.string.responses))
        toolbar.showSearchText(false)
        toolbar.showProfileImage(false)
        toolbar.showNotificationIcon(false)
        if (args.dataTikcet.id != null)
            getResponses(args.dataTikcet.id!!, 1, 5)
        checkValidation(args.dataTikcet)
    }


    override fun onClick() {
        binding.sendBtn.onDebouncedListener {
            val action =
                ResponsesFragmentDirections.actionResponsesFragmentToSendCommentFragment(args.dataTikcet.id!!)
            findNavController().safeNavigate(action)
        }
        binding.closeBtn.onDebouncedListener { }
        binding.escalationBtn.onDebouncedListener {
            val action =
                ResponsesFragmentDirections.actionResponsesFragmentToSendEscalationFragment(args.dataTikcet.id!!)
            findNavController().safeNavigate(action)
        }
        binding.rateBtn.onDebouncedListener {
            val action =
                ResponsesFragmentDirections.actionResponsesFragmentToRatingFragment(args.dataTikcet.id!!)
            findNavController().safeNavigate(action)
        }

    }

    private fun getResponses(ticketId: Long, page: Int, perPage: Int) {
        responsesViewModel.getResponses(ticketId, page, perPage)
        responsesViewModel.getResponses.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    showProgressBar()
                }

                is NetworkResult.Success -> {
                    if (it.data.data.isNotEmpty()) {
                        it.data.data.forEach { it.added_by_customer }
                        val adapter = ResponsesAdapter(it.data.data)
                        binding.recyclerViewResponses.visibility = View.VISIBLE
                        binding.recyclerViewResponses.adapter = adapter
                        binding.groupEmptyPage.visibility = View.GONE
                    } else {
                        binding.recyclerViewResponses.visibility = View.GONE
                        binding.groupEmptyPage.visibility = View.VISIBLE

                    }
                    hideProgressBar()
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

    private fun checkValidation(dataTicket: DataTicket) {
        if (dataTicket.already_escalated != 3 && dataTicket.already_escalated != 4 && dataTicket.already_escalated != 8) {
            binding.escalationBtn.visibility = View.VISIBLE
        } else {
            binding.escalationBtn.visibility = View.GONE
        }
        if (dataTicket.status?.main_status_id != 11 && dataTicket.status?.main_status_id != 8 && dataTicket.status?.main_status_id != 4 &&
            dataTicket.status?.main_status_id != 3
        ) {
            binding.closeBtn.visibility = View.VISIBLE
        } else {
            binding.closeBtn.visibility = View.GONE
        }

        if (dataTicket.status?.main_status_id == 4 && dataTicket.feedback_link_expired_at == null) {
            binding.rateBtn.visibility = View.VISIBLE
        } else {
            binding.rateBtn.visibility = View.GONE
        }
    }

}