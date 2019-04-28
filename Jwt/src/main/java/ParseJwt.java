import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.util.List;

/**
 * Created by icejam.
 */
public class ParseJwt {

    public static void main(String[] args) {

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDAwIiwic3ViIjoiaWNlamFtIiwiaWF0IjoxNTU1NzcxNzU3LCJyb2xlcyI6WyJhZG1pbiIsInVzZXIiXSwiZXhwIjoxNTU1NzcxNzg3fQ.R6dyqbZFHB9q42IcRODQbxd0tkTgnSlG-wpfHKASiKM";
        Jws<Claims> jws = Jwts.parser().setSigningKey("miyao").
                parseClaimsJws(token);// 通过parseClaimsJws() 方法判断，不报错为合法token

        Claims body = jws.getBody();  // Claims对象封装载荷信息
        System.out.println("登录Id：" + body.getId());
        System.out.println("登录用户名" + body.getSubject());
        System.out.println("登录时间" + body.getIssuedAt());
        System.out.println("过期时间" + body.getExpiration());  // 过期就报错
//        System.out.println("用户角色" + body.get("roles"));
        List list = (List) body.get("roles");
        System.out.println("用户角色" + list.get(0) + "," +list.get(1));

    }
}
