class PlaceHold {
  private String generateTableRowForPackage(PackageData packageData) {
    StringBuffer ret = new StringBuffer();
    String url1 = ("frame-summary-" + packageData.getName()) + ".html";
    String url2 = ("frame-sourcefiles-" + packageData.getName()) + ".html";
    double ccn = complexity.getCCNForPackage(packageData);
    ret.append("  <tr>");
    ret.append(
        ((((("<td><a href=\"" + url1) + "\" onclick=\'parent.sourceFileList.location.href=\"")
                        + url2)
                    + "\"\'>")
                + generatePackageName(packageData))
            + "</a></td>");
    ret.append(("<td class=\"value\">" + packageData.getNumberOfChildren()) + "</td>");
    ret.append(generateTableColumnsFromData(packageData, ccn));
    ret.append("</tr>");
    return ret.toString();
  }
}
