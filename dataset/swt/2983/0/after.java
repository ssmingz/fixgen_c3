class PlaceHold {
  public String[] getAvailableTypeNames() {
    checkWidget();
    FORMATETC[] types = _getAvailableTypes();
    String[] names = new String[types.length];
    int maxSize = 128;
    for (int i = 0; i < types.length; i++) {
      TCHAR buffer = new TCHAR(0, maxSize);
      int size = OS.GetClipboardFormatName(types[i].cfFormat, buffer, maxSize);
      if (size != 0) {
        names[i] = buffer.toString(0, size);
      } else {
        switch (types[i].cfFormat) {
          case COM.CF_HDROP:
            names[i] = "CF_HDROP";
            break;
          case COM.CF_TEXT:
            names[i] = "CF_TEXT";
            break;
          case COM.CF_BITMAP:
            names[i] = "CF_BITMAP";
            break;
          case COM.CF_METAFILEPICT:
            names[i] = "CF_METAFILEPICT";
            break;
          case COM.CF_SYLK:
            names[i] = "CF_SYLK";
            break;
          case COM.CF_DIF:
            names[i] = "CF_DIF";
            break;
          case COM.CF_TIFF:
            names[i] = "CF_TIFF";
            break;
          case COM.CF_OEMTEXT:
            names[i] = "CF_OEMTEXT";
            break;
          case COM.CF_DIB:
            names[i] = "CF_DIB";
            break;
          case COM.CF_PALETTE:
            names[i] = "CF_PALETTE";
            break;
          case COM.CF_PENDATA:
            names[i] = "CF_PENDATA";
            break;
          case COM.CF_RIFF:
            names[i] = "CF_RIFF";
            break;
          case COM.CF_WAVE:
            names[i] = "CF_WAVE";
            break;
          case COM.CF_UNICODETEXT:
            names[i] = "CF_UNICODETEXT";
            break;
          case COM.CF_ENHMETAFILE:
            names[i] = "CF_ENHMETAFILE";
            break;
          case COM.CF_LOCALE:
            names[i] = "CF_LOCALE";
            break;
          case COM.CF_MAX:
            names[i] = "CF_MAX";
            break;
          default:
            names[i] = "UNKNOWN";
        }
      }
    }
    return names;
  }
}
