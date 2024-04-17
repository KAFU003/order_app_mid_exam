package im.ntub.myapplicationactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import im.ntub.myapplicationactivity.databinding.ActivitySecBinding
import android.widget.*
import androidx.appcompat.app.AlertDialog

class SecActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecBinding
    private var total = 0  // 移到這裡以便全域使用

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 確認按鈕點擊監聽器
        binding.btnConfirm.setOnClickListener {
            // 這裡不需要再進行 total += ... 的操作了，因為已經在選擇監聽器中進行了加總
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
                    total += 50
                }
                "C餐 $70" -> {
                    total += 50
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

            // 將結果放入 Intent 並設置結果
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
                }
                .create()
                .show()
        }
    }
}