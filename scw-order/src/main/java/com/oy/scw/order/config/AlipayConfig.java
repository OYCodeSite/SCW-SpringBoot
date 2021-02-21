package com.oy.scw.order.config;

import java.io.FileWriter;
import java.io.IOException;
/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000117611573";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDE1mgAQ6IQeKRKjGuSCF4enRZleYsxbYUjFVrFPJkpv7wwKYNo+rBkYtMB2HIKPCulS1v5sz0H3MZdvCQoQWpqBF3qVRHYq+NLGtG9Txe3U+/hiOL4q5YO7Rc61jgeEXhl7RCZOITMtE46+TDLwJDBWBvBJk3HggPPRsGmh+bL9s3RcfGmYYruorEZDJ6vuUEP0XqkzcLKE+lY9wkplI4MhY6RMLu9lt5FRpPdb01LAMDLS8fpXdKtLNTXZvA1NK/FpeY+o6Bae/Sc4GnN8kTOlc2w/ACNC+vkvkoCC9vwSDjS8bfps3/UI36OrCYAbjXXc+Oh+cTvP58GPayPv5AhAgMBAAECggEAIqzGi21eqlomTv+nn4Ub13bC0EN2EtIBRynm4KIJNUKSi0V04dRvqG+ETmnrxQPOiILbBATgNndc9nxDPlgvv9dfF/2+dBXrp3plAl1hhykhM/DiUv4fJCf+FJ5SKnHXbNUzPFqpgkotEdR+FRaqKXU1BnSR9RhhRW1ohcahEMLtYaZoaZsVT2zVvozClTDROMt5rV02lErF7ULOitgau0PXB90pMhRGE5SFwzemYFW4NfGa+hWG5g5AjfuPUyHT5Ejzv8bHrC36ooB5ye86+IO66zasjMPcAyIiyNvRcCB9HHSJcyR8SPgGWSrZD4vk86yHQqdFfqBlkAmjaNnJsQKBgQDoftw58/TofQ8FmvKkiawapA1DfULuNSmLsxKYJL4nv6uyZJ6FpYVNRTT3R5IanDEAgemQxo4KPQRUyDP49JmhNKdpojMu2yXVWR8HhZDUsRGti3XPenhX5A2II39K7352PYT+mhejKt3fy4VMV9m0yTZVsKpJ/VRdz/Cb9WhQlQKBgQDYvLFM/rE4jRHK3EOBnH8dpgv12vSUdxVg84Hyz8hfcLGhMpN5UhDPHPh1aNdrZ5BjOrsL7PCI1HLFGrR0rQieXOkcNEDqDxOgSPxtVqkKTCemY8xPuse24fnd/HiDsyiBNzb52D9A4Mn+FaKTn7vOyRGbWpp98NW4ME9AC2yiXQKBgHU//Rpw8LMK1reR9AqMr7HjyBigJE/zmZctYRQ/e3KaVCGsYtGtrvNQEq0eeA1ZddD2s27i46FAMl37dVJ5ML0+y6sXNM7NkF/vCRoG/wp0niCyc2CGOAjDJxvgIwMMku9YiSjJV2BfbvO9NOVSbKWiz/cZGFgmHRUOmB5e/hOdAoGACXR5250EyC3HR+1B+KaGGRoETY5muw9S3G7jDV+pIxi8dwbIzjMZTp+56w1O0H2tG9cVD9kfSSjTdOvMUYa26ImvFa7l4hAUUBq/AFoWd7+Iv0Z9REEms0C6cMexzjUDoFdzS4T2hXMI/M+ezwfZ3pSA8yEJulLGUAmze1zEOXUCgYAFSyDyWJczkkWmn/mhBBppjDA7b1X4oSFiuY4yudgYVjo5GxAEaSlg/M1lwMZjEN1zSB8yQk63KOj0TeWm5ln50Vp8ybJcgH3VbT0zg2kKD+QxDH+0em9vYNpooOtL93W4A3LQ5glSZNGPexNk11V4mMt/VqxC+NuC6fTgH3Xbzw==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtxPL9OeQwvB2LQZN7cNKp+mp8Suks2KNImMNyJX5QfmulKbV1CfENBw7sAPfaeRZCiwHkOGTBeB342fDEuBh7pJEKbpy0e9EJK7IAKbHOLRveAr8QSh2VRmr0XJ7157kix0jJIOzd92Cl+rIJpwOVudrFcl558wNFTx1B9BVPJM1/mciWSQhRXFeHWmLIvuuea/YGsf1nz5PhTFZPKZh2FjiFBDhGafJVDduPm1jOHlDXYLkWcnJXYhWs+6xDhc1Db+9jcSFFVqP6Og1lV3ks+t7xkoAxoOvYRwcl4PTtTRhWMVvuiKT0ZX/vB+d9fPaLGLjZ1ut7kBCivxNDujQJQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://imoyt.cn.utools.club/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://imoyt.cn.utools.club/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
