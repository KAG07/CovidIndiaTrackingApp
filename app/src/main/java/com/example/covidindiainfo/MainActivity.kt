package com.example.covidindiainfo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val url="https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true"
        var requestq=Volley.newRequestQueue(this@MainActivity)
        var strreq=StringRequest(
            Request.Method.GET,
            url,
            { response ->
                try {

                    val jsonObject = JSONObject(
                        response.toString())
                    txtcases.text=jsonObject.getString("activeCases")
                    txtrecover.text=jsonObject.getString("recovered")
                    txtdeath.text=jsonObject.getString("deaths")
                    txttodaycase.text=jsonObject.getString("activeCasesNew")
                    txttodayrecover.text=jsonObject.getString("recoveredNew")
                    txttodaydeath.text=jsonObject.getString("deathsNew")


                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                Toast.makeText(
                    this@MainActivity,
                    error.message,
                    Toast.LENGTH_SHORT)
                    .show();
            }
        )
        requestq.add(strreq)
    }
}