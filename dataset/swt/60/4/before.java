class PlaceHold {
  void saveExtraAttributes(String xmlPath, Document document) {
    try {
      String packageName = getPackageName(mainClassName);
      String fileName =
          (((outputDir + packageName.replace('.', '/')) + "/") + getFileName(xmlPath)) + ".extras";
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      DOMWriter writer = new DOMWriter(new PrintStream(out));
      String[] names = getIDAttributeNames();
      String[] filter = new String[names.length + 2];
      filter[0] = "class_method";
      filter[1] = "swt_.*";
      System.arraycopy(names, 0, filter, 2, names.length);
      writer.setAttributeFilter(filter);
      writer.setNodeFilter("swt_");
      writer.print(document);
      if (out.size() > 0) {
        output(out.toByteArray(), fileName);
      }
    } catch (Exception e) {
      System.out.println("Problem");
      e.printStackTrace(System.out);
    }
  }
}
