class PlaceHold {
  public void execute() {
    try {
      OutputStream os = null;
      if (file != null) {
        os = new FileOutputStream(file, append);
      } else {
        os = new LogOutputStream(this, Project.MSG_INFO);
      }
      Node n = getFragment().getFirstChild();
      if (n == null) {
        throw new BuildException("No nested XML specified");
      }
      writer.write(((Element) (n)), os);
    } catch (Exception e) {
      throw new BuildException(e);
    }
  }
}
