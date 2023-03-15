package com.tech.spring.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Service;

import com.tech.spring.dto.APIDto;

@Service
public class CustomAPIServicempl implements CustomAPIService{

	@Override
	public String getChinese(APIDto dto) {
		String korean = dto.getKorean();
		String result = "";
	        try {
	            String text = URLEncoder.encode(korean, "UTF-8");
	            URL url = new URL("https://openapi.naver.com/v1/papago/n2mt");
	            HttpURLConnection con = (HttpURLConnection)url.openConnection(); //연결
	            con.setRequestMethod("POST");
	            con.setRequestProperty("X-Naver-Client-Id", "uGIWAPuB5a5GZq27NBsS");
	            con.setRequestProperty("X-Naver-Client-Secret", "0qBf2BMhKC");
	            // post request
	            String postParams = "source=ko&target=zh-CN&text=" + text;
	            con.setDoOutput(true); //연결
	            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	            wr.writeBytes(postParams);
	            wr.flush();
	            wr.close();
	            int responseCode = con.getResponseCode(); //연결 응답코드
	            BufferedReader br;
	            if(responseCode==200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            } else {  // 에러 발생
	                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	            }
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                result += response.append(inputLine);
	            }
	            br.close();
	            System.out.println("서비스-중문:"+response.toString());
	        } catch (Exception e) {
	            System.out.println(e);
	        }
		return result;
	}

	@Override
	public String getEnglish(APIDto dto) {
		String korean = dto.getKorean();
		String result = "";
	        try {
	            String text = URLEncoder.encode(korean, "UTF-8");
	            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("POST");
	            con.setRequestProperty("X-Naver-Client-Id", "uGIWAPuB5a5GZq27NBsS");
	            con.setRequestProperty("X-Naver-Client-Secret", "0qBf2BMhKC");
	            // post request
	            String postParams = "source=ko&target=en&text=" + text;
	            con.setDoOutput(true);
	            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	            wr.writeBytes(postParams);
	            wr.flush();
	            wr.close();
	            int responseCode = con.getResponseCode();
	            BufferedReader br;
	            if(responseCode==200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            } else {  // 에러 발생
	                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	            }
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                result += response.append(inputLine);
	            }
	            br.close();
	            System.out.println("서비스-영문:"+response.toString());
	        } catch (Exception e) {
	            System.out.println(e);
	        }
		return result;
	}

}
