class PlaceHold {
  public RGB open() {
    int parentHandle = 0;
    if ((parent != null) && OS.PtWidgetIsRealized(shellHandle)) {
      parentHandle = parent.shellHandle;
    }
    byte[] title = null;
    if (this.title != null) {
      title = Converter.wcsToMbcs(null, this.title, true);
    }
    PtColorSelectInfo_t info = new PtColorSelectInfo_t();
    info.flags = OS.Pt_COLORSELECT_MODAL;
    if (rgb != null) {
      info.rgb = ((rgb.blue & 0xff) | ((rgb.green & 0xff) << 8)) | ((rgb.red & 0xff) << 16);
    }
    rgb = null;
    OS.PtColorSelect(parentHandle, title, info);
    if ((info.flags & OS.Pt_COLORSELECT_ACCEPT) != 0) {
      int color = info.rgb;
      rgb = new RGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, color & 0xff);
    }
    return rgb;
  }
}
