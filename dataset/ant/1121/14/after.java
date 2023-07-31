class PlaceHold {
  protected OutputStream getErrorStream() {
    if (this.errorStream == null) {
      if (error != null) {
        try {
          setErrorStream(
              new PrintStream(
                  new BufferedOutputStream(new FileOutputStream(error.getPath(), append))));
        } catch (IOException e) {
          throw new BuildException(e, getLocation());
        }
      } else {
        setErrorStream(new LogOutputStream(this, Project.MSG_WARN));
      }
    }
    return this.errorStream;
  }
}
