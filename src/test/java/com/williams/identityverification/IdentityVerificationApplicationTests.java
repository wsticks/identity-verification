//package com.williams.identityverification;
//
//import com.williams.identityverification.model.request.ProductRequest;
//import com.williams.identityverification.model.response.ProductResponse;
//import com.williams.identityverification.service.ProductService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.Silent.class)
//@SpringBootTest
//public class IdentityVerificationApplicationTests {
//
//    @Mock
//    ProductService service;
//
//    @Test
//    public void validateProductCreation(){
//        ProductRequest request= new ProductRequest();
//        request.setName("Bag");
//        ProductResponse response = new ProductResponse();
//        response.setName("Bag");
//        response.setAge("13=");
//        response.setSerialNo("PRD50003ef5fe4554f97dbd");
//        when(service.createProduct(request)).thenReturn(response);
//        Assert.assertNotNull(response);
//        Assert.assertEquals(request.getName(), response.getName());
//    }
//
//    @Test
//    public void validateProductUpdate(){
//        ProductRequest request= new ProductRequest();
//        request.setName("Bag");
//        ProductResponse response = new ProductResponse();
//        response.setName("Bag");
//        response.setAge("13=");
//        response.setSerialNo("PRD50003ef5fe4554f97dbd");
//        when(service.updateProduct(request)).thenReturn(response);
//        Assert.assertNotNull(response);
//        Assert.assertEquals(request.getName(), response.getName());
//    }
//
//    @Test
//    public void validateProductGet(){
//       String productName = "Bag";
//        ProductResponse response = new ProductResponse();
//        response.setName("Bag");
//        response.setAge("13=");
//        response.setSerialNo("PRD50003ef5fe4554f97dbd");
//        when(service.getProduct(productName)).thenReturn(response);
//        Assert.assertNotNull(response);
//        Assert.assertEquals(productName, response.getName());
//    }
//
//    @Test
//    public void validateProductGets(){
//        String productName = "Bag";
//        List<ProductResponse> responses = new ArrayList<>();
//        responses.forEach(response ->{
//        response.setName("Bag");
//        response.setAge("13=");
//        response.setSerialNo("PRD50003ef5fe4554f97dbd");
//        when(service.getProduct(productName)).thenReturn(response);
//        Assert.assertNotNull(response);
//        Assert.assertEquals(productName, response.getName());
//        });
//    }
//
////    @Test
////    public void googleTest() throws InterruptedException {
////
////        ChromeOptions co = new ChromeOptions();
////        co.addArguments("--remote-allow-origins=*");
//////        WebDriverManager.chromedriver().setup();
////        WebDriver driver=new ChromeDriver(co);
//////        driver.manage().window().maximize();
//////        driver.get("https://www.google.com");
//////        driver.findElement(By.name("RNNXgb")).sendKeys("Olumuyiwa Akanni", Keys.ENTER);
//////        System.out.println(driver.getTitle());
//////        Thread.sleep(5000);
////
////    }
//
//    @Test
//    public void validateLengthOfSerialNumber(){
//        ProductRequest request= new ProductRequest();
//        request.setSerialNo("PRD257f903be44cbe2f9357");
//        String serialNoSample = "PRD50003ef5fe4554f97dbd";
//        ProductResponse response = new ProductResponse();
////        response.setMessage("The searched BVN is invalid");
////        response.setCode("02");
//        when(service.createProduct(request)).thenReturn(response);
//        Assert.assertEquals(request.getSerialNo().length(),serialNoSample.length());
//    }
//
//}
