class PlaceHold {
  void generateSTRUCTS_H(JNIClass[] classes) {
    try {
      StructsGenerator gen = new StructsGenerator(true);
      gen.setMainClass(mainClass);
      gen.setClasses(classes);
      gen.setMetaData(metaData);
      gen.setProgressMonitor(progress);
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      gen.setOutput(new PrintStream(out));
      gen.generate();
      if (out.size() > 0) {
        JNIGenerator.output(out.toByteArray(), outputDir + gen.getFileName());
      }
    } catch (Exception e) {
      System.out.println("Problem");
      e.printStackTrace(System.out);
    }
  }
}
