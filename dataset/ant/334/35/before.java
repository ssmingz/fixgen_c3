class PlaceHold {
  public void stop() {
    super.stop();
    try {
      getErr().close();
      getOut().close();
    } catch (IOException e) {
      throw new BuildException(e);
    }
  }
}
