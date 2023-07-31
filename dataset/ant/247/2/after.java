class PlaceHold {
  protected void attach(File file, PrintStream out) throws IOException {
    if ((!file.exists()) || (!file.canRead())) {
      throw new BuildException(
          (("File \"" + file.getName()) + "\" does not exist or is not ") + "readable.");
    }
    if (includeFileNames) {
      out.println();
      String filename = file.getName();
      int filenamelength = filename.length();
      out.println(filename);
      for (int star = 0; star < filenamelength; star++) {
        out.print('=');
      }
      out.println();
    }
    int length;
    byte[] buf = new byte[1024];
    FileInputStream finstr = new FileInputStream(file);
    try {
      BufferedInputStream in = new BufferedInputStream(finstr, buf.length);
      while ((length = in.read(buf)) != (-1)) {
        out.write(buf, 0, length);
      }
    } finally {
      finstr.close();
    }
  }
}
