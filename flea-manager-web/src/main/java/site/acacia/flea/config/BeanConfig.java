package site.acacia.flea.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import site.acacia.flea.interceptor.LoginIntercepter;

@SpringBootConfiguration
public class BeanConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private LoginIntercepter loginIntercepter;

	@Bean
	// 使用fastjson
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		// 1.需要定义一个convert 转换消息的对象
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

		// 2.添加fastJso的配置信息，比如，是否格式化返回json数据；
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

		// 3.在convert中添加配置信息；
		fastConverter.setFastJsonConfig(fastJsonConfig);
		HttpMessageConverter<?> converter = fastConverter;

		// 4.将convert添加到converts中
		return new HttpMessageConverters(converter);
	}

	// 拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginIntercepter).addPathPatterns("/api/user/**");
	}
}
