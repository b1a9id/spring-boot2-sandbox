package com.example.springboot2sandbox.service;

import com.example.springboot2sandbox.dto.SampleDto;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class FileUploadService {

	public void upload(MultipartFile multipartFile) {
		CsvMapper csvMapper = new CsvMapper();
		CsvSchema csvSchema = csvMapper.schemaFor(SampleDto.class).withHeader();

		try(InputStreamReader reader = new InputStreamReader(multipartFile.getInputStream(), StandardCharsets.ISO_8859_1)) {
			MappingIterator<SampleDto> sampleDtoMappingIterator =
					csvMapper.readerFor(SampleDto.class).with(csvSchema).readValues(reader);
			List<SampleDto> result = sampleDtoMappingIterator.readAll();
			System.out.println(result);
		} catch (IOException e) {
			System.out.println(e);
		}

	}
}
