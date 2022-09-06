package com.example.imdbtopmovies.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.imdbtopmovies.R
import com.example.imdbtopmovies.databinding.FragmentRegisterBinding
import com.example.imdbtopmovies.models.register.RegisterBody
import com.example.imdbtopmovies.utils.UserDataStore
import com.example.imdbtopmovies.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    //Binding
    lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    @Inject
    lateinit var user: RegisterBody

    @Inject
    lateinit var dataStore: UserDataStore


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init views
        binding.apply {

            //click
            submit.setOnClickListener {

                //Get Inputs
                val name = userName.text.toString().trim()
                val email = userEmail.text.toString().trim()
                val password = userPassword.text.toString().trim()

                //Input Validation
                if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                    user.name = name
                    user.password = password
                    user.email = email

                    //send Data
                    viewModel.registerUser(user)
                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.fillAllFields),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                //ProgressBar
                viewModel.loading.observe(viewLifecycleOwner) {
                    loading.visibility = if (it) View.VISIBLE else View.INVISIBLE
                    submit.visibility = if (it) View.INVISIBLE else View.VISIBLE
                }

                //Register Response
                viewModel.registerUserResponse.observe(viewLifecycleOwner) {
                    lifecycleScope.launchWhenCreated {
                        dataStore.saveUserToken(it.name.toString())
                    }
                }

                //Error
                viewModel.error.observe(viewLifecycleOwner) {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}