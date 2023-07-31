class PlaceHold {
  public OutputStream getOutputStream() {
    if (STDOUT.equals(value)) {
      return new KeepAliveOutputStream(System.out);
    } else if (STDERR.equals(value)) {
      return new KeepAliveOutputStream(System.err);
    }
    File f = project.resolveFile(value);
    try {
      return new FileOutputStream(f);
    } catch (IOException e) {
      throw new BuildException(e);
    }
  }
}
