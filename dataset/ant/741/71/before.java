class PlaceHold {
  public void execute() throws TaskException {
    if ((username == null) || (password == null)) {
      throw new TaskException("weblogic username and password must both be set");
    }
    if (serverURL == null) {
      throw new TaskException("The url of the weblogic server must be provided.");
    }
    Java weblogicAdmin = ((Java) (project.createTask("java")));
    weblogicAdmin.setFork(true);
    weblogicAdmin.setClassname("weblogic.Admin");
    String args;
    if (beaHome == null) {
      args = (((((serverURL + " SHUTDOWN ") + username) + " ") + password) + " ") + delay;
    } else {
      args =
          (((((((" -url " + serverURL) + " -username ") + username) + " -password ") + password)
                      + " SHUTDOWN ")
                  + " ")
              + delay;
    }
    weblogicAdmin.setArgs(args);
    weblogicAdmin.setClasspath(classpath);
    weblogicAdmin.execute();
  }
}
