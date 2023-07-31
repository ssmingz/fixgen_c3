class PlaceHold {
  private void executeWLS6() {
    File securityPolicyFile = findSecurityPolicyFile(DEFAULT_WL60_POLICY_FILE);
    if (!beaHome.isDirectory()) {
      throw new BuildException(("BEA home " + beaHome.getPath()) + " is not valid");
    }
    File configFile =
        new File(weblogicSystemHome, ("config/" + weblogicDomainName) + "/config.xml");
    if (!configFile.exists()) {
      throw new BuildException(("Server config file " + configFile) + " not found.");
    }
    if (managementPassword == null) {
      throw new BuildException("You must supply a management password to start the server");
    }
    Java weblogicServer = ((Java) (getProject().createTask("java")));
    weblogicServer.setTaskName(getTaskName());
    weblogicServer.setFork(true);
    weblogicServer.setDir(weblogicSystemHome);
    weblogicServer.setClassname(weblogicMainClass);
    String jvmArgs = additionalJvmArgs;
    jvmArgs += " -Dweblogic.Domain=" + weblogicDomainName;
    jvmArgs += " -Dweblogic.Name=" + weblogicSystemName;
    jvmArgs += " -Dweblogic.system.home=" + weblogicSystemHome;
    jvmArgs += " -Dbea.home=" + beaHome;
    jvmArgs += " -Djava.security.policy==" + securityPolicyFile;
    jvmArgs += " -Dweblogic.management.username=" + managementUsername;
    jvmArgs += " -Dweblogic.management.password=" + managementPassword;
    if (pkPassword != null) {
      jvmArgs += " -Dweblogic.pkpassword=" + pkPassword;
    }
    weblogicServer.createJvmarg().setLine(jvmArgs);
    weblogicServer.createArg().setLine(additionalArgs);
    if (classpath != null) {
      weblogicServer.setClasspath(classpath);
    }
    if (weblogicServer.executeJava() != 0) {
      throw new BuildException("Execution of weblogic server failed");
    }
  }
}
