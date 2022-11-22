package library.controllers;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

	@Autowired
	// Allows for reading the SMTP properties specified in the
	// application.properties file
	private JavaMailSender mailSender;

	@GetMapping("/contact")
	public String viewContactForm() {
		return "contactUs";
	}

	@PostMapping("/contact")
	public String submitInquery(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
		String subject = request.getParameter("subject");
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String content = request.getParameter("message");

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		String mailSubject = fullName + " sent a new message ";
		String mailContent = "<p><b>Sender's name:</b> " + fullName + "</p>";
		mailContent += "<p><b>Sender's E-Mail:</b> " + email + "</p>";
		mailContent += "<p><b>Subject:</b> " + subject + "</p>";
		mailContent += "<p><b>Message:</b> " + content + "</p>";
		mailContent += "<hr><img src='cid:logoImage' />";

		helper.setFrom("lsaraivafilho@dmacc.edu", "BearsLMS Inquiry");
		helper.setTo("myselfigb@gmail.com");
		helper.setSubject(mailSubject);
		helper.setText(mailContent, true);
		
		ClassPathResource resource = new ClassPathResource("/static/images/EmailLogo.png");
		helper.addInline("logoImage", resource);

		mailSender.send(message);

		return "contactSuccess";
	}
}
