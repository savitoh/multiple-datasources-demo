package com.savitoh.multipledatasourcesdemo;

import com.savitoh.multipledatasourcesdemo.cartorio.repository.CartorioRepository;
import com.savitoh.multipledatasourcesdemo.unidadejudicial.repository.UnidadeJudicialRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class MultipleDatasourcesDemoApplicationTests {

	@Autowired
	private UnidadeJudicialRepository unidadeJudicialRepository;

	@Autowired
	private CartorioRepository cartorioRepository;

	@Test
	public void contextLoads() {
		Assert.assertNotNull(unidadeJudicialRepository);
		Assert.assertNotNull(cartorioRepository);
	}

}
