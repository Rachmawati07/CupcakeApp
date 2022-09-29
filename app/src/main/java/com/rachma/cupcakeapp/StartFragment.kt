package com.rachma.cupcakeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rachma.cupcakeapp.databinding.FragmentStartBinding
import com.rachma.cupcakeapp.model.OrderViewModel

/**
 * StartFragment Ini adalah layar pertama dari aplikasi Cupcake. Pengguna dapat memilih berapa banyak cupcakes yang akan dipesan.
 */

// Untuk mendeklarasikan class yang bernama StartFragment
class StartFragment : Fragment() {

    // Untuk mendeklarasikan objek binding instance yang sesuai dengan layout fragment_start.xml
    // Dan Properti ini bukan nol antara callback siklus  onCreateView() dan onDestroyView(), ketika hierarki tampilan dilampirkan ke fragmen.
    private var binding: FragmentStartBinding? = null

    // Untuk menggunakan delegasi properti Kotlin 'by activityViewModels()' dari artefak fragmen-ktx
    private val sharedViewModel: OrderViewModel by activityViewModels()

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Untuk memanggil FragmenStartBinding dengan menggunakan binding
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment = this
    }

    // Untuk memulai pesanan dengan memilih jumlah cupdake yang diinginkan dan navigasikan ke tampilan berikutnya.
    fun orderCupcake(quantity: Int) {

        // Untuk memperbarui model tampilan dengan jumlah cupcake yang dipilih oleh pengguna
        sharedViewModel.setQuantity(quantity)

        // Jika belum ada rasa yang diatur dalam model tampilan, maka akan dipilih vanilla sebagai rasa default
        if (sharedViewModel.hasNoFlavorSet()) {
            sharedViewModel.setFlavor(getString(R.string.vanilla))
        }

        // Untuk menavigasikan ke tujuan berikutnya untuk memilih rasa cupcakes
        findNavController().navigate(R.id.action_startFragment_to_flavorFragment)
    }

    // Metode lifecycle fragmen ini dipanggil saat hierarki tampilan terkait dengan fragmen yang sedang dihapus.
    // Dan akibatnya, akan membersihkan objek binding.
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}