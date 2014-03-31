package com.github.fakemongo.integration;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Alexander Arutuniants <alex.art@in2circle.com>
 */
@Document
@CompoundIndexes({
  @CompoundIndex(name = "serial_date_idx", def = "{'barcode.serial': 1, 'barcode.tme': 1}", unique = true)
})
public class SpringModelReceipt {

  public static final String COLLECTION = "receipts";

  private SpringModelBarcode barcode;

  SpringModelReceipt(SpringModelBarcode barcode) {
    this.barcode = barcode;
  }

  public SpringModelBarcode getBarcode() {
    return barcode;
  }

  public void setBarcode(SpringModelBarcode barcode) {
    this.barcode = barcode;
  }
}
