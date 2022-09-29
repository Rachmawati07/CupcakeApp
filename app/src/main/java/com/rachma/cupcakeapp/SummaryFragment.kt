package com.rachma.cupcakeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.rachma.cupcakeapp.databinding.FragmentSummaryBinding
import com.rachma.cupcakeapp.model.OrderViewModel

/**
 * [SummaryFragment] ini berisi ringkasan detail pesanan dengan tombol untuk membagikan pesanan
 * melalui aplikasi lain.
 */

// Untuk mendeklarasikan class yang bernama SummaryFragment
class SummaryFragment : Fragment() {

    // Untuk mendeklarasikan  objek binding instance yang sesuai dengan layout fragment_pickup.xml
    // Dan properti ini bukan nol antara callback lifecycle onCreateView() dan onDestroyView(), ketika hierarki tampilan dilampirkan ke fragmen.
    private var binding: FragmentSummaryBinding? = null

    // Menggunakan delegasi properti Kotlin 'by activityViewModels()' dari artefact fragmen-ktx
    private val sharedViewModel: OrderViewModel by activityViewModels()

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Untuk memanggil FragmentSummarynBinding dengan menggunakan binding
        val fragmentBinding = FragmentSummaryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            summaryFragment = this@SummaryFragment
        }
    }

    // Untuk mendeklarasikan function yang bernama sendOrder
    // Untuk mengirim pesanan dengan membagikan detail pesanan ke aplikasi lain melalui intent implisit.
    fun sendOrder() {
        Toast.makeText(activity, "Send Order", Toast.LENGTH_SHORT).show()
    }

    // Metode lifecycle fragmen ini akan dipanggil saat hierarki tampilan terkait dengan fragmen yang sedang dihapus.
    // Dan akibatnya, akan membersihkan objek binding.
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}