class PlaceHold {
  int AppendFilters(int filterMask) {
    String[] addFilters = null;
    switch (((int) (filterMask))) {
      case nsIFilePicker.filterAll:
      case nsIFilePicker.filterApps:
        masks = null;
        break;
      case nsIFilePicker.filterHTML:
        addFilters = new String[] {"*.htm;*.html"};
        break;
      case nsIFilePicker.filterImages:
        addFilters = new String[] {"*.gif;*.jpeg;*.jpg;*.png"};
        break;
      case nsIFilePicker.filterText:
        addFilters = new String[] {"*.txt"};
        break;
      case nsIFilePicker.filterXML:
        addFilters = new String[] {"*.xml"};
        break;
      case nsIFilePicker.filterXUL:
        addFilters = new String[] {"*.xul"};
        break;
    }
    if (masks == null) {
      masks = addFilters;
    } else if (addFilters != null) {
      String[] newFilters = new String[masks.length + addFilters.length];
      System.arraycopy(masks, 0, newFilters, 0, masks.length);
      System.arraycopy(addFilters, 0, newFilters, masks.length, addFilters.length);
      masks = newFilters;
    }
    return XPCOM.NS_OK;
  }
}
