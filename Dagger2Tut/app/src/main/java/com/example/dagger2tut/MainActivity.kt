package com.example.dagger2tut

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.dagger2tut.basics.Car
import com.example.dagger2tut.basics.DaggerCarComponent
import com.example.dagger2tut.interfaceinject.DaggerCar3Component
import com.example.dagger2tut.runtimeinject.DaggerCoffeeComponent
import com.example.dagger2tut.runtimeinject.DaggerTeaComponent
import com.example.dagger2tut.runtimeinject.TeaModule
import com.example.dagger2tut.thirdpartylibs.DaggerCar2Component
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var car: Car

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //.ctor injection doesn't work for Activities as Android instantiates them at runtime
        //This is how you inject a field into MainActivity
        val carComponent = DaggerCarComponent.create()
        carComponent.fieldInject(this)
        car.drive()

        //Third party library classes injection
        val car2 = DaggerCar2Component.create().createNewCar2()
        car2.drive()

        //Interface injection
        val car3 = DaggerCar3Component.create().createNewCar3()
        car3.drive()

        //injecting values at runtime example
        //First approach - @Provides and Module params
        for(i in 1..3) {
            val tea = DaggerTeaComponent.builder()
                .teaModule(TeaModule(sugar = i))
                .build()
                .createNewTea()
            Log.d(AppConfig.TAG, "tea created: $tea")
        }
        //second approach - @Binds and Builder Pattern
        for(i in 1..2) {
            val coffee = DaggerCoffeeComponent.builder()
                .sugar(i)
                .milk(i)
                .build()
                .createNewCoffee()
            Log.d(AppConfig.TAG, "coffee created: $coffee")
        }

    }
}