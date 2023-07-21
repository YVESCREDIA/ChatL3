package com.example.chatl3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.chatl3.databinding.FragmentFirstBinding
import com.example.chatl3.entity.Msg
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {

            val user= binding.edtUser.text.toString()
            val msg= binding.edtMsg.text.toString()

            if (user.isEmpty() || msg.isEmpty()){
                binding.edtUser.error = "Please enter your name"
                binding.edtMsg.error = "Please enter your message"
                return@setOnClickListener
            }
            else{

                val database = Firebase.database
                val myRef = database.getReference("messages")
                myRef.addValueEventListener( object : com.google.firebase.database.ValueEventListener {
                    override fun onDataChange(snapshot: com.google.firebase.database.DataSnapshot) {
                        val message = Msg("1",user,msg)
                        message.id = myRef.child("messages").push().key.toString()

                        myRef.child(message.id).setValue(message)
                    }

                    override fun onCancelled(error: com.google.firebase.database.DatabaseError) {
                       Toast.makeText(context,"Failed to send message",Toast.LENGTH_SHORT).show()
                    }
                })
                 findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

            }



        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}