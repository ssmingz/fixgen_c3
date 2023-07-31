class PlaceHold {
  void generateSTRUCTS_C(Class[] classes) {
    try {
      StructsGenerator gen = new StructsGenerator(false);
      gen.setMainClass(mainClass);
      gen.setClasses(classes);
      gen.setMetaData(metaData);
      gen.setProgressMonitor(progress);
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      PrintStream print = new PrintStream(out);
      gen.setOutput(print);
      gen.generate();
      print.flush();
      if (out.size() > 0) {
        output(out.toByteArray(), outputDir + gen.getOutputName());
      }
    } catch (Exception e) {
      System.out.println("Problem");
      e.printStackTrace(System.out);
    }
  }
}
