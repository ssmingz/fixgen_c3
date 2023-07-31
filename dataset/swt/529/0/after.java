class PlaceHold {
  String computeResultClassicDialog() {
    filterIndex = -1;
    GtkFileSelection selection = new GtkFileSelection();
    OS.memmove(selection, handle);
    int entry = selection.selection_entry;
    int entryText = OS.gtk_entry_get_text(entry);
    int entryLength = OS.strlen(entryText);
    if (entryLength == 0) {
      int fileList = selection.file_list;
      int listSelection = OS.gtk_tree_view_get_selection(fileList);
      int[] model = new int[1];
      int selectedList = OS.gtk_tree_selection_get_selected_rows(listSelection, model);
      if (selectedList == 0) {
        return null;
      }
      int listLength = OS.g_list_length(selectedList);
      if (listLength == 0) {
        OS.g_list_free(selectedList);
        return null;
      }
      int path = OS.g_list_nth_data(selectedList, 0);
      int[] ptr = new int[1];
      int iter = OS.g_malloc(OS.GtkTreeIter_sizeof());
      if (OS.gtk_tree_model_get_iter(model[0], iter, path)) {
        OS.gtk_tree_model_get(model[0], iter, 0, ptr, -1);
      }
      OS.g_free(iter);
      for (int i = 0; i < listLength; i++) {
        OS.gtk_tree_path_free(OS.g_list_nth_data(selectedList, i));
      }
      OS.g_list_free(selectedList);
      if (ptr[0] == 0) {
        return null;
      }
      int length = OS.strlen(ptr[0]);
      byte[] buffer = new byte[length];
      OS.memmove(buffer, ptr[0], length);
      OS.g_free(ptr[0]);
      OS.gtk_entry_set_text(entry, buffer);
    }
    int fileNamePtr = OS.gtk_file_selection_get_filename(handle);
    int utf8Ptr = OS.g_filename_to_utf8(fileNamePtr, -1, null, null, null);
    int[] items_written = new int[1];
    int utf16Ptr = OS.g_utf8_to_utf16(utf8Ptr, -1, null, items_written, null);
    entryLength = ((int) (items_written[0]));
    char[] buffer = new char[entryLength];
    OS.memmove(buffer, utf16Ptr, entryLength * 2);
    String osAnswer = new String(buffer);
    OS.g_free(utf16Ptr);
    OS.g_free(utf8Ptr);
    if (osAnswer == null) {
      return null;
    }
    int separatorIndex = osAnswer.lastIndexOf(SEPARATOR);
    if ((separatorIndex + 1) == osAnswer.length()) {
      return null;
    }
    String answer = fullPath = osAnswer;
    fileName = fullPath.substring(separatorIndex + 1);
    filterPath = fullPath.substring(0, separatorIndex);
    if ((style & SWT.MULTI) == 0) {
      fileNames = new String[] {fileName};
    } else {
      int namesPtr = OS.gtk_file_selection_get_selections(handle);
      int namesPtr1 = namesPtr;
      int[] namePtr = new int[1];
      OS.memmove(namePtr, namesPtr1, PTR_SIZEOF);
      int length = 0;
      while (namePtr[0] != 0) {
        length++;
        namesPtr1 += OS.PTR_SIZEOF;
        OS.memmove(namePtr, namesPtr1, PTR_SIZEOF);
      }
      fileNames = new String[length];
      namePtr = new int[length];
      OS.memmove(namePtr, namesPtr, length * OS.PTR_SIZEOF);
      for (int i = 0; i < length; i++) {
        utf8Ptr = OS.g_filename_to_utf8(namePtr[i], -1, null, null, null);
        items_written = new int[1];
        utf16Ptr = OS.g_utf8_to_utf16(utf8Ptr, -1, null, items_written, null);
        buffer = new char[((int) (items_written[0]))];
        OS.memmove(buffer, utf16Ptr, items_written[0] * 2);
        String name = new String(buffer);
        fileNames[i] = name.substring(name.lastIndexOf(SEPARATOR) + 1);
        OS.g_free(utf16Ptr);
        OS.g_free(utf8Ptr);
      }
      OS.g_strfreev(namesPtr);
    }
    return answer;
  }
}
