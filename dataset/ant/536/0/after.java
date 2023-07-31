class PlaceHold {
  public void execute() throws BuildException {
    int result = 0;
    if ((repository == null) || repository.trim().equals("")) {
      throw new BuildException("Required argument repository not specified");
    }
    Commandline commandLine = new Commandline();
    commandLine.setExecutable(getExecutable(PCLI_EXE));
    commandLine.createArgument().setValue("lvf");
    commandLine.createArgument().setValue("-z");
    commandLine.createArgument().setValue("-aw");
    if (getWorkspace() != null) {
      commandLine.createArgument().setValue("-sp" + getWorkspace());
    }
    commandLine.createArgument().setValue("-pr" + getRepository());
    String uid = getUserId();
    if (uid != null) {
      commandLine.createArgument().setValue("-id" + uid);
    }
    if ((getPvcsproject() == null) && getPvcsprojects().isEmpty()) {
      pvcsProject = "/";
    }
    if (getPvcsproject() != null) {
      commandLine.createArgument().setValue(getPvcsproject());
    }
    if (!getPvcsprojects().isEmpty()) {
      Enumeration e = getPvcsprojects().elements();
      while (e.hasMoreElements()) {
        String projectName = ((PvcsProject) (e.nextElement())).getName();
        if ((projectName == null) || projectName.trim().equals("")) {
          throw new BuildException("name is a required attribute " + "of pvcsproject");
        }
        commandLine.createArgument().setValue(projectName);
      }
    }
    File tmp = null;
    File tmp2 = null;
    try {
      Random rand = new Random(System.currentTimeMillis());
      tmp = new File(("pvcs_ant_" + rand.nextLong()) + ".log");
      FileOutputStream fos = new FileOutputStream(tmp);
      tmp2 = new File(("pvcs_ant_" + rand.nextLong()) + ".log");
      log(commandLine.describeCommand(), MSG_VERBOSE);
      try {
        result =
            runCmd(
                commandLine,
                new PumpStreamHandler(fos, new LogOutputStream(this, Project.MSG_WARN)));
      } finally {
        fos.close();
      }
      if (Execute.isFailure(result) && (!ignorerc)) {
        String msg = "Failed executing: " + commandLine.toString();
        throw new BuildException(msg, getLocation());
      }
      if (!tmp.exists()) {
        throw new BuildException(
            ("Communication between ant and pvcs "
                    + "failed. No output generated from executing PVCS ")
                + "commandline interface \"pcli\" and \"get\"");
      }
      log("Creating folders", MSG_INFO);
      createFolders(tmp);
      massagePCLI(tmp, tmp2);
      commandLine.clearArgs();
      commandLine.setExecutable(getExecutable(GET_EXE));
      if ((getConfig() != null) && (getConfig().length() > 0)) {
        commandLine.createArgument().setValue("-c" + getConfig());
      }
      if ((getForce() != null) && getForce().equals("yes")) {
        commandLine.createArgument().setValue("-Y");
      } else {
        commandLine.createArgument().setValue("-N");
      }
      if (getPromotiongroup() != null) {
        commandLine.createArgument().setValue("-G" + getPromotiongroup());
      } else if (getLabel() != null) {
        commandLine.createArgument().setValue("-r" + getLabel());
      } else if (getRevision() != null) {
        commandLine.createArgument().setValue("-r" + getRevision());
      }
      if (updateOnly) {
        commandLine.createArgument().setValue("-U");
      }
      commandLine.createArgument().setValue("@" + tmp2.getAbsolutePath());
      log("Getting files", MSG_INFO);
      log("Executing " + commandLine.toString(), MSG_VERBOSE);
      result = runCmd(commandLine, new LogStreamHandler(this, Project.MSG_INFO, Project.MSG_WARN));
      if ((result != 0) && (!ignorerc)) {
        String msg =
            (("Failed executing: " + commandLine.toString()) + ". Return code was ") + result;
        throw new BuildException(msg, getLocation());
      }
    } catch (FileNotFoundException e) {
      String msg =
          (("Failed executing: " + commandLine.toString()) + ". Exception: ") + e.getMessage();
      throw new BuildException(msg, getLocation());
    } catch (IOException e) {
      String msg =
          (("Failed executing: " + commandLine.toString()) + ". Exception: ") + e.getMessage();
      throw new BuildException(msg, getLocation());
    } catch (ParseException e) {
      String msg =
          (("Failed executing: " + commandLine.toString()) + ". Exception: ") + e.getMessage();
      throw new BuildException(msg, getLocation());
    } finally {
      if (tmp != null) {
        tmp.delete();
      }
      if (tmp2 != null) {
        tmp2.delete();
      }
    }
  }
}
