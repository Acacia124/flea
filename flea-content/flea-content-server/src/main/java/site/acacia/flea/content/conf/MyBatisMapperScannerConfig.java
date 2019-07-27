/**
 * 
 */
package site.acacia.flea.content.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张胤
 *
 *         2018年8月28日-上午9:35:44
 */
@Configuration
@AutoConfigureAfter(MybatisConfig.class)
@MapperScan("site.acacia.flea.mapper")
public class MyBatisMapperScannerConfig {
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("site.acacia.flea.mapper");
		return mapperScannerConfigurer;
	}
}
