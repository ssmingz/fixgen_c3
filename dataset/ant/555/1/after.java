class PlaceHold {
  private void touch(File fromDir, String filename, long defaultTimestamp) {
    File f = FILE_UTILS.resolveFile(fromDir, filename);
    if (fileNameMapper == null) {
      touch(f, defaultTimestamp);
    } else {
      String[] mapped = fileNameMapper.mapFileName(filename);
      if ((mapped != null) && (mapped.length > 0)) {
        long modTime = (f.exists()) ? f.lastModified() : defaultTimestamp;
        for (int i = 0; i < mapped.length; i++) {
          touch(getProject().resolveFile(mapped[i]), modTime);
        }
      }
    }
  }
}
