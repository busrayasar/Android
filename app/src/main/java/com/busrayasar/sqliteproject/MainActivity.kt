package com.busrayasar.sqliteproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //try catch içinde SQLite kodlarımızı yazıcaz
        try {
            //db yi oluşturalım
            val database = this.openOrCreateDatabase("products", Context.MODE_PRIVATE, null)
            database.execSQL("CREATE TABLE IF NOT EXISTS products (id INTEGER PRIMARY KEY ,name VARCHAR, price INT)")
            //database.execSQL("INSERT INTO product(name, price) VALUES('Shoes', 100)")
            //SQLite hatasında uygulamayı silip yeniden çalıştır
            //database.execSQL("INSERT INTO product(name, price) VALUES('Shirt', 200)")
            //database.execSQL("INSERT INTO product(name, price) VALUES('Tshirt', 50)")
            //database.execSQL("INSERT INTO product(name, price) VALUES('Scarf', 120)")
            //database.execSQL("INSERT INTO product(name, price) VALUES('Hat', 40)")

            //database.execSQL("DELETE  FROM products  WHERE id = 5")
            //database.execSQL("UPDATE products SET price = 250  WHERE name = 'Shirt' ")
            database.execSQL("UPDATE products SET name = 'Boots' WHERE id = 1")



            val cursor = database.rawQuery("SELECT * FROM products", null)

           // val cursor = database.rawQuery("SELECT * FROM products WHERE name = 'Hat'", null)
            //val cursor = database.rawQuery("SELECT * FROM products WHERE id = 3 ", null)
            //val cursor = database.rawQuery("SELECT * FROM products WHERE name LIKE 'A%'", null)//A ile başlayan

            val idColumnIndex = cursor.getColumnIndex("id")
            val isimColumnIndex = cursor.getColumnIndex("name")
            val priceColumnIndex = cursor.getColumnIndex("price")
            //db ye eklediklerimizi okuyalım
            while (cursor.moveToNext()){
                println("ID: ${cursor.getInt(idColumnIndex)}")
                println("Name ${cursor.getString(isimColumnIndex)}")
                println("Price ${cursor.getInt(priceColumnIndex)}")


            }
            cursor.close()


        }catch( e : Exception){
            e.printStackTrace()
        }
    }
}