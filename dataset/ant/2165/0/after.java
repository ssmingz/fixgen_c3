class PlaceHold {
  public void execute() throws BuildException {
    if (file == null) {
      log(message, logLevel);
    } else {
      FileWriter out = null;
      try {
        out = new FileWriter(file.getAbsolutePath(), append);
        out.write(message, 0, message.length());
      } catch (IOException ioe) {
        throw new BuildException(ioe, getLocation());
      } finally {
        FileUtils.close(out);
      }
    }
  }
}
