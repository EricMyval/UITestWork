package com.ericmyval.uitestwork

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.ericmyval.uitestwork.databinding.FragmentScannerBinding

class ScannerFragment: Fragment(R.layout.fragment_scanner) {
    private lateinit var binding: FragmentScannerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScannerBinding.bind(view)
        binding.lvBL.adapter = ArrayAdapter(requireContext(),
            R.layout.list_item_bl, arrayOf(
                "Xiaomi Redmi 9", "Samsung s21", "Xiaomi band 4","Xiaomi mi 9", "Samsung a52",
                "Samsung s20", "Xiaomi mi 8", "Samsung a50","Xiaomi band 2", "Xiaomi mi 8",
                "Xiaomi band 3", "Xiaomi band 1",  "Samsung a50", "Xiaomi mi 9", "Samsung a52",
                "Samsung s22", "Xiaomi Redmi 9", "Samsung s21", "Xiaomi band 4",  "Samsung s20",
                "Samsung a50","Xiaomi band 2", "Xiaomi band 3", "Xiaomi band 1",  "Samsung a50"
            ))
    }
}