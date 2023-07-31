class PlaceHold {
  protected File createListFile(Vector files) throws IOException {
    File listFile = FILE_UTILS.createTempFile("ant", "", null, true, true);
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(listFile));
      final int size = files.size();
      for (int i = 0; i < size; i++) {
        writer.write(('\"' + files.elementAt(i).toString()) + '\"');
        writer.newLine();
      }
    } finally {
      FileUtils.close(writer);
    }
    return listFile;
  }
}
