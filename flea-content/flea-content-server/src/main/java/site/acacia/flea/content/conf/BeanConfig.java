package site.acacia.flea.content.conf;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@SpringBootConfiguration
public class BeanConfig {
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
}
