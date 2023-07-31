class PlaceHold {
  void generateMainClass() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    this.out = new PrintStream(out);
    String header = "";
    String footer = "";
    String fileName = (outputDir + mainClassName.replace('.', '/')) + ".java";
    FileInputStream is = null;
    try {
      InputStreamReader input =
          new InputStreamReader(new BufferedInputStream(is = new FileInputStream(fileName)));
      StringBuffer str = new StringBuffer();
      char[] buffer = new char[4096];
      int read;
      while ((read = input.read(buffer)) != (-1)) {
        str.append(buffer, 0, read);
      }
      String section = "/** This section is auto generated */";
      int start = str.indexOf(section) + section.length();
      int end = str.indexOf(section, start);
      header = str.substring(0, start);
      footer = str.substring(end);
    } catch (IOException e) {
    } finally {
      try {
        if (is != null) {
          is.close();
        }
      } catch (IOException e) {
      }
    }
    out(header);
    outln();
    outln();
    out("/** Classes */");
    outln();
    generateClassesConst();
    outln();
    out("/** Protocols */");
    outln();
    generateProtocolsConst();
    outln();
    out("/** Selectors */");
    outln();
    generateSelectorsConst();
    outln();
    out("/** Constants */");
    outln();
    generateEnums();
    outln();
    out("/** Globals */");
    outln();
    generateConstants();
    outln();
    out("/** Functions */");
    outln();
    outln();
    generateFunctions();
    outln();
    out("/** Sends */");
    outln();
    generateSends();
    outln();
    generateStructNatives();
    outln();
    out(footer);
    try {
      out.flush();
      if (out.size() > 0) {
        output(out.toByteArray(), fileName);
      }
    } catch (Exception e) {
      System.out.println("Problem");
      e.printStackTrace(System.out);
    }
  }
}
