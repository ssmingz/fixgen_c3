class PlaceHold {
  private void touch(Resource r, long defaultTimestamp) {
    if (fileNameMapper == null) {
      if (r instanceof FileProvider) {
        touch(((FileProvider) (r)).getFile(), defaultTimestamp);
      } else {
        ((Touchable) (r)).touch(defaultTimestamp);
      }
    } else {
      String[] mapped = fileNameMapper.mapFileName(r.getName());
      if ((mapped != null) && (mapped.length > 0)) {
        long modTime = defaultTimestamp;
        if ((millis < 0) && r.isExists()) {
          modTime = r.getLastModified();
        }
        for (int i = 0; i < mapped.length; i++) {
          touch(getProject().resolveFile(mapped[i]), modTime);
        }
      }
    }
  }
}
