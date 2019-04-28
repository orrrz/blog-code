import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by icejam.
 * 生成jwt的字符串
 */
public class CreateJwt {
    public static void main(String[] args) {

        List list = new ArrayList();
        list.add("admin");
        list.add("user");
        /**
         * jwt 组成部分
         * 头部       {"typ":"JWT","alg":"HS256"}
         *      SignatureAlgorithm.HS256 = "alg" : "HS256"
         *
         * 载荷  :  存放用户登录信息
         *          setId("1000") = "jti" : "1000"
         *          setSubject("icejam") = "sub" : "icejam"
         *          setIssuedAt(new Date()) = "iat" : Long值，当前时间
         *
         * 签名   header (base64 后的) + payload (base64 后的) + secret
         *      secret = miyao
         */
        long start = System.currentTimeMillis();
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("1000")
                .setSubject("icejam")
                .setIssuedAt(new Date())
//                .claim("roles", "admin")        // 添加非标准声明
                .claim("roles", list)
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 1000))    // 设置过期时间
                .signWith(SignatureAlgorithm.HS256, "miyao");

        String token = jwtBuilder.compact();
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(token);  //eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDAwIiwic3ViIjoiaWNlamFtIiwiaWF0IjoxNTU1NzY4NzkzfQ.DMfqJZbepi1YkUZUocV757AMxUcRf0Q1Fwjd3b-41PU
    }

    //iss: jwt 签发者
    //sub: jwt 所面向的用户
    //aud: 接收 jwt 的一方
    //exp: jwt 的过期时间， 这个过期时间必须要大于签发时间
    //nbf: 定义在什么时间之前， 该 jwt 都是不可用的.
    //iat: jwt 的签发时间
    //jti: jwt 的唯一身份标识， 主要用来作为一次性 token,从而回避重放攻击。
}
