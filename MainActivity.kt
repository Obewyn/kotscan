import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : Activity() {

    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)
        val scanButton: Button = findViewById(R.id.scanButton)

        scanButton.setOnClickListener {
            IntentIntegrator(this).initiateScan() // Start QR scanner
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                resultTextView.text = "Result: ${result.contents}" // Display scanned QR code content
            } else {
                resultTextView.text = "Scan cancelled"
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
