package im.ntub.myapplicationactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import im.ntub.myapplicationactivity.databinding.ActivitySecBinding
import android.widget.*
import androidx.appcompat.app.AlertDialog

class SecActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecBinding
    private var total = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConfirm.setOnClickListener {

            val main = findViewById<RadioButton>(binding.rdgpMain.checkedRadioButtonId)?.text?.toString() ?: ""
            val drink = findViewById<RadioButton>(binding.rdgpDrink.checkedRadioButtonId)?.text?.toString() ?: ""
            val result_main = "主食: $main"
            val result_drink = "飲料: $drink"

            val CheeseCakeChecked = binding.cbCheeseCake.isChecked
            val ChocoCakeChecked = binding.cbChocoCake.isChecked

            when (main) {
                "A餐 $50" -> {
                    total += 50
                }
                "B餐 $60" -> {
                    total += 60
                }
                "C餐 $70" -> {
                    total += 70
                }
                else -> {
                    total += 0
                }
            }

            when (drink) {
                "奶茶 $30" -> {
                    total += 30
                }
                "紅茶 $25" -> {
                    total += 25
                }
                else -> {
                    total += 0
                }
            }

            val result_dessert = when {
                !CheeseCakeChecked && ChocoCakeChecked -> {
                    total += 50
                    "點心: 巧克力蛋糕 $50"
                }
                CheeseCakeChecked && !ChocoCakeChecked -> {
                    total += 45
                    "點心: 起司蛋糕 $45"
                }
                CheeseCakeChecked && ChocoCakeChecked -> {
                    total += 95
                    "點心: 巧克力蛋糕與起司蛋糕 $95"
                }
                else -> "點心: 你沒點喔"
            }

            val result_total = "總金額: $total"

            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder
                .setTitle("確認訂單")
                .setMessage("確定下單喔")
                .setCancelable(false)
                .setPositiveButton("是") { dialog, _ ->
                    dialog.dismiss()
                    intent.putExtra("Main", result_main)
                    intent.putExtra("Drink", result_drink)
                    intent.putExtra("Dessert", result_dessert)
                    intent.putExtra("Total", result_total)
                    setResult(RESULT_OK, intent)
                    finish()
                }
                .setNegativeButton("否") { dialog, _ ->
                dialog.dismiss()
                    total = 0
                }
                .create()
                .show()
        }
    }
}