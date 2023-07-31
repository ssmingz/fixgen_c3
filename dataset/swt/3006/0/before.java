class PlaceHold {
  void generateSTRUCTS_C(Class[] classes) {
    try {
      String outputName = getClassName(mainClass).toLowerCase();
      String inc = (("#include \"swt.h\"\n" + "#include \"") + outputName) + "_structs.h\"\n";
      metaData.setMetaData("swt_includes", inc);
      StructsGenerator gen = new StructsGenerator();
      gen.setMetaData(metaData);
      gen.setProgressMonitor(progress);
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      PrintStream print = new PrintStream(out);
      gen.setOutput(print);
      gen.generateSourceFile(classes);
      print.flush();
      String extension = (gen.getCPP()) ? ".cpp" : ".c";
      if (out.size() > 0) {
        output(out.toByteArray(), ((outputDir + outputName) + "_structs") + extension);
      }
    } catch (Exception e) {
      System.out.println("Problem");
      e.printStackTrace(System.out);
    }
  }
}
