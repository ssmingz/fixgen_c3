class PlaceHold {
  public void addJavaRuntime() {
    if (JavaEnvUtils.isKaffe()) {
      File kaffeShare =
          new File(
              (((System.getProperty("java.home") + File.separator) + "share") + File.separator)
                  + "kaffe");
      if (kaffeShare.isDirectory()) {
        FileSet kaffeJarFiles = new FileSet();
        kaffeJarFiles.setDir(kaffeShare);
        kaffeJarFiles.setIncludes("*.jar");
        addFileset(kaffeJarFiles);
      }
    } else if ("GNU libgcj".equals(System.getProperty("java.vm.name"))) {
      addExisting(systemBootClasspath);
    }
    if (System.getProperty("java.vendor").toLowerCase(Locale.US).indexOf("microsoft") >= 0) {
      FileSet msZipFiles = new FileSet();
      msZipFiles.setDir(new File((System.getProperty("java.home") + File.separator) + "Packages"));
      msZipFiles.setIncludes("*.ZIP");
      addFileset(msZipFiles);
    } else {
      addExisting(
          new Path(
              null,
              (((System.getProperty("java.home") + File.separator) + "lib") + File.separator)
                  + "rt.jar"));
      addExisting(
          new Path(
              null,
              (((((System.getProperty("java.home") + File.separator) + "jre") + File.separator)
                          + "lib")
                      + File.separator)
                  + "rt.jar"));
      String[] secJars = new String[] {"jce", "jsse"};
      for (int i = 0; i < secJars.length; i++) {
        addExisting(
            new Path(
                null,
                ((((System.getProperty("java.home") + File.separator) + "lib") + File.separator)
                        + secJars[i])
                    + ".jar"));
        addExisting(
            new Path(
                null,
                ((((((System.getProperty("java.home") + File.separator) + "..") + File.separator)
                                + "Classes")
                            + File.separator)
                        + secJars[i])
                    + ".jar"));
      }
      String[] ibmJars = new String[] {"core", "graphics", "security", "server", "xml"};
      for (int i = 0; i < ibmJars.length; i++) {
        addExisting(
            new Path(
                null,
                ((((System.getProperty("java.home") + File.separator) + "lib") + File.separator)
                        + ibmJars[i])
                    + ".jar"));
      }
      addExisting(
          new Path(
              null,
              (((((System.getProperty("java.home") + File.separator) + "..") + File.separator)
                          + "Classes")
                      + File.separator)
                  + "classes.jar"));
      addExisting(
          new Path(
              null,
              (((((System.getProperty("java.home") + File.separator) + "..") + File.separator)
                          + "Classes")
                      + File.separator)
                  + "ui.jar"));
    }
  }
}
