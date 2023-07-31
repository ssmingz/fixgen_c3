class PlaceHold {
  private static void gnome_parseMimeFile(Hashtable info, BufferedReader reader) {
    Vector mimeExts = null;
    String mimeType = null;
    boolean saveType = false;
    String line = "#";
    while (line != null) {
      boolean newType = (line.length() > 0) && Compatibility.isLetter(line.charAt(0));
      String data = line.trim();
      if ((data.length() > 0) && (data.charAt(0) != '#')) {
        if (newType) {
          if (mimeType != null) {
            saveType = true;
          } else {
            int colon = data.indexOf(':');
            if (colon != (-1)) {
              mimeType = data.substring(0, colon);
            } else {
              mimeType = data;
            }
            mimeExts = new Vector();
          }
        } else if ((data.indexOf("ext") == 0) && (mimeType != null)) {
          String exts = "";
          int colon = data.indexOf(':');
          if ((colon != (-1)) && ((colon + 1) < data.length())) {
            exts = data.substring(colon + 1).trim();
          }
          exts = exts.replace('\t', ' ');
          exts = exts.replace(',', ' ');
          while (exts.length() != 0) {
            String newExt;
            int space = exts.indexOf(' ');
            if (space != (-1)) {
              newExt = exts.substring(0, space);
              exts = exts.substring(space).trim();
            } else {
              newExt = exts;
              exts = "";
            }
            if (newExt.charAt(0) != '.') {
              newExt = "." + newExt;
            }
            mimeExts.add(newExt);
          }
        } else if ((data.indexOf("regex") == 0) && (mimeType != null)) {
        }
      }
      if (!saveType) {
        try {
          line = reader.readLine();
        } catch (IOException e) {
          line = null;
        }
      }
      if (saveType || (line == null)) {
        if (mimeType != null) {
          Vector prevExts = ((Vector) (info.get(mimeType)));
          if (prevExts == null) {
            info.put(mimeType, mimeExts);
          } else {
            for (int i = 0; i < mimeExts.size(); i++) {
              prevExts.add(mimeExts.elementAt(i));
            }
          }
        }
        mimeType = null;
        mimeExts = null;
        saveType = false;
      }
    }
  }
}
