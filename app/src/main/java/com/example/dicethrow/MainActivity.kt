package com.example.dicethrow

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlin.math.sqrt


class MainActivity : AppCompatActivity() {

    private var sensorManager: SensorManager? = null
    private var acceleration = 0f
    private var currentAcceleration = 0f
    private var lastAcceleration = 0f

    var last_button: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        Objects.requireNonNull(sensorManager)!!.registerListener(sensorListener, sensorManager!!
            .getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
        acceleration = 10f
        currentAcceleration = SensorManager.GRAVITY_EARTH
        lastAcceleration = SensorManager.GRAVITY_EARTH

        val dice1_Button = findViewById<Button>(R.id.one_dice_button)
        val dice2_Button = findViewById<Button>(R.id.two_dice_button)
        val dice3_Button = findViewById<Button>(R.id.three_dice_button)
        val clear_Button = findViewById<Button>(R.id.clear_button)


        val number1=findViewById<TextView>(R.id.dice1)
        val number2=findViewById<TextView>(R.id.dice2)
        val number3=findViewById<TextView>(R.id.dice3)

        val image1=findViewById<ImageView>(R.id.image_1)
        val image2=findViewById<ImageView>(R.id.image_2)
        val image3=findViewById<ImageView>(R.id.image_3)

        dice1_Button.setOnClickListener{
            last_button=1
            number1.setText("")
            number2.setText("")
            number3.setText("")
            image1.setImageResource(R.drawable.background)
            image2.setImageResource(R.drawable.background)
            image3.setImageResource(R.drawable.background)
        }
        dice2_Button.setOnClickListener{
            last_button=2
            number1.setText("")
            number2.setText("")
            number3.setText("")
            image1.setImageResource(R.drawable.background)
            image2.setImageResource(R.drawable.background)
            image3.setImageResource(R.drawable.background)
        }
        dice3_Button.setOnClickListener{
            last_button=3
            number1.setText("")
            number2.setText("")
            number3.setText("")
            image1.setImageResource(R.drawable.background)
            image2.setImageResource(R.drawable.background)
            image3.setImageResource(R.drawable.background)
        }
        clear_Button.setOnClickListener{
            last_button=0
            number1.setText("")
            number2.setText("")
            number3.setText("")
            image1.setImageResource(R.drawable.background)
            image2.setImageResource(R.drawable.background)
            image3.setImageResource(R.drawable.background)
        }
    }
    private val sensorListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {

            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            lastAcceleration = currentAcceleration
            currentAcceleration = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
            val delta: Float = currentAcceleration - lastAcceleration
            acceleration = acceleration * 0.9f + delta
            if (acceleration > 1) {
                if(last_button==1){
                    rollDice1()
                } else if(last_button==2){
                    rollDice2()
                }else if(last_button==3) {
                    rollDice3()
                }
            }
        }
        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
    }
    override fun onResume() {
        sensorManager?.registerListener(sensorListener, sensorManager!!.getDefaultSensor(
            Sensor .TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL
        )
        super.onResume()
    }
    override fun onPause() {
        sensorManager!!.unregisterListener(sensorListener)
        super.onPause()
    }

    private fun rollDice1() {
        val diceRoll = roll()
        val image1=findViewById<ImageView>(R.id.image_1)
        val image2=findViewById<ImageView>(R.id.image_2)
        val image3=findViewById<ImageView>(R.id.image_3)
        when (diceRoll){
            1->image1.setImageResource(R.drawable.dice_1)
            2->image1.setImageResource(R.drawable.dice_2)
            3->image1.setImageResource(R.drawable.dice_3)
            4->image1.setImageResource(R.drawable.dice_4)
            5->image1.setImageResource(R.drawable.dice_5)
            6->image1.setImageResource(R.drawable.dice_6)
        }
        image2.setImageResource(R.drawable.background)
        image3.setImageResource(R.drawable.background)

        val number1=findViewById<TextView>(R.id.dice1)
        val number2=findViewById<TextView>(R.id.dice2)
        val number3=findViewById<TextView>(R.id.dice3)
        number1.text = diceRoll.toString()
        number2.text = ""
        number3.text = ""
    }
    private fun rollDice2() {
        val diceRoll = roll()
        val diceRoll2 = roll()
        val image1=findViewById<ImageView>(R.id.image_1)
        val image2=findViewById<ImageView>(R.id.image_2)
        val image3=findViewById<ImageView>(R.id.image_3)
        when (diceRoll){
            1->image1.setImageResource(R.drawable.dice_1)
            2->image1.setImageResource(R.drawable.dice_2)
            3->image1.setImageResource(R.drawable.dice_3)
            4->image1.setImageResource(R.drawable.dice_4)
            5->image1.setImageResource(R.drawable.dice_5)
            6->image1.setImageResource(R.drawable.dice_6)
        }
        when (diceRoll2){
            1->image2.setImageResource(R.drawable.dice_1)
            2->image2.setImageResource(R.drawable.dice_2)
            3->image2.setImageResource(R.drawable.dice_3)
            4->image2.setImageResource(R.drawable.dice_4)
            5->image2.setImageResource(R.drawable.dice_5)
            6->image2.setImageResource(R.drawable.dice_6)
        }
        image3.setImageResource(R.drawable.background)
        // Update the screen with the dice roll
        val number1=findViewById<TextView>(R.id.dice1)
        val number2=findViewById<TextView>(R.id.dice2)
        val number3=findViewById<TextView>(R.id.dice3)
        number1.text = diceRoll.toString()
        number2.text = diceRoll2.toString()
        number3.text = ""
    }
    private fun rollDice3() {
        val diceRoll = roll()
        val diceRoll2 = roll()
        val diceRoll3 = roll()
        val image1=findViewById<ImageView>(R.id.image_1)
        val image2=findViewById<ImageView>(R.id.image_2)
        val image3=findViewById<ImageView>(R.id.image_3)
        when (diceRoll){
            1->image1.setImageResource(R.drawable.dice_1)
            2->image1.setImageResource(R.drawable.dice_2)
            3->image1.setImageResource(R.drawable.dice_3)
            4->image1.setImageResource(R.drawable.dice_4)
            5->image1.setImageResource(R.drawable.dice_5)
            6->image1.setImageResource(R.drawable.dice_6)
        }
        when (diceRoll2){
            1->image2.setImageResource(R.drawable.dice_1)
            2->image2.setImageResource(R.drawable.dice_2)
            3->image2.setImageResource(R.drawable.dice_3)
            4->image2.setImageResource(R.drawable.dice_4)
            5->image2.setImageResource(R.drawable.dice_5)
            6->image2.setImageResource(R.drawable.dice_6)
        }
        when (diceRoll3){
            1->image3.setImageResource(R.drawable.dice_1)
            2->image3.setImageResource(R.drawable.dice_2)
            3->image3.setImageResource(R.drawable.dice_3)
            4->image3.setImageResource(R.drawable.dice_4)
            5->image3.setImageResource(R.drawable.dice_5)
            6->image3.setImageResource(R.drawable.dice_6)
        }
        val number1=findViewById<TextView>(R.id.dice1)
        val number2=findViewById<TextView>(R.id.dice2)
        val number3=findViewById<TextView>(R.id.dice3)
        number1.text = diceRoll.toString()
        number2.text = diceRoll2.toString()
        number3.text = diceRoll3.toString()
    }
    fun roll(): Int {
        return (1..6).random()
    }
}
