package com.ssinc.Nuzlocke;

import com.ssinc.Nuzlocke.dataSource.FirebaseImportData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class NuzlockeApplication {

	public static void main(String[] args) {

		SpringApplication.run(NuzlockeApplication.class, args);

	}
}
