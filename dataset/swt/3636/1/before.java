class PlaceHold {
  int createHeaderTemplate(int columnJniRef) {
    int template = OS.gcnew_DataTemplate();
    int stackPanelType = OS.StackPanel_typeid();
    int stackPanelName = createDotNetString(STACKPANEL_PART_NAME, false);
    int stackPanelNode = OS.gcnew_FrameworkElementFactory(stackPanelType, stackPanelName);
    int textType = OS.TextBlock_typeid();
    int textName = createDotNetString(TEXT_PART_NAME, false);
    int textNode = OS.gcnew_FrameworkElementFactory(textType, textName);
    int verticalAlignmentProperty = OS.FrameworkElement_VerticalAlignmentProperty();
    OS.FrameworkElementFactory_SetValueVerticalAlignment(
        textNode, verticalAlignmentProperty, VerticalAlignment_Center);
    int imageType = OS.Image_typeid();
    int imageName = createDotNetString(IMAGE_PART_NAME, false);
    int imageNode = OS.gcnew_FrameworkElementFactory(imageType, imageName);
    int marginProperty = OS.FrameworkElement_MarginProperty();
    int thickness = OS.gcnew_Thickness(0, 0, 4, 0);
    OS.FrameworkElementFactory_SetValue(imageNode, marginProperty, thickness);
    int orientationProperty = OS.StackPanel_OrientationProperty();
    OS.FrameworkElementFactory_SetValueOrientation(
        stackPanelNode, orientationProperty, Orientation_Horizontal);
    OS.FrameworkElementFactory_AppendChild(stackPanelNode, imageNode);
    OS.FrameworkElementFactory_AppendChild(stackPanelNode, textNode);
    OS.FrameworkTemplate_VisualTree(template, stackPanelNode);
    OS.GCHandle_Free(stackPanelName);
    OS.GCHandle_Free(imageType);
    OS.GCHandle_Free(imageName);
    OS.GCHandle_Free(marginProperty);
    OS.GCHandle_Free(thickness);
    OS.GCHandle_Free(textType);
    OS.GCHandle_Free(textName);
    OS.GCHandle_Free(stackPanelType);
    OS.GCHandle_Free(stackPanelNode);
    OS.GCHandle_Free(textNode);
    OS.GCHandle_Free(imageNode);
    OS.GCHandle_Free(orientationProperty);
    OS.GCHandle_Free(verticalAlignmentProperty);
    return template;
  }
}
