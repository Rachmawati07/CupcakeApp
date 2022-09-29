package com.rachma.cupcakeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rachma.cupcakeapp.databinding.FragmentFlavorBinding
import com.rachma.cupcakeapp.model.OrderViewModel

/**
 * [FlavorFragment] memungkinkan pengguna untuk memilih rasa cupcake untuk pesanannya.
 */

// Untuk mendeklarasikan class yang bernama FlavorFragment
class FlavorFragment : Fragment() {

    // Untuk mendeklarasikan objek binding instance yang sesuai dengan layout fragment_flavor.xml
    // Dan properti ini bukan nol antara callback lifecycle onCreateView() dan onDestroyView(), ketika hierarki tampilan dilampirkan ke fragmen.
    private var binding: FragmentFlavorBinding? = null

    // Menggunakan delegasi properti Kotlin 'by activityViewModels()' dari artefak fragmen-ktx
    private val sharedViewModel: OrderViewModel by activityViewModels()

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Untuk memanggil FragmentFlavorBinding dengan menggunakan binding
        val fragmentBinding = FragmentFlavorBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            // Untuk menentukan fragmen sebagai pemilik lifecycle
            lifecycleOwner = viewLifecycleOwner

            // Untuk menetapkan view model ke properti di kelas binding
            viewModel = sharedViewModel

            // Untuk menetapkan fragmen
            flavorFragment = this@FlavorFragment
        }
    }

    // Untuk mendeklarasikan function yang bernama goToNextScreen
    // Untuk menavigasikan ke tampilan berikutnya untuk memilih tanggal pengambilan cupcake
    fun goToNextScreen() {
        findNavController().navigate(R.id.action_flavorFragment_to_pickupFragment)
    }

    // Metode lifecycle fragmen ini dipanggil saat hierarki tampilan terkait dengan fragmen yang sedang dihapus
    // Dan akibatnya, akan membersihkan objek binding
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}