package com.syndet.syndeettori.api


import com.syndet.syndeettori.HintState
import org.apache.tomcat.util.http.fileupload.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.io.IOException
import java.io.File
import java.io.InputStream
import java.nio.charset.Charset

data class Greeting(val id: Long, val content: String)

@RestController
class GreetingController {

    val IMAGE_FOLDER = "/home/ainohai/syndeettori/src/main/resources/images/"

    @Autowired
    lateinit var hintState : HintState

    @CrossOrigin(origins = ["http://localhost:4200"])
    @GetMapping("/greeting")
    fun getHint(@RequestParam(value = "hint") hint: String) =
            "{\"id\": "+ hintState.addCounter() + "}"

    @CrossOrigin
    @GetMapping(value = ["/get-image"])
    @ResponseBody
    @Throws(IOException::class)
    fun getImageWithMediaType(@RequestParam(value = "hint") hint: String): ByteArray {

        println(hint);
        println(hintState.counter)

        val hintString = hint.toUpperCase()

        if (hintState.counter >= 1 && hintString.equals("TULIPALLO")) {
            return hintFound(IMAGE_FOLDER + "tulipallo.jpg")
        }

        else if (hintState.counter >= 2 && hintString.equals("SOKIW")) {
            return hintFound(IMAGE_FOLDER + "sokiw.jpg")
        }
        else if (hintState.counter >= 3 && hintString.equals("DYRTU")) {
            return hintFound(IMAGE_FOLDER + "dyrtu.jpg")
        }
        else if (hintState.counter >= 4 && hintString.equals("LEHTI")) {
            return hintFound(IMAGE_FOLDER + "lehti.jpg")
        }
        else if (hintState.counter >= 5 && hintString.equals("JUHLA")) {
            return hintFound(IMAGE_FOLDER + "juhla.jpg")
        }
        else {
            val file = File(IMAGE_FOLDER + "vaarin.jpg")
            return file.readBytes()
        }

    }

    fun hintFound(fileName : String) : ByteArray {
        hintState.addCounter();

        val file = File(fileName)
        return file.readBytes()
    }

}
