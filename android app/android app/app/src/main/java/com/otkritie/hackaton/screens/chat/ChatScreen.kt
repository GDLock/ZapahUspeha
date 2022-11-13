package com.otkritie.hackaton.screens.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.otkritie.hackaton.MainActivity
import com.otkritie.hackaton.R
import com.otkritie.hackaton.databinding.FragmentChatScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatScreen : Fragment() {

    private var _binding: FragmentChatScreenBinding? = null
    private val mBinding get() = _binding!!

    val args: ChatScreenArgs by navArgs()

    private val adapter = ChatAdapter()
    private val viewModel by viewModels<ChatViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatScreenBinding.inflate(layoutInflater, container, false)
        viewModel.getHistory()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.list.observe(viewLifecycleOwner) {
            adapter.setList(it)
            mBinding.rvChat.scrollToPosition(0)
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.clearMessageEvent.collect {
                    mBinding.etInputText.setText(it)
                }
            }
        }
        mBinding.apply {
            etInputText.setText(viewModel.message)
            rvChat.adapter = adapter
            etInputText.doAfterTextChanged { viewModel.message = it.toString() }
            btnSend.setOnClickListener {
                viewModel.sendMessage()
            }

            btnBack.setOnClickListener {
                openMenu()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun openMenu() {
        (requireActivity() as MainActivity).navController.navigate(R.id.action_chatScreen_to_chatMenuScreen)
    }

}
