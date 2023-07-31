class PlaceHold {
  boolean gnome_execute(String fileName) {
    if (gnomeExpectUri) {
      byte[] fileNameBuffer = Converter.wcsToMbcs(null, fileName, true);
      int uri = GNOME.gnome_vfs_make_uri_from_input(fileNameBuffer);
      if (uri != 0) {
        int length = OS.strlen(uri);
        if (length > 0) {
          byte[] buffer = new byte[length];
          OS.memmove(buffer, uri, length);
          fileName = new String(Converter.mbcsToWcs(null, buffer));
        }
        OS.g_free(uri);
      }
    }
    String[] args = parseCommand(command);
    int fileArg = -1;
    int index;
    for (index = 0; index < args.length; index++) {
      int j = args[index].indexOf("%f");
      if (j != (-1)) {
        String value = args[index];
        fileArg = index;
        args[index] = (value.substring(0, j) + fileName) + value.substring(j + 2);
      }
    }
    if ((fileName.length() > 0) && (fileArg < 0)) {
      String[] newArgs = new String[args.length + 1];
      for (index = 0; index < args.length; index++) {
        newArgs[index] = args[index];
      }
      newArgs[args.length] = fileName;
      args = newArgs;
    }
    try {
      Compatibility.exec(args);
    } catch (IOException e) {
      return false;
    }
    return true;
  }
}
