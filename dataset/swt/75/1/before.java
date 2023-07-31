class PlaceHold {
  private void openAddressBook() {
    FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
    fileDialog.setFilterExtensions(new String[] {"*.adr;", "*.*"});
    fileDialog.setFilterNames(
        new String[] {
          resAddressBook.getString("Book_filter_name") + " (*.adr)",
          resAddressBook.getString("All_filter_name") + " (*.*)"
        });
    String name = fileDialog.open();
    if (name == null) {
      return;
    }
    File file = new File(name);
    if (!file.exists()) {
      displayError(
          ((resAddressBook.getString("File") + file.getName()) + " ")
              + resAddressBook.getString("Does_not_exist"));
      return;
    }
    Cursor waitCursor = new Cursor(shell.getDisplay(), SWT.CURSOR_WAIT);
    shell.setCursor(waitCursor);
    FileReader fileReader = null;
    BufferedReader bufferedReader = null;
    String[] data = new String[0];
    try {
      fileReader = new FileReader(file.getAbsolutePath());
      bufferedReader = new BufferedReader(fileReader);
      String nextLine = bufferedReader.readLine();
      while (nextLine != null) {
        String[] newData = new String[data.length + 1];
        System.arraycopy(data, 0, newData, 0, data.length);
        newData[data.length] = nextLine;
        data = newData;
        nextLine = bufferedReader.readLine();
      }
    } catch (FileNotFoundException e) {
      displayError((resAddressBook.getString("File_not_found") + "\n") + file.getName());
      return;
    } catch (IOException e) {
      displayError((resAddressBook.getString("IO_error_read") + "\n") + file.getName());
      return;
    } finally {
      shell.setCursor(null);
      waitCursor.dispose();
      if (fileReader != null) {
        try {
          fileReader.close();
        } catch (IOException e) {
          displayError((resAddressBook.getString("IO_error_close") + "\n") + file.getName());
          return;
        }
      }
    }
    String[][] tableInfo = new String[data.length][table.getColumnCount()];
    int writeIndex = 0;
    for (int i = 0; i < data.length; i++) {
      String[] line = decodeLine(data[i]);
      if (line != null) {
        tableInfo[writeIndex++] = line;
      }
    }
    if (writeIndex != data.length) {
      String[][] result = new String[writeIndex][table.getColumnCount()];
      System.arraycopy(tableInfo, 0, result, 0, writeIndex);
      tableInfo = result;
    }
    Arrays.sort(tableInfo, new RowComparator(0));
    for (int i = 0; i < tableInfo.length; i++) {
      TableItem item = new TableItem(table, SWT.NONE);
      item.setText(tableInfo[i]);
    }
    shell.setText(resAddressBook.getString("Title_bar") + fileDialog.getFileName());
    isModified = false;
    this.file = file;
  }
}
