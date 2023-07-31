class PlaceHold {
  public void execute() throws BuildException {
    checkConfiguration();
    Vector files = getFileList();
    if (isUpToDate(files)) {
      return;
    }
    log((("Building " + archiveType) + ": ") + cabFile.getAbsolutePath());
    if (!Os.isFamily("windows")) {
      log("Using listcab/libcabinet", MSG_VERBOSE);
      StringBuffer sb = new StringBuffer();
      Enumeration fileEnum = files.elements();
      while (fileEnum.hasMoreElements()) {
        sb.append(fileEnum.nextElement()).append("\n");
      }
      sb.append("\n").append(cabFile.getAbsolutePath()).append("\n");
      try {
        Process p =
            Execute.launch(
                getProject(),
                new String[] {"listcab"},
                null,
                baseDir != null ? baseDir : getProject().getBaseDir(),
                true);
        OutputStream out = p.getOutputStream();
        LogOutputStream outLog = new LogOutputStream(this, Project.MSG_VERBOSE);
        LogOutputStream errLog = new LogOutputStream(this, Project.MSG_ERR);
        StreamPumper outPump = new StreamPumper(p.getInputStream(), outLog);
        StreamPumper errPump = new StreamPumper(p.getErrorStream(), errLog);
        new Thread(outPump).start();
        new Thread(errPump).start();
        out.write(sb.toString().getBytes());
        out.flush();
        out.close();
        int result = -99;
        try {
          result = p.waitFor();
          outPump.waitFor();
          outLog.close();
          errPump.waitFor();
          errLog.close();
        } catch (InterruptedException ie) {
          log("Thread interrupted: " + ie);
        }
        if (result != 0) {
          log("Error executing listcab; error code: " + result);
        }
      } catch (IOException ex) {
        String msg = (("Problem creating " + cabFile) + " ") + ex.getMessage();
        throw new BuildException(msg, getLocation());
      }
    } else {
      try {
        File listFile = createListFile(files);
        ExecTask exec = createExec();
        File outFile = null;
        exec.setFailonerror(true);
        exec.setDir(baseDir);
        if (!doVerbose) {
          outFile = fileUtils.createTempFile("ant", "", null);
          exec.setOutput(outFile);
        }
        exec.setExecutable("cabarc");
        exec.createArg().setValue("-r");
        exec.createArg().setValue("-p");
        if (!doCompress) {
          exec.createArg().setValue("-m");
          exec.createArg().setValue("none");
        }
        if (cmdOptions != null) {
          exec.createArg().setLine(cmdOptions);
        }
        exec.createArg().setValue("n");
        exec.createArg().setFile(cabFile);
        exec.createArg().setValue("@" + listFile.getAbsolutePath());
        exec.execute();
        if (outFile != null) {
          outFile.delete();
        }
        listFile.delete();
      } catch (IOException ioe) {
        String msg = (("Problem creating " + cabFile) + " ") + ioe.getMessage();
        throw new BuildException(msg, getLocation());
      }
    }
  }
}
