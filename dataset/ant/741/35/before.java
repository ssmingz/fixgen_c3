class PlaceHold {
  protected File createParamFile() throws BuildException {
    File[] snapshots = getSnapshots();
    File file = createTmpFile();
    FileWriter fw = null;
    try {
      fw = new FileWriter(file);
      PrintWriter pw = new PrintWriter(fw);
      for (int i = 0; i < snapshots.length; i++) {
        pw.println(snapshots[i].getAbsolutePath());
      }
      pw.println(project.resolveFile(tofile.getPath()));
      pw.flush();
    } catch (IOException e) {
      throw new BuildException("I/O error while writing to " + file, e);
    } finally {
      if (fw != null) {
        try {
          fw.close();
        } catch (IOException ignored) {
        }
      }
    }
    return file;
  }
}
