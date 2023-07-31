class PlaceHold {
  protected void attach(File file, PrintStream out) throws IOException {
    if ((!file.exists()) || (!file.canRead())) {
      throw new BuildException(
          (("File \"" + file.getName()) + "\" does not exist or is not ") + "readable.");
    }
    FileInputStream finstr = new FileInputStream(file);
    try {
      BufferedInputStream in = new BufferedInputStream(finstr);
      UUEncoder encoder = new UUEncoder(file.getName());
      encoder.encode(in, out);
    } finally {
      finstr.close();
    }
  }
}
