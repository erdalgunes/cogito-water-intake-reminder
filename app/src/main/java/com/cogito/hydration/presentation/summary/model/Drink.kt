package com.cogito.hydration.presentation.summary.model

import androidx.annotation.DrawableRes
import com.cogito.hydration.R

sealed class Drink(@DrawableRes val icon: Int, val amount: Int, val unit: Unit){
    data object GlassOfWater: Drink(R.drawable.glass_half_alt, 300, Unit.Milliliters)
    data object CupOfCoffee: Drink(R.drawable.coffee_cup, 200, Unit.Milliliters)
//    data object SportsDrink: Type("Sports Drink", 500, Unit.Milliliters)
//    data object CanOfSoda: Type("Can of Soda", 150, Unit.Milliliters)
//    data object GlassOfMilk: Type("Glass of Milk", 200, Unit.Milliliters)
}
