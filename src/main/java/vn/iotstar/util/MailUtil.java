package vn.iotstar.util;
 
import java.util.Properties;
 
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
 
public class MailUtil {
 
    private static final String EMAIL_FROM = "dungcucvn@gmail.com";
    private static final String APP_PASSWORD = "xdle jdec vplf mbha";
 
    public static void sendOtpEmail(String toEmail, String otp, String purpose) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
 
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, APP_PASSWORD);
            }
        });
 
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_FROM));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
 
            String subject;
            String body;
            if ("activate".equals(purpose)) {
                subject = "Mã kích hoạt tài khoản - Shopping";
                body = "Xin chào,\n\nMã OTP kích hoạt tài khoản của bạn là: " + otp
                        + "\n\nMã có hiệu lực trong 5 phút. Vui lòng không chia sẻ mã này cho bất kỳ ai.";
            } else {
                subject = "Mã xác nhận đặt lại mật khẩu - Shopping";
                body = "Xin chào,\n\nMã OTP đặt lại mật khẩu của bạn là: " + otp
                        + "\n\nMã có hiệu lực trong 5 phút. Nếu bạn không yêu cầu, vui lòng bỏ qua email này.";
            }
 
            message.setSubject(subject);
            message.setText(body);
 
            Transport.send(message);
            System.out.println("Đã gửi email OTP tới: " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
 
