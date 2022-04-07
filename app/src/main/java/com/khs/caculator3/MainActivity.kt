package com.khs.caculator3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.khs.caculator3.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder // 이 빌더 부분을 잘 모르겠습니다

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 숫자 버튼 클릭 이벤트 리스너
        binding.btn00.setOnClickListener { appendOnClick(true, "00") }
        binding.btn0.setOnClickListener { appendOnClick(true, "0") }
        binding.btn1.setOnClickListener { appendOnClick(true, "1") }
        binding.btn2.setOnClickListener { appendOnClick(true, "2") }
        binding.btn3.setOnClickListener { appendOnClick(true, "3") }
        binding.btn4.setOnClickListener { appendOnClick(true, "4") }
        binding.btn5.setOnClickListener { appendOnClick(true, "5") }
        binding.btn6.setOnClickListener { appendOnClick(true, "6") }
        binding.btn7.setOnClickListener { appendOnClick(true, "7") }
        binding.btn8.setOnClickListener { appendOnClick(true, "8") }
        binding.btn9.setOnClickListener { appendOnClick(true, "9") }
        binding.btnDot.setOnClickListener { appendOnClick(true, ".") }

        binding.btnPlus.setOnClickListener { appendOnClick(false, "+") }
        binding.btnMinus.setOnClickListener { appendOnClick(false, "-") }
        binding.btnMultiply.setOnClickListener { appendOnClick(false, "*") }

        binding.btnDivide.setOnClickListener { appendOnClick(false, "/") }
        binding.btnLeftB.setOnClickListener { appendOnClick(false, "(") }
        binding.btnRightB.setOnClickListener { appendOnClick(false, ")") }

        binding.btnClear.setOnClickListener {
            clear()
        }

        binding.btnEqual.setOnClickListener {
            calculate()
        }
    }

    private fun appendOnClick(clear: Boolean, string: String) {
        if (clear) {
            binding.tvOutput.text = ""
            binding.tvInput.append(string)
        } else {
            binding.tvInput.append(binding.tvOutput.text)
            binding.tvInput.append(string)
            binding.tvOutput.text = ""
        }
    }

    private fun clear() {
        binding.tvInput.text = ""
        binding.tvOutput.text = ""
    }

    private fun calculate() {
        try {
            val input = ExpressionBuilder(binding.tvInput.text.toString()).build() // 빌더 부분을 잘 모르겠습니다.
            val output = input.evaluate()
            val longOutput = input.evaluate()
            if (output == longOutput.toDouble()) {
                binding.tvOutput.text = longOutput.toString()
            } else {
                binding.tvOutput.text = output.toString()
            }

        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()

        }
    }
}