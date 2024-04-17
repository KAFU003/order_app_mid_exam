/**
 * 組員
 *11056034楊文豪
 *11056003鄧寓憲
 */
package im.ntub.myapplicationactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import im.ntub.myapplicationactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            if(result.resultCode == RESULT_OK){
                val data = result.data
                binding.txtMain.text = data?.getStringExtra("Main")
                binding.txtDrink.text = data?.getStringExtra("Drink")
                binding.txtDessert.text = data?.getStringExtra("Dessert")
                binding.txtTotal.text = data?.getStringExtra("Total")
            }
        }

        binding.btnOrder.setOnClickListener {
            val intent = Intent(this, SecActivity::class.java)
            launcher.launch(intent)
        }
    }
}