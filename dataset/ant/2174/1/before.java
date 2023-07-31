class PlaceHold {
  private void expandFile(Touch touch, File srcF, File dir) {
    ZipInputStream zis = null;
    try {
      log((("Expanding: " + srcF) + " into ") + dir, MSG_INFO);
      zis = new ZipInputStream(new FileInputStream(srcF));
      ZipEntry ze = null;
      while ((ze = zis.getNextEntry()) != null) {
        File f = new File(dir, project.translatePath(ze.getName()));
        try {
          log("expand-file " + ze.getName(), MSG_VERBOSE);
          File dirF = new File(f.getParent());
          dirF.mkdirs();
          if (ze.isDirectory()) {
            f.mkdirs();
          } else {
            byte[] buffer = new byte[1024];
            int length = 0;
            FileOutputStream fos = new FileOutputStream(f);
            while ((length = zis.read(buffer)) >= 0) {
              fos.write(buffer, 0, length);
            }
            fos.close();
          }
          if (project.getJavaVersion() != Project.JAVA_1_1) {
            touch.setFile(f);
            touch.setMillis(ze.getTime());
            touch.touch();
          }
        } catch (FileNotFoundException ex) {
          log("Unable to expand to file " + f.getPath(), MSG_WARN);
        }
      }
      log("expand complete", MSG_VERBOSE);
    } catch (IOException ioe) {
      throw new BuildException("Error while expanding " + srcF.getPath(), ioe);
    } finally {
      if (zis != null) {
        try {
          zis.close();
        } catch (IOException e) {
        }
      }
    }
  }
}
