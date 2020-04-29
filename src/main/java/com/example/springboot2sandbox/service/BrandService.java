package com.example.springboot2sandbox.service;

import com.example.springboot2sandbox.dto.BrandDto;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService {

	public List<BrandDto> upload(MultipartFile multipartFile) {
		CsvMapper csvMapper = new CsvMapper();
		CsvSchema csvSchema = csvMapper.schemaFor(BrandDto.class).withHeader();

		List<BrandDto> result = new ArrayList<>();
		try(InputStreamReader reader = new InputStreamReader(multipartFile.getInputStream(), Charset.forName("MS932"))) {
			MappingIterator<BrandDto> sampleDtoMappingIterator = csvMapper
					.readerFor(BrandDto.class)
					.with(csvSchema)
					.readValues(reader);


			while (sampleDtoMappingIterator.hasNextValue()) {
				BrandDto brandDto = sampleDtoMappingIterator.nextValue();
				result.add(brandDto);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
}
