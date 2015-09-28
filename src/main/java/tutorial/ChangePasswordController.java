package tutorial;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.account.PasswordResetToken;
import com.stormpath.sdk.servlet.account.AccountResolver;
import com.stormpath.sdk.servlet.application.ApplicationResolver;
import com.stormpath.sdk.application.Application;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChangePasswordController {

    @RequestMapping("/change_password")
    public String changePassword(HttpServletRequest request) {
        Application application = ApplicationResolver.INSTANCE.getApplication(request);
        Account account = AccountResolver.INSTANCE.getAccount(request);

        if (account != null && application != null) {
            String email = account.getEmail();
            PasswordResetToken token = application.sendPasswordResetEmail(email);
            return "redirect:/change?sptoken="+token.getValue();
        }
        return "redirect:/login?next=/change_password";
    }

}
