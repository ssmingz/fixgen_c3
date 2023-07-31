class PlaceHold {
  private InputStream getResourceStream(File file, String resourceName) {
    try {
      if (!file.exists()) {
        return null;
      }
      if (file.isDirectory()) {
        File resource = new File(file, resourceName);
        if (resource.exists()) {
          return new FileInputStream(resource);
        }
      } else {
        ZipFile zipFile = null;
        try {
          zipFile = new ZipFile(file);
          ZipEntry entry = zipFile.getEntry(resourceName);
          if (entry != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            InputStream stream = zipFile.getInputStream(entry);
            while ((bytesRead = stream.read(buffer, 0, BUFFER_SIZE)) != (-1)) {
              baos.write(buffer, 0, bytesRead);
            }
            return new ByteArrayInputStream(baos.toByteArray());
          }
        } finally {
          if (zipFile != null) {
            zipFile.close();
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
