package com.example.springboot2sandbox.service;

import com.example.springboot2sandbox.dto.BrandDto;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService {

	public void upload(MultipartFile multipartFile) {
		CsvMapper csvMapper = new CsvMapper();
		CsvSchema csvSchema = csvMapper.schemaFor(BrandDto.class).withHeader();

		try(InputStreamReader reader = new InputStreamReader(multipartFile.getInputStream(), StandardCharsets.ISO_8859_1)) {
			MappingIterator<BrandDto> sampleDtoMappingIterator = csvMapper
					.readerFor(BrandDto.class)
					.with(csvSchema)
					.readValue(reader);

			List<BrandDto> result = new ArrayList<>();
			while (sampleDtoMappingIterator.hasNextValue()) {
				BrandDto brandDto = sampleDtoMappingIterator.nextValue();
				result.add(brandDto);
			}
			System.out.println(result);
		} catch (IOException e) {
			System.out.println(e);
		}

	}
}
