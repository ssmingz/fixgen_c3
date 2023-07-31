class PlaceHold {
  protected File createListFile(Vector files) throws IOException {
    File listFile = createTempFile("ant", null);
    PrintWriter writer = new PrintWriter(new FileOutputStream(listFile));
    for (int i = 0; i < files.size(); i++) {
      writer.println(files.elementAt(i).toString());
    }
    writer.close();
    return listFile;
  }
}
