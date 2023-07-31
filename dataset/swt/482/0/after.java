class PlaceHold {
  void resize() {
    if ((editor == null) || editor.isDisposed()) {
      return;
    }
    if (editor.getVisible()) {
      hadFocus = editor.isFocusControl();
    }
    editor.setBounds(computeBounds());
    if (hadFocus) {
      if ((editor == null) || editor.isDisposed()) {
        return;
      }
      editor.setFocus();
    }
  }
}
