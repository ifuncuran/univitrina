package ru.hh.univitrina;

import ru.hh.nab.starter.NabApplication;
import ru.hh.univitrina.config.ProdConfig;

public class UnivitrinaWebapp {
  public static void main(String[] args) {
    buildApplication().run(ProdConfig.class);
  }

  static NabApplication buildApplication() {
    return NabApplication.builder()
      .configureJersey().bindToRoot()
      .build();
  }
}
