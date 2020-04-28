package wangdinghua17204222.log;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import wangdinghua17204222.entity.User;

import java.io.*;

@Aspect
@Configuration
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private String filename = "log.txt";

    @Before("execution(public * wangdinghua17204222.controller.HomeController.check(..)) && args(user,result,..)")
    public void doBefore(User user, BindingResult result) throws Throwable {
        String info = String.format("Account:%s, login %s.", user.getAccount(), result.hasErrors() ? "failed" : "succeeded");
        logger.info(info);
        try{
            OutputStream outputStream = new FileOutputStream(filename, true);
            outputStream.write(info.getBytes());
            outputStream.write("\n".getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            try{
                new File(filename).createNewFile();
            }catch (IOException e1){
                e1.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
