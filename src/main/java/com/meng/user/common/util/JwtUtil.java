package com.meng.user.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.meng.user.repository.system.entity.User;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Java Web Token 工具类
 *
 * @author dujianwei
 * @create 2020-07-06
 */
public class JwtUtil {

    /**
     * 过期时间一天，
     * TODO 正式运行时修改为15分钟
     */
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;
    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "f26e587c28064d0e855e72c0a6a0e618";

    /**
     * 校验token是否正确
     *
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("loginName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取登陆用户ID
     *
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,15min后过期
     *
     * Algorithm.HMAC256():使用HS256生成token,密钥则是用户的密码，唯一密钥的话可以保存在服务端。如 TOKEN_SECRET
     * withAudience()存入需要保存在token的信息，这里我把用户ID存入token中
     *
     * @param user 用户
     * @return 加密的token
     */
    public static String sign(User user) {
        try {
            // 过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);

            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");

            // 附带username，userId信息，生成签名
            return JWT.create()
                    .withHeader(header)
                    .withClaim("username", user.getUsername())
                    .withClaim("userId", user.getUserId())
                    .withExpiresAt(date)
                    .sign(Algorithm.HMAC256(user.getPassword()));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    //该方法使用HS256算法和Secret:bankgl生成signKey
    // private static Key getKeyInstance() {
    //     //We will sign our JavaWebToken with our ApiKey secret
    //     SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    //     byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("bankgl");
    //     Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    //     return signingKey;
    // }
    //
    // //使用HS256签名算法和生成的signingKey最终的Token,claims中是有效载荷
    // public static String createJavaWebToken(Map<String, Object> claims) {
    //     return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();
    // }
    //
    // //解析Token，同时也能验证Token，当验证失败返回null
    // public static Map<String, Object> parserJavaWebToken(String jwt) {
    //     try {
    //         Map<String, Object> jwtClaims =
    //                 Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
    //         return jwtClaims;
    //     } catch (Exception e) {
    //         log.error("json web token verify failed");
    //         return null;
    //     }
    // }
}
