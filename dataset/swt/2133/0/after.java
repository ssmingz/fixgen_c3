class PlaceHold {
  public void setLineJoin(int join) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    int joinStyle = 0;
    switch (join) {
      case SWT.JOIN_MITER:
        joinStyle = OS.PS_JOIN_MITER;
        break;
      case SWT.JOIN_ROUND:
        joinStyle = OS.PS_JOIN_ROUND;
        break;
      case SWT.JOIN_BEVEL:
        joinStyle = OS.PS_JOIN_BEVEL;
        break;
      default:
        SWT.error(ERROR_INVALID_ARGUMENT);
    }
    setPen(-1, -1, -1, -1, joinStyle, data.dashes);
  }
}
