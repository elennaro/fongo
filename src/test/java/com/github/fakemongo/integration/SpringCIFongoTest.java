package com.github.fakemongo.integration;

import com.github.fakemongo.Fongo;
import com.github.fakemongo.junit.FongoRule;
import com.mongodb.Mongo;
import java.util.List;
import org.joda.time.DateTime;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Alexander Arutuniants <alex.art@in2circle.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringCIFongoTest.MongoConfig.class})
public class SpringCIFongoTest {

  @Rule
  public FongoRule fongoRule = new FongoRule(false);

  @Autowired
  private ApplicationContext ApplicationContext;

  @Autowired
  SpringReceiptTestRepository springReceiptRepository;

  @Test
  public void test() throws Exception {
    String serial = "serial1";
    DateTime time = new DateTime();
    SpringModelBarcode springModelBarcode = new SpringModelBarcode(serial, time);
    SpringModelReceipt receipt = new SpringModelReceipt(springModelBarcode);
    springReceiptRepository.save(receipt);
    List<SpringModelReceipt> receipts = springReceiptRepository.findByBarcodeSerialAndBarcodeTime(serial, time);
    assertEquals(1, receipts.size());
  }

  @Configuration
  @EnableMongoRepositories("com.github.fakemongo.integration")
  public static class MongoConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
      return "db";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
      return new Fongo("spring-test").getMongo();
    }

    @Override
    protected String getMappingBasePackage() {
      return SpringModelReceipt.class.getPackage().toString();
    }

  }

}
