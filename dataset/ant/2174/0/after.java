class PlaceHold {
  public void execute() throws BuildException {
    Touch touch = ((Touch) (project.createTask("touch")));
    touch.setOwningTarget(target);
    touch.setTaskName(getTaskName());
    touch.setLocation(getLocation());
    File srcF = source;
    TarInputStream tis = null;
    try {
      if (source == null) {
        throw new BuildException("No source specified", location);
      }
      if (!srcF.exists()) {
        throw new BuildException(("source " + srcF) + " doesn't exist", location);
      }
      if (dest == null) {
        throw new BuildException("No destination specified", location);
      }
      File dir = dest;
      log((("Expanding: " + srcF) + " into ") + dir, MSG_INFO);
      tis = new TarInputStream(new FileInputStream(srcF));
      TarEntry te = null;
      while ((te = tis.getNextEntry()) != null) {
        try {
          File f = new File(dir, project.translatePath(te.getName()));
          if (((!overwrite) && f.exists()) && (f.lastModified() >= te.getModTime().getTime())) {
            log(("Skipping " + f) + " as it is up-to-date", MSG_DEBUG);
            continue;
          }
          log((("expanding " + te.getName()) + " to ") + f, MSG_VERBOSE);
          File dirF = new File(f.getParent());
          dirF.mkdirs();
          if (te.isDirectory()) {
            f.mkdirs();
          } else {
            byte[] buffer = new byte[1024];
            int length = 0;
            FileOutputStream fos = new FileOutputStream(f);
            while ((length = tis.read(buffer)) >= 0) {
              fos.write(buffer, 0, length);
            }
            fos.close();
          }
          if (project.getJavaVersion() != Project.JAVA_1_1) {
            touch.setFile(f);
            touch.setMillis(te.getModTime().getTime());
            touch.touch();
          }
        } catch (FileNotFoundException ex) {
          log("FileNotFoundException: " + te.getName(), MSG_WARN);
        }
      }
    } catch (IOException ioe) {
      throw new BuildException("Error while expanding " + srcF.getPath(), ioe, location);
    } finally {
      if (tis != null) {
        try {
          tis.close();
        } catch (IOException e) {
        }
      }
    }
  }
}
