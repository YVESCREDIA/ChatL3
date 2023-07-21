package com.example.chatl3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.chatl3.adapters.AdapterMessages
import com.example.chatl3.databinding.FragmentSecondBinding
import com.example.chatl3.entity.Msg
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        val database = Firebase.database
        val databaseReference = database.getReference("messages")
        val list = mutableListOf<Msg>()

        databaseReference.addValueEventListener(object : com.google.firebase.database.ValueEventListener{

            override fun onDataChange(snapshot: com.google.firebase.database.DataSnapshot) {
                list.clear()
                for (postSnapshot in snapshot.children){
                   val message = postSnapshot.getValue(Msg::class.java)
                    Toast.makeText(requireContext(),message!!.msg, Toast.LENGTH_LONG).show()
                   // list.add(message!!)
                }

//                val adapter = AdapterMessages(requireContext(),list)
//                val layoutManager: androidx.recyclerview.widget.RecyclerView.LayoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
//                binding.rcLMessage.layoutManager = layoutManager
//                binding.rcLMessage.adapter = adapter
//                binding.rcLMessage.setHasFixedSize(true)
            }

            override fun onCancelled(error: com.google.firebase.database.DatabaseError) {
                Toast.makeText(requireContext(),error.message, Toast.LENGTH_LONG).show()
            }

        })

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}