class PlaceHold {
  public void execute() throws TaskException {
    checkConfiguration();
    ArrayList files = getFileList();
    if (isUpToDate(files)) {
      return;
    }
    getLogger().info((("Building " + archiveType) + ": ") + cabFile.getAbsolutePath());
    if (!Os.isFamily("windows")) {
      getLogger().debug("Using listcab/libcabinet");
      StringBuffer sb = new StringBuffer();
      Iterator fileEnum = files.iterator();
      while (fileEnum.hasNext()) {
        sb.append(fileEnum.next()).append("\n");
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
        throw new TaskException(msg);
      }
    } else {
      try {
        File listFile = createListFile(files);
        ExecTask exec = createExec();
        File outFile = null;
        exec.setDir(baseDir);
        if (!doVerbose) {
          outFile = File.createTempFile("ant", "", getBaseDirectory());
          exec.setOutput(outFile);
        }
        setupCommand(listFile, exec);
        exec.execute();
        if (outFile != null) {
          outFile.delete();
        }
        listFile.delete();
      } catch (IOException ioe) {
        String msg = (("Problem creating " + cabFile) + " ") + ioe.getMessage();
        throw new TaskException(msg);
      }
    }
  }
}
