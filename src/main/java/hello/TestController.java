package hello;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.IOUtils.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

@RestController
public class TestController {

    private static final String template = "Hello, %s %s!";
    private final int age = 18;
    File file = new File("/Users/Davide/Desktop/Roba/images/blue.png");

    /*
    @RequestMapping("/test")
    public Test test(@RequestParam(value="name", defaultValue="Mario") String name, @RequestParam(value="surname", defaultValue="Rossi") String surname, @RequestParam(value="age", defaultValue="18") int age) {
        return new Test(name, surname, age);
    }
    */


    @RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
	public void getFile(@PathVariable("file_name") String fileName, HttpServletResponse response) {
    try {
      // get your file as InputStream
      InputStream is = new FileInputStream(file);
      // copy it to response's OutputStream
      org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
      response.flushBuffer();
    } catch (IOException ex) {
      throw new RuntimeException("IOError writing file to output stream");
    }

}
}
