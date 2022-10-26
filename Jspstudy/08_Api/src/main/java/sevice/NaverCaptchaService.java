package sevice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface NaverCaptchaService {
	// 서비스 하나당 메소드가 하나이던걸 서비스 하나에 메소드 3개를 넣음
	public String getCaptchaKey();
	public Map<String, String> getCaptchaImage(HttpServletRequest request, String key);
	public void refreshCaptcha(HttpServletRequest request, HttpServletResponse response);
	public boolean validateUserInput(HttpServletRequest request);
}
