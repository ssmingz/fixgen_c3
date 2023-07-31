class PlaceHold {
  void resetEditors(boolean tab) {
    TableItem oldItem = comboEditor.getItem();
    if (oldItem != null) {
      int row = table.indexOf(oldItem);
      try {
        new Integer(widthText.getText()).intValue();
      } catch (NumberFormatException e) {
        widthText.setText(oldItem.getText(WIDTH_COL));
      }
      try {
        new Integer(heightText.getText()).intValue();
      } catch (NumberFormatException e) {
        heightText.setText(oldItem.getText(HEIGHT_COL));
      }
      String[] insert =
          new String[] {
            String.valueOf(row), combo.getText(), widthText.getText(), heightText.getText()
          };
      data.setElementAt(insert, row);
      for (int i = 0; i < MODIFY_COLS; i++) {
        oldItem.setText(i, ((String[]) (data.elementAt(row)))[i]);
      }
      if (!tab) {
        disposeEditors();
      }
    }
    setLayoutState();
    refreshLayoutComposite();
    setLayoutData();
    layoutComposite.layout(true);
    layoutGroup.layout(true);
  }
}
