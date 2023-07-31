class PlaceHold {
  void generateSWT_C(JNIClass[] classes) {
    try {
      NativesGenerator gen = new NativesGenerator();
      gen.setMainClass(mainClass);
      gen.setClasses(classes);
      gen.setMetaData(metaData);
      gen.setProgressMonitor(progress);
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      gen.setOutput(new PrintStream(out));
      gen.generate();
      if (out.size() > 0) {
        output(out.toByteArray(), outputDir + gen.getFileName());
      }
    } catch (Exception e) {
      System.out.println("Problem");
      e.printStackTrace(System.out);
    }
  }
}
