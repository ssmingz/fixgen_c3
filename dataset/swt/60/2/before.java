class PlaceHold {
  void generateMetaData(JNIClass[] classes) {
    try {
      MetaDataGenerator gen = new MetaDataGenerator();
      gen.setMainClass(mainClass);
      gen.setClasses(classes);
      gen.setMetaData(metaData);
      gen.setProgressMonitor(progress);
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      gen.setOutput(new PrintStream(out));
      if (new File(getMetaDataDir() + gen.getFileName()).exists()) {
        gen.generate();
        if (!new File(getMetaDataDir()).exists()) {
          System.out.println("Warning: Meta data output dir does not exist");
          return;
        }
        if (out.size() > 0) {
          output(out.toByteArray(), getMetaDataDir() + gen.getFileName());
        }
      }
    } catch (Exception e) {
      System.out.println("Problem");
      e.printStackTrace(System.out);
    }
  }
}
