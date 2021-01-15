package dev.paullindquist.picciottoweb.controller;

import dev.paullindquist.picciotto.parse.css.CSSParser;
import dev.paullindquist.picciotto.parse.css.PHCSSParser;
import dev.paullindquist.picciotto.parse.css.SteadyStateCSSParser;
import dev.paullindquist.picciotto.parse.xml.XmlParser;
import lombok.extern.java.Log;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;

@Log
@Controller
public class APIController {
    @PostMapping( path = "/")
    public ResponseEntity<ByteArrayResource> download(@RequestParam String xml) throws Exception {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "force-download"));
            header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ProductTemplate.xlsx");
            //CSSParser cssParser = new SteadyStateCSSParser();
            CSSParser cssParser = new PHCSSParser();
            XmlParser parser = new XmlParser(cssParser);
            parser.parseToBAIS(xml, stream);
            return new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()),
                header, HttpStatus.CREATED);
        } catch (Exception e) {
            log.warning(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping( path = "/")
    public String doIndex(Model model) {
        model.addAttribute("hello", "world");
        return "index";
    }
}
