package com.example.quadraticequation

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var valueA: TextView = findViewById(R.id.valueA) as TextView;
        var valueB: TextView = findViewById(R.id.valueB) as TextView;
        var valueC: TextView = findViewById(R.id.valueC) as TextView;
        var button: Button = findViewById(R.id.button) as Button;

        val alert1 = AlertDialog.Builder(this).setPositiveButton("Понял", { d, id->d.cancel() } )

        fun discriminant(a: Double, b: Double, c: Double) =
            (b * b) - (4 * a * c)

        fun quadraticEquationRoot(a: Double, b: Double, c: Double): Double {
            try {
                var df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.DOWN
                val sd: Double = sqrt(discriminant(a, b, c))
                var x1 = (-b + sd) / (2 * a)
                var x2 = (-b - sd) / (2 * a)
                if (a == 0.0 && b != 0.0) {
                    x1 = -c/b

                    val valX1 = df.format(x1)
                    alert1.setMessage("Уравнение является линейным! \n x1 = $valX1").create();
                    alert1.show();
                }
                else if (a == 0.0 && b == 0.0 && c == 0.0){
                    alert1.setMessage("При любых значениях х уравнение принимает верное равенство").create();
                    alert1.show();
                }
                else if (discriminant(a, b, c) < 0){
                    alert1.setMessage("Уравнение не имеет корней").create();
                    alert1.show();
                }
                else if (a == 0.0 && b == 0.0 && c != 0.0){
                    val x = (-b + sqrt(discriminant(a, b, c))) / (2 * a)
                    alert1.setMessage("Выражение не является уравнением").create();
                    alert1.show();
                }
                else if (discriminant(a, b, c) == 0.0){
                    val x = (-b - sqrt(discriminant(a, b, c))) / (2 * a)
                    alert1.setMessage("Уравнение имеет 2 одинаковых корня x1 = x2 = $x ").create();
                    alert1.show();
                }
                else if (a == b){
                    alert1.setMessage("Уравнение имеет 2 корня равные $x1 ").create();
                    alert1.show();
                }
                else{
                    val valX1 = df.format(x1)
                    val valX2 = df.format(x2)
                    alert1.setMessage("Уравнение имеет 2 корня \n  x1 = $valX1 x2 = $valX2 ").create();
                    alert1.show();
                }
            }
            catch (e: Exception) {
                alert1.setMessage("Проверьте введеные данные").create();
                alert1.show();
            }
            return 1.0
        }

        button.setOnClickListener{

            quadraticEquationRoot(valueA.text.toString().toDouble(),
                                    valueB.text.toString().toDouble(), valueC.text.toString().toDouble())
        }

    }
}