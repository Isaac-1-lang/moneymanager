package in.projects.moneymanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.projects.moneymanager.service.EmailService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1.0/test")
@RequiredArgsConstructor
public class TestEmailController {

    private final EmailService emailService;

    @GetMapping("/send")
    public String sendTestEmail() {
        emailService.sendEmail(
            "uhiriwechrisostom5@gmail.com", 
            "Test Email", 
            "This is a test email from Spring Boot!"
        );
        return "Email sent (check spam folder!)";
    }
}
