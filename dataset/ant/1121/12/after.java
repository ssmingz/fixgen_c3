class PlaceHold {
  protected OutputStream getOutputStream() {
    if (this.outputStream == null) {
      if (output != null) {
        try {
          setOutputStream(
              new PrintStream(
                  new BufferedOutputStream(new FileOutputStream(output.getPath(), append))));
        } catch (IOException e) {
          throw new BuildException(e, getLocation());
        }
      } else {
        setOutputStream(new LogOutputStream(this, Project.MSG_INFO));
      }
    }
    return this.outputStream;
  }
}
