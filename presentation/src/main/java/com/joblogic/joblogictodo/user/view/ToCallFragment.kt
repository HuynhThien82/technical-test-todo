package com.joblogic.joblogictodo.user.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.joblogic.joblogictodo.R
import com.joblogic.joblogictodo.databinding.FragmentToCallBinding
import com.joblogic.joblogictodo.user.view.adapter.ContactAdapter
import com.joblogic.joblogictodo.user.viewmodel.ToCallViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ToCallFragment : Fragment() {
    private val viewModel: ToCallViewModel by viewModel()
    private var binding: FragmentToCallBinding? = null
    private lateinit var adapter: ContactAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToCallBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCallList()
    }

    private fun initCallList(){
        adapter = ContactAdapter()
        binding!!.recCallList.adapter = adapter
        viewModel.callList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(),error, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.getCallList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ToCallFragment()
    }
}