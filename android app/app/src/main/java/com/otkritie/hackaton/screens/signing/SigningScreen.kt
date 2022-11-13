package com.otkritie.hackaton.screens.signing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.otkritie.hackaton.MainActivity
import com.otkritie.hackaton.R
import com.otkritie.hackaton.core.RequestState
import com.otkritie.hackaton.databinding.FragmentSigningScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SigningScreen : Fragment() {

    private var _binding: FragmentSigningScreenBinding? = null
    private val mBinding get() = _binding!!

    private val viewModel by viewModels<SigningViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSigningScreenBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(mBinding) {
            etLogin.setText(viewModel.login)
            etPassword.setText(viewModel.password)
            etLogin.doAfterTextChanged { viewModel.login = it.toString() }
            etPassword.doAfterTextChanged { viewModel.password = it.toString() }
            btnStart.setOnClickListener {
                viewModel.login()
            }
        }
        viewModel.requestState.observe(viewLifecycleOwner) {
            when (it) {
                is RequestState.Failure -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    mBinding.progressBar.visibility = View.GONE
                }
                RequestState.Loading -> {
                    mBinding.progressBar.visibility = View.VISIBLE
                }
                RequestState.Success -> {
                    mBinding.progressBar.visibility = View.GONE
                    (requireActivity() as MainActivity).navController.navigate(R.id.action_signingScreen_to_chatMenuScreen)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
