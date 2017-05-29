package org.core.java;

public class FileUpload {
  public static void main(String[] args) throws Exception {
   /* PdfReader reader = new PdfReader("HelloWorldRead.pdf");
    PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("HelloWorldStamper2.pdf"));
    Image img = Image.getInstance("watermark.jpg");
    img.setAbsolutePosition(200, 400);
    BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
    PdfContentByte under, over;
    int total = reader.getNumberOfPages() + 1;
    for (int i = 1; i < total; i++) {
      stamper.setRotateContents(false);
      under = stamper.getUnderContent(i);
      under.addImage(img);
      over = stamper.getOverContent(i);
      over.beginText();
      over.setFontAndSize(bf, 18);
      over.setTextMatrix(30, 30);
      over.showText("page " + i);
      over.endText();
    }
    stamper.close();*/
  }

}