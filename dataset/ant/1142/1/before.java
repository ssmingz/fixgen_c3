class PlaceHold {
  public void link() throws Exception {
    ZipOutputStream output = new ZipOutputStream(new FileOutputStream(outfile));
    if (compression) {
      output.setMethod(ZipOutputStream.DEFLATED);
      output.setLevel(Deflater.DEFAULT_COMPRESSION);
    } else {
      output.setMethod(ZipOutputStream.STORED);
    }
    Enumeration merges = mergefiles.elements();
    while (merges.hasMoreElements()) {
      String path = ((String) (merges.nextElement()));
      File f = new File(path);
      if (f.getName().endsWith(".jar") || f.getName().endsWith(".zip")) {
        mergeZipJarContents(output, f);
      } else {
        addAddFile(path);
      }
    }
    Enumeration adds = addfiles.elements();
    while (adds.hasMoreElements()) {
      String name = ((String) (adds.nextElement()));
      File f = new File(name);
      if (f.isDirectory()) {
        addDirContents(output, f, f.getName() + '/', compression);
      } else {
        addFile(output, f, "", compression);
      }
    }
    if (output != null) {
      try {
        output.close();
      } catch (IOException ioe) {
      }
    }
  }
}
