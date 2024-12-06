package com.holat.holat.ui.home

import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.holat.holat.R
import com.holat.holat.data.models.tickets.DataTicket
import com.holat.holat.data.models.tickets.Tickets
import com.holat.holat.databinding.FragmentHomeBinding
import com.holat.holat.ui.home.activity.MainActivity
import com.holat.holat.ui.home.adapter.CompliantAdapter
import com.holat.holat.ui.home.compliants.viewmodel.CompliantViewModel
import com.holat.login.base.BaseFragment
import com.holat.login.network.NetworkResult
import com.holat.login.utils.Constants
import com.holat.login.utils.listener.ClickListener
import com.holat.login.utils.listener.NotificationListener
import com.holat.login.utils.listener.SearchListener
import com.holat.login.utils.onDebouncedListener
import com.holat.login.utils.safeNavigate
import com.holat.login.utils.showSnackBar
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    private val compliantViewModel by viewModels<CompliantViewModel>()
    private var ticketId = ""

//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)
//
//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//       // val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//          //  textView.text = it
//        }
//        return root
//    }

    override fun getBinding(): ViewBinding {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding
    }

    override fun onClick() {
        (requireActivity() as MainActivity).setNotificationListener(object : NotificationListener {
            override fun openNotificationPage() {
                val action = HomeFragmentDirections.actionMainHomeToNotificationsFragment()
                findNavController().safeNavigate(action)
            }

        })

        (requireActivity() as MainActivity).setSearchListener(object : SearchListener {
            override fun openSearchDetails(text: String?) {
                ticketId = text ?: ""
                if (ticketId == "") {
                    binding.root.showSnackBar(getString(R.string.error_ticket))
                    return
                }
                ticketSearch(ticketId)
                hideKeyBoardForActivity()
            }
        }
        )
        (requireActivity() as MainActivity).setMyAccountListener(object : NotificationListener {
            override fun openNotificationPage() {
                (requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)).selectedItemId =
                    R.id.my_account
            }
        })
    }

    override fun getData() {
        toolbar.changeTextTitle(getString(R.string.wellcome))
        toolbar.showSearchText(true)
        toolbar.showProfileImage(true)
        toolbar.showNotificationIcon(true)
        oldTickets()
        getServiceConfigData()
    }

    private fun oldTickets() {
        compliantViewModel.oldTickets(1)
        compliantViewModel.oldTickets.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    showProgressBar()
                }

                is NetworkResult.Success -> {
                    if (it.data.data.isNotEmpty()) {
                        filterComplaints(it.data)
                        val adapter = CompliantAdapter(object : ClickListener<DataTicket> {
                            override fun onClick(view: View, t: DataTicket) {
                                val action =
                                    HomeFragmentDirections.actionMainHomeToTicketDetailsFragment(
                                        t
                                    )
                                findNavController().safeNavigate(action)
                            }
                        })
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.recyclerView.adapter = adapter
                        adapter.submitList(it.data.data)
                    } else {
                        binding.recyclerView.visibility = View.GONE
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

    private fun filterComplaints(data: Tickets) {
        val closeComplaint = arrayListOf<DataTicket>()
        val underProcessComplaint = arrayListOf<DataTicket>()
        val newComplaint = arrayListOf<DataTicket>()

        val currentLanguage = Lingver.getInstance().getLanguage()

        val closedComplaintZero = data.data.filter { it.status?.use_replacement == 0 }
        closedComplaintZero.forEach {
            if (currentLanguage == Constants.AR_LANGUAGE) {
                when (it.status?.title_ar) {
                    binding.root.context.getString(R.string.close) -> {
                        closeComplaint.add(it)
                    }

                    binding.root.context.getString(R.string.under_process) -> {
                        underProcessComplaint.add(it)
                    }

                    binding.root.context.getString(R.string.new_) -> {
                        newComplaint.add(it)
                    }
                }
            } else {
                when (it.status?.title_en) {
                    binding.root.context.getString(R.string.close) -> {
                        closeComplaint.add(it)
                    }

                    binding.root.context.getString(R.string.under_process) -> {
                        underProcessComplaint.add(it)
                    }

                    binding.root.context.getString(R.string.new_) -> {
                        newComplaint.add(it)
                    }
                }
            }
        }
        val closedComplaintOne = data.data.filter { it.status?.use_replacement == 1 }
        closedComplaintOne.forEach {
            if (currentLanguage == Constants.AR_LANGUAGE) {
                when (it.status?.replacement_status?.title_ar) {
                    binding.root.context.getString(R.string.close) -> {
                        closeComplaint.add(it)
                    }

                    binding.root.context.getString(R.string.under_process) -> {
                        underProcessComplaint.add(it)
                    }

                    binding.root.context.getString(R.string.new_) -> {
                        newComplaint.add(it)
                    }
                }
            } else {
                when (it.status?.replacement_status?.title_en) {
                    binding.root.context.getString(R.string.close) -> {
                        closeComplaint.add(it)
                    }

                    binding.root.context.getString(R.string.under_process) -> {
                        underProcessComplaint.add(it)
                    }

                    binding.root.context.getString(R.string.new_) -> {
                        newComplaint.add(it)
                    }
                }
            }
        }

        showComplaintsNumber(newComplaint, underProcessComplaint, closeComplaint)
    }

    private fun showComplaintsNumber(
        newComplaint: ArrayList<DataTicket>,
        underProcessComplaint: ArrayList<DataTicket>,
        closeComplaint: ArrayList<DataTicket>
    ) {
        binding.includeTotalComplaint.includeNew.tvTitle.text = getString(R.string.new_)
        binding.includeTotalComplaint.includeNew.tvNumbers.text = newComplaint.size.toString()

        binding.includeTotalComplaint.includeUnderProcess.tvTitle.text =
            getString(R.string.under_apply)
        binding.includeTotalComplaint.includeUnderProcess.tvNumbers.text =
            underProcessComplaint.size.toString()

        binding.includeTotalComplaint.includeClosed.tvTitle.text = getString(R.string.close)
        binding.includeTotalComplaint.includeClosed.tvNumbers.text = closeComplaint.size.toString()
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
                        if (it.data.status?.title_en == "success") {
                            val action =
                                HomeFragmentDirections.actionMainHomeToTicketDetailsFragment(
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

    private fun getServiceConfigData() {
        selfServiceConfigDatabaseViewModel.getServiceConfig()
        //Get data from local
        viewLifecycleOwner.lifecycleScope.launch {
            selfServiceConfigDatabaseViewModel.getServiceConfig.collect {
                if (it != null) {
                    // if (it.selfServiceKBDisplay == "1" )
                    informationBank()
                    //  else
                    //     binding.informationBank.cardView.visibility = View.GONE

                    //  if (it.selfServiceContactUsDisplay == "0")
                    contactUs()
                    // else
                    //    binding.contactUs.cardView.visibility = View.GONE
                }
            }
        }
    }

    private fun informationBank() {
        binding.includeInformationBank.tvHeader.text = getString(R.string.information_bank)
        binding.includeInformationBank.image.setBackgroundDrawable(
            ContextCompat.getDrawable(
                requireActivity(),
                R.drawable.bg_bottom_dialog_green
            )
        )
        binding.includeInformationBank.image.setImageResource(R.drawable.info)
        binding.includeInformationBank.cardView.onDebouncedListener {
            val action = HomeFragmentDirections.actionMainHomeToInformationBankFragment()
            findNavController().safeNavigate(action)
        }

    }

    private fun contactUs() {
        binding.includeContactUs.tvHeader.text = getString(R.string.contact_us)
        binding.includeContactUs.image.setBackgroundDrawable(
            ContextCompat.getDrawable(
                requireActivity(),
                R.drawable.bg_bottom_dialog_green
            )
        )
        binding.includeContactUs.image.setImageResource(R.drawable.phone)
        binding.includeContactUs.cardView.onDebouncedListener {
            val action = HomeFragmentDirections.actionMainHomeToContactUsFragment()
            findNavController().safeNavigate(action)
        }

    }
//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }
}