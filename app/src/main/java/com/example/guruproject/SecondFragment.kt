package com.example.guruproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import com.example.guruproject.databinding.FragmentSecondBinding
import org.threeten.bp.LocalDate
import org.threeten.bp.format.TextStyle
import java.util.Locale

class SecondFragment : Fragment() {

    private var binding: FragmentSecondBinding? = null

    // 각 버튼의 체크 상태를 저장하는 MutableLiveData
    private val isButton1Checked = MutableLiveData(false)
    private val isButton2Checked = MutableLiveData(false)
    private val isButton3Checked = MutableLiveData(false)
    private val isButton4Checked = MutableLiveData(false)
    private val isButton5Checked = MutableLiveData(false)
    private val isButton6Checked = MutableLiveData(false)
    private val isButton7Checked = MutableLiveData(false)
    private val isButton8Checked = MutableLiveData(false)
    private val isButton9Checked = MutableLiveData(false)

    private val viewModel: SecondFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("SecondFragment", "onCreateView")
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("SecondFragment", "onViewCreated")

        // 캘린더 날짜 선택 시 날짜 텍스트 표기
        binding?.calendarView?.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "${year}년 ${month + 1}월 ${dayOfMonth}일 ${getDayOfWeek(year, month, dayOfMonth)}"
            binding?.caltext?.text = selectedDate
        }

        // 각 버튼에 클릭 리스너를 설정
        binding?.button1?.setOnClickListener { isButton1Checked.value = !isButton1Checked.value!! }
        binding?.button2?.setOnClickListener { isButton2Checked.value = !isButton2Checked.value!! }
        binding?.button3?.setOnClickListener { isButton3Checked.value = !isButton3Checked.value!! }
        binding?.button4?.setOnClickListener { isButton4Checked.value = !isButton4Checked.value!! }
        binding?.button5?.setOnClickListener { isButton5Checked.value = !isButton5Checked.value!! }
        binding?.button6?.setOnClickListener { isButton6Checked.value = !isButton6Checked.value!! }
        binding?.button7?.setOnClickListener { isButton7Checked.value = !isButton7Checked.value!! }
        binding?.button8?.setOnClickListener { isButton8Checked.value = !isButton8Checked.value!! }
        binding?.button9?.setOnClickListener { isButton9Checked.value = !isButton9Checked.value!! }

        // 버튼의 체크 상태를 관찰하여 버튼 배경색 업데이트
        isButton1Checked.observe(viewLifecycleOwner) { isChecked ->
            updateButtonCheckState(binding?.button1, isChecked)
            updateButtonCount()
        }
        isButton2Checked.observe(viewLifecycleOwner) { isChecked ->
            updateButtonCheckState(binding?.button2, isChecked)
            updateButtonCount()
        }
        isButton3Checked.observe(viewLifecycleOwner) { isChecked ->
            updateButtonCheckState(binding?.button3, isChecked)
            updateButtonCount()
        }
        isButton4Checked.observe(viewLifecycleOwner) { isChecked ->
            updateButtonCheckState(binding?.button4, isChecked)
            updateButtonCount()
        }
        isButton5Checked.observe(viewLifecycleOwner) { isChecked ->
            updateButtonCheckState(binding?.button5, isChecked)
            updateButtonCount()
        }
        isButton6Checked.observe(viewLifecycleOwner) { isChecked ->
            updateButtonCheckState(binding?.button6, isChecked)
            updateButtonCount()
        }
        isButton7Checked.observe(viewLifecycleOwner) { isChecked ->
            updateButtonCheckState(binding?.button7, isChecked)
            updateButtonCount()
        }
        isButton8Checked.observe(viewLifecycleOwner) { isChecked ->
            updateButtonCheckState(binding?.button8, isChecked)
            updateButtonCount()
        }
        isButton9Checked.observe(viewLifecycleOwner) { isChecked ->
            updateButtonCheckState(binding?.button9, isChecked)
            updateButtonCount()
        }
    }

    // 체크된 버튼의 개수를 반환하는 함수
    private fun getCheckedCount(): Int {
        var count = 0
        if (isButton1Checked.value == true) count++
        if (isButton2Checked.value == true) count++
        if (isButton3Checked.value == true) count++
        if (isButton4Checked.value == true) count++
        if (isButton5Checked.value == true) count++
        if (isButton6Checked.value == true) count++
        if (isButton7Checked.value == true) count++
        if (isButton8Checked.value == true) count++
        if (isButton9Checked.value == true) count++
        return count
    }

    // 캘린더 날짜 선택시 요일을 반환하는 함수
    private fun getDayOfWeek(year: Int, month: Int, dayOfMonth: Int): String {
        val localDate = LocalDate.of(year, month + 1, dayOfMonth)
        return localDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN)
    }

    // 버튼의 체크 상태를 업데이트하는 함수
    private fun updateButtonCheckState(button: Button?, isChecked: Boolean) {
        button?.let {
            if (isChecked) {
                it.background = ContextCompat.getDrawable(requireContext(), R.drawable.checked)
            } else {
                it.background = ContextCompat.getDrawable(requireContext(), R.drawable.before_check)
            }
        }
    }

    // 누적된 숫자를 업데이트하는 함수
    private fun updateButtonCount() {
        val checkedCount = getCheckedCount()
        viewModel.updateWaterButtonCount(checkedCount)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

