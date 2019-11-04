import com.ifi.controller.Controller;
import com.ifi.controller.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping(uri="/hello")
    public String sayHello(){
        return "Hello World !";
    }

    @RequestMapping(uri="/bye")
    public String sayGoodBye(){
        return "Goodbye !";
    }

    @RequestMapping(uri="/boum")
    public String explode(){
        throw new RuntimeException("Explosion !");
    }
}
