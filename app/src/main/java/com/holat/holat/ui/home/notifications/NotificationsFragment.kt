package com.holat.holat.ui.home.notifications

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.holat.holat.R
import com.holat.holat.data.models.tickets.DataTicket
import com.holat.holat.databinding.FragmentNotificationsBinding
import com.holat.holat.ui.home.compliants.viewmodel.CompliantViewModel
import com.holat.login.base.BaseFragment
import com.holat.login.network.NetworkResult
import com.holat.login.utils.listener.ClickListener
import com.holat.login.utils.safeNavigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : BaseFragment() {
    private var _binding: FragmentNotificationsBinding? = null
    private val compliantViewModel by viewModels<CompliantViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun getBinding(): ViewBinding {
        _binding = FragmentNotificationsBinding.inflate(layoutInflater)
        return binding
    }

    override fun getData() {
        toolbar.changeTextTitle(getString(R.string.notification))
        toolbar.showSearchText(false)
        toolbar.showNotificationIcon(false)
        toolbar.showProfileImage(false)
        oldTickets()
    }

    override fun onClick() {
    }

    private fun oldTickets(
    ) {

//        val touchHelper = ItemTouchHelper(object: ItemTouchHelper.Callback(){
//            override fun getMovementFlags(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder
//            ): Int {
//                return getMovementFlags(ItemTouchHelper.END,ItemTouchHelper.END)
//            }
//
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//               return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//               // TODO("Not yet implemented")
//            }
//
//        })
        compliantViewModel.oldTickets(1)
        compliantViewModel.oldTickets.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    showProgressBar()
                }

                is NetworkResult.Success -> {
                    if (it.data.data.isNotEmpty()) {
                        val adapter = NotificationAdapter(object : ClickListener<DataTicket> {
                            override fun onClick(view: View, t: DataTicket) {
                                val action =
                                    NotificationsFragmentDirections.actionNotificationsFragmentToNotificationDetailsFragment(
                                        t
                                    )
                                findNavController().safeNavigate(action)
                            }

                        })
                        binding.notificationRecyclerView.visibility = View.VISIBLE
                        binding.notificationRecyclerView.adapter = adapter
                        adapter.submitList(it.data.data)
                        binding.groupEmptyPage.visibility = View.GONE
                        //    touchHelper.attachToRecyclerView(binding.notificationRecyclerView)
                    } else {
                        binding.notificationRecyclerView.visibility = View.GONE
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}