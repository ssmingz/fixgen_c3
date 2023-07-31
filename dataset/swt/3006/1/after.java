class PlaceHold {
  void generateSTATS_C(Class[] classes) {
    try {
      String outputName = getClassName(mainClass).toLowerCase();
      String inc = (("#include \"swt.h\"\n" + "#include \"") + outputName) + "_stats.h\"\n";
      metaData.setMetaData("swt_includes", inc);
      StatsGenerator gen = new StatsGenerator(false);
      gen.setClasses(classes);
      gen.setMetaData(metaData);
      gen.setProgressMonitor(progress);
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      PrintStream print = new PrintStream(out);
      gen.setOutput(print);
      gen.generate();
      print.flush();
      String extension = (gen.getCPP()) ? ".cpp" : ".c";
      if (out.size() > 0) {
        output(out.toByteArray(), ((outputDir + outputName) + "_stats") + extension);
      }
    } catch (Exception e) {
      System.out.println("Problem");
      e.printStackTrace(System.out);
    }
  }
}
