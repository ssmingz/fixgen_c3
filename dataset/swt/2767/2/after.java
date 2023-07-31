class PlaceHold {
  int widgetStyle() {
    int bits =
        (((super.widgetStyle() | OS.TVS_SHOWSELALWAYS) | OS.TVS_LINESATROOT) | OS.TVS_HASBUTTONS)
            | OS.TVS_NONEVENHEIGHT;
    if (((EXPLORER_THEME && (!OS.IsWinCE)) && (OS.WIN32_VERSION >= OS.VERSION(6, 0)))
        && OS.IsAppThemed()) {
      bits |= OS.TVS_TRACKSELECT;
      if ((style & SWT.FULL_SELECTION) != 0) {
        bits |= OS.TVS_FULLROWSELECT;
      }
    } else if ((style & SWT.FULL_SELECTION) != 0) {
      bits |= OS.TVS_FULLROWSELECT;
    } else {
      bits |= OS.TVS_HASLINES;
    }
    return bits | OS.TVS_DISABLEDRAGDROP;
  }
}
