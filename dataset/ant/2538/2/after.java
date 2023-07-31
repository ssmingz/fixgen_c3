class PlaceHold {
  public void execute() throws TaskException {
    if ((ejbjarfile == null) || ejbjarfile.isDirectory()) {
      throw new TaskException("invalid ejb jar file.");
    }
    if ((clientjarfile == null) || clientjarfile.isDirectory()) {
      getLogger().debug("invalid or missing client jar file.");
      String ejbjarname = ejbjarfile.getAbsolutePath();
      String clientname = ejbjarname.substring(0, ejbjarname.lastIndexOf("."));
      clientname = clientname + "client.jar";
      clientjarfile = new File(clientname);
    }
    if (mode == null) {
      getLogger().info("mode is null default mode  is java");
      setMode(JAVA_MODE);
    }
    getLogger().info("client jar file is " + clientjarfile);
    if (mode.equalsIgnoreCase(FORK_MODE)) {
      executeFork();
    } else {
      executeJava();
    }
  }
}
