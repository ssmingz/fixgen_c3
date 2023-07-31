class PlaceHold {
  public void execute() throws BuildException {
    checkConfiguration();
    Vector files = getFileList();
    if (isUpToDate(files)) {
      return;
    }
    log((("Building " + archiveType) + ": ") + cabFile.getAbsolutePath());
    if (Os.isFamily("windows")) {
      log("Using listcab/libcabinet", MSG_VERBOSE);
      StringBuffer sb = new StringBuffer();
      Enumeration fileEnum = files.elements();
      while (fileEnum.hasMoreElements()) {
        sb.append(fileEnum.nextElement()).append("\n");
      }
      sb.append("\n").append(cabFile.getAbsolutePath()).append("\n");
      try {
        Process p = Runtime.getRuntime().exec("listcab");
        OutputStream out = p.getOutputStream();
        out.write(sb.toString().getBytes());
        out.flush();
        out.close();
      } catch (IOException ex) {
        String msg = (("Problem creating " + cabFile) + " ") + ex.getMessage();
        throw new BuildException(msg);
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
        exec.setCommand(createCommand(listFile));
        exec.execute();
        if (outFile != null) {
          outFile.delete();
        }
        listFile.delete();
      } catch (IOException ioe) {
        String msg = (("Problem creating " + cabFile) + " ") + ioe.getMessage();
        throw new BuildException(msg);
      }
    }
  }
}
