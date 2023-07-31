class PlaceHold {
  public void execute() throws BuildException {
    if (project.getJavaVersion().equals(JAVA_1_1)) {
      throw new BuildException(
          "The genkey task is only available on JDK" + " versions 1.2 or greater");
    }
    if (null == alias) {
      throw new BuildException("alias attribute must be set");
    }
    if (null == storepass) {
      throw new BuildException("storepass attribute must be set");
    }
    if ((null == dname) && (null == expandedDname)) {
      throw new BuildException("dname must be set");
    }
    final StringBuffer sb = new StringBuffer();
    sb.append("keytool -genkey ");
    if (verbose) {
      sb.append("-v ");
    }
    sb.append("-alias \"");
    sb.append(alias);
    sb.append("\" ");
    if (null != dname) {
      sb.append("-dname \"");
      sb.append(dname);
      sb.append("\" ");
    }
    if (null != expandedDname) {
      sb.append("-dname \"");
      sb.append(expandedDname);
      sb.append("\" ");
    }
    if (null != keystore) {
      sb.append("-keystore \"");
      sb.append(keystore);
      sb.append("\" ");
    }
    if (null != storepass) {
      sb.append("-storepass \"");
      sb.append(storepass);
      sb.append("\" ");
    }
    if (null != storetype) {
      sb.append("-storetype \"");
      sb.append(storetype);
      sb.append("\" ");
    }
    sb.append("-keypass \"");
    if (null != keypass) {
      sb.append(keypass);
    } else {
      sb.append(storepass);
    }
    sb.append("\" ");
    if (null != sigalg) {
      sb.append("-sigalg \"");
      sb.append(sigalg);
      sb.append("\" ");
    }
    if (null != keyalg) {
      sb.append("-keyalg \"");
      sb.append(keyalg);
      sb.append("\" ");
    }
    if (0 < keysize) {
      sb.append("-keysize \"");
      sb.append(keysize);
      sb.append("\" ");
    }
    if (0 < validity) {
      sb.append("-validity \"");
      sb.append(validity);
      sb.append("\" ");
    }
    log("Generating Key for " + alias);
    final ExecTask cmd = ((ExecTask) (project.createTask("exec")));
    cmd.setCommand(new Commandline(sb.toString()));
    cmd.setFailonerror(true);
    cmd.setTaskName(getTaskName());
    cmd.execute();
  }
}
