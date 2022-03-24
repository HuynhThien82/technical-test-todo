package com.joblogic.joblogictodo.product.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.joblogic.joblogictodo.R
import com.joblogic.joblogictodo.databinding.FragmentToSellBinding
import com.joblogic.joblogictodo.product.view.adapter.ProductAdapter
import com.joblogic.joblogictodo.product.viewmodel.ToSellViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ToSellFragment : Fragment() {
    private val viewModel: ToSellViewModel by viewModel()
    private var binding: FragmentToSellBinding? = null
    private lateinit var adapter: ProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToSellBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        generateData()
        initSellList()
    }

    private fun generateData() {
        val sharePref = requireContext().getSharedPreferences("TODO", Context.MODE_PRIVATE)
        val isFirstRun = sharePref.getBoolean("first_run",true)
        Log.d(TAG, "generateData: $isFirstRun")
        if (isFirstRun) {
            with(sharePref.edit()) {
                putBoolean("first_run", false)
                apply()
            }
            viewModel.generateData()
        }
    }

    private fun initSellList() {
        adapter = ProductAdapter()
        binding!!.recSellList.adapter = adapter
        viewModel.sellList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(),error, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.getSellList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ToSellFragment()

        private val TAG = this::class.java.name
    }
}