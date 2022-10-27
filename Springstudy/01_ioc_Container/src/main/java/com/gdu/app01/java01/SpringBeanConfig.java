package com.gdu.app01.java01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// XML 처리하는 역할 수행함

/*
 	@Configuration
 	Bean 만드는 Java 파일
 	Spring Bean Configuration File하고 하는 일 같음
*/

@Configuration
public class SpringBeanConfig {

	// 메소드 하나당 Bean 하나를 맡아서 생성한다.
	
	/*
		@Bean
		Bean 만드는 메소드
		메소드 이름이 Bean의 id
		반환타입이 Bean의 class
	*/
	/*
	 	<bean id="song1" class="song">
	 		<property name="title" value="제목1" />
	 		<property name="genre" value="장르1" />
	 	</bean>
	 	위 xml코드와 아래 java코드가 같은 코드임
	*/
	@Bean
	public Song song1() {
		Song song = new Song();
		song.setTitle("제목1");
		song.setGenre("장르1");
		return song;
	}
	
	/*
	 	<bean id="song2" class="song">
	 		<property name="title" value="제목2" />
	 		<property name="genre" value="장르2" />
	 	</bean>
	 */
	@Bean(name="song2") // @Bean에 name 값을 지정하면
	public Song a234ㅅㅎㄱsdf() {  // 메소드 이름은 아무거나 적어도 된다
		Song song = new Song();
		song.setTitle("제목2");
		song.setGenre("장르2");
		return song;
	}
	

	/*
	 	<bean id="song1" class="song">
	 		<constructor-arg value="제목3" />
	 		<constructor-arg value="장르3" />
	 	</bean>
	*/
	@Bean
	public Song song3() {
		return new Song("제목3", "장르3");
	}
	
	
	// 미션
	
	// song1를 가지는 singer1을 만들어 보자
	// setter injection
	@Bean
	public Singer singer1() {
		Singer singer = new Singer();
		singer.setName("가수1");
		singer.setSong(song1());
		return singer;
	}
	
	// song2를 가지는 singer2를 만들어 보자
	// setter injection
	@Bean(name="singer2")
	public Singer asdhklf() {
		Singer singer = new Singer();
		singer.setName("가수2");
		singer.setSong(a234ㅅㅎㄱsdf());
		return singer;
	}
	
	
	// song3를 가지는 singer3을 만들어 보자
	// constructor injection
	@Bean
	public Singer singer3() {
		return new Singer("가수3", song3());
	}
	

	
	
	
}
