class PlaceHold {
  public String open() {
    int parentHandle = 0;
    if ((parent != null) && OS.PtWidgetIsRealized(shellHandle)) {
      parentHandle = parent.shellHandle;
    }
    byte[] title = null;
    if (this.title != null) {
      title = Converter.wcsToMbcs(null, this.title, true);
    }
    byte[] root_dir = null;
    if (filterPath != null) {
      root_dir = Converter.wcsToMbcs(null, filterPath, true);
    }
    String mask = FILTER;
    if (filterNames == null) {
      filterNames = new String[0];
    }
    if (filterExtensions == null) {
      filterExtensions = new String[0];
    }
    if (filterExtensions.length > 0) {
      String comma = ",";
      mask = comma;
      for (int i = 0; i < filterExtensions.length; i++) {
        String ext = filterExtensions[i];
        int length = ext.length();
        int end;
        int start = 0;
        do {
          end = ext.indexOf(';', start);
          if (end < 0) {
            end = length;
          }
          String subExt = ext.substring(start, end).trim();
          if (subExt.length() > 0) {
            subExt += comma;
            if (mask.indexOf(comma + subExt) == (-1)) {
              mask += subExt;
            }
          }
          start = end + 1;
        } while (end < length);
      }
      mask = mask.substring(1, Math.max(1, mask.length() - 1));
    }
    byte[] file_spec = Converter.wcsToMbcs(null, mask, true);
    byte[] btn1_text = null;
    if ((style & SWT.SAVE) != 0) {
      btn1_text = Converter.wcsToMbcs(null, SWT.getMessage("SWT_Save"), true);
    }
    int flags = OS.Pt_FSR_NO_FCHECK;
    PtFileSelectionInfo_t info = new PtFileSelectionInfo_t();
    OS.PtFileSelection(
        parentHandle, null, title, root_dir, file_spec, btn1_text, null, null, info, flags);
    if (info.ret == OS.Pt_FSDIALOG_BTN2) {
      return null;
    }
    int length = 0;
    while ((length < info.path.length) && (info.path[length] != 0)) {
      length++;
    }
    byte[] path = new byte[length];
    System.arraycopy(info.path, 0, path, 0, length);
    String fullPath = new String(Converter.mbcsToWcs(null, path));
    length = fullPath.length();
    if (length != 0) {
      int index = length - 1;
      while ((index >= 0) && (fullPath.charAt(index) != '/')) {
        --index;
      }
      fileName = fullPath.substring(index + 1, length);
      filterPath = fullPath.substring(0, index);
      filterIndex = ((filterExtensions == null) || (filterExtensions.length == 0)) ? -1 : 0;
    }
    return fullPath;
  }
}
