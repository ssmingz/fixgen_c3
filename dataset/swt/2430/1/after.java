class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int flags = (OS.Pt_SHOW_TITLE | OS.Pt_ETCH_TITLE_AREA) | OS.Pt_GRADIENT_TITLE_AREA;
    byte[] buffer = Converter.wcsToMbcs(null, stripMnemonics(string), true);
    int ptr = OS.malloc(buffer.length);
    OS.memmove(ptr, buffer, buffer.length);
    int[] args =
        new int[] {
          OS.Pt_ARG_TITLE,
          ptr,
          0,
          OS.Pt_ARG_CONTAINER_FLAGS,
          string.length() == 0 ? 0 : flags,
          flags
        };
    OS.PtSetResources(handle, args.length / 3, args);
    OS.free(ptr);
  }
}
