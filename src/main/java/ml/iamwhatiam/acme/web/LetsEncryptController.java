package ml.iamwhatiam.acme.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Let's Encrypt challenge
 * @author iMinusMinus
 * @date 2019-10-13
 */
@Controller
@Slf4j
public class LetsEncryptController {

    private String thumbprint;

    @RequestMapping("/lock")
    @ResponseBody
    public boolean beforeChallenge(@RequestHeader(value = "Referer", required = false) String referrer, @RequestParam("thumbprint") String thumbprint) {
        log.info("referer: {}, thumbprint: {}", referrer, thumbprint);
        if(this.thumbprint != null) {
            return false;
        }
        synchronized(this) {
            if(this.thumbprint == null) {
                this.thumbprint = thumbprint;
                return true;
            }
        }
        return false;
    }

    @RequestMapping("/.well-known/acme-challenge/{token}")
    @ResponseBody
    public String challenge(@RequestHeader(value = "Referer", required = false) String referrer, @PathVariable("token") String token) {
        log.info("referer: {}, token: {}", referrer, token);
        return token + "." + thumbprint;
    }

    @RequestMapping("/unlock")
    @ResponseBody
    public boolean afterChallenge(@RequestHeader(value = "Referer", required = false) String referrer, @RequestParam("thumbprint") String thumbprint) {
        log.info("referer: {}, thumbprint: {}", referrer, thumbprint);
        if(thumbprint.equals(this.thumbprint)) {
            this.thumbprint = null;
            return true;
        }
        return false;
    }
}
