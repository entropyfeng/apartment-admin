package com.github.entropyfeng.apartment;

import com.github.entropyfeng.apartment.config.AuthProperties;
import com.github.entropyfeng.common.util.JWTUtil;
import com.github.entropyfeng.common.util.JwtAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UtilTest {

    @Autowired
    AuthProperties authProperties;

    @Test
    public void test(){
      JwtAccount account= JWTUtil.parseJwt("eyJhbGciOiJIUzM4NCIsInppcCI6IkRFRiJ9.eNpMjcEKwjAQRP9lzw1skppIP8KLR9tDttlApdXSJCCI_-7WXpzTzGOGecO9TNCBS62z5pTUaJBUGxMqCiMp8g5Hpw1Z8tBAriRlI27KWVzkFOpc9hwKdNrps0W0Hhvg1yoA8Z9tz5n32a2HXGrkR-lhkPHK2_Ljw_FxCQtLvOIhjfD5AgAA__8.4Vpsbb2ChGf9SKCzrX0jj8g6PLYMaKtjCa0zq1ciFUv_xEmKJNZyXcPlVmaWrmyQ",authProperties.getJwtSecretKey());
      account.getRoles();

    }

}
