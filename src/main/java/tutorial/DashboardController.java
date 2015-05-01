package tutorial;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.directory.CustomData;
import com.stormpath.sdk.lang.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/dashboard")
public class DashboardController {

    @RequestMapping(method=RequestMethod.GET)
    public String get() {
        return "dashboard";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String onSubmit(HttpServletRequest req) {

        String birthday = req.getParameter("birthday");
        String color = req.getParameter("color");

        //get the currently-logged-in account:
        Account account = (Account)req.getAttribute("account");

        if (account != null) {

            CustomData data = account.getCustomData();

            if (Strings.hasText(birthday)) {
                data.put("birthday", birthday);
            } else {
                data.remove("birthday");
            }

            if (Strings.hasText(color)) {
                data.put("color", color);
            } else {
                data.remove("color");
            }

            data.save();
        }

        return "dashboard";
    }

}
