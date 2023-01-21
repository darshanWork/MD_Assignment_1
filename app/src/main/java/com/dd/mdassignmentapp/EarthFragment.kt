package com.dd.mdassignmentapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.dd.mdassignmentapp.databinding.FragmentEarthBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EarthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EarthFragment : Fragment(), SensorEventListener {
    // TODO: Rename and change types of parameters
    private lateinit var binding : FragmentEarthBinding
    private lateinit var navController: NavController
    private lateinit var myViewModel: MyViewModel
    private lateinit var sensorManager: SensorManager
    lateinit var accelerometer: Sensor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //create model for fragment
        myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        var myModel=myViewModel.myLiveModel.value

        //sensor management
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)

        for(s in deviceSensors) {
            Log.d("MyTAG",s.name)
        }

        //accelerometer
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_STATUS_ACCURACY_LOW)

        //increase score
        /*while(binding.earthSurfaceView.isRunning)
        {
            if(myModel != null)
            {
                myModel.score += 1
                binding.earthScoreTextView.text = "Score: " + myModel.score
            }
        }*/

        //navigation
        navController = findNavController()
        if(!binding.earthSurfaceView.isRunning)
        {
            navController.navigate(R.id.action_earthFragment_to_gameOverFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
            //param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_earth, container, false)

        binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EarthFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EarthFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null)
            return

        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER)
        {
            var x=event.values[0]
            var y=event.values[1]
            var z=event.values[2]


            var myText = "%.2f".format(x) + " %.2f".format(y) + " %.2f".format(z)


            if(event.values[0] > 1f)
            {
                binding.earthSurfaceView.redBall.move(binding.earthSurfaceView.canvas, -50)
                myText += " MOVE LEFT"
            }
            else if(event.values[0] < -1f)
            {
                binding.earthSurfaceView.redBall.move(binding.earthSurfaceView.canvas, 50)
                myText += " MOVE RIGHT"
            }

            Log.d("MyTAG",myText)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}