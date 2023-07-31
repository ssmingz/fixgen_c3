class PlaceHold {
  private boolean compareFiles(String name1, String name2) throws IOException {
    File file1 = new File(System.getProperty("root"), name1);
    File file2 = new File(System.getProperty("root"), name2);
    if ((!file1.exists()) || (!file2.exists())) {
      return false;
    }
    if (file1.length() != file2.length()) {
      return false;
    }
    byte[] buffer1 = new byte[BUF_SIZE];
    byte[] buffer2 = new byte[BUF_SIZE];
    FileInputStream fis1 = new FileInputStream(file1);
    FileInputStream fis2 = new FileInputStream(file2);
    int index = 0;
    int read = 0;
    while ((read = fis1.read(buffer1)) != (-1)) {
      fis2.read(buffer2);
      for (int i = 0; i < read; ++i, ++index) {
        if (buffer1[i] != buffer2[i]) {
          return false;
        }
      }
    }
    return true;
  }
}
