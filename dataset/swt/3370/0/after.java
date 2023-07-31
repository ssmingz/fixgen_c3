class PlaceHold {
  public void setContent(Control content) {
    checkWidget();
    if ((this.content != null) && (!this.content.isDisposed())) {
      this.content.removeListener(Resize, contentListener);
      this.content.setBounds(new Rectangle(-200, -200, 0, 0));
    }
    this.content = content;
    ScrollBar vBar = getVerticalBar();
    ScrollBar hBar = getHorizontalBar();
    if (this.content != null) {
      if (vBar != null) {
        vBar.setMaximum(0);
        vBar.setThumb(0);
        vBar.setSelection(0);
      }
      if (hBar != null) {
        hBar.setMaximum(0);
        hBar.setThumb(0);
        hBar.setSelection(0);
      }
      content.setLocation(0, 0);
      layout(false);
      this.content.addListener(Resize, contentListener);
    } else {
      if (hBar != null) {
        hBar.setVisible(alwaysShowScroll);
      }
      if (vBar != null) {
        vBar.setVisible(alwaysShowScroll);
      }
    }
  }
}
