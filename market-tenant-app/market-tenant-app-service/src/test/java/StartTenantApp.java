/**
 * @author songxuefeng
 * @create 2018-10-11 15:30
 * @description: ${description}
 **/
public class StartTenantApp {
    public static void main(String[] args) {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
//        UserService userService = (UserService)applicationContext.getBean("userService");

        com.alibaba.dubbo.container.Main.main(args);
//		System.out.println(userService);
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
