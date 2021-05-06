package com.com.online.store.online.store.serviceimpl;

import com.com.online.store.online.store.exception.ResourceMailException;
import com.com.online.store.online.store.service.IMailService;
import com.com.online.store.online.store.util.Constants;
import com.com.online.store.online.store.util.MailContentBuilder;
import com.com.online.store.online.store.util.NotificationEmail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements IMailService {

    private final JavaMailSender mailTrapMailSender;
    private final MailContentBuilder mailContentBuilder;

    @Value("${fromEmail}")
    String fromEmail;

    @Override
    public void sendMailTrapMail(NotificationEmail notificationEmail) throws ResourceMailException {
        MimeMessagePreparator messagePreparator = messagePreparator(notificationEmail);
        try {
            mailTrapMailSender.send(messagePreparator);
            log.info("Email sent");
        } catch (MailException e) {
            throw new ResourceMailException("Something occurred trying sending the email" + e);
        }
    }

    private MimeMessagePreparator messagePreparator(NotificationEmail notificationEmail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");

            URL urlImage = new URL(Constants.LOGO_BACKGROUND_IMAGE_URL);
            String imageResourceName = Constants.LOGO_BACKGROUND_IMAGE_URL.substring(Constants.LOGO_BACKGROUND_IMAGE_URL.lastIndexOf('/') + 1,
                    Constants.LOGO_BACKGROUND_IMAGE_URL.lastIndexOf('.'));

            log.info(imageResourceName);
            URLConnection urlConnection = urlImage.openConnection();
            String imageContentType = urlConnection.getContentType();

            messageHelper.setFrom(fromEmail);
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.mailTrapEmail(notificationEmail.getBody(), imageResourceName), true);

            // Add the attachment
            log.info("array image" + Arrays.toString(downloadImageFromCloudinary(urlImage)));
            final InputStreamSource attachmentSource = new ByteArrayResource(downloadImageFromCloudinary(urlImage));
            messageHelper.addAttachment(Constants.LOGO_BACKGROUND_IMAGE_NAME, attachmentSource, imageContentType);

            // Add the inline image, referenced from the HTML code as "cid:${imageResourceName}"
            final InputStreamSource imageSource = new ByteArrayResource(downloadImageFromCloudinary(urlImage));
            messageHelper.addInline(imageResourceName, imageSource, imageContentType);
        };
    }

    private byte[] downloadImageFromCloudinary(URL fileUrl) {
        byte[] fileByteArray = new byte[1024];
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileUrl.openStream());
            fileByteArray = toByteArray(bufferedInputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileByteArray;
    }

    public static byte[] toByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            byte[] b = new byte[1024];
            boolean var3 = false;

            int n;
            while ((n = is.read(b)) != -1) {
                output.write(b, 0, n);
            }
            byte[] var4 = output.toByteArray();
            return var4;
        } finally {
            output.close();
        }
    }
}
