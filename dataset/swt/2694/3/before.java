class PlaceHold {
  void updateHeader() {
    char[] buffer = new char[text.length()];
    text.getChars(0, buffer.length, buffer, 0);
    int i = 0;
    int j = 0;
    while (i < buffer.length) {
      if ((buffer[j++] = buffer[i++]) == Mnemonic) {
        if (i == buffer.length) {
          continue;
        }
        if (buffer[i] == Mnemonic) {
          i++;
          continue;
        }
        j--;
      }
    }
    int str = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, j);
    if (str == 0) {
      error(ERROR_CANNOT_SET_TEXT);
    }
    DataBrowserListViewHeaderDesc desc = new DataBrowserListViewHeaderDesc();
    desc.version = OS.kDataBrowserListViewLatestHeaderDesc;
    if (resizable) {
      desc.minimumWidth = 0;
      desc.maximumWidth = 0x7fff;
    } else {
      short[] width = new short[1];
      OS.GetDataBrowserTableViewNamedColumnWidth(parent.handle, id, width);
      desc.minimumWidth = desc.maximumWidth = width[0];
    }
    desc.titleString = str;
    OS.SetDataBrowserListViewHeaderDesc(parent.handle, id, desc);
    OS.CFRelease(str);
  }
}
