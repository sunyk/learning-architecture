# LogAspectAnnotation

### @ControllerLogAspectAnnotation

~~~java
/**
 * 
 * Define a log facet annotation
 * @author sunyang
 * @date 2018/12/19 14:23
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.METHOD})
public @interface ControllerLogAspectAnnotation {
    //Description
    String description() default "";

    //Print request paramters
    boolean isPrintPostData() default true;

    //Print response Results
    boolean isPrintResultData() default true;

    //Print exceptions
    boolean isPrintThrowing() default true;

    //Print consumption time
    boolean isPrintSpendTime() default false;

    //Default value
    String value() default "";

}
~~~

### AbstractControllerLogAspect

`com.sunyk.annotation.AbstractControllerLogAspect`

~~~java
/**
 * Define abstract class log slices
 *
 * @author sunyang
 * @date 2018/12/19 14:30
 */
public abstract class AbstractControllerLogAspect {

    private static final Logger log = LoggerFactory.getLogger(AbstractControllerLogAspect.class);

    public AbstractControllerLogAspect() {
    }

    //Define an abstract method
    public abstract void controllerLog();

    //Logic processing before operation
    @Before("controllerLog() && @annotation(controllerLogAspectAnnotation)")
    public void doBefore(JoinPoint joinPoint, ControllerLogAspectAnnotation controllerLogAspectAnnotation) throws Throwable{
        if (controllerLogAspectAnnotation.isPrintPostData()){
            log.info(controllerLogAspectAnnotation.description() + "开始调用：" + "requestData = {}", joinPoint.getArgs());
        }

    }
}

~~~

### ControllerLogAspect

`com.sunyk.aop.ControllerLogAspect`

~~~java
/**
 * 
 * Using AOP to define aspect components, and used for annotation such as 
 * @ControllerLogAspectAnnotation. 
 * From the AbstractColltrollerLogAspect class.
 * 
 * @author sunyang
 * @date 2018/12/19 15:07
 */

@Aspect
@Component
@Configuration
public class ControllerLogAspect  extends AbstractControllerLogAspect{
    @Override
    @Pointcut("execution(* com.sunyk.*.controller.*Controller.*(..))")
    public void controllerLog() {
    }
}
~~~

### ServiceMonitor

`com.sunyk.aop.ServiceMonitor`

~~~java
/**
 * Use AOP alone to define aspect scan components
 * @author sunyang
 * @date 2018/12/19 18:39
 */
@Aspect
@Component
public class ServiceMonitor {

    @AfterReturning("execution(* com.sunyk.*.controller.UseWebFluxController.useLog())")
    public void logServiceAccess(JoinPoint joinPoint){
        System.out.println("Completed:" + joinPoint);
    }
}
~~~

### Test

`com.sunyk.flux.controller.UseWebFluxController`

~~~java
/**
 * @author sunyang
 * @date 2018/12/19 14:40
 */
@RestController
public class UseWebFluxController {

    @GetMapping("/")
    @ControllerLogAspectAnnotation(description = "start...", isPrintPostData = true)
    public String useLog(){
        System.out.println("use log....");
        return null;
    }
}
~~~

Effect Diagram

![1545287858622](https://img2018.cnblogs.com/blog/612682/201812/612682-20181220144907555-517362374.png)

### Other

> pom.xml dependencies

~~~xml
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-aop</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webflux</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>io.projectreactor</groupId>
			<artifactId>reactor-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
~~~



