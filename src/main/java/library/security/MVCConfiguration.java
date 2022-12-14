package library.security;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfiguration implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path bookCoverUploadDir = Paths.get("./src/main/resources/static/thumbnails/");
		String bookCoverUploadPath = bookCoverUploadDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/src/main/resources/static/thumbnails/**").addResourceLocations("file:/" + bookCoverUploadPath + "/");
	}
	
}
