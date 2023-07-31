class PlaceHold {
  public void execute() throws TaskException {
    Server s = getServer();
    try {
      runServer(s);
    } finally {
      s.disconnect();
    }
    getContext().info(checkedOut + " files checked out.");
  }
}
