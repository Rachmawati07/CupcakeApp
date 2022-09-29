package com.rachma.cupcakeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rachma.cupcakeapp.databinding.FragmentPickupBinding
import com.rachma.cupcakeapp.model.OrderViewModel

/**
 * [PickupFragment] berguna untuk memungkinkan pengguna untuk memilih tanggal pengambilan untuk pesanan cupcake.
 */

// Untuk mendeklarasikan class yang bernama PickupFragment
class PickupFragment : Fragment() {

    // Untuk mendeklarasikan  objek binding instance yang sesuai dengan layout fragment_pickup.xml
    // Dan properti ini bukan nol antara callback siklus hidup onCreateView() dan onDestroyView(), ketika hierarki tampilan dilampirkan ke fragmen.
    private var binding: FragmentPickupBinding? = null

    // Menggunakan delegasi properti Kotlin 'by activityViewModels()' dari artefact fragmen-ktx
    private val sharedViewModel: OrderViewModel by activityViewModels()

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Untuk memanggil FragmentPickupBinding dengan menggunakan binding
        val fragmentBinding = FragmentPickupBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            pickupFragment = this@PickupFragment
        }
    }

    // Untuk mendeklarasikan function yang bernama goToNextScreen
    // Untuk menavigasikan ke tampilan berikutnya untuk melihat ringkasan pesanan cupcake
    fun goToNextScreen() {
        findNavController().navigate(R.id.action_pickupFragment_to_summaryFragment)
    }

    //  Metode lifecycle fragmen ini dipanggil saat hierarki tampilan terkait dengan fragmen yang sedang dihapus.
    //  Dan akibatnya, akan membersihkan objek binding.
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}