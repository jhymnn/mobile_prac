package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    /**
     * [서버 접속 URL 안내]
     * 1. 안드로이드 에뮬레이터(AVD)에서 실행 시: "http://10.0.2.2:8080/param7" (에뮬레이터에서 PC의 localhost 가리킴)
     * 2. 실제 스마트폰 기기에서 실행 시: "http://<PC의_로컬_IP>:8080/param7" (예: "http://192.168.0.15:8080/param7")
     * 3. 안드로이드 기기 자체를 가리키는 localhost: "http://localhost:8080/param7"
     */
    private static final String SERVER_URL = "http://localhost:8080/param7";

    private TextView tvResult;
    private Button btnSend;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvResult = findViewById(R.id.tv_result);
        btnSend = findViewById(R.id.btn_send);

        btnSend.setOnClickListener(v -> sendPostRequest());

        sendPostRequest();
    }


    private void sendPostRequest() {
        tvResult.setText("서버로 요청을 전송하고 있습니다...\nURL: " + SERVER_URL);
        btnSend.setEnabled(false);

        executorService.execute(() -> {
            String resultText;
            HttpURLConnection conn = null;

            try {
                URL url = new URL(SERVER_URL);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json, text/plain, */*");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                conn.setDoOutput(true);
                conn.setDoInput(true);

                // 요청 JSON 바디 구성: {"itemname": "최민", "itemprice": "6000"}
                JSONObject jsonBody = new JSONObject();
                jsonBody.put("itemname", "최민");
                jsonBody.put("itemprice", "6000");

                byte[] postData = jsonBody.toString().getBytes(StandardCharsets.UTF_8);
                try (OutputStream os = conn.getOutputStream()) {
                    os.write(postData);
                    os.flush();
                }

                int responseCode = conn.getResponseCode();
                InputStream is = (responseCode >= 200 && responseCode < 300)
                        ? conn.getInputStream()
                        : conn.getErrorStream();

                StringBuilder sb = new StringBuilder();
                if (is != null) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line).append("\n");
                        }
                    }
                }

                resultText = "[응답 코드: " + responseCode + "]\n\n" + sb.toString().trim();

            } catch (Exception e) {
                Log.e(TAG, "네트워크 요청 실패", e);
                resultText = "요청 실패: " + e.getMessage()
                        + "\n\n※ 참고:\n"
                        + "1. 안드로이드 에뮬레이터에서는 localhost 대신 10.0.2.2 를 사용해야 합니다.\n"
                        + "   (예: http://10.0.2.2:8080/param7)\n"
                        + "2. 실제 스마트폰 기기에서는 PC의 Wi-Fi IP 주소를 사용해야 합니다.\n"
                        + "   (예: http://192.168.0.x:8080/param7)\n"
                        + "3. Spring Boot 서버가 정상 실행 중인지 확인해 주세요.";
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }

            final String finalResult = resultText;
            mainHandler.post(() -> {
                tvResult.setText(finalResult);
                btnSend.setEnabled(true);
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}