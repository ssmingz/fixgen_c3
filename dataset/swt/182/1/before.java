class PlaceHold {
  String getCodePage() {
    return Converter.getCodePage(OS.XtDisplay(handle), getFontList());
  }
}
