package com.github.fakemongo.integration;

import org.joda.time.DateTime;

/**
 * @author Alexander Arutuniants <alex.art@in2circle.com>
 */

  public class SpringModelBarcode {

    private String serial;
    private DateTime time;

    public SpringModelBarcode(String serial, DateTime time) {
      this.time = time;
      this.serial = serial;
    }

    public DateTime getTime() {
      return time;
    }

    public void setTime(DateTime time) {
      this.time = time;
    }

    public String getSerial() {
      return serial;
    }

    public void setSerial(String serial) {
      this.serial = serial;
    }
  }
