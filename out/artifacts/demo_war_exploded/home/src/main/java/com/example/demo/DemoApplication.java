package com.example.demo;

import com.example.annotation.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
@RestController
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	private static final String template = "Hello, %s!";

	private final AtomicLong idCounter = new AtomicLong();
	private final AtomicLong authorIdCounter = new AtomicLong();
	private final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	@GetMapping("/hello")
	@LogUtils("hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format(template, name);
	}

	@GetMapping("/greeting")
	@LogUtils("greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(idCounter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/spittle")
	public List<Spittle> spittle(@RequestParam(value = "name", defaultValue = "World") String name) {
		Spittle hello = new Spittle(idCounter.incrementAndGet(), authorIdCounter.incrementAndGet(), name);
		return Collections.singletonList(hello);
	}

	@LogUtils("spittles")
	@GetMapping("/spittles")
	public List<Spittle> spittles() {
		Spittle hello = new Spittle(idCounter.incrementAndGet(), authorIdCounter.incrementAndGet(), "hello");
		Spittle world = new Spittle(idCounter.incrementAndGet(), authorIdCounter.incrementAndGet(), "world");
		return Arrays.asList(hello, world);
	}
}

class Greeting {
	private final long id;
	private final String content;

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}

class Spittle {
	long id;
	long authorId;
	String content;

	public Spittle(long id, long authorId, String content) {
		this.id = id;
		this.authorId = authorId;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}