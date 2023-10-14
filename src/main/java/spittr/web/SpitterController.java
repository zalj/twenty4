package spittr.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spittr.Spitter;
import spittr.data.SpitterRepository;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

    @Autowired
    private SpitterRepository spitterRepository;

    private static final Logger logger = LoggerFactory.getLogger(SpitterController.class);

    @RequestMapping(value = "/register", method = GET)
    public String showRegisterForm(Model model) {
        model.addAttribute(new Spitter());
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = POST)
    public String processRegistration(
            @RequestPart("picture-part") MultipartFile multipartFile,
            @Valid Spitter spitter,
            Errors errors,
            RedirectAttributes model) {
        if (errors.hasErrors()) {
            return "registerForm";
        }
        if (multipartFile != null) {
            logger.info("Size:{}", multipartFile.getSize());
            logger.info("Content-type:{}", multipartFile.getContentType());
            logger.info("Filename:{}", multipartFile.getName());
            logger.info("OriginalFilename:{}", multipartFile.getOriginalFilename());
        }
        logger.info("Spitter:{}", spitter);
        spitterRepository.save(spitter);
        model.addAttribute("username", spitter.getUsername())
                .addFlashAttribute("spitter", spitter);
        return "redirect:/spitter/{username}";
    }

    @RequestMapping(value = "/{username}", method = GET)
    public String showSpitterProfile(
            @PathVariable("username") String username,
            Model model) {
        logger.info("model: {}", model.asMap());
        if (!model.containsAttribute("spitter")) {
            Spitter spitter = spitterRepository.findByUsername(username);
            model.addAttribute(spitter);
            logger.warn("model doesn't contains attribute spitter");
        } else {
            logger.warn("model contains attribute spitter");
        }
        return "profile";
    }
}
