package apap.ti.silogistik2006596705;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.github.javafaker.Faker;

import apap.ti.silogistik2006596705.DTO.GudangMapper;
import apap.ti.silogistik2006596705.DTO.KaryawanMapper;
import apap.ti.silogistik2006596705.service.GudangService;
import apap.ti.silogistik2006596705.service.KaryawanService;
import apap.ti.silogistik2006596705.DTO.CreateGudangRequestDTO;
import jakarta.transaction.Transactional;

import java.util.Locale;

@SpringBootApplication
public class Silogistik2006596705Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2006596705Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(GudangService gudangService, GudangMapper gudangMapper, KaryawanService karyawanService,
			KaryawanMapper karyawanMapper) {
		return args -> {
			var faker = new Faker(new Locale("in-ID"));

			// Create GudangDTO to be filled with data from javafaker
			var gudangDTO = new CreateGudangRequestDTO();
			gudangDTO.setNama(faker.company().name());
			gudangDTO.setAlamatGudang(faker.address().fullAddress());

			// Map GudangDTO to Gudang to be saved via service via jpa
			var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangDTO);
			gudangService.saveGudang(gudang);

			// Create KaryawanDTO to be filled with data from javafaker

			// Map KaryawanDTO to be
		};
	}

}
