package com.rishi.userbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.rishi.userbooking.entity") 
@EnableJpaRepositories("com.rishi.userbooking.repository")
public class UserBookingApplication {

	public static void main(String[] args) {
		System.out.println("Application going to be start");
		SpringApplication.run(UserBookingApplication.class, args);
		System.out.println("Application  started");
	}
	
	
	
//	@Primary
//    @Bean
//    public LinkDiscoverers discoverers() {
//        List<LinkDiscoverer> plugins = new ArrayList<>();
//        plugins.add(new CollectionJsonLinkDiscoverer());
//        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
//    }   
//	
//	  @Bean
//	   public Docket productApi() {
//	      return new Docket(DocumentationType.SWAGGER_2).select()
//	         .apis(RequestHandlerSelectors.basePackage("com.rishi.userbooking"))
//	         .build()
//	         .apiInfo(apiDetails());
//	   }
//	 
//	 
//	 private ApiInfo apiDetails(){
//		 return new ApiInfo(
//			 "UserBooking API",
//			 "UserBooking Project with the help of Springboot ",
//			 "2.0",
//			 "Free to use",
//			 new springfox.documentation.service.Contact("Rishi Singhal", "notavailablenow.com","singhalrishi258@gmail.com"),
//			 "API License",
//			 "http://localhost:8080",
//			 Collections.emptyList());
//	 }
}
