class PlaceHold {
  public FontData[] getFontList(String faceName, boolean scalable) {
    checkDevice();
    if (!scalable) {
      return new FontData[0];
    }
    int[] family = new int[1];
    int[] face = new int[1];
    int[] families = new int[1];
    int[] n_families = new int[1];
    int[] faces = new int[1];
    int[] n_faces = new int[1];
    int context = OS.gdk_pango_context_get();
    OS.pango_context_list_families(context, families, n_families);
    int nFds = 0;
    FontData[] fds = new FontData[faceName != null ? 4 : n_families[0]];
    for (int i = 0; i < n_families[0]; i++) {
      OS.memmove(family, families[0] + (i * OS.PTR_SIZEOF), PTR_SIZEOF);
      boolean match = true;
      if (faceName != null) {
        int familyName = OS.pango_font_family_get_name(family[0]);
        int length = OS.strlen(familyName);
        byte[] buffer = new byte[length];
        OS.memmove(buffer, familyName, length);
        String name = new String(Converter.mbcsToWcs(null, buffer));
        match = Compatibility.equalsIgnoreCase(faceName, name);
      }
      if (match) {
        OS.pango_font_family_list_faces(family[0], faces, n_faces);
        for (int j = 0; j < n_faces[0]; j++) {
          OS.memmove(face, faces[0] + (j * OS.PTR_SIZEOF), PTR_SIZEOF);
          int fontDesc = OS.pango_font_face_describe(face[0]);
          Font font = Font.gtk_new(this, fontDesc);
          FontData data = font.getFontData()[0];
          if (nFds == fds.length) {
            FontData[] newFds = new FontData[fds.length + n_families[0]];
            System.arraycopy(fds, 0, newFds, 0, nFds);
            fds = newFds;
          }
          fds[nFds++] = data;
          OS.pango_font_description_free(fontDesc);
        }
        OS.g_free(faces[0]);
        if (faceName != null) {
          break;
        }
      }
    }
    OS.g_free(families[0]);
    OS.g_object_unref(context);
    if (nFds == fds.length) {
      return fds;
    }
    FontData[] result = new FontData[nFds];
    System.arraycopy(fds, 0, result, 0, nFds);
    return result;
  }
}
