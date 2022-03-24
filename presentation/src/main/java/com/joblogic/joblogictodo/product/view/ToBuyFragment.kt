package com.joblogic.joblogictodo.product.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.joblogic.joblogictodo.R
import com.joblogic.joblogictodo.databinding.FragmentToBuyBinding
import com.joblogic.joblogictodo.product.view.adapter.ProductAdapter
import com.joblogic.joblogictodo.product.viewmodel.ToBuyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ToBuyFragment : Fragment() {
    private val viewModel: ToBuyViewModel by viewModel()
    private var binding: FragmentToBuyBinding? = null
    private lateinit var adapter: ProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToBuyBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBuyList()
    }

    private fun initBuyList() {
        adapter = ProductAdapter()
        binding!!.recBuyList.adapter = adapter
        viewModel.buyList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(),error,Toast.LENGTH_LONG).show()
            }
        }
        viewModel.getBuyList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ToBuyFragment()
        private val TAG = this::class.java.name
    }
}