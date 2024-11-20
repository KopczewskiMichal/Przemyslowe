package org.example.springintro;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object statusCode = request.getAttribute("javax.servlet.error.status_code");
        Object errorMessage = request.getAttribute("javax.servlet.error.message");
        Object timestamp = request.getAttribute("javax.servlet.error.timestamp");

        model.addAttribute("status", statusCode != null ? statusCode.toString() : "Unknown");
        model.addAttribute("error", errorMessage != null ? errorMessage.toString() : "Unknown");
        model.addAttribute("timestamp", timestamp != null ? timestamp.toString() : null);

        return "error";
    }

    public String getErrorPath() {
        return "/error";
    }
}
