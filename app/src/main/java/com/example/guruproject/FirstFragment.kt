package com.example.guruproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.guruproject.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    // MainActivity에서 가져온 SecondFragmentViewModel (fragmentsecond button 누적 model) 사용
    private val viewModel: SecondFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // firstfragment에서 page(자세히 보기 >) 버튼 클릭시 secondfragment로 이동
        binding.page.setOnClickListener {
            navigateToSecondFragment()
        }

        // ViewModel의 버튼 상태 관찰해 누적된 숫자 업데이트
        viewModel.waterButtonCount.observe(viewLifecycleOwner, Observer { (checkedCount, _) ->
            val todo1Count = checkedCount / 3
            val todo2Count = (checkedCount % 3) / 3
            val todo3Count = (checkedCount % 3) % 3

            // firstfragment textview id todo1,2,3 에 나타내기
            binding.todo1.text = "$todo1Count/3"
            binding.todo2.text = "$todo2Count/3"
            binding.todo3.text = "$todo3Count/3"
        })
    }

    private fun navigateToSecondFragment() {
        val navController = findNavController()
        navController.navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
