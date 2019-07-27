/**
 * 
 */
package site.acacia.flea.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * @author 张胤
 *
 *         2018年8月28日-上午10:01:49
 */
@Configuration
@PropertySource("classpath:dubbo/dubbo.properties")
@ImportResource({ "classpath:dubbo/*.xml" })
public class DubboConfig {

}
