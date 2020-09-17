package pro.profsoft.meetnetbackend.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.profsoft.meetnetbackend.exception.NoContentException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/content")
public class ContentController {
    private final String contentPath;

    public ContentController(@Value("${sfera.content}") String contentPath) {
        this.contentPath = contentPath;
    }

    @ApiOperation(value = "Отдает картинку по имени")
    @GetMapping(value = "/{filename}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImg(@PathVariable("filename") String filename) {
        try {
            InputStream in = new FileInputStream(contentPath + filename);
            if (in == null) {
                throw new NoContentException(contentPath + filename);
            }
            return IOUtils.toByteArray(in);
        } catch (FileNotFoundException e) {
            throw new NoContentException(contentPath + filename);
        } catch (IOException e) {
            throw new NoContentException(contentPath + filename);
        }
    }
}
