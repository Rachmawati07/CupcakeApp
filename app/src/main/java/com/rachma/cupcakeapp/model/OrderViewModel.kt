package com.rachma.cupcakeapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// Untuk mendeklarasikan harga untuk satu cupcake
private const val PRICE_PER_CUPCAKE = 2.00

// Untuk mendeklarasikan biaya tambahan untuk pengambilan pesanan di hari yang sama
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

/**
 * [OrderViewModel] ini berguna untuk menyimpan informasi tentang pesanan cupcake dalam hal jumlah, rasa, dan
 * tanggal pengambilan. Dan activity ini juga tahu bagaimana menghitung harga total berdasarkan rincian pesanan pelanggan.
 */

// Untuk mendeklarasikan class yang bernama OrderViewModel
class OrderViewModel : ViewModel() {

    // Untuk mendeklarasikan jumlah cupcake pada pesanan pelanggan
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    // Untuk mendeklarasikan rasa pada pesanan cupcake
    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    // Untuk mendeklarasikan kemungkinan pilihan tanggal
    val dateOptions: List<String> = getPickupOptions()

    // Untuk mendeklarasikan tanggal pengambilan cupcake
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    // Untuk mendeklarasikan harga pesanan yang ditampilkan saat ini
    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price) {
        // Untuk memformat harga ke dalam mata uang lokal dan ini akan dikembalikan sebagai LiveData<String>
        NumberFormat.getCurrencyInstance().format(it)
    }

    init {
        // Untuk menenetapkan nilai awal untuk pesanan cupcake
        resetOrder()
    }

    // Untuk mendeklarasikan fungsi yang bernama setQuantity
    // Untuk mengatur jumlah cupcakes untuk pesanan yang dipesan pelanggan
    // @param numberCupcake sesuai pesanan
    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
        updatePrice()
    }

    // Untuk mendeklarasikan function yang bernama setFlavor
    // Untuk mengatur rasa cupcakes untuk pesanan. Dan hanya ada 1 rasa yang dapat dipilih untuk seluruh pesanan.
    // @param yang diinginkan Flavor adalah rasa cupcake sebagai string
    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    // Untuk mendeklarasikan function yang bernama setDate
    // Untuk menetapkan tanggal pengambilan untuk pesanan cupcake yang dipesan oleh pelanggan
    // @param pickupDate adalah tanggal pengambilan sebagai string
    fun setDate(pickupDate: String) {
        _date.value = pickupDate
        updatePrice()
    }

    // Untuk mendeklarasikan function yang bernama hasNoFlavorSet
    // Untuk mengembalikan nilai true jika rasa belum dipilih untuk pesanan. Mengembalikan false sebaliknya.
    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

    // Untuk mendeklarasikan function yang bernama resetOrder
    // Untuk mengatur ulang pesanan dengan menggunakan nilai default awal untuk jumlah, rasa, tanggal, dan harga.
    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }

    // Untuk mendeklarasikan function yang bernama updatePrice
    // Untuk  memperbarui harga berdasarkan detail pesanan.
    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_CUPCAKE

        // Jika pengguna memilih opsi pertama (hari ini) untuk pengambilan, maka akan tambahkan biaya tambahan
        if (dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }

    // Untuk mendeklarasikan function yang bernama getPickupOptions
    // Dan akan mengembalikan daftar opsi tanggal yang dimulai dengan tanggal saat ini dan 3 tanggal berikutnya
    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }
}