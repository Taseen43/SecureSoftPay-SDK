package com.example.securesoftpayy;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

// SDK-এর প্রয়োজনীয় ক্লাসগুলো ইম্পোর্ট করা হচ্ছে
import com.securesoft.pay.PaymentRequest;
import com.securesoft.pay.PaymentResultListener;
import com.securesoft.pay.SecureSoftPay;
import com.securesoft.pay.SecureSoftPayConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // আপনার অ্যাপের লেআউট ফাইল এখানে সেট করুন।
        // উদাহরণস্বরূপ, যদি আপনার লেআউটের নাম activity_main.xml হয়:
        // setContentView(R.layout.activity_main);

        // ★★★ মূল পরিবর্তন এখানে ★★★
        // SDK initialize করার সময় এখন চারটি প্যারামিটারই প্রদান করতে হবে।

        String apiKey = "YOUR_SECRET_API_KEY_FROM_DASHBOARD";
        String baseUrl = "https://pay.yourdomain.com/api"; // আপনার পেমেন্ট গেটওয়ের বেস ইউআরএল
        String checkoutUrl = "https://pay.yourdomain.com/api/v1/checkout"; // পেমেন্ট শুরু করার সম্পূর্ণ ইউআরএল
        String verifyUrl = "https://pay.yourdomain.com/api/v1/verify"; // পেমেন্ট ভেরিফাই করার সম্পূর্ণ ইউআরএল

        // কনস্ট্রাক্টরটি এখন চারটি প্যারামিটার গ্রহণ করছে
        SecureSoftPayConfig config = new SecureSoftPayConfig(
                apiKey,
                baseUrl,
                checkoutUrl,
                verifyUrl
        );

        // SDK ইনিশিয়ালাইজ করা
        SecureSoftPay.initialize(config);

        // --- পেমেন্ট প্রক্রিয়া শুরু করার উদাহরণ ---
        // আপনার লেআউটে একটি বাটন থাকতে হবে যার আইডি payButton
        // Button payButton = findViewById(R.id.payButton);
        // payButton.setOnClickListener(v -> {

        // পেমেন্টের জন্য একটি রিকোয়েস্ট অবজেক্ট তৈরি করা
        PaymentRequest request = new PaymentRequest(
                150.50, // Example Amount
                "John Doe", // Example Customer Name
                "john.doe@example.com" // Example Customer Email
        );

        // পেমেন্ট প্রক্রিয়া শুরু করা
        SecureSoftPay.startPayment(MainActivity.this, request, new PaymentResultListener() {
            @Override
            public void onSuccess(String transactionId) {
                // পেমেন্ট সফল হলে এই মেথডটি কল হবে
                // এখানে আপনার প্রয়োজনীয় কোড লিখুন
                Toast.makeText(MainActivity.this, "Payment Success! TrxID: " + transactionId, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(String errorMessage) {
                // পেমেন্ট ব্যর্থ বা বাতিল হলে এই মেথডটি কল হবে
                // এখানে আপনার প্রয়োজনীয় কোড লিখুন
                Toast.makeText(MainActivity.this, "Payment Failed: " + errorMessage, Toast.LENGTH_LONG).show();
            }
        });
        // });
    }
}