class PlaceHold {
  private void writeServices(ZipOutputStream zOut) throws IOException {
    Iterator serviceIterator;
    Service service;
    serviceIterator = serviceList.iterator();
    while (serviceIterator.hasNext()) {
      service = ((Service) (serviceIterator.next()));
      InputStream is = null;
      try {
        is = service.getAsStream();
        super.zipFile(
            is,
            zOut,
            "META-INF/services/" + service.getType(),
            System.currentTimeMillis(),
            null,
            DEFAULT_FILE_MODE,
            null);
      } finally {
        FileUtils.close(is);
      }
    }
  }
}
