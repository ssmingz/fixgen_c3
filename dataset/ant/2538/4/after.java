class PlaceHold {
  protected void expandFile(File srcF, File dir) throws TaskException {
    ZipInputStream zis = null;
    try {
      zis = new ZipInputStream(new FileInputStream(srcF));
      ZipEntry ze = null;
      while ((ze = zis.getNextEntry()) != null) {
        extractFile(srcF, dir, zis, ze.getName(), new Date(ze.getTime()), ze.isDirectory());
      }
      getLogger().debug("expand complete");
    } catch (IOException ioe) {
      throw new TaskException("Error while expanding " + srcF.getPath(), ioe);
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
