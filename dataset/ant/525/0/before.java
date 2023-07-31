class PlaceHold {
  private boolean compareFiles(String name1, String name2) {
    File file1 = new File(System.getProperty("root"), name1);
    File file2 = new File(System.getProperty("root"), name2);
    try {
      if ((!file1.exists()) || (!file2.exists())) {
        System.out.println((("One or both files do not exist:" + name1) + ", ") + name2);
        return false;
      }
      if (file1.length() != file2.length()) {
        System.out.println(
            ((((((("File size mismatch:" + name1) + "(") + file1.length()) + "), ") + name2) + "(")
                    + file2.length())
                + ")");
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
            System.out.println(
                (((("Bytes mismatch:" + name1) + ", ") + name2) + " at byte ") + index);
            return false;
          }
        }
      }
      return true;
    } catch (IOException e) {
      System.out.println((("IOException comparing files: " + name1) + ", ") + name2);
      return false;
    }
  }
}
